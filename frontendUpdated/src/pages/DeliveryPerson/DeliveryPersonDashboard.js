import React, {useState, useEffect} from "react";
import { useLocation , useNavigate} from "react-router-dom";
import styles from "./DeliveryPersonDashboard.module.css";
import { Header } from "../../components/Header";
import axios from "axios";
import { BASE_URL } from "../../services/helper";

export const DeliveryPersonDashboard = () => {
  
  const location = useLocation();
  const userData = location.state?.userData;
  const [donationId, setDonationId] = useState(null);
  const [donationStatus, setDonationStatus] = useState(userData?.status);
  const navigate = useNavigate(); 

  console.log(userData.id);

  useEffect(() => {
    // Fetch all donations associated with the delivery person's userId
    const fetchUserDonations = async () => {
      try {
        const response = await axios.get(`${BASE_URL}/api/donations/userdonations?userId=${userData.id}&userType=DELIVERY_PERSON`);
        // Assuming the first donation is the most recent one
        if (response.data.length > 0) {
          setDonationId(response.data[0].donationId);
        }
      } catch (error) {
        console.error('Error fetching donation details:', error);
        // Handle error
      }
    };

    fetchUserDonations();
  }, [userData]);


  const handleDonationStatusChange = async () => {
    try {
      if (donationId) {
        const newStatus = donationStatus === "pickedup" ? "delivered" : "pickedup";
        await axios.put(`${BASE_URL}/api/donations/${donationId}/${newStatus}`);
        setDonationStatus(newStatus);
      } else {
        console.error('Donation details not found for the delivery person');
      }
    } catch (error) {
      console.error('Error updating donation status:', error);
    }
  };
  const handleLogout = async () => {
    try {
      // Call backend logout endpoint
      await axios.post(`${BASE_URL}/api/deliveryperson/logout`);
      // Redirect to the homepage
      navigate("/");
    } catch (error) {
      console.error('Error logging out:', error);
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.navbar_container}>
        <Header />
      </div>
      <div className={styles.hero_container}>
      <div className={styles.headingTeam}>
        <h1>Welcome to the Dashboard</h1>
      </div>
      <div className={styles.dashboard_content}>
        <div className={styles.user_info}>
          <h3>User Information</h3>
          {userData && (
            <div>
              <p>
                <strong>EMAIL:</strong> {userData.emailId}
              </p>
              <p>
                <strong>NAME:</strong> {userData.name}
              </p>
              <p>
                <strong>PHONE NUMBER:</strong> {userData.phoneNumber}
              </p>
              <p>
                <strong>STATUS:</strong> {userData.status}
              </p>
            </div>
          )}
        </div>
        <button onClick={handleDonationStatusChange}>
            {donationStatus === "pickedup" ? "Mark as Delivered" : "Mark as Picked Up"}
        </button>
        <button onClick={handleLogout}>Logout</button>
      </div>
      </div>
    </div>
  );
};
