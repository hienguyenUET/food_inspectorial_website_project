import './Management.css'

function Management() {
    const user = {
        name: 'Nguyễn Đức Hiệp',
        role: 'Quản lý'
    }

    return (
        <div className='management-container'>
            <div className='management-welcome'>
                <h1>Xin chào</h1>
                <br />
                <h2>{user.role + ' ' + user.name}</h2>
            </div>
        </div>
    )
}

export default Management