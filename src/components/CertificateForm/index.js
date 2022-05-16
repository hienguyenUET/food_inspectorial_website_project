
import { useState, useRef } from 'react'
import { standard, facilities } from '../../constants'
import { Button } from '../Button'
import cogoToast from 'cogo-toast'
import './CertificateForm.css'

function CertificateForm(props) {
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

    const handleAddCertificate = () => {
        if (corpcodeA === '') {
            cogoToast.warn('Vui lòng điền mã cơ sở')
        } else if (!standard.find(item => item.CORPCODE === corpcodeA)) {
            cogoToast.error(
                <div>
                    <b>Lỗi!</b>
                    <div>Không tìm thấy mã cơ sở</div>
                </div>
            )
        } else {
            cogoToast.success('Cấp mới giấy chứng nhận thành công!')
            standard = standard.map(item => {
                if (item.CORPCODE === corpcodeA) {
                    if (item.TYPE === 'Kinh doanh thực phẩm') {
                        item.CODE = 'SUP' + item.CORPCODE
                    } else {
                        item.CODE = 'BUS' + item.CORPCODE
                    }
                    item.CODEEXPIREDDATE = today.getDate() + 
                    '/' + (today.getMonth() + 1) +
                    '/' + (today.getFullYear() + 1)
                }
            })
        }
    }

    const handleRemoveCertificate = () => {
        if (corpcodeB === '') {
            cogoToast.warn('Vui lòng điền mã cơ sở')
        } else if (!standard.find(item => item.CORPCODE === corpcodeB)) {
            cogoToast.error(
                <div>
                    <b>Lỗi!</b>
                    <div>Không tìm thấy mã cơ sở</div>
                </div>
            )
        } else if (standard.find(item => item.CORPCODE === corpcodeB && item.CODE === 'Không')) {
            cogoToast.error(
                <div>
                    <b>Lỗi!</b>
                    <div>Cơ sở này không có giấy chứng nhận</div>
                </div>
            )
        } else {
            cogoToast.success('Thu hồi giấy chứng nhận thành công!')
            standard = standard.filter(item => item.CORPCODE !== corpcodeA)
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
                                    <label htmlFor='c-add-codeexpiredold'>Ngày hết hạn cũ</label>
                                    <input id='c-add-codeexpiredold' readOnly='readonly' value={
                                        item.CODE !== 'Không' ?
                                        item.CODEEXPIREDDATE :
                                        ''
                                    } />
                                </div>
                                <div>
                                    <label htmlFor='c-add-codeexpirednew'>Ngày hết hạn mới</label>
                                    <input id='c-add-codeexpirednew' readOnly='readonly' value={
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
                                onClick={handleAddCertificate}
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