import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import './App.css';
import {Home} from './pages/Home';
import {WhatWeDo} from './pages/WhatWeDo/WhatWeDo';
import {GetInvolved} from './pages/GetInvolved';
import {WhoWeAre} from './pages/WhoWeAre/WhoWeAre';
import {ContactUs} from './pages/ContactUs/ContactUs';
import {AdminLogin} from './pages/Admin/AdminLogin';
import {AdminDashboard} from './pages/Admin/AdminDashboard';
import {UserLogin} from './pages/User/UserLogin';
import {UserRegister} from './pages/User/UserRegister';
import {DonateFoodForm} from './pages/User/DonateFoodForm';
import {AdminFoodRequests} from './pages/Admin/AdminFoodRequests';
import {DeliveryPersonLogin} from './pages/DeliveryPerson/DeliveryPersonLogin';
import {DeliveryPersonDashboard} from './pages/DeliveryPerson/DeliveryPersonDashboard';
import {DeliveryPersonPickup } from './pages/DeliveryPerson/DeliveryPersonPickup';
import {DeliveryPersonOrders} from './pages/DeliveryPerson/DeliveryPersonOrders';
import {OurTeam} from './pages/OurTeam/OurTeam';
import { DeliveryPersonRegister } from './pages/DeliveryPerson/DeliveryPersonRegister';

function App() {
  return (
    <div className="App">
      <Router>
  
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/what-we-do" element={<WhatWeDo />} />
          <Route path="/get-involved" element={<GetInvolved />} />
          <Route path="/who-we-are" element={<WhoWeAre />} />
          <Route path="/contact-us" element={<ContactUs />} />
          <Route path="/admin-login" element={<AdminLogin />} />
          <Route path="/admin-dashboard" element={<AdminDashboard />} />
          <Route path="/admin-food-requests" element={<AdminFoodRequests />} />
          <Route path="/user-login" element={<UserLogin />} />
          <Route path="/user-register" element={<UserRegister />} />
          <Route path="/donate-food-form" element={<DonateFoodForm />} />
          <Route path="/admin-dashboard" element={<AdminDashboard />} />
          <Route path="/admin/admin-food-requests" element={<AdminFoodRequests />} />
          <Route path="/delivery-person-login" element={<DeliveryPersonLogin />} />
          <Route path="/delivery-person-register" element={<DeliveryPersonRegister />} />
          <Route path="/delivery-person-dashboard" element={<DeliveryPersonDashboard />} />
          <Route path="/delivery-person-orders" element={<DeliveryPersonOrders />} />
          <Route path="/delivery-person-pickup" element={<DeliveryPersonPickup />} />
          <Route path="/our-team" element={<OurTeam/>} />
         
          <Route path="*" element={<h1>Page Not Found</h1>} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
