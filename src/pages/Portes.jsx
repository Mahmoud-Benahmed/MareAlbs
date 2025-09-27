import React, { useState, useEffect } from 'react';
    import { Link } from 'react-router-dom';
    import './Portes.css';

    const portes = () => {
      const [currentDateTime, setCurrentDateTime] = useState(new Date());
      const [doorStates, setDoorStates] = useState({
        alwan: 0,
        porteAgricole: 0,
        e4: 0,
        y: 0
      });
      const [initialStates, setInitialStates] = useState({ ...doorStates });

      useEffect(() => {
        const timer = setInterval(() => {
          setCurrentDateTime(new Date());
        }, 1000);
        return () => clearInterval(timer);
      }, []);

      const handleDoorChange = (door, value) => {
        setDoorStates(prev => ({
          ...prev,
          [door]: value
        }));
      };

      const handleIgnore = () => {
        setDoorStates({ ...initialStates });
      };

      const handleSave = () => {
        setInitialStates({ ...doorStates });
        alert('Etats de portes enregistrés avec succés');
      };

      return (
        <div className="portes">
          <header>
            <Link to="/" className="home-link">🏠</Link>
            <h1>Portes</h1>
          </header>

          <div className="content">
            <div className="datetime">
              {currentDateTime.toLocaleString()}
            </div>

            <div className="door-controls">
              {Object.entries(doorStates).map(([door, value]) => (
                <div key={door} className="door">
                  <label>{door.replace(/([A-Z])/g, ' $1').toUpperCase()}:</label>
                  <div className="buttons">
                    {[0, 50, 100].map(percent => (
                      <button
                        key={percent}
                        className={value === percent ? 'active' : ''}
                        onClick={() => handleDoorChange(door, percent)}
                      >
                        {percent}%
                      </button>
                    ))}
                  </div>
                </div>
              ))}
            </div>

            <div className="actions">
              <button className="ignore" onClick={handleIgnore}>
                Ignorer
              </button>
              <button className="save" onClick={handleSave}>
                Sauvegarder
              </button>
            </div>
          </div>
        </div>
      );
    };

    export default portes;
