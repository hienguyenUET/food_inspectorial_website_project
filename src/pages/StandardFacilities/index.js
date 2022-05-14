import { standard, facilities } from '../../constants'
import './StandardFacilities.css'

function StandardFacilities() {

    return (
        <div className='stanFac-container'>
            <h1>Các cơ sở sản xuất thực phẩm và / hoặc kinh doanh dịch vụ ăn uống</h1>
            
            <div className='stanFac-table'>
                <table className='stanFac-table-content'>
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
    );
}

export default StandardFacilities;
