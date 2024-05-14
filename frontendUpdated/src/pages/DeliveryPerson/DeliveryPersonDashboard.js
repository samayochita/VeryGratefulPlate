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

  

  useEffect(() => {
    // Fetch the donation ID associated with the delivery person
    const fetchDonationId = async () => {
      try {
        const response = await axios.get(`${BASE_URL}/api/deliveryperson/${userData.id}/pendingdonations`);
        if (response.data && response.data.length > 0) {
          const newDonationId = response.data[0].donationId; // Access donationId correctly
          setDonationId(newDonationId);
          console.log("Donation ID:", newDonationId);
        }
      } catch (error) {
        console.error("Error fetching donation ID:", error);
      }
    };
    if (userData?.id) {
      fetchDonationId();
    }
  }, [userData?.id]);

  

  const handleDonationStatusChange = async () => {
    try {
      if (donationId) {
        const newStatus = donationStatus === "pending" ? "pickedup" : "delivered";
        await axios.put(`${BASE_URL}/api/deliveryperson/${userData.id}/donation/${donationId}/status`, { status: newStatus });
        setDonationStatus(newStatus);
      } else {
        console.error("Donation details not found for the delivery person");
      }
    } catch (error) {
      console.error("Error updating donation status:", error);
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
            {donationStatus === "pending" ? "Mark As pickedup" : "Mark as delivered"}
        </button>
        <button onClick={handleLogout}>Logout</button>
      </div>
      </div>
    </div>
  );
};
