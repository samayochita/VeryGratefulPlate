import React from 'react';
import { Link , useNavigate} from "react-router-dom";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import styles from './UserLogin.module.css'; // Import the CSS file
import { Header } from '../../components/Header';
import axios from "axios";
import { useState } from "react";
import { BASE_URL } from "../../services/helper";

export const UserLogin = () => {
  const schema = yup.object().shape({
    emailId: yup.string().email().required(),
    password: yup.string().min(4).max(20).required(),
  });

  const { register, handleSubmit, formState: { errors } } = useForm({
    resolver: yupResolver(schema),
  });

  const navigate = useNavigate();
  const [loginError, setLoginError] = useState("");

  const onSubmit = async (data) => {
    try {
      const response = await axios.post(`${BASE_URL}/api/users/login`, data);
      console.log(response);
      if (response.status === 200) {
        console.log("Login successful:", response.data);

        // Store user ID in localStorage
        localStorage.setItem('userId', parseInt(response.data.userId));

        navigate("/donate-food-form");
      }
    } catch (error) {
      console.error("Login failed:", error);
      setLoginError("Login failed. Please check your credentials.");
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
            <h2>User Login</h2>
            <form onSubmit={handleSubmit(onSubmit)}>
              <label htmlFor="emailId">Email:</label>
              <input type="email" id="emailId" placeholder="Email..." {...register("emailId")} />
              <p>{errors.email?.message}</p>

              <label htmlFor="password">Password:</label>
              <input type="password" id="password" placeholder="Password..." {...register("password")} />
              <p>{errors.password?.message}</p>
              <button type="submit" >LOGIN</button>
            </form>
            {loginError && <p className={styles.error_message}>{loginError}</p>}
            <p className={styles.register_link}>
              Don't have an account? <Link to="/user-register">Register</Link>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};
