import React from 'react';
import { Link } from 'react-router-dom';
import './Home.css';

export default function Home() {
  return (
    <div className="home">
      <div className="home-content">
        <h1>Centre de contrôle des Marealbs</h1>
        <p className="home-subtitle">
         Gérez et surveillez vos opérations avec précision et facilité. Accédez aux données en temps réel et aux systèmes de contrôle depuis n’importe où.
        </p>
        
        <div className="buttons-grid">
          <Link to="/pumping-station" className="action-card">
            <div className="action-icon">💧</div>
            <div className="action-title">Station de Pompage</div>
            <div className="action-description">
              Surveiller et contrôler les opérations de pompage d’eau
            </div>
          </Link>
          
          <Link to="/portes" className="action-card">
            <div className="action-icon">🚪</div>
            <div className="action-title">Les Portes</div>
            <div className="action-description">
           Gérer les points d'accès et les opérations de portail
            </div>
          </Link>
        </div>
      </div>
    </div>
    
  );
}
