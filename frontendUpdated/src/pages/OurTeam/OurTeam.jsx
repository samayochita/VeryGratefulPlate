import styles from "./OurTeam.module.css";
import { Header } from "../../components/Header";

export const OurTeam = () => {
  return (
    <div className={styles.container}>
      <div className={styles.navbar_container}>
        <Header />
      </div>
      <div className={styles.hero_container}>
        <div className={styles.headingTeam}>
          <h1>Our Team</h1>
        </div>
        <div className={styles.card}>
          <div className={styles.card_content}>
            <img
              src="./member1.jpg"
              alt="donation1"
              className={styles.card_image}
            />
            <h1>Michael Chen</h1>
            <h3>Senior Software Engineer</h3>
            <p>
              Michael is a highly skilled software engineer with expertise in
              full-stack development. With a master's degree in computer science
              and several years of experience in the tech industry, he excels at
              designing and implementing complex software solutions. Michael is
              proficient in multiple programming languages and frameworks,
              allowing him to adapt to new technologies quickly. 
            </p>
          </div>

          <div className={styles.card_content}>
            <img
              src="./member2.jpg"
              alt="donation2"
              className={styles.card_image}
            />
            <h1>Emily Johnson</h1>
            <h3>Human Resources Manager</h3>
            <p>
              Emily is a dedicated HR professional with a passion for fostering
              a positive work environment. With a background in psychology and
              organizational behavior, she understands the importance of
              employee engagement and morale. Emily oversees various HR
              functions, including recruitment, onboarding, performance
              management, and employee relations. She is committed to supporting
              the professional growth and well-being of all employees.
            </p>
          </div>

          <div className={styles.card_content}>
            <img
              src="./member3.jpg"
              alt="donation3"
              className={styles.card_image}
            />
            <h1>David Rodriguez</h1>
            <h3>Financial Analyst</h3>
            <p>
              David is a detail-oriented financial analyst with expertise in
              financial modeling and data analysis. With a bachelor's degree in
              finance and accounting, he possesses a strong understanding of
              financial principles and practices. David plays a critical role in
              helping the company make informed decisions by providing accurate
              financial forecasts and insights. He is proficient in using
              financial software and tools to analyze trends, identify risks,
              and optimize financial performance. 
            </p>
          </div>

          <div className={styles.card_content}>
            <img
              src="./member4.jpeg"
              alt="donation4"
              className={styles.card_image}
            />
            <h1>Alex Patel</h1>
            <h3>Product Manager</h3>
            <p>
              Alex is a dynamic product manager with a passion for innovation
              and customer-centric design. With a background in product
              development and project management, he excels at leading
              cross-functional teams to deliver successful product launches.
              Alex has a keen understanding of market trends and user needs,
              allowing him to prioritize features and enhancements that drive
              customer satisfaction and business value. 
            </p>
          </div>

          <div className={styles.card_content}>
            <img
              src="./member5.jpg"
              alt="donation5"
              className={styles.card_image}
            />
            <h1>Ryan Reynolds</h1>
            <h3>Marketing Manager</h3>
            <p>
              Ryan is a seasoned marketing professional with over a decade of
              experience in digital marketing and branding. He has a knack for
              developing creative campaigns that resonate with target audiences
              and drive engagement. With his strong leadership skills, he
              effectively manages a team of marketing specialists and ensures
              that marketing strategies align with the company's goals. Ryan is
              known for his innovative ideas and strategic thinking.
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};
