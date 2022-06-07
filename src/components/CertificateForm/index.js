
import { useState } from 'react'
import { Button } from '../Button'
import PdfComponent from '../../components/PdfComponent'
import cogoToast from 'cogo-toast'
import axios from 'axios'
import './CertificateForm.css'

function CertificateForm(props) {
    let today = new Date()

    //Các cơ sở gốc
    const defaultStandard = props.standard
    //Mã cơ sở form cấp mới
    const [regNoA, setRegNoA] = useState('')
    //Mã cơ sở form thu hồi
    const [regNoB, setRegNoB] = useState('')
    //Thể loại form cấp mới hay thu hồi
    const [formType, setFormType] = useState('add')
    //Mã giấy chứng nhận cơ sở cấp mới
    const [certNumber, setCertNumber] = useState('')

    //Xử lý đổi kiểu form
    const handleFormChange = (e) => {
        setFormType(e.target.value)
    }

    //Xử lý nhập mã cơ sở cấp mới
    const handleRegnoChangeA = (e) => {
        setRegNoA(e.target.value)
    }

    //Xử lý mã cơ sở thu hồi
    const handleRegnoChangeB = (e) => {
        setRegNoB(e.target.value)
    }

    const handleCertChange = (e) => {
        setCertNumber(e.target.value)
    }

    //Xử lý cấp mới giấy chứng nhận
    const handleAddCertificate = () => {
        if (regNoA === '') {
            cogoToast.warn('Vui lòng điền mã cơ sở')
        } else if (!props.standard.find(item => item.regNo === regNoA)) {
            cogoToast.error(
                <div>
                    <b>Lỗi!</b>
                    <div>Không tìm thấy mã cơ sở</div>
                </div>
            )
        } else {
            if (!certNumber) {
                cogoToast.warn('Vui lòng cấp mã giấy chứng nhận!')
            } else {
                defaultStandard.find(item => {
                    if (item.regNo === regNoA) {
                        if (item.qualify === false) {
                            cogoToast.error('Cơ sở này không đạt đủ điều kiện an toàn thực phẩm')
                        } else {
                            const found = defaultStandard.find(item => {
                                if (item.certification !== null) {
                                    if (item.certification.certificationNumber.includes(certNumber)) {
                                        return item
                                    }
                                }
                            })

                            if (found !== undefined) {
                                cogoToast.error('Mã số giấy chứng nhận đã tồn tại')
                            } else {
                                cogoToast.success('Cấp mới giấy chứng nhận thành công!')
                                //Tìm cơ sở để cập nhật mã giấy và ngày hết hạn
                                const certificationNumber = certNumber + '/ATTP'
                                const startDate = (today.getFullYear()) +
                                    '-' + ('0' + (today.getMonth() + 1)).slice(-2) +
                                    '-' + ('0' + today.getDate()).slice(-2)
                                const expirationDate = (today.getFullYear() + 1) +
                                    '-' + ('0' + (today.getMonth() + 1)).slice(-2) +
                                    '-' + ('0' + (today.getDate() + 1)).slice(-2)
                                axios('http://localhost:8080/store/add/certificate', {
                                    headers: {
                                        Authorization: 'Bearer ' + localStorage.getItem('token'),
                                        'Access-Control-Allow-Origin': '*',
                                    },
                                    data: {
                                        reg_number: regNoA,
                                        certificate_number: certificationNumber,
                                        start_date: startDate,
                                        expiration_date: expirationDate
                                    },
                                    method: 'put'
                                })
                            }
                        }
                    }
                })
            }
        }
    }

    //Xử lý thu hồi giấy chứng nhận
    const handleRemoveCertificate = () => {
        if (regNoB === '') {
            cogoToast.warn('Vui lòng điền mã cơ sở')
        } else if (!props.standard.find(item => item.regNo === regNoB)) {
            cogoToast.error(
                <div>
                    <b>Lỗi!</b>
                    <div>Không tìm thấy mã cơ sở</div>
                </div>
            )
        } else {
            const found = props.standard.find(item => item.regNo === regNoB)
            if (!found.certification) {
                cogoToast.error(
                    <div>
                        <b>Lỗi!</b>
                        <div>Cơ sở này không có giấy chứng nhận</div>
                    </div>
                )
            } else {
                cogoToast.success('Thu hồi giấy chứng nhận thành công!')
                //Gửi request để xoá giấy chứng nhận
                axios('http://localhost:8080/store/certificate/delete', {
                    headers: {
                        Authorization: 'Bearer ' + localStorage.getItem('token'),
                        'Access-Control-Allow-Origin': '*',
                    },
                    data: {
                        reg_number: regNoB
                    },
                    method: 'delete'
                })
            }
        }
    }

    return (
        <div className='certificate-container'>
            <div className='radios'>
                <div className='center'>
                    <label class='radio-container'>
                        <input type='radio' defaultChecked name='radio' value='add' onChange={handleFormChange} />
                        Cấp mới / gia hạn giấy chứng nhận
                        <span className='checkmark'></span>
                    </label>
                </div>

                <div className='center'>
                    <label class='radio-container'>
                        <input type='radio' name='radio' value='remove' onChange={handleFormChange} />
                        Thu hồi giấy chứng nhận
                        <span className='checkmark'></span>
                    </label>
                </div>
            </div>

            <div>
                <div className='certificate-forms-container'>
                    <div className='certificate-form add'>
                        <div>
                            <label htmlFor='c-add-regNo'>Mã cơ sở</label>
                            <input id='c-add-regNo' disabled={formType === 'remove' && 'disabled'} onChange={handleRegnoChangeA} />
                        </div>
                        {formType === 'add' && props.standard.map(item => (
                            item.regNo === regNoA &&
                            <>
                                <div>
                                    <label htmlFor='c-add-name'>Tên cơ sở</label>
                                    <input id='c-add-name' readOnly='readonly' value={item.name} />
                                </div>
                                <div>
                                    <label htmlFor='c-add-address'>Địa chỉ</label>
                                    <input
                                        id='c-add-address'
                                        readOnly='readonly'
                                        value={'Số ' + item.address.number + ' ' + (item.address.alley !== null ? item.address.alley : '') + ' ' + item.address.street + ', phường ' + item.address.subDistrict.subDistrictName + ', quận ' + item.address.subDistrict.district.districtName + ', thành phố ' + item.address.subDistrict.district.city.city}
                                    />
                                </div>
                                <div>
                                    <label htmlFor='c-add-name'>Số điện thoại</label>
                                    <input id='c-add-phone' readOnly='readonly' value={item.phoneNumber} />
                                </div>
                                <div>
                                    <label htmlFor='c-add-qualify'>Điều kiện ATTP</label>
                                    <input id='c-add-qualify' readOnly='readonly' value={item.qualify === false ? 'Không đạt' : 'Đạt'} />
                                </div>
                                <div>
                                    <label htmlFor='c-add-type'>Loại hình kinh doanh</label>
                                    <input id='c-add-type' readOnly='readonly' value={item.businessType.businessType} />
                                </div>
                                <div>
                                    <label htmlFor='c-add-code'>Mã giấy chứng nhận</label>
                                    <input id='c-add-code' disabled={formType === 'remove' && 'disabled'} onChange={handleCertChange} />
                                </div>
                                <div>
                                    <label htmlFor='c-add-codestart'>Ngày cấp</label>
                                    <input id='c-add-codestart' readOnly='readonly' value={
                                        ('0' + today.getDate()).slice(-2) +
                                        '-' + ('0' + (today.getMonth() + 1)).slice(-2) +
                                        '-' + (today.getFullYear())
                                    }
                                    />
                                </div>
                                <div>
                                    <label htmlFor='c-add-codeexpirednew'>Ngày hết hạn</label>
                                    <input id='c-add-codeexpirednew' readOnly='readonly' value={
                                        ('0' + (today.getDate() + 1)).slice(-2) +
                                        '-' + ('0' + (today.getMonth() + 1)).slice(-2) +
                                        '-' + (today.getFullYear() + 1)
                                    }
                                    />
                                </div>
                            </>
                        ))}
                        <span className='proc-btn'>
                            <Button
                                children='Cấp giấy phép'
                                buttonStyle='btn--outline'
                                buttonSize='btn--large'
                                buttonHref='procedures'
                                buttonDisable={formType === 'remove' && true}
                                onClick={handleAddCertificate}
                            />
                            {(regNoA !== '' && certNumber !== '') && <PdfComponent />}
                        </span>
                    </div>

                    <div className='form-divider'></div>

                    <div className='certificate-form remove'>
                        <div>
                            <label htmlFor='c-remove-regNo'>Mã cơ sở</label>
                            <input id='c-remove-regNo' disabled={formType === 'add' && 'disabled'} onChange={handleRegnoChangeB}></input>
                        </div>
                        {formType === 'remove' && props.standard.map((item) => (
                            item.regNo === regNoB &&
                            <>
                                <div>
                                    <label htmlFor='c-remove-name'>Tên cơ sở</label>
                                    <input id='c-remove-name' readOnly='readonly' value={item.name} />
                                </div>
                                <div>
                                    <label htmlFor='c-remove-address'>Địa chỉ</label>
                                    <input
                                        id='c-remove-address'
                                        readOnly='readonly'
                                        value={'Số ' + item.address.number + ' ' + (item.address.alley !== null ? item.address.alley : '') + ' ' + item.address.street + ', phường ' + item.address.subDistrict.subDistrictName + ', quận ' + item.address.subDistrict.district.districtName + ', thành phố ' + item.address.subDistrict.district.city.city}
                                    />
                                </div>
                                <div>
                                    <label htmlFor='c-remove-name'>Số điện thoại</label>
                                    <input id='c-remove-phone' readOnly='readonly' value={item.phoneNumber} />
                                </div>
                                <div>
                                    <label htmlFor='c-remove-type'>Loại hình kinh doanh</label>
                                    <input id='c-remove-type' readOnly='readonly' value={item.businessType.businessType} />
                                </div>
                                <div>
                                    <label htmlFor='c-remove-code'>Giấy chứng nhận</label>
                                    <input id='c-remove-code' readOnly='readonly' value={item.certification !== null ? item.certification.certificationNumber : 'Không'} />
                                </div>
                            </>
                        ))}
                        <span className='proc-btn'>
                            <Button
                                children='Thu hồi giấy phép'
                                buttonStyle='btn--outline'
                                buttonSize='btn--large'
                                buttonHref='procedures'
                                buttonDisable={formType === 'add' && true}
                                onClick={handleRemoveCertificate}
                            />
                        </span>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default CertificateForm