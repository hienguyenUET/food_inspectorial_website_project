import Header from './components/Header'
import './App.css'

function App() {

    return (
        <div className='app'>
            {window.location.href !== 'http://localhost:3000/' && <Header />}
        </div>
    )
}

export default App