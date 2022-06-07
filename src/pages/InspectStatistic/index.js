import './InspectStatistic.css'
import Chart from 'react-apexcharts'
import axios from 'axios'
import cogoToast from 'cogo-toast'
import { useEffect, useState } from 'react'

function InspectStatistic() {
    const [chartArrayStore, setChartArrayStore] = useState([])
    const [chartArrayCorp, setChartArrayCorp] = useState([])
    const [standard, setStandard] = useState([])
    const userInfo = JSON.parse(localStorage.getItem('user-info'))

    const optionsStore = {
        title: {
            text: 'Cơ sở kinh doanh ăn uống',
            align: 'center',
            style: {
                fontSize: '20px',
                fontWeight: 'bold',
                fontFamily: 'Roboto, sans-serif',
                color: '#05386b'
            },
        },
        legend: {
            fontFamily: 'Roboto, sans-serif',
            fontSize: '14px',
            fontWeight: 'bold',
            position: 'bottom',
            labels: {
                colors: '#feffff'
            }
        },
        colors: ['#ec6b56', '#ffc154', '#52489c'],
        labels: ['Đạt điều kiện ATTP', 'Không đạt điều kiện ATTP', 'Chưa thanh tra'],
    }

    const optionsCorp = {
        title: {
            text: 'Cơ sở sản xuất thực phẩm',
            align: 'center',
            style: {
                fontSize: '20px',
                fontWeight: 'bold',
                fontFamily: 'Roboto, sans-serif',
                color: '#05386b'
            },
        },
        legend: {
            fontFamily: 'Roboto, sans-serif',
            fontSize: '14px',
            fontWeight: 'bold',
            position: 'bottom',
            labels: {
                colors: '#feffff'
            }
        },
        colors: ['#ec6b56', '#ffc154', '#52489c'],
        labels: ['Đạt điều kiện ATTP', 'Không đạt điều kiện ATTP', 'Chưa thanh tra'],
    }

    useEffect(() => {
        axios.get(`http://localhost:8080/${userInfo.role === '[ADMIN]' ? 'admin' : 'specialist'}/stores/get`, {
            headers: {
                Authorization: 'Bearer ' + localStorage.getItem('token'),
                'Access-Control-Allow-Origin': '*',
            },
        }).then(res => {
            let storeNotInspected = 0
            let storeQualified = 0
            let storeNotQualified = 0
            let corpNotInspected = 0
            let corpQualified = 0
            let corpNotQualified = 0
            res.data.forEach(item => {
                if (item.businessType.businessType === 'Kinh doanh ăn uống') {
                    if (item.status.status === 'NO') {
                        storeNotInspected++
                    } else if (item.qualify === true) {
                        storeQualified++
                    } else {
                        storeNotQualified++
                    }
                } else {
                    if (item.status.status === 'NO') {
                        corpNotInspected++
                    } else if (item.qualify === true) {
                        corpQualified++
                    } else {
                        corpNotQualified++
                    }
                }
            })
            let tempArrayStore = []
            let tempArrayCorp = []
            tempArrayStore.push(storeQualified, storeNotQualified, storeNotInspected)
            tempArrayCorp.push(corpQualified, corpNotQualified, corpNotInspected)
            setStandard(res.data)
            setChartArrayStore(tempArrayStore)
            setChartArrayCorp(tempArrayCorp)
        }).catch(() => {
            cogoToast.error('Có lỗi xảy ra')
        })
    }, [])

    return (
        <div className='inspect-statistic-container'>
            <div className='statistic-table-container'>
                <h1>Thống kê kết quả điều tra</h1>
                <table className='statistic-table'>
                    <thead>
                        <tr>
                            <td>Mã cơ sở</td>
                            <td>Tên cơ sở</td>
                            <td>Trạng thái thanh tra</td>
                        </tr>
                    </thead>
                    <tbody>
                        {standard.map((item, index) => (
                            <tr key={index}>
                                <td>{item.regNo}</td>
                                <td>{item.name}</td>
                                <td>{item.status.status === 'NO' ? 'Chưa thanh tra' : (item.qualify === true ? ('Đạt điều kiện an toàn thực phẩm' + '(' + item.reason + ')') : ('Không đạt điều kiện an toàn thực phẩm' + '(' + item.reason + ')'))}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
            <div className='statistic-chart-container'>
                <Chart options={optionsStore} series={chartArrayStore} type="pie" width={360} />
                <br />
                <br />
                <Chart options={optionsCorp} series={chartArrayCorp} type="pie" width={360} />
            </div>
        </div>
    )
}

export default InspectStatistic