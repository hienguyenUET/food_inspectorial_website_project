import { useState, useEffect, } from 'react'
import { Link } from 'react-router-dom'
import { Button } from '../Button'
import { Twirl as Hamburger, Twirl } from 'hamburger-react'
import './Header.css'
import axios from 'axios'

function Header() {
    const [click, setClick] = useState(false)
    const [button, setButton] = useState(true)
    const [fir, setFir] = useState(false)
    const [sec, setSec] = useState(false)
    const [thi, setThi] = useState(false)
    const user = JSON.parse(localStorage.getItem('user-info'))

    const handleClick = () => setClick(!click)

    const closeMobileMenu = () => setClick(false)

    const showButton = () => {
        if (window.innerWidth <= 960) {
            setButton(false)
        } else {
            setButton(true)
        }
    }

    //Khi đăng xuất xóa dữ liệu người dùng ở bộ nhớ đệm và trong csdl
    const handleLogout = () => {
        axios('http://localhost:8080/auth/logout')
        localStorage.clear()
    }

    useEffect(() => {
        showButton()
    }, [])

    useEffect(() => {
        if (fir) {
            setSec(false)
            setThi(false)
        }
    }, [fir])

    useEffect(() => {
        if (sec) {
            setFir(false)
            setThi(false)
        }
    }, [sec])

    useEffect(() => {
        if (thi) {
            setFir(false)
            setSec(false)
        }
    }, [thi])

    window.addEventListener('resize', showButton)

    return (
        <>
            {
                <nav className='navbar'>
                    <div className='navbar-container'>
                        <div className='navbar-logo'>
                            Healthy first
                            <img className='logo' src={`${process.env.PUBLIC_URL}/assets/small-logo.png`} alt='Description' />
                        </div>

                        <div className='menu-icon' onClick={handleClick}>
                            <Twirl color='#05386b' toggled={click} toggle={setClick} />
                        </div>

                        <ul className={click ? 'nav-menu active' : 'nav-menu'}>
                            <li className='nav-item dropdown'>
                                <Link to='/management' className='nav-links' onClick={closeMobileMenu}>
                                    Trang chủ
                                </Link>
                            </li>
                            {
                                user.role === '[ADMIN]' && (<li className='nav-item dropdown'>
                                    <Link to='/areaassignment' className='nav-links' onClick={closeMobileMenu}>
                                        Phân công địa bàn
                                    </Link>
                                </li>)
                            }
                            <li className='nav-item dropdown'>
                                <Link to='/standardfacilities' className='nav-links' onClick={closeMobileMenu}>
                                    Cơ sở sản xuất
                                </Link>
                            </li>
                            <li className={`nav-item dropdown ${sec ? 'focus' : ''}`} onClick={() => setSec(!sec)}>
                                <div className='nav-links'>
                                    Giấy chứng nhận
                                    <i className='fa-solid fa-caret-down'></i>
                                </div>
                                <ul className='dropdown-menu'>
                                    <li><Link className='dropdown-item' to='/filter'>Lọc danh sách</Link></li>
                                    <li><Link className='dropdown-item' to='/procedures'>Thủ tục hành chính</Link></li>
                                </ul>
                            </li>
                            <li className={`nav-item dropdown ${thi ? 'focus' : ''}`} onClick={() => setThi(!thi)}>
                                <div className='nav-links'>
                                    Hoạt thông thanh
                                    <i className='fa-solid fa-caret-down'></i>
                                </div>
                                <ul className='dropdown-menu'>
                                    <li><Link className='dropdown-item' to='/inspectsuggest'>Gợi ý điều tra</Link></li>
                                    <li><Link className='dropdown-item' to='/inspectplan'>Kế hoạch điều tra</Link></li>
                                    <li><Link className='dropdown-item' to='/inspectstatistic'>Thống kê kết quả</Link></li>
                                </ul>
                            </li>
                            <li className='nav-item'>
                                <Link to='/' className='nav-links-mobile' onClick={closeMobileMenu}>
                                    Đăng xuất
                                </Link>
                            </li>
                        </ul>
                        {button && <Button
                            children='ĐĂNG XUẤT'
                            buttonStyle='button--primary'
                            buttonSize='btn--medium'
                            buttonHref=''
                            onClick={handleLogout}
                        />}
                    </div>
                </nav>
            }
        </>
    )
}

export default Header