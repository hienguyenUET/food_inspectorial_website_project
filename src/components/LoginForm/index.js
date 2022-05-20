import { useState, useRef } from 'react'
import './LoginForm.css'

function LoginForm({ login, error }) {
    const [details, setDetails] = useState({username: '', password: ''})
    const [usernameActive, setUsernameActive] = useState(false)
    const [passActive, setPassActive] = useState(false)

    const submitHandler = e => {
        e.preventDefault()

        login(details)
    }

    const usernameFocusHandler = () => {
        if (!usernameActive) {
            setUsernameActive(true)
        }
    }

    const usernameBlurHandler = () => {
        if (usernameRef.current.value === '') {
            setUsernameActive(false)
        }
    }

    const passFocusHandler = () => {
        if (!passActive) {
            setPassActive(true)
        }
    }

    const passBlurHandler = () => {
        if (passwordRef.current.value === '') {
            setPassActive(false)
        }
    }

    const usernameRef = useRef()
    const passwordRef = useRef()

    return (
        <form onSubmit={submitHandler} className='login-form'>
            <div className='form-content'>
                <h2>Xin chào</h2>
                {/* Error */}
                <div className={`form-group
                    form-username
                    ${usernameActive && 'focus'}`
                }>
                    <div className='i-container'>
                        <i class='fa-solid fa-envelope'></i>
                    </div>
                    <div className='input-container'>
                        <h5>Tài khoản</h5>
                        <input
                            ref={usernameRef}
                            type='username'
                            name='username'
                            id='username'
                            onChange={e => setDetails({
                                ...details,
                                username: e.target.value
                            })}
                            value={details.username}
                            onFocus={usernameFocusHandler}
                            onBlur={usernameBlurHandler}
                        />
                    </div>
                </div>
                <div className={`form-group
                    form-pass
                    ${passActive && 'focus'}`
                }>
                    <div className='i-container'>
                        <i class='fa-solid fa-lock'></i>
                    </div>
                    <div className='input-container'>
                        <h5>Mật khẩu</h5>
                        <input
                            ref={passwordRef}
                            type='password'
                            name='password'
                            id='password'
                            onChange={e => setDetails({
                                ...details,
                                password: e.target.value
                            })}
                            value={details.password}
                            onFocus={passFocusHandler}
                            onBlur={passBlurHandler}
                        />
                    </div>
                </div>
                <br />
                <input
                    type='submit'
                    value='Đăng nhập'
                    className='login-submit-btn'
                />
            </div>
        </form>
    )
}

export default LoginForm