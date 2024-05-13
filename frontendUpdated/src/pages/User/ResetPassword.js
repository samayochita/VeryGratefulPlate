import React, { useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import styles from './ResetPassword.module.css';
import axios from 'axios';
import { BASE_URL } from '../../services/helper';

export const ResetPassword = () => {
  const { emailId } = useParams();
  const [newPassword, setNewPassword] = useState('');
  const [resetMessage, setResetMessage] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await axios.post(`${BASE_URL}/resetpassword`, { emailId, newPassword });
      setResetMessage('Password reset successful. You can now login with your new password.');
    } catch (error) {
      console.error('Password reset failed:', error);
      setResetMessage('Failed to reset password. Please try again later.');
    }
  };

  return (
    <div className={styles.container}>
      <h2>Reset Password</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="password">Enter your new password:</label>
        <input type="password" id="password" value={newPassword} onChange={(e) => setNewPassword(e.target.value)} required />
        <button type="submit">Reset Password</button>
      </form>
      {resetMessage && <p className={styles.reset_message}>{resetMessage}</p>}
      <p className={styles.back_to_login}>
        Remember your password? <Link to="/user-login">Back to Login</Link>
      </p>
    </div>
  );
};
