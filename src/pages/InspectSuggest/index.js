import './InspectSuggest.css'
import { FACILITIES } from '../../constants'
import React from 'react'
import axios from 'axios'
import cogoToast from 'cogo-toast'
import { Link } from 'react-router-dom'

export default class InspectSuggest extends React.Component {
    constructor(props) {
        super(props)

        //Dữ liệu khởi tạo
        this.state = {
            inspect: [],
            userInfo: JSON.parse(localStorage.getItem('user-info')),
        }

        //Bind các hàm với this
        this.handlePlan = this.handlePlan.bind(this)
    }

    componentDidMount() {
        //Lấy các cửa hàng chưa được thanh tra
        axios(`http://localhost:8080/${this.state.userInfo.role === '[ADMIN]' ? 'admin' : 'specialist'}/get/non_inspected`, {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token'),
                'Access-Control-Allow-Origin': '*',
            },
            method: 'get'
        }).then(res => {
            this.setState({ inspect: res.data })
        }).catch(() => {
            cogoToast('Có lỗi xảy ra')
        })
    }

    handlePlan(regNo) {
        //Chuyển cơ sở sang thanh tra
        axios('http://localhost:8080/inspection/store', {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token'),
                'Access-Control-Allow-Origin': '*',
            },
            data: {
                'reg_no': regNo
            },
            method: 'post',
        })
    }

    render() {
        return (
            <div className='inspect-suggest-container' >
                <h1>Cơ sở cần thanh tra</h1>
                <table className='inspect-suggest-table'>
                    <thead>
                        <tr>
                            <td>{FACILITIES[0].name}</td>
                            <td>{FACILITIES[1].name}</td>
                            <td>{FACILITIES[2].name}</td>
                            <td>{FACILITIES[3].name}</td>
                            <td></td>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.inspect.map((item, index) => (
                            <tr key={index}>
                                <td>{item.regNo}</td>
                                <td>{item.name}</td>
                                <td>{'Số ' + item.address.number + ' ' + (item.address.alley !== null ? item.address.alley : '') + ' ' + item.address.street + ', phường ' + item.address.subDistrict.subDistrictName + ', quận ' + item.address.subDistrict.district.districtName + ', thành phố ' + item.address.subDistrict.district.city.city}</td>
                                <td>{item.phoneNumber}</td>

                                <td onClick={() => this.handlePlan(item.regNo)}>
                                    <Link to='/inspectplan' className='to-inspect-plan'>
                                        Lên kế hoạch kiểm tra
                                        <i className='fa-solid fa-arrow-right'></i>
                                    </Link>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        )
    }

}