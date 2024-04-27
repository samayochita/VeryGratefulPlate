import React from 'react';

import { Link } from "react-router-dom";

export const Footer = () => {
  return (
    <section className="footer">
      <div className="foot">
        <div className="footer-content">
          <div className="footlinks">
            <h4>Quick Links</h4>
            <ul>
              {/* Use Link component for navigation within React */}
              <li><Link to="/donate">Donate</Link></li>
              <li><Link to="/about-us">About Us</Link></li>
              <li><Link to="/contact-us">Contact Us</Link></li>
            </ul>
          </div>

          <div className="footlinks">
            <h4>Connect</h4>
            <div className="social">
              <a href="https://www.linkedin.com/"><img src="../files/linkedin.png" alt="LinkedIn" /></a>
              <a href="https://www.instagram.com/"><img src="../files/instagram.png" alt="Instagram" /></a>
              <a href="https://twitter.com/"><img src="../files/twitter.png" alt="Twitter" /></a>
              <a href="https://www.facebook.com/"><img src="../files/facebook.png" alt="Facebook" /></a>
              <a href="https://www.youtube.com/"><img src="../files/youtube.png" alt="YouTube" /></a>
              </div>
          </div>

        </div>
      </div>

      <div className="end">
        <p>Tel: 1800-00-000 Email: support@gratefulplate.com | Copyright © 2023 Gratefulplate | All Rights Reserved. Website developed by:  Samayochita | Riya Joshi </p>
      </div>
    </section>
  );
};
