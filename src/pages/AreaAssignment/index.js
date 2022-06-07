import React from 'react'
import axios from 'axios'
import cogoToast from 'cogo-toast'
import { Button } from '../../components/Button'
import './AreaAssignment.css'

export default class AreaAssignment extends React.Component {
    constructor(props) {
        super(props)

        // Set initial state 
        this.state = {
            specialistId: '',
            specialistExist: false,
            districts: [],
            district: '',
            subDistricts: [],
            subDistrict: ''
        }

        // Binding this keyword 
        this.handleSpecialistIdChange = this.handleSpecialistIdChange.bind(this)
        this.handleDistrictChange = this.handleDistrictChange.bind(this)
        this.handleSubDistrictChange = this.handleSubDistrictChange.bind(this)
    }

    componentDidMount() {
        localStorage.removeItem('specialist-info')

        if (!localStorage.getItem('districts')) {
            axios.get('http://localhost:8080/address/districts')
                .then(res => {
                    localStorage.setItem('districts', JSON.stringify(res.data.map(item => item.districtName)))
                    this.setState({ districts: res.data.map(item => item.districtName) })
                })
                .catch(() => {
                    cogoToast.error('Lấy dữ liệu không thành công!')
                })
        }
    }

    componentWillUnmount() {
        localStorage.removeItem('specialist-info')
        localStorage.removeItem('districts')
        localStorage.removeItem('sub-districts')
    }

    handleSpecialistIdChange(event) {
        this.setState({ specialistId: event.target.value, specialistExist: false })
    }

    handleDistrictChange(event) {
        this.setState({ district: event.target.value, subDistrict: '' })
        axios.get(`http://localhost:8080/address/subdistricts/${event.target.value}`)
            .then(res => {
                localStorage.setItem('sub-districts', JSON.stringify(res.data.map(item => item.subDistrictName)))
                this.setState({ districts: res.data.map(item => item.districtName) })
            })
            .catch(() => {
                cogoToast.error('Lấy dữ liệu không thành công!')
            })
    }

    handleSubDistrictChange(event) {
        this.setState({ subDistrict: event.target.value })
    }

    handleCheckInfo = async () => {
        const res = await axios.get('http://localhost:8080/admin/get/specialist', {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token'),
                'Access-Control-Allow-Origin': '*',
            },
        })

        if (this.state.specialistId === '') {
            cogoToast.warn('Vui lòng điền mã chuyên viên')
        } else if (!res.data.find(item => item.id === (this.state.specialistId - ''))) {
            cogoToast.error(
                <div>
                    <b>Lỗi!</b>
                    <div>{`Không tìm thấy chuyên viên với mã ${this.state.specialistId}`}</div>
                </div>
            )
        } else {
            localStorage.setItem('specialist-info', JSON.stringify(res.data.find(item => item.id === (this.state.specialistId - ''))))
            this.setState({ specialistExist: true })
        }
    }

    handleAssignArea = async () => {
        const res = await axios.get('http://localhost:8080/admin/get/specialist', {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token'),
                'Access-Control-Allow-Origin': '*',
            },
        })

        if (this.state.specialistId === '') {
            cogoToast.warn('Vui lòng điền mã nhân viên')
        } else if (!res.data.find(item => item.id === (this.state.specialistId - ''))) {
            cogoToast.error(
                <div>
                    <b>Lỗi!</b>
                    <div>{`Không tìm thấy chuyên viên với mã ${this.state.specialistId}`}</div>
                </div>
            )
        } else if (this.state.district === '' || this.state.subDistrict === '') {
            cogoToast.warn('Vui lòng chọn phân vùng mới cho chuyên viên')
        } else {
            localStorage.setItem('specialist-info', JSON.stringify(res.data.find(item => item.id === (this.state.specialistId - ''))))

            axios({
                url: `http://localhost:8080/admin/assign/`,
                headers: {
                    Authorization: 'Bearer ' + localStorage.getItem('token'),
                    'Access-Control-Allow-Origin': '*',
                },
                data: {
                    id: this.state.specialistId,
                    sub_district_name: this.state.subDistrict,
                },
                method: 'put'
            })

            cogoToast.loading('Đang chuyển vùng chuyên viên...', {
                hideAfter: 1.5
            })

            setTimeout(() => {
                cogoToast.success('Phân vùng thành công!')
            }, 1500)
        }
    }

    render() {
        const districts = localStorage.getItem('districts') ? JSON.parse(localStorage.getItem('districts')) : []
        const subDistricts = localStorage.getItem('sub-districts') ? (this.state.district === '' ? [] : JSON.parse(localStorage.getItem('sub-districts'))) : []
        const specialistInfo = this.state.specialistExist ? JSON.parse(localStorage.getItem('specialist-info')) : {}
        console.log(subDistricts)

        return (
            <div className='area-container' >
                <div className='area-content'>
                    <div className='input-container'>
                        <label htmlFor='specialistId'>Mã số chuyên viên</label>
                        <input
                            onChange={this.handleSpecialistIdChange}
                            value={this.state.specialistId}
                        />
                    </div>

                    <div className='input-container'>
                        <label htmlFor='city'>Tỉnh/ Thành phố</label>
                        <select id='city'>
                            <option>Hà Nội</option>
                        </select>
                    </div>

                    <div className='input-container'>
                        <label htmlFor='district'>Quận/ Huyện</label>
                        <select
                            id='district'
                            onChange={this.handleDistrictChange}
                            value={this.state.district}
                        >
                            <option selected='selected'></option>
                            {
                                districts !== '' && districts.map(item => {
                                    return <option>{item}</option>
                                }
                                )}
                        </select>
                    </div>

                    <div className='input-container'>
                        <label htmlFor='sub-district'>Xã/ Phường</label>
                        <select
                            id='sub-district'
                            onChange={this.handleSubDistrictChange}
                            value={this.state.subDistrict}
                        >
                            <option selected='selected'></option>
                            {
                                subDistricts !== '' && subDistricts.map(item => {
                                    return <option>{item}</option>
                                }
                                )}
                        </select>
                    </div>
                </div>

                {
                    this.state.specialistExist &&
                    (<div className='area-info-container'>
                        <table>
                            <thead></thead>
                            <tbody>
                                <tr>
                                    <td>Mã nhân viên: </td>
                                    <td>{this.state.specialistId}</td>
                                </tr>
                                <tr>
                                    <td>Tên nhân viên: </td>
                                    <td>{specialistInfo.firstName + ' ' + specialistInfo.lastName}</td>
                                </tr>
                                <tr>
                                    <td>Phân vùng cũ: </td>
                                    <td>Phường {specialistInfo.subDistrict.subDistrictName}, Quận {specialistInfo.subDistrict.district.districtName}</td>
                                </tr>
                                <tr>
                                    <td>Phân vùng mới: </td>
                                    <td>Phường {this.state.subDistrict}, Quận {this.state.district}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>)
                }

                <div className='area-confirm'>
                    <Button
                        children='KIỂM TRA THÔNG TIN'
                        buttonStyle='btn--filled'
                        buttonSize='btn--extra--large'
                        buttonHref='areaassignment'
                        onClick={this.handleCheckInfo}
                    />

                    <Button
                        children='XÁC NHẬN PHÂN CÔNG'
                        buttonStyle='btn--filled'
                        buttonSize='btn--extra--large'
                        buttonHref='areaassignment'
                        onClick={this.handleAssignArea}
                    />
                </div>
            </div>
        )
    }
}