import { Routes, Route, Link, BrowserRouter } from 'react-router-dom'
import Home from '../pages/Home'

const createRoutes = () => {
    <Routes>
        <Route path='/' element={Home} />
    </Routes>
}

export default createRoutes