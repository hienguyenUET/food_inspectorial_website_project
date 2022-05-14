import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter as Router } from 'react-router-dom';
import App from './App';
// import createRoutes from './routes';

const root = ReactDOM.createRoot(document.getElementById('root'));

// const routes = createRoutes();

root.render(
  <Router>
    <App />
  </Router>
);
