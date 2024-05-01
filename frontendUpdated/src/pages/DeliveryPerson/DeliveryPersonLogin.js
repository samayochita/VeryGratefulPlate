import React from 'react';
import { Link, useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import axios from "axios";
import styles from "./DeliveryPersonLogin.module.css";
import { Header } from '../../components/Header';
import { BASE_URL } from "../../services/helper";

export const DeliveryPersonLogin = () => {
  const schema = yup.object().shape({
    emailId: yup.string().email().required(),
    password: yup.string().min(4).max(20).required(),
  });

  const { register, handleSubmit, formState: { errors } } = useForm({
    resolver: yupResolver(schema),
  });

  const navigate = useNavigate();
  const [error, setError] = React.useState('');

  const onSubmit = async (data) => {
    try {
      const response = await axios.post(`${BASE_URL}/api/deliverypersons/login`, data);
      if (response.status === 200) {
        const { emailId } = response.data;
        navigate("/delivery-person-dashboard", { state: { email: emailId , userData: response.data} });
      }
    } catch (error) {
      setError('Invalid email or password');
      console.error("Login failed:", error);
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.navbar_container}>
        <Header />
      </div>
      <div className={styles.banner}>
        <div className={styles.user_login_content} id="user-login">
          <div className={styles.user_login_form}>
            <h2>Pickup Person Login</h2>
            <form onSubmit={handleSubmit(onSubmit)}>
              <label htmlFor="emailId">Email:</label>
              <input type="email" id="emailId" placeholder="Email..." {...register("emailId")} />
              <p>{errors.emailId?.message}</p>

              <label htmlFor="password">Password:</label>
              <input type="password" id="password" placeholder="Password..." {...register("password")} />
              <p>{errors.password?.message}</p>
              <button type="submit">LOGIN</button>
            </form>

            <p className={styles.register_link}>
              Don't have an account? <Link to="/delivery-person-register">Register</Link>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};
