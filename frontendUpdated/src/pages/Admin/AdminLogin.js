import React from 'react';
import { Link , useNavigate} from "react-router-dom";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import * as yup from "yup";
import { BASE_URL } from '../../services/helper';
import axios from 'axios';

import './AdminLogin.css'; // Import the CSS file

export const AdminLogin = () => {
  const schema = yup.object().shape({
    emailId: yup.string().email().required(),
    password: yup.string().min(4).max(20).required(),
  });

  const { register, handleSubmit, formState: { errors } } = useForm({
    resolver: yupResolver(schema),
  });
  
  
  const navigate = useNavigate();
  const onSubmit = async (data) => {
    try {
      // Add the userType to the data
      data.userType = "ADMIN";

      // Make the POST request to the /loginadmin endpoint
      const response = await fetch('http://localhost:9191/api/users/loginadmin', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
      });

      // const response = await axios.post(`${BASE_URL}/api/users/loginadmin`, data);
      // console.log(response);

      const result = await response.json();

      if (response.ok) {
        // Handle successful login
        console.log('Login successful:', result);
        navigate("/admin-dashboard");
        
      } else {
        // Handle login failure
        console.error('Login failed:', result);
        alert(result);
      }
    } catch (error) {
      console.error('An error occurred:', error);
    }
  };

  return (
    <div className="banner">
      <img src="./why_we01.png" alt="Background Image" />

      <div className="admin-login-content" id="admin-login">
        <div className="admin-login-form">
          <h2>Admin Login</h2>
          <form onSubmit={handleSubmit(onSubmit)}>
            <label htmlFor="emailId">Email:</label>
            <input type="emailId" id="emailId" placeholder="Email..." {...register("emailId")} />
            <p>{errors.emailId?.message}</p>

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
