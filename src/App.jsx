import React from 'react';
    import { Routes, Route, Link } from 'react-router-dom';
    import Home from './pages/Home';
    import PumpingStation from './pages/PumpingStation';
    import Portes from './pages/portes';
  

    function App() {
      return (
        <div className="app">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/pumping-station" element={<PumpingStation />} />
            <Route path="/portes" element={<Portes />} />
            
          </Routes>
        </div>
      );
    }

    export default App;
