//Trang chủ
import { useState } from 'react'
import axios from 'axios'
import LoginForm from '../../components/LoginForm'
import './Home.css'

function Home() {
    const [user, setUser] = useState({name: '', role: ''})
    const [error, setError] = useState('')

    const login = details => {
        axios.get('http://localhost:8080/auth/login')
            .then(res => {
                console.log(res.data)
            })
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
                    <h1>Health first</h1>
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