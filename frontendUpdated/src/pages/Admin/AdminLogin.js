import React from 'react';
import { Link } from "react-router-dom";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";

import './AdminLogin.css'; // Import the CSS file

export const AdminLogin = () => {
  const schema = yup.object().shape({
    email: yup.string().email().required(),
    password: yup.string().min(4).max(20).required(),
  });

  const { register, handleSubmit, formState: { errors } } = useForm({
    resolver: yupResolver(schema),
  });

  const onSubmit = (data) => {
    console.log(data);
    // You can implement further logic such as submitting the form data to the server for authentication
  };

  return (
    <div className="banner">
      <img src="./why_we01.png" alt="Background Image" />

      <div className="admin-login-content" id="admin-login">
        <div className="admin-login-form">
          <h2>Admin Login</h2>
          <form onSubmit={handleSubmit(onSubmit)}>
            <label htmlFor="email">Email:</label>
            <input type="email" id="email" placeholder="Email..." {...register("email")} />
            <p>{errors.email?.message}</p>

            <label htmlFor="password">Password:</label>
            <input type="password" id="password" placeholder="Password..." {...register("password")} />
            <p>{errors.password?.message}</p>

            <button type="submit">Login</button>
          </form>

      
        </div>
      </div>
    </div>
  );
};
