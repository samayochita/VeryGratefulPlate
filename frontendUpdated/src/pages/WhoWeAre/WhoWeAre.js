import { Header } from "../../components/Header";
import styles from "./WhoWeAre.module.css";

export const WhoWeAre = () => {
  return (
    <div className={styles.container}>
      <div className={styles.navbar_container}>
        <Header />
      </div>
      <div className={styles.hero_container}>

          <div className={styles.content_container}>
            <h1>HELPING HANDS FOR PEOPLE IN NEED</h1>

            <p>
              At Grateful Plate, our vision is to create a community where no
              meal goes to waste and everyone has access to the food they need.
              Our mission is to collect surplus and donated food from local
              businesses, farms, and individuals, and distribute it to those
              facing food insecurity. We aim to bridge the gap between abundance
              and need by efficiently coordinating food donations to reach our
              neighbors who are struggling. Through careful handling and swift
              delivery, we ensure that high-quality food makes its way to the
              people who need it most. Our dedicated team works tirelessly to
              uphold the values of generosity, compassion, and sustainability as
              we strive to alleviate hunger and nourish our community.
            </p>
          </div>


        <div className={styles.card}>
          <div className={styles.card_content}>
            <img
              src="./donation1.avif"
              alt="donation1"
              className={styles.card_image}
            />
          </div>

          <div className={styles.card_content}>
            <img
              src="./donation2.jpg"
              alt="donation2"
              className={styles.card_image}
            />
          </div>

          <div className={styles.card_content}>
            <img
              src="./donation3.jpg"
              alt="donation3"
              className={styles.card_image}
            />
          </div>

          <div className={styles.card_content}>
            <img
              src="./donation4.jpg"
              alt="donation4"
              className={styles.card_image}
            />
          </div>

          <div className={styles.card_content}>
            <img
              src="./donation5.jpg"
              alt="donation5"
              className={styles.card_image}
            />
          </div>
        </div>
      </div>
    </div>
  );
};
