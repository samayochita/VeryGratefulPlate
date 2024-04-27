import React from "react";
import { useLocation } from "react-router-dom";
import dummyData from "./dummydata.json";
import styles from "./DeliveryPersonDashboard.module.css";
import { Header } from "../../components/Header";

export const DeliveryPersonDashboard = () => {
  // Retrieve the email from the route parameters
  const location = useLocation();
  const email = location.state?.email;

  // Find the user data based on the email
  const userData = dummyData.deliveryPersons.find(
    (person) => person.email === email
  );

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
                <strong>EMAIL:</strong> {userData.email}
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
      </div>
      </div>
    </div>
  );
};
