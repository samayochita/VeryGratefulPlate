import React from "react";
import { useLocation , useNavigate} from "react-router-dom";
import styles from "./DeliveryPersonDashboard.module.css";
import { Header } from "../../components/Header";
import axios from "axios";
import { BASE_URL } from "../../services/helper";

export const DeliveryPersonDashboard = () => {
  // Retrieve the email from the route parameters
  const location = useLocation();

  // Find the user data based on the email
  const userData = location.state?.userData;
  const navigate = useNavigate(); 

  const handleLogout = async () => {
    try {
      // Call backend logout endpoint
      await axios.post(`${BASE_URL}/api/deliveryperson/logout`);
      // Redirect to the homepage
      navigate("/");
    } catch (error) {
      console.error('Error logging out:', error);
      // Handle logout error
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
        <button onClick={handleLogout}>Logout</button>
      </div>
      </div>
    </div>
  );
};
