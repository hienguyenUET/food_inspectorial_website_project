import './InspectEvidence.css'

function InspectEvidence() {
    return (
        <div className='inspect-evidence-container'>
            <h1>Kết quả giám định các mẫu thực phẩm</h1>
            <div className='inspect-table'>
                <table className='inspect-table-content'>
                    <thead>
                        <tr>
                            <td>Mã mẫu thực phẩm</td>
                            <td>Kết quả giám định</td>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
    )
}

export default InspectEvidence