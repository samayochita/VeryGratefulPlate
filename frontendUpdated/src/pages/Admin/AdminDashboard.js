import React, { useEffect, useState } from 'react';
import styles from './AdminDashboard.module.css'
import { BASE_URL } from "../../services/helper";

export const AdminDashboard = () => {
    const [donations, setDonations] = useState([]);

  useEffect(() => {
    const fetchDonations = async () => {
      try {
        const response = await fetch(`${BASE_URL}/api/donations/userdonations?userId=1&userType=ADMIN`); // Assuming admin userId is 1
        if (!response.ok) {
          throw new Error('Failed to fetch donations');
        }
        const data = await response.json();
        setDonations(data);
      } catch (error) {
        console.error('Error fetching donations:', error);
      }
    };

    fetchDonations();
  }, []);

  return (
    <div className={styles.dashboard}>
      <h1>Donations Dashboard</h1>
      <div className={styles.donations_grid}>
        {donations.map((donation) => (
          <div className={styles.donation_card} key={donation.donationId}>
            <h2>{donation.donorName}</h2>
            <p><strong>Phone Number:</strong> {donation.donorPhoneNumber}</p>
            <p><strong>Pickup Address:</strong> {donation.pickupAddress}</p>
            <p><strong>Category:</strong> {donation.category}</p>
            <p><strong>Quantity:</strong> {donation.quantityInKg} kg</p>
            <p><strong>Preparation Date:</strong> {new Date(donation.foodPreparationDateTime).toLocaleDateString()}</p>
            <p><strong>Additional Notes:</strong> {donation.additionalNotes}</p>
            <p><strong>Status:</strong> {donation.donationStatus}</p>
          </div>
        ))}
      </div>
    </div>
  );
};