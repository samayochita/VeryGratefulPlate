import React, { useState } from "react";
import { Link } from "react-router-dom";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import axios from "axios";
import styles from "./UserRegister.module.css";
import { Header } from "../../components/Header";
import { BASE_URL } from "../../services/helper";

export const UserRegister = () => {
  const schema = yup.object().shape({
    emailId: yup.string().email().required(),
    password: yup.string().min(4).max(20).required(),
    confirmPassword: yup
      .string()
      .oneOf([yup.ref("password"), null], "Passwords must match")
      .required(),
  });

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    resolver: yupResolver(schema),
  });

  const [registrationSuccess, setRegistrationSuccess] = useState(false);

  const onSubmit = async (data) => {
    try {
      data.userType = "USER";
      const response = await axios.post(`${BASE_URL}/api/users/register`, data);
      console.log(response);
      if (response.status === 200) {
        console.log("Account created successfully:", data);
        setRegistrationSuccess(true);
      }
    } catch (error) {
      console.error("Registration failed:", error);
      // Handle registration failure, e.g., display error message
    }
  };

  return (
    <div className={styles.container}>
      <div className={styles.navbar_container}>
        <Header />
      </div>
      <div className={styles.register_page}>
        {!registrationSuccess ? (
          <div>
            <form onSubmit={handleSubmit(onSubmit)}>
              <h2>Register As User</h2>

              <label htmlFor="emailId">Email:</label>
              <input
                type="emailId"
                id="emailId"
                placeholder="Email..."
                {...register("emailId")}
              />
              <p>{errors.email?.message}</p>

              <label htmlFor="password">Password:</label>
              <input
                type="password"
                id="password"
                placeholder="Password..."
                {...register("password")}
              />
              <p>{errors.password?.message}</p>

              <label htmlFor="confirmPassword">Confirm Password:</label>
              <input
                type="password"
                id="confirmPassword"
                placeholder="Confirm Password..."
                {...register("confirmPassword")}
              />
              <p>{errors.confirmPassword?.message}</p>

              <button type="submit">Register</button>
              <p className={styles.login_link}>
                Already have an account? <Link to="/user-login">Login</Link>
              </p>
            </form>
          </div>
        ) : (
          <div className={styles.success_message_container}>
            <p>Account created successfully!</p>
            <br></br>
            <Link to="/donate-food-form">
              <button>Login</button>
            </Link>
          </div>
        )}
      </div>
    </div>
  );
};
