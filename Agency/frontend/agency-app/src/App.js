import './App.css';
import React from 'react';
import {Route, Routes} from 'react-router-dom';
import HomePage from './pages/HomePage/HomePage';
import SearchPage from './pages/SearchPage/SearchPage';
import SearchResultsPage from './pages/SearchResultsPage/SearchResultsPage';
import RegistrationPage from './pages/CandidateRegistration/Registration';
import StatisticsPage from './pages/StatisticsPage/StatisticsPage';

function App() {
  return (
    <div className="App">
        <Routes>
            <Route path="/"                 element={<HomePage />}/>
            <Route path="/search"           element={<SearchPage />} />
            <Route path="/search-results"   element={<SearchResultsPage />} />
            <Route path="/register"         element={<RegistrationPage />} />
            <Route path="/statistics"       element={<StatisticsPage />}/> 
        </Routes>
    </div>
  );
}

export default App;
