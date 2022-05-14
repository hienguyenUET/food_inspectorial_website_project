import { Route, Routes } from 'react-router-dom'
import Header from './components/Header'
import Home from './pages/Home'
import Management from './pages/Management'
import StandardFacilities from './pages/StandardFacilities'
import Filter from './pages/Filter'
import './App.css'

function App() {
    return (
        <div className='app'>
            {window.location.href !== 'http://localhost:3000/' && <Header />}
            <Routes>
                <Route path='/filter' exact element={<Filter />} />
            </Routes>
        </div>
    )
}

export default App