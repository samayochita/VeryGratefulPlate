import React from 'react';
import { Link } from "react-router-dom";
import { Footer } from "../components/Footer";
import logoImg from '../files/Grateful Plate copy.png'; // Assuming this is your new logo image

export const GetInvolved = () => {
    return (
        <div className="banner">
            <img src="./why_we01.png" alt="Background Image" />

            <div className="content" id="get-involved">
                <nav>
                    <img src={logoImg} className="logo" alt="Grateful Plate" />
                    {/* No "Get Involved" option in Navbar */}
                </nav>

                <div className="title">
                    <h1>Get Involved</h1>
                    <p>How would you like to help?</p>
                    <div className="involvement-options">
                        <Link to="/user-login">
                            <button className="button"> User </button>
                        </Link>
                        <Link to="/admin-login">
                            <button className="button"> Admin </button>
                        </Link>
                        <Link to="/delivery-person-login">
                            <button className="button"> Delivery Person </button>
                        </Link>
                    </div>
                </div>
            </div>
        </div>

       
    );
};
