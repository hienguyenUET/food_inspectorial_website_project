
import { useState } from 'react'
import { standard, facilities } from '../../constants'
import { Button } from '../Button'
import './CertificateForm.css'

function CertificateForm(props) {
    let found
    let today = new Date()

    const [corpcodeA, setCorpcodeA] = useState('')
    const [corpcodeB, setCorpcodeB] = useState('')
    const [formType, setFormType] = useState('add')

    const handleFormChange = (e) => {
        setFormType(e.target.value)
    }

    const handleCorpcodeChangeA = (e) => {
        setCorpcodeA(e.target.value)
    }

    const handleCorpcodeChangeB = (e) => {
        setCorpcodeB(e.target.value)
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
                            <label htmlFor='c-add-corpcode'>Mã cơ sở</label>
                            <input id='c-add-corpcode' disabled={formType === 'remove' && 'disabled'} onChange={handleCorpcodeChangeA}></input>
                        </div>
                        {standard.map((item) => (
                            item.CORPCODE === corpcodeA &&
                            <>
                                <div>
                                    <label htmlFor='c-add-name'>Tên cơ sở</label>
                                    <input id='c-add-name' readOnly='readonly' value={item.NAME} />
                                </div>
                                <div>
                                    <label htmlFor='c-add-address'>Địa chỉ</label>
                                    <input id='c-add-address' readOnly='readonly' value={item.ADDRESS} />
                                </div>
                                <div>
                                    <label htmlFor='c-add-name'>Số điện thoại</label>
                                    <input id='c-add-phone' readOnly='readonly' value={item.PHONE} />
                                </div>
                                <div>
                                    <label htmlFor='c-add-type'>Loại hình kinh doanh</label>
                                    <input id='c-add-type' readOnly='readonly' value={item.TYPE} />
                                </div>
                                <div>
                                    <label htmlFor='c-add-code'>Giấy chứng nhận</label>
                                    <input id='c-add-code' readOnly='readonly' value={item.CODE} />
                                </div>
                                <div>
                                    <label htmlFor='c-add-code'>Ngày hết hạn</label>
                                    <input id='c-add-code' readOnly='readonly' value={
                                        today.getDate() + 
                                        '/' + (today.getMonth() + 1) +
                                        '/' + (today.getFullYear() + 1)
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
                                buttonDisable={formType === 'remove' && 'true'}
                            />
                        </span>
                    </div>

                    <div className='form-divider'></div>

                    <div className='certificate-form remove'>
                        <div>
                            <label htmlFor='c-remove-corpcode'>Mã cơ sở</label>
                            <input id='c-remove-corpcode' disabled={formType === 'add' && 'disabled'} onChange={handleCorpcodeChangeB}></input>
                        </div>
                        {standard.map((item) => (
                            item.CORPCODE === corpcodeB &&
                            <>
                                <div>
                                    <label htmlFor='c-remove-name'>Tên cơ sở</label>
                                    <input id='c-remove-name' readOnly='readonly' value={item.NAME} />
                                </div>
                                <div>
                                    <label htmlFor='c-remove-address'>Địa chỉ</label>
                                    <input id='c-remove-address' readOnly='readonly' value={item.ADDRESS} />
                                </div>
                                <div>
                                    <label htmlFor='c-remove-name'>Số điện thoại</label>
                                    <input id='c-remove-phone' readOnly='readonly' value={item.PHONE} />
                                </div>
                                <div>
                                    <label htmlFor='c-remove-type'>Loại hình kinh doanh</label>
                                    <input id='c-remove-type' readOnly='readonly' value={item.TYPE} />
                                </div>
                                <div>
                                    <label htmlFor='c-remove-code'>Giấy chứng nhận</label>
                                    <input id='c-remove-code' readOnly='readonly' value={item.CODE} />
                                </div>
                            </>
                        ))}
                        <span className='proc-btn'>
                            <Button
                                children='Thu hồi giấy phép'
                                buttonStyle='btn--outline'
                                buttonSize='btn--large'
                                buttonHref='procedures'
                                buttonDisable={formType === 'add' && 'true'}
                            />
                        </span>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default CertificateForm