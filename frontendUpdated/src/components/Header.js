import React from 'react';
import { Link } from "react-router-dom";
import logoImg from '../files/Grateful Plate copy.png'; // Assuming this is your new logo image

export const Header = () => {
  return (
    // <div className="content" id="home">
      <nav>
         <img src={logoImg} className="logo" alt="Grateful Plate" /> 

        <ul className="navbar">
          <li>
            {/* Use Link component for navigation within React */}
            <Link to="/">Home</Link>
            <Link to="/get-involved">Get Involved</Link>
            <Link to="/who-we-are">Who We Are</Link>
            <Link to="/what-we-do">What We Do</Link>
            <Link to="/our-team">Our Team</Link>
            <Link to="/contact-us">Contact Us</Link>
          </li>
        </ul>
      </nav>
    // </div>
  );
};


