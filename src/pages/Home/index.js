//Trang chủ
import { useState } from 'react'
import axios from 'axios'
import LoginForm from '../../components/LoginForm'
import cogoToast from 'cogo-toast'
import { useNavigate } from 'react-router-dom';
import './Home.css'

function Home() {
    const [user, setUser] = useState({ name: '', role: '' })
    const [error, setError] = useState('')
    const navigate = useNavigate();

    //Xử lý đăng nhập
    const login = details => {
        if (details.username === '') {
            //Người dùng chưa điền tài khoản
            cogoToast.error('Vui lòng điền tài khoản')
        } else if (details.password === '') {
            //Người dùng chưa nhập mật khẩu
            cogoToast.error('Vui lòng nhập mật khẩu')
        } else {
            //Gửi request kiểm tra chính chủ
            axios.post('http://localhost:8080/auth/login', {
                username: details.username,
                password: details.password
            }).then(res => {
                localStorage.setItem('token', res.data.token)
                localStorage.setItem('user-info', JSON.stringify({
                    id: res.data.id,
                    firstName: res.data.firstName,
                    lastName: res.data.lastName,
                    role: res.data.role
                }))
                navigate('/management');
            }).catch(() => {
                cogoToast.error('Tài khoản hoặc mật khẩu không đúng')
            })
        }

    }

    const logout = () => {
        console.log('Log out')
    }

    return (
        <div className='home-container'>
            <div>
                <img src={`${process.env.PUBLIC_URL}/assets/big-logo.png`}></img>
            </div>
            <div>
                <div className='home-intro'>
                    <h1>Healthy first</h1>
                    <br />
                    <h1>Hệ thống quản lý các cơ sở sản xuất thực phẩm, kinh doanh dịch vụ ăn uống</h1>
                    <br />
                </div>
                <div className='home-form'>
                    <LoginForm login={login} error={error} />
                </div>
            </div>
        </div>
    )
}

export default Home