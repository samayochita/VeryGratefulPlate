import { Header } from "../../components/Header";
import styles from "./WhatWeDo.module.css";

export const WhatWeDo = () => {
  return (
    <div className={styles.top_container}>
      <div className={styles.navbar_container}>
        <Header />
      </div>
      <div className={styles.hero_container}>

        <div className={styles.heading}>
          <h1>Feed the Soul, Fill the Stomach</h1>
        </div>

        <div className={styles.container}>

          <div className={styles.collect_food}>
            <h1>Take Food</h1>
            <p>
            At Grateful Plate, we are dedicated to ensuring that donated food is of the highest quality before distributing it to those in need. 
            We gladly accept food donations from individuals and businesses, but we first verify the quality of the donations to guarantee freshness and safety. 
            If the food is in good condition and suitable for consumption, we accept the donation and promptly dispatch one of our pickup team members to collect it. 
            This careful process allows us to provide wholesome meals to those facing food insecurity while maintaining high standards of quality and safety.
            </p>
            <img src="./donation_food.jpg" alt="donation" className={styles.card_image} />
          </div>

          <div className={styles.donate_food}>
            <h1>Give Food</h1>
            <p>
            Once we have received and verified the donated meals, our dedicated teams set out to bring nourishment directly to those who need it most. 
            We visit nearby slums and underprivileged neighborhoods, where many families face daily struggles to access enough food. 
            Our team members work tirelessly to distribute meals and provide a sense of relief and hope to those who are struggling. 
            By delivering fresh, wholesome meals directly to the community, we strive to make a meaningful impact and alleviate hunger one meal at a time. 
            
            </p>
            <img src="./distribution_food.jpg" alt="distribution" className={styles.card_image} />
          </div>

        </div>
      </div>
      <div className={styles.footer}></div>
    </div>
  );
};
