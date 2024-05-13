import React from 'react';
import './DonateForm.css'; // Import the CSS file
import axios from 'axios';
import { Header } from '../../components/Header';
import { BASE_URL } from "../../services/helper";
import { useState } from "react";
import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';


export const DonateFoodForm = () => {
  const navigate = useNavigate();
  const [userId, setUserId] = useState("");

  useEffect(() => {
    // Fetch user ID from localStorage
    const storedUserId = localStorage.getItem('userId');
    if (storedUserId) {
      setUserId(parseInt(storedUserId));
    }
  }, []);

  const handleSubmit = async (event) => {
    event.preventDefault();
     // Get selected radio button value for category
    const categoryInput = document.querySelector('input[name="category"]:checked');
    const category = categoryInput ? categoryInput.value : ''; // If no radio button is selected, set category to an empty string
    // Get donation data from form inputs
    const formData = new FormData(event.target);
    const donationData = {
      user: {
        userId: userId, // Use userId instead of emailId
      },
      donorName: formData.get('donorName'),
      donorPhoneNumber: formData.get('donorPhoneNumber'),
      pickupAddress: formData.get('pickupAddress'),
      category: category, // Ensure category is sent as a string
      quantityInKg: parseFloat(formData.get('quantityInKg')),
      foodPreparationDateTime: formData.get('foodPreparationDateTime'),
      additionalNotes: formData.get('additionalNotes'),
      donationStatus: 'picked up',
    };


    try {
      const response = await axios.post(`${BASE_URL}/api/donations/add`, donationData);
      console.log(response.data);
      // Handle success
    } catch (error) {
      console.error('Error:', error);
      // Handle error
    }
  };

  const handleLogout = async () => {
    try {
      // Call backend logout endpoint
      const response = await axios.post(`${BASE_URL}/api/users/logout`);
      console.log(response.data);
      // Handle logout success
      // Clear localStorage or perform any other necessary cleanup
      localStorage.clear();
      navigate("/");
    } catch (error) {
      console.error('Error:', error);
      // Handle logout error
    }
  };
  return (
    
    <section className="registration">  
    <div className="register-form">
        <h1>Donate <span>Food</span></h1>
        <p>It's not how much we give, but how much love we put into giving!</p>

        <form onSubmit={handleSubmit}>
        <input type="text" name="donorName" placeholder="Your Name" id="donorName" required />
        <input type="tel" name="donorPhoneNumber" placeholder="Your Phone No." id="donorPhoneNumber" required />
        <input type="text" name="pickupAddress" placeholder="Collection Address" id="pickupAddress" required />

        <h4>Category</h4>
        <label><input type="radio" name="category" value="Veg" required /> Veg</label>
        <label><input type="radio" name="category" value="Non-Veg" /> Non-Veg</label>
        <label><input type="radio" name="category" value="Both" /> Both</label>

        <h4>Quantity (in Kg)</h4>
        <input type="number" name="quantityInKg" id="quantityInKg" />

        <h4>Food Preparation Date and Time</h4>
        <input type="datetime-local" name="foodPreparationDateTime" id="foodPreparationDateTime" required />

        <textarea name="additionalNotes" id="additionalNotes" cols="30" rows="3" placeholder="Special Note"></textarea>

        <input type="submit" value="Submit" className="submitbtn" />
        <button type="button" onClick={handleLogout}>Logout</button>
        </form>
    </div>
    </section>

  );
};

