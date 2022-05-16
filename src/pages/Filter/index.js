import { useState, useRef } from 'react'
import { standard, facilities } from '../../constants'
import './Filter.css'

function Filter() {
    const [searchActive, setSearchActive] = useState(false)

    const searchRef = useRef()

    return (
        <div className='filter-container'>
            <h1>Lọc danh sách các cơ sở kinh doanh thực phẩm</h1>

            <div className='filter-radios-container'>
                <label class='radio-container'>
                    <input type='radio' checked='checked' name='radio' />
                    Đủ điều kiện an toàn thực phẩm
                    <span className='checkmark'></span>
                </label>
                <label class='radio-container'>
                    <input type='radio' name='radio' />
                    Không đủ điều kiện an toàn thực phẩm
                    <span className='checkmark'></span>
                </label>
            </div>

            <div className='filter-searchBar'>
                <label for='filter'>
                    <i class='fa-solid fa-filter'></i>
                </label>
                <select id='filter'>
                    <option>Lọc theo...</option>
                    <option>Tên cơ sở</option>
                    <option>Số điện thoại</option>
                    <option>Loại hình kinh doanh</option>
                    <option>Mã chứng nhận</option>
                </select>
                <div className={`filter-searchby
                    ${searchActive && 'focus'}`
                }>
                    <h5>Tìm kiếm...</h5>
                    <input
                        ref={searchRef}
                        onFocus={() => setSearchActive(!searchActive)}
                        onBlur={() => {
                            if (searchRef.current.value === '') {
                                setSearchActive(false)
                            }
                        }}
                    />
                </div>
            </div>

            <div className='filter-table'>
                <table className='filter-table-content'>
                    <thead>
                        <tr>
                            <td>{facilities[0].name}</td>
                            <td>{facilities[1].name}</td>
                            <td>{facilities[2].name}</td>
                            <td>{facilities[3].name}</td>
                            <td>{facilities[4].name}</td>
                        </tr>
                    </thead>
                    <tbody>
                        {standard.map((item, index) => (
                            <tr key={index}>
                                <td>{item.NAME}</td>
                                <td>{item.ADDRESS}</td>
                                <td>{item.PHONE}</td>
                                <td>{item.TYPE}</td>
                                <td>{item.CODE}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default Filter