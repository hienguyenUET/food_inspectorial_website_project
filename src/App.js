import Header from './components/Header'
import './App.css'
import { useLocation } from 'react-router-dom'

function App() {
    const location = useLocation()

    return (
        <div className='app'>
            {location.pathname !== '/' && <Header />}
        </div>
    )
}

export default App