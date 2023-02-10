import './App.css';
import React from 'react';
import {Route, Routes} from 'react-router-dom';
import HomePage from './pages/HomePage/HomePage';
import RegistrationPage from './pages/CandidateRegistration/Registration';

function App() {
  return (
    <div className="App">
        <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/register" element={<RegistrationPage />} />
        </Routes>
    </div>
  );
}

export default App;
