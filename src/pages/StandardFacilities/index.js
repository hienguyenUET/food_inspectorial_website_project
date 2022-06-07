import { FACILITIES } from '../../constants'
import { useState, useEffect } from 'react'
import axios from 'axios'
import cogoToast from 'cogo-toast'
import './StandardFacilities.css'

function StandardFacilities() {
    const [standard, setStandard] = useState([])
    const userInfo = JSON.parse(localStorage.getItem('user-info'))

    useEffect(() => {
        axios(`http://localhost:8080/${userInfo.role === '[ADMIN]' ? 'admin' : 'specialist'}/stores/get`, {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token'),
                'Access-Control-Allow-Origin': '*',
            },
            method: 'get'
        }).then(res => {
            setStandard(res.data)
        }).catch(() => {
            cogoToast.error('Có lỗi xảy ra!')
        })
    })

    return (
        <div className='stanFac-container'>
            <h1>Các cơ sở sản xuất thực phẩm và / hoặc kinh doanh dịch vụ ăn uống</h1>

            <div className='stanFac-table'>
                <table className='stanFac-table-content'>
                    <thead>
                        <tr>
                            <td>{FACILITIES[0].name}</td>
                            <td>{FACILITIES[1].name}</td>
                            <td>{FACILITIES[2].name}</td>
                            <td>{FACILITIES[3].name}</td>
                            <td>{FACILITIES[4].name}</td>
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
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default StandardFacilities
