import './InspectPlan.css'
import { useEffect, useState, React } from 'react'
import { Swiper, SwiperSlide } from 'swiper/react'
import { Navigation, Pagination } from 'swiper'
import 'swiper/css'
import 'swiper/css/navigation'
import { Button } from '../../components/Button'
import cogoToast from 'cogo-toast'
import axios from 'axios'
import TextField from '@mui/material/TextField'
import Dialog from '@mui/material/Dialog'
import DialogActions from '@mui/material/DialogActions'
import DialogContent from '@mui/material/DialogContent'
import DialogContentText from '@mui/material/DialogContentText'
import DialogTitle from '@mui/material/DialogTitle'

function InspectPlan() {
    const [inspectFacilities, setInspectFacilities] = useState({})
    const [inspectFoods, setInspectFoods] = useState('')
    const [inspectFoodNames, setInspectFoodNames] = useState([])
    const [inspectedFoods, setInspectedFoods] = useState([])
    const [qualifiedReason, setQualifiedReason] = useState('')
    const [qualifiedReasonStore, setQualifiedReasonStore] = useState('')
    const [dialogOpen, setDialogOpen] = useState(false)
    const [dialogOpen2, setDialogOpen2] = useState(false)
    const [storeQualify, setStoreQualify] = useState(true)

    useEffect(() => {
        //Lấy cơ sở đang được thanh tra
        axios('http://localhost:8080/inspection/store/current', {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token'),
                'Access-Control-Allow-Origin': '*',
            },
            method: 'get'
        }).then(res => {
            setInspectFacilities(res.data)
            if (res.data.status !== 'NO') {
                //Nếu cửa hàng chưa bắt đầu thanh tra
                axios(`http://localhost:8080/inspection/sample/${res.data.regNo}`, {
                    headers: {
                        Authorization: 'Bearer ' + localStorage.getItem('token'),
                        'Access-Control-Allow-Origin': '*',
                    },
                    method: 'get'
                }).then(res2 => {
                    setInspectFoods(res2.data.storeName)
                    setInspectFoodNames(res2.data.food)
                })
            }
        })

        axios('http://localhost:8080/inspection/inspected/food', {
            method: 'get'
        }).then(res => {
            setInspectedFoods(res.data)
        })

        localStorage.setItem('swiper-index', '0')
    }, [])

    //Hàm xử lý bắt đầu thanh tra cơ sở
    const handleStartInspect = (corpcode, id) => {
        //Thay đổi tình trạng, ngày bắt đầu
        cogoToast.info(`Bắt đầu thanh tra cơ sở với mã ${corpcode}`)
        axios('http://localhost:8080/inspection/inspected/no', {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token'),
                'Access-Control-Allow-Origin': '*',
            },
            data: {
                'id': parseInt(id)
            },
            method: 'put'
        })
        localStorage.setItem('swiper-index', '0')
        window.setTimeout(() => window.location.reload(false), 1500)
    }

    //Hàm xử lý kết thúc thanh tra cơ sở
    const handleEndInspect = (corpcode, id) => {
        //Thay đổi tình trạng, chuyển cơ sở sang pha sau
        cogoToast.success(`Hoàn tất thanh tra cơ sở với mã ${corpcode}`)
        axios('http://localhost:8080/inspection/inspected/pending', {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token'),
                'Access-Control-Allow-Origin': '*',
            },
            data: {
                'id': parseInt(id)
            },
            method: 'put'
        })
        localStorage.setItem('swiper-index', '0')
        window.setTimeout(() => window.location.reload(false), 1500)
    }

    //Xử lý loại bỏ cơ sở
    const handleRemoveInspect = (corpcode) => {
        //Loại bỏ cơ sở khỏi bảng thanh tra
        if (inspectFacilities.qualify === false && inspectFacilities.reason === null) {
            //Nếu chuyên viên chưa có quyết định
            cogoToast.error('Chưa có quyết định cuối cùng của chuyên viên')
        } else {
            cogoToast.info(`Kết thúc thanh tra cơ sở với mã ${corpcode}`)
            axios('http://localhost:8080/inspection/finish', {
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('token'),
                    'Access-Control-Allow-Origin': '*',
                },
                method: 'put'
            })
            localStorage.setItem('swiper-index', '0')
            window.setTimeout(() => window.location.href = 'http://localhost:3000/inspectsuggest', 1500)
        }
    }

    //Xử lý bắt đầu kiểm định thực phẩm
    const handleStartFoodInspect = (foodCode) => {
        cogoToast.info(`Bắt đầu kiểm định thực phẩm có mã ${foodCode}`)
        //Thay đổi tình trạng, ngày bắt đầu
        axios(`http://localhost:8080/inspection/pending/${foodCode}/${inspectFacilities.id}`, {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token'),
                'Access-Control-Allow-Origin': '*',
            },
            method: 'put'
        })
        localStorage.setItem('swiper-index', '1')
        window.setTimeout(() => window.location.reload(false), 1500)
    }

    //Hàm xử lý kết thúc kiểm định thực phẩm
    const handleEndFoodInspect = (foodCode) => {
        cogoToast.success(`Hoàn tất kiểm định thực phẩm có mã ${foodCode}`)
        //Thay đổi tình trạng, chuyển mẫu thực phẩm sang pha sau
        axios(`http://localhost:8080/inspection/yes/${foodCode}/${inspectFacilities.id}`, {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token'),
                'Access-Control-Allow-Origin': '*',
            },
            method: 'put'
        })
        localStorage.setItem('swiper-index', '1')
        window.setTimeout(() => window.location.reload(false), 1500)
    }

    //Xử lý loại bỏ thực phẩm
    const handleRemoveFoodInspect = (foodCode) => {
        cogoToast.info(`Kết thúc kiểm định thực phẩm có mã ${foodCode}`)
        //Loại bỏ cơ sở khỏi bảng thanh tra
        setDialogOpen(true)
        localStorage.setItem('swiper-index', '1')
        localStorage.setItem('food-being-inspected', foodCode)
    }

    const handleLogCanceled = () => {
        setDialogOpen(false)
    }

    const handleLogCanceled2 = () => {
        setDialogOpen2(false)
    }

    //Xử lý thực phẩm đạt chuẩn
    const handleFoodPassed = () => {
        const foodCode = localStorage.getItem('food-being-inspected')
        axios(`http://localhost:8080/inspection/qualified/${foodCode}/${inspectFacilities.id}`, {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token'),
                'Access-Control-Allow-Origin': '*',
            },
            data: {
                'qualified': true,
                'reason': qualifiedReason === '' ? 'Đạt' : qualifiedReason
            },
            method: 'put'
        })
        localStorage.removeItem('food-being-inspected')
        setDialogOpen(false)
        localStorage.setItem('swiper-index', '1')
        window.setTimeout(() => window.location.reload(false), 1500)
    }

    //Xử lý thực phẩm không đạt chuẩn
    const handleFoodFailed = () => {
        const foodCode = localStorage.getItem('food-being-inspected')
        axios(`http://localhost:8080/inspection/qualified/${foodCode}/${inspectFacilities.id}`, {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token'),
                'Access-Control-Allow-Origin': '*',
            },
            data: {
                'qualified': false,
                'reason': qualifiedReason
            },
            method: 'put'
        })
        localStorage.removeItem('food-being-inspected')
        setDialogOpen(false)
        localStorage.setItem('swiper-index', '1')
        window.setTimeout(() => window.location.reload(false), 1500)
    }

    //Xử lý cơ sở đạt chuẩn
    const handleStorePassed = () => {
        if (inspectedFoods.length <= 2) {
            cogoToast.error('Chưa kiểm định đủ 3 mẫu thực phẩm trở lên')
        } else {
            setDialogOpen2(true)
            setStoreQualify(true)
        }
    }

    //Xử lý cơ sở không đạt chuẩn
    const handleStoreFailed = () => {
        if (inspectedFoods.length <= 2) {
            cogoToast.error('Chưa kiểm định đủ 3 mẫu thực phẩm trở lên')
        } else {
            setDialogOpen2(true)
            setStoreQualify(false)
        }
    }

    //Xử lý sau khi đã quyết định cơ sở đạt chuẩn hay không
    const handleStoreQualify = () => {
        axios('http://localhost:8080/inspection/update/qualify/status', {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token'),
                'Access-Control-Allow-Origin': '*',
            },
            data: {
                'qualify': storeQualify,
                'reason': qualifiedReasonStore
            },
            method: 'put'
        })
        setDialogOpen2(false)
        window.location.reload()
    }

    return (
        <div className='inspect-plan-container'>
            <Swiper
                modules={[Navigation, Pagination]}
                navigation
                pagination={{
                    clickable: true
                }}
                initialSlide={localStorage.getItem('swiper-index') ? parseInt(localStorage.getItem('swiper-index')) : 0}
            >
                <SwiperSlide>
                    <h1>Thanh tra cơ sở</h1>
                    <div className='inspect-table'>
                        <table className='inspect-table-content'>
                            <thead>
                                <tr>
                                    <td>Mã cơ sở</td>
                                    <td className='enlarge'>Tình trạng thanh tra</td>
                                    <td>Ngày bắt đầu thanh tra</td>
                                    <td>Thao tác</td>
                                </tr>
                            </thead>
                            <tbody>
                                {inspectFacilities.regNo !== undefined && (<tr>
                                    <td>{inspectFacilities.regNo}</td>
                                    <td
                                        className={`enlarge ${inspectFacilities.status === 'NO' ?
                                            'waiting' : (inspectFacilities.status === 'YES' ? 'finished' : 'pending')}`}
                                    >
                                        {inspectFacilities.status === 'NO' ? 'Chưa bắt đầu' : (inspectFacilities.status === 'YES' ? 'Đã hoàn thành' : 'Đang trong quá trình thanh tra...')}
                                    </td>
                                    <td>{inspectFacilities.inspectedDate !== null ? inspectFacilities.inspectedDate : 'Chưa bắt đầu'}</td>
                                    <td>
                                        {inspectFacilities.status === 'NO' && <Button
                                            children='Tiến hành kiểm tra'
                                            onClick={() => handleStartInspect(inspectFacilities.regNo, inspectFacilities.id)}
                                            buttonStyle='btn--outline'
                                            buttonSize='btn--large'
                                            buttonHref='inspectplan'
                                        ></Button>}
                                        {inspectFacilities.status === 'PENDING' && <Button
                                            children='Hoàn thành kiểm tra'
                                            onClick={() => handleEndInspect(inspectFacilities.regNo, inspectFacilities.id)}
                                            buttonStyle='btn--outline'
                                            buttonSize='btn--large'
                                            buttonHref='inspectplan'
                                        ></Button>}
                                        {inspectFacilities.status === 'YES' && <Button
                                            children='Loại bỏ'
                                            onClick={() => handleRemoveInspect(inspectFacilities.regNo)}
                                            buttonStyle='btn--outline'
                                            buttonSize='btn--large'
                                            buttonHref='inspectplan'
                                        ></Button>}
                                    </td>
                                </tr>)}
                            </tbody>
                        </table>
                    </div>
                </SwiperSlide>
                <SwiperSlide>
                    <div>
                        <Dialog open={dialogOpen} onClose={() => setDialogOpen(false)}>
                            <DialogTitle>Loại bỏ mẫu thực phẩm</DialogTitle>
                            <DialogContent>
                                <DialogContentText>
                                    Vui lòng nêu lí do nếu thực phẩm không đạt đủ điều kiện an toàn thực phẩm
                                </DialogContentText>
                                <TextField
                                    autoFocus
                                    margin="dense"
                                    id="name"
                                    label="Lí do"
                                    type="text"
                                    fullWidth
                                    variant="standard"
                                    value={qualifiedReason}
                                    onChange={(e) => setQualifiedReason(e.target.value)}
                                />
                            </DialogContent>
                            <DialogActions>
                                <Button
                                    buttonSize='btn--medium'
                                    onClick={() => handleLogCanceled()}>Huỷ</Button>
                                <Button
                                    buttonSize='btn--medium'
                                    onClick={() => handleFoodPassed()}>Đạt</Button>
                                <Button
                                    buttonSize='btn--medium'
                                    onClick={() => handleFoodFailed()}>Không đạt</Button>
                            </DialogActions>
                        </Dialog>
                    </div>
                    <h1>Lấy mẫu thực phẩm - Thực hiện kiểm định</h1>
                    <div className='inspect-table'>
                        <table className='inspect-table-content'>
                            <thead>
                                <tr>
                                    <td>Mã mẫu thực phẩm</td>
                                    <td>Tên thực phẩm</td>
                                    <td className='enlarge'>Tình trạng kiểm định</td>
                                    <td>Thao tác</td>
                                </tr>
                            </thead>
                            <tbody>
                                {inspectFoodNames.map((item, index) => (
                                    <tr key={index}>
                                        <td>{inspectFoods + ' ' + item.id}</td>
                                        <td>{item.name}</td>
                                        <td className={`enlarge ${item.status === 'NO' ?
                                            'waiting' : (item.status === 'YES' ? 'finished' : 'pending')
                                            }`}>{item.status === 'NO' ? 'Chưa thanh tra' : (item.status === 'YES' ? 'Quá trình kiểm định hoàn tất!' : 'Trong quá trình kiểm định...')}</td>
                                        <td>
                                            {item.status === 'NO' && <Button
                                                children='Tiến hành kiểm định'
                                                onClick={() => handleStartFoodInspect(item.id)}
                                                buttonStyle='btn--outline'
                                                buttonSize='btn--large'
                                                buttonHref='inspectplan'
                                            ></Button>}
                                            {item.status === 'PENDING' && <Button
                                                children='Hoàn thành kiểm định'
                                                onClick={() => handleEndFoodInspect(item.id)}
                                                buttonStyle='btn--outline'
                                                buttonSize='btn--large'
                                                buttonHref='inspectplan'
                                            ></Button>}
                                            {item.status === 'YES' && <Button
                                                children='Loại bỏ'
                                                onClick={() => handleRemoveFoodInspect(item.id)}
                                                buttonStyle='btn--outline'
                                                buttonSize='btn--large'
                                                buttonHref='inspectplan'
                                            ></Button>}
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </SwiperSlide>
                <SwiperSlide>
                    <h1>Kết quả thanh kiểm</h1>
                    <div className='inspect-table'>
                        <table className='inspect-table-content'>
                            <thead>
                                <tr>
                                    <td>Mã mẫu thực phẩm</td>
                                    <td>Tên thực phẩm</td>
                                    <td>Ngày giám định</td>
                                    <td className='enlarge'>Kết quả giám định thực phẩm</td>
                                </tr>
                            </thead>
                            <tbody>
                                {inspectedFoods.map((item, index) => (
                                    <tr key={index}>
                                        <td>{inspectFoods + ' ' + item.id}</td>
                                        <td>{item.name}</td>
                                        <td>{item.inspectedDate}</td>
                                        <td className='enlarge'>{item.qualified === true ? 'Đạt' : ('Không đạt(' + item.reason + ')')}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                    </div>
                </SwiperSlide>
                <SwiperSlide>
                    <Dialog open={dialogOpen2} onClose={() => setDialogOpen(false)}>
                        <DialogTitle>Hoàn tất thanh tra cơ sở</DialogTitle>
                        <DialogContent>
                            <DialogContentText>
                                Vui lòng nêu lí do nếu cơ sở đạt / không đạt đủ điều kiện an toàn thực phẩm
                            </DialogContentText>
                            <TextField
                                autoFocus
                                margin="dense"
                                id="name"
                                label="Lí do"
                                type="text"
                                fullWidth
                                variant="standard"
                                value={qualifiedReasonStore}
                                onChange={(e) => setQualifiedReasonStore(e.target.value)}
                            />
                        </DialogContent>
                        <DialogActions>
                            <Button
                                buttonSize='btn--medium'
                                onClick={() => handleLogCanceled2()}>Huỷ</Button>
                            <Button
                                buttonSize='btn--medium'
                                onClick={() => handleStoreQualify()}>Chấp nhận</Button>
                        </DialogActions>
                    </Dialog>
                    <h1>Quyết định cuối cùng</h1>
                    <div className='inspect-table'>
                        <table className='inspect-table-content'>
                            <thead>
                                <tr>
                                    <td>Mã cơ sở</td>
                                    <td>Ngày kiểm định</td>
                                    <td className='enlarge'>Quyết định của chuyên viên</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>{(inspectFacilities.status === 'YES' && inspectFacilities.qualify === false && inspectFacilities.reason === null) && (
                                    <>
                                        <td>{inspectFacilities.regNo}</td>
                                        <td>{inspectFacilities.inspectedDate}</td>
                                        <td className='enlarge'>
                                            <Button
                                                children='Đạt điều kiện'
                                                onClick={() => handleStorePassed()}
                                                buttonStyle='btn--outline'
                                                buttonSize='btn--large'
                                            ></Button>
                                            &nbsp;&nbsp;&nbsp;&nbsp;
                                            <Button
                                                children='Không đạt điều kiện'
                                                onClick={() => handleStoreFailed()}
                                                buttonStyle='btn--outline'
                                                buttonSize='btn--large'
                                            ></Button>
                                        </td>
                                    </>
                                )}</tr>
                            </tbody>
                        </table>
                    </div>
                </SwiperSlide>
            </Swiper>
        </div>
    )
}

export default InspectPlan