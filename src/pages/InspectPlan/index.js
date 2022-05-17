import './InspectPlan.css'
import { Swiper, SwiperSlide } from 'swiper/react'
import { Navigation, Pagination } from 'swiper'
import 'swiper/css'
import 'swiper/css/navigation'
import { inspectfacilities, inspectfoods } from '../../constants'
import { Button } from '../../components/Button'
import cogoToast from 'cogo-toast'

function InspectPlan() {
    //Hàm xử lý bắt đầu thanh tra cơ sở
    const handleStartInspect = (corpcode) => {
        cogoToast.info(`Bắt đầu thanh tra cơ sở với mã ${corpcode}`)
        //Thay đổi tình trạng, ngày bắt đầu
    }

    //Hàm xử lý kết thúc thanh tra cơ sở
    const handleEndInspect = (corpcode) => {
        cogoToast.success(`Hoàn tất thanh tra cơ sở với mã ${corpcode}`)
        //Thay đổi tình trạng, chuyển mẫu thực phẩm sang pha sau
    }

    const handleRemoveInspect = (corpcode) => {
        cogoToast.info(`Kết thúc thanh tra cơ sở với mã ${corpcode}`)
        //Loại bỏ cơ sở khỏi bảng thanh tra
    }

    const handleStartFoodInspect = (corpcode) => {
        cogoToast.info(`Bắt đầu kiểm định thực phẩm có mã ${corpcode}`)
        //Thay đổi tình trạng, ngày bắt đầu
    }

    //Hàm xử lý kết thúc thanh tra cơ sở
    const handleEndFoodInspect = (corpcode) => {
        cogoToast.success(`Hoàn tất kiểm định thực phẩm có mã ${corpcode}`)
        //Thay đổi tình trạng, chuyển mẫu thực phẩm sang pha sau
    }

    const handleRemoveFoodInspect = (corpcode) => {
        cogoToast.info(`Kết thúc kiểm định thực phẩm có mã ${corpcode}`)
        //Loại bỏ cơ sở khỏi bảng thanh tra
    }

    return (
        <div className='inspect-plan-container'>
            <Swiper
                modules={[Navigation, Pagination]}
                navigation
                pagination={{
                    clickable: true
                }}
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
                                {inspectfacilities.map((item, index) => (
                                    <tr key={index}>
                                        <td>{item.CORPCODE}</td>
                                        <td className='enlarge'>{item.INSPECTSTATE}</td>
                                        <td className={
                                            item.INSPECTSTATE === 'Chưa bắt đầu' ?
                                            '' : (item.INSPECTSTATE === 'Hoàn thành' ? 'finished' : 'pending')
                                        }>{item.INSPECTSTATE}</td>
                                        <td>
                                            {item.INSPECTSTATE === 'Chưa bắt đầu' && <Button
                                                children='Tiến hành kiểm tra'
                                                onClick={handleStartInspect(item.CORPCODE)}
                                                buttonStyle='btn--outline'
                                                buttonSize='btn--large'
                                                buttonHref='inspectplan'
                                            ></Button>}
                                            {item.INSPECTSTATE === 'Trong quá trình kiểm tra...' && <Button
                                                children='Hoàn thành kiểm tra'
                                                onClick={handleEndInspect(item.CORPCODE)}
                                                buttonStyle='btn--outline'
                                                buttonSize='btn--large'
                                                buttonHref='inspectplan'
                                            ></Button>}
                                            {item.INSPECTSTATE === 'Quá trình kiểm tra hoàn tất!' && <Button
                                                children='Loại bỏ'
                                                onClick={handleRemoveInspect(item.CORPCODE)}
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
                    <h1>Lấy mẫu thực phẩm - Thực hiện kiểm định</h1>
                    <div className='inspect-table'>
                        <table className='inspect-table-content'>
                                <thead>
                                    <tr>
                                        <td>Mã cơ sở</td>
                                        <td>Mã mẫu thực phẩm</td>
                                        <td className='enlarge'>Tình trạng kiểm định</td>
                                        <td>Thao tác</td>
                                    </tr>
                                </thead>
                                <tbody>
                                    {inspectfoods.map((item, index) => (
                                        <tr key={index}>
                                            <td>{item.CORPCODE}</td>
                                            <td>{item.FOODCODE}</td>
                                            <td className={`enlarge ${
                                                item.INSPECTSTATE === 'Chưa bắt đầu' ?
                                                '' : (item.INSPECTSTATE === 'Hoàn thành' ? 'finished' : 'pending')
                                            }`}>{item.INSPECTSTATE}</td>
                                            <td>
                                                {item.INSPECTSTATE === 'Chưa bắt đầu' && <Button
                                                    children='Tiến hành kiểm định'
                                                    onClick={handleStartFoodInspect(item.CORPCODE)}
                                                    buttonStyle='btn--outline'
                                                    buttonSize='btn--large'
                                                    buttonHref='inspectplan'
                                                ></Button>}
                                                {item.INSPECTSTATE === 'Trong quá trình kiểm định...' && <Button
                                                    children='Hoàn thành kiểm tra'
                                                    onClick={handleEndFoodInspect(item.CORPCODE)}
                                                    buttonStyle='btn--outline'
                                                    buttonSize='btn--large'
                                                    buttonHref='inspectplan'
                                                ></Button>}
                                                {item.INSPECTSTATE === 'Quá trình kiểm tra hoàn tất!' && <Button
                                                    children='Loại bỏ'
                                                    onClick={handleRemoveFoodInspect(item.CORPCODE)}
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
                                    <td>Mã cơ sở</td>
                                    <td>Mã thực phẩm</td>
                                    <td className='enlarge'>Kết quả giám định thực phẩm</td>
                                    <td>Thao tác</td>
                                </tr>
                            </thead>
                            <tbody>
                                
                            </tbody>
                        </table>
                    </div>
                </SwiperSlide>
                <SwiperSlide>
                    <h1>Quyết định cuối cùng</h1>
                    <div className='inspect-table'>
                        <table className='inspect-table-content'>
                            <thead>
                                <tr>
                                    <td>Mã cơ sở</td>
                                    <td>Quyết định của chuyên viên</td>
                                </tr>
                            </thead>
                            <tbody>
                                
                            </tbody>
                        </table>
                    </div>
                </SwiperSlide>
            </Swiper>
        </div>
    )
}

export default InspectPlan