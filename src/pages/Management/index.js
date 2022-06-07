import './Management.css'

function Management() {
    const userInfo = JSON.parse(localStorage.getItem('user-info'))

    return (
        <div className='management-container'>
            <div className='management-welcome'>
                <h1>Xin chào</h1>
                <br />
                <h2>{(userInfo.role === '[ADMIN]' ? 'Quản lý ' : 'Chuyên viên ') + ' ' + userInfo.firstName + ' ' + userInfo.lastName}</h2>
            </div>
        </div>
    )
}

export default Management