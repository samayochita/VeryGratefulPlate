import React, { useState } from "react";
import { Link } from "react-router-dom";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import axios from "axios"; // Import Axios
import styles from "./DeliveryPersonRegister.module.css";
import { Header } from "../../components/Header";
import { BASE_URL } from "../../services/helper";

export const DeliveryPersonRegister = () => {
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
    console.log("Form submitted with data:", data); 
    try {
      const response = await axios.post(`${BASE_URL}/api/deliverypersons/register`, data);
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
            <h2>Register As Pickup Person</h2>

            <label htmlFor="name">Name:</label>
            <input
              type="text"
              id="name"
              placeholder="Name..."
              {...register("name")}
            />
            <p>{errors.name?.message}</p>

            <label htmlFor="phoneNumber">Phone Number:</label>
            <input
              type="text"
              id="phoneNumber"
              placeholder="Phone Number..."
              {...register("phoneNumber")}
            />
            <p>{errors.phoneNumber?.message}</p>

            <label htmlFor="email_id">Email:</label>
            <input
              type="email"
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
              Already have an account? <Link to="/delivery-person-login">Login</Link>
            </p>
          </form>
        </div>
      ) : (
        <div className={styles.success_message_container}>
          <p>Account created successfully!</p>
          <br></br>
          <Link to="/delivery-person-login">
            <button>Login</button>
          </Link>
        </div>
      )}
    </div>
    </div>
  );
};
