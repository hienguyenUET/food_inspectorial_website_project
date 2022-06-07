import axios from 'axios'
import cogoToast from 'cogo-toast'
import { useState, useEffect } from 'react'
import CertificateForm from '../../components/CertificateForm'
import './Procedures.css'

function Procedures() {
    const [standard, setStandard] = useState([])
    const userInfo = JSON.parse(localStorage.getItem('user-info'))

    useEffect(() => {
        axios.get(`http://localhost:8080/${userInfo.role === '[ADMIN]' ? 'admin' : 'specialist'}/stores/get`, {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token'),
                'Access-Control-Allow-Origin': '*',
            },
        }).then(res => {
            setStandard(res.data)
        }).catch(() => {
            cogoToast.error('Có lỗi xảy ra')
        })
    }, [])

    return (
        <div className='procedures-container'>
            <h1>Các thủ tục hành chính</h1>
            <CertificateForm
                standard={standard}
            />
        </div>
    )
}

export default Procedures