//Trang chủ
import { useState } from 'react'
import LoginForm from '../../components/LoginForm'
import './Home.css'

function Home() {
    const adminUser = {
        email: 'admin@admin.com',
        password: 'admin123'
    }

    const [user, setUser] = useState({name: '', email: ''})
    const [error, setError] = useState('')

    const login = details => {
        console.log(details)

        if (details.email === adminUser.email
            && details.password === adminUser.password) {
                console.log('Successful')
                setUser({
                    email: details.email
                })
            }
        else {
            console.log('Fail')
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