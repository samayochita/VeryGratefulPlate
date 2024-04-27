import React from 'react';
import { Link } from "react-router-dom";
import logoImg from '../files/Grateful Plate copy.png'; // Assuming this is your new logo image
import { GetInvolved } from './GetInvolved';

export const Home = () => {
    return (
    <div className="banner">
      <img src="./why_we01.png" alt="Background Image" />

      <div className="content" id="home">
        <nav>
          <img src={logoImg} className="logo" alt="Grateful Plate" />

          <ul className="navbar">
            <li>
              {/* Use Link component for navigation within React */}
              <Link to="/">Home</Link>
              <Link to="/who-we-are">Who We Are</Link>
              <Link to="/why-we-exist">Why We Exist</Link>
              <Link to="/what-we-do">What We Do</Link>
              <Link to="/our-team">Our Team</Link>
              <Link to="/contact-us">Contact Us</Link>
            </li>
          </ul>
        </nav>

        <div className="title">
          <h1>Grateful Plate</h1>
          <p>"There is food for everyone on this planet, but not everyone eats." - Carlo Petrini.</p>
          {/* Use button component for styling and functionality */}
          <button className="button">
          <Link to="/get-involved">Get Involved!</Link>
          </button>
         
        </div>
    
      </div>
     
    </div>
    
    );
};

