import { useState, useRef, useEffect } from 'react'
import { FACILITIES, FILTER_BY } from '../../constants'
import axios from 'axios'
import cogoToast from 'cogo-toast'
import './Filter.css'

function Filter() {
    const [searchActive, setSearchActive] = useState(false)
    const [isNoneSafe, setIsNoneSafe] = useState(true)
    const [isSafe, setIsSafe] = useState(true)
    const [defaultStandard, setDefaultStandard] = useState([])
    const [standard, setStandard] = useState([])
    const [filterBy, setFilterBy] = useState('')
    const userInfo = JSON.parse(localStorage.getItem('user-info'))

    const searchRef = useRef()

    //Xử lý khi người dùng thay đổi bộ lọc
    const handleFilterChange = (event) => {
        console.log(event.target.value)
        if (event.target.value !== 'Tất cả') {
            switch (event.target.value) {
                case (FILTER_BY[0]):
                    setFilterBy('regNo')
                    setStandard(defaultStandard)
                    break
                case (FILTER_BY[1]):
                    setFilterBy('name')
                    setStandard(defaultStandard)
                    break
                case (FILTER_BY[2]):
                    setFilterBy('address')
                    setStandard(defaultStandard)
                    break
                case (FILTER_BY[3]):
                    setFilterBy('phone')
                    setStandard(defaultStandard)
                    break
                case (FILTER_BY[4]):
                    setFilterBy('certification')
                    setStandard(defaultStandard)
                    break
                default:
                    break
            }
        } else {
            setFilterBy('all')
            setStandard(defaultStandard)
        }
    }

    const handleFindBy = (event) => {
        const value = event.target.value
        console.log(value)

        if (filterBy === 'regNo') {
            setStandard(defaultStandard.filter(item => item.regNo.includes(value)))
        } else if (filterBy === 'name') {
            setStandard(defaultStandard.filter(item => item.name.includes(value)))
        } else if (filterBy === 'address') {
            setStandard(defaultStandard.filter(item => {
                const standardName = 'Số ' + item.address.number + ' ' + (item.address.alley !== null ? item.address.alley : '') + ' ' + item.address.street + ', phường ' + item.address.subDistrict.subDistrictName + ', quận ' + item.address.subDistrict.district.districtName + ', thành phố ' + item.address.subDistrict.district.city.city
                if (standardName.includes(value)) {
                    return item
                }
            }))
        } else if (filterBy === 'phone') {
            setStandard(defaultStandard.filter(item => item.phoneNumber.includes(value)))
        } else if (filterBy === 'certification') {
            setStandard(defaultStandard.filter(item => (item.certification && item.certification.certificationNumber.includes(value))))
        }
    }

    useEffect(() => {
        //Request cửa hàng
        axios(`http://localhost:8080/${userInfo.role === '[ADMIN]' ? 'admin' : 'specialist'}/stores/get`, {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token'),
                'Access-Control-Allow-Origin': '*',
            },
        }).then(res => {
            setDefaultStandard(res.data)
            setStandard(res.data)
        }).catch(() => {
            cogoToast.error('Có lỗi xảy ra!')
        })
    }, [])

    //Lọc có giấy chứng nhận hay không
    useEffect(() => {
        if (!isNoneSafe) {
            if (isSafe) {
                setStandard(defaultStandard.filter(item => item.certification))
            } else {
                setStandard(defaultStandard.filter(item => !item.certification))
            }
        } else {
            setStandard(defaultStandard)
        }
    }, [isNoneSafe, isSafe, defaultStandard])

    return (
        <div className='filter-container'>
            <h1>Lọc danh sách các cơ sở kinh doanh thực phẩm</h1>

            <div className='filter-radios-container'>
                <label class='radio-container'>
                    <input type='radio' name='radio' onClick={() => { setIsNoneSafe(true) }} />
                    Tất cả
                    <span className='checkmark'></span>
                </label>
                <label class='radio-container'>
                    <input type='radio' name='radio' onClick={() => {
                        setIsSafe(true)
                        setIsNoneSafe(false)
                    }} />
                    Đủ điều kiện an toàn thực phẩm
                    <span className='checkmark'></span>
                </label>
                <label class='radio-container'>
                    <input type='radio' name='radio' onClick={() => {
                        setIsSafe(false)
                        setIsNoneSafe(false)
                    }} />
                    Không đủ điều kiện an toàn thực phẩm
                    <span className='checkmark'></span>
                </label>
            </div>

            <div className='filter-searchBar'>
                <label for='filter'>
                    <i class='fa-solid fa-filter'></i>
                </label>
                <select
                    id='filter'
                    onChange={handleFilterChange}
                    placeholder='Lọc theo'
                >
                    <option>Tất cả</option>
                    <option>Mã số kinh doanh</option>
                    <option>Tên cơ sở</option>
                    <option>Địa chỉ</option>
                    <option>Số điện thoại</option>
                    <option>Mã chứng nhận</option>
                </select>
                <div className={`filter-searchby
                    ${searchActive && 'focus'}`
                }>
                    <h5>Tìm kiếm...</h5>
                    <input
                        ref={searchRef}
                        onFocus={() => setSearchActive(true)}
                        onBlur={() => {
                            if (searchRef.current.value === '') {
                                setSearchActive(false)
                            } else {
                                setSearchActive(true)
                            }
                        }}
                        onChange={handleFindBy}
                    />
                </div>
            </div>

            <div className='filter-table'>
                <table className='filter-table-content'>
                    <thead>
                        <tr>
                            <td>{FACILITIES[0].name}</td>
                            <td>{FACILITIES[1].name}</td>
                            <td>{FACILITIES[2].name}</td>
                            <td>{FACILITIES[3].name}</td>
                            <td>{FACILITIES[4].name}</td>
                            <td>{FACILITIES[5].name}</td>
                        </tr>
                    </thead>
                    <tbody>
                        {standard.map((item, index) => (
                            <tr key={index}>
                                <td>{item.regNo}</td>
                                <td>{item.name}</td>
                                <td>{'Số ' + item.address.number + ' ' + (item.address.alley !== null ? item.address.alley : '') + ' ' + item.address.street + ', phường ' + item.address.subDistrict.subDistrictName + ', quận ' + item.address.subDistrict.district.districtName + ', thành phố ' + item.address.subDistrict.district.city.city}</td>
                                <td>{item.phoneNumber}</td>
                                <td>{item.businessType.businessType}</td>
                                <td>{item.certification !== null ? item.certification.certificationNumber : 'Không'}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default Filter