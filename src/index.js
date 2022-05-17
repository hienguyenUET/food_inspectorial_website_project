import React from 'react';
import ReactDOM from 'react-dom/client';
import { Route, Routes } from 'react-router-dom';
import { BrowserRouter as Router } from 'react-router-dom';
import App from './App';
import Home from './pages/Home';
import Management from './pages/Management';
import StandardFacilities from './pages/StandardFacilities';
import Procedures from './pages/Procedures';
import Filter from './pages/Filter';
import InspectPlan from './pages/InspectPlan';
import InspectEvidence from './pages/InspectEvidence';
import InspectStatistic from './pages/InspectStatistic';
import InspectSuggest from './pages/InspectSuggest';
// import createRoutes from './routes';

const root = ReactDOM.createRoot(document.getElementById('root'));

// const routes = createRoutes();

root.render(
  <Router basename='/'>
    <App />
    <Routes>
      <Route exact path='/' element={<Home />} />
      <Route exact path='/management' element={<Management />} />
      <Route exact path='/standardfacilities' element={<StandardFacilities />} />
      <Route exact path='/procedures' element={<Procedures />} />
      <Route exact path='/filter' element={<Filter />} />
      <Route exact path='/inspectsuggest' element={<InspectSuggest />} />
      <Route exact path='/inspectplan' element={<InspectPlan />} />
      <Route exact path='/inspectevidence' element={<InspectEvidence />} />
      <Route exact path='/inspectstatistic' element={<InspectStatistic />} />
    </Routes>
  </Router>
);
