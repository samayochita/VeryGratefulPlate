package com.example.demo.controller;
import com.example.demo.controllers.DeliveryPersonController;
import com.example.demo.model.DeliveryPerson;
import com.example.demo.model.Donation;
import com.example.demo.service.DeliveryPersonService;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeliveryPersonControllerTest {


    @Mock
    private DeliveryPersonService deliveryPersonService;

    @InjectMocks
    private DeliveryPersonController deliveryPersonController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegisterDeliveryPerson_Success() {
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        // Set delivery person properties

        when(deliveryPersonService.saveDeliveryPerson(deliveryPerson)).thenReturn(deliveryPerson);

        ResponseEntity<Object> response = deliveryPersonController.registerDeliveryPerson(deliveryPerson);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(deliveryPerson, response.getBody());
    }

    @Test
    public void testRegisterDeliveryPerson_Failure() {
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        // Set delivery person properties

        when(deliveryPersonService.saveDeliveryPerson(deliveryPerson)).thenThrow(new RuntimeException());

        ResponseEntity<Object> response = deliveryPersonController.registerDeliveryPerson(deliveryPerson);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to register delivery person", response.getBody());
    }

    @Test
    public void testLoginDeliveryPerson_Success() {
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        // Set delivery person properties
        String email = "test@example.com";
        String password = "password";
        deliveryPerson.setEmailId(email);
        deliveryPerson.setPassword(password);

        when(deliveryPersonService.findByEmailId(email)).thenReturn(deliveryPerson);

        ResponseEntity<Object> response = deliveryPersonController.loginDeliveryPerson(deliveryPerson);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(deliveryPerson, response.getBody());
    }

    @Test
    public void testLoginDeliveryPerson_Failure_InvalidCredentials() {
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        // Set delivery person properties
        String email = "test@example.com";
        String password = "password";
        deliveryPerson.setEmailId(email);
        deliveryPerson.setPassword(password);

        when(deliveryPersonService.findByEmailId(email)).thenReturn(null);

        ResponseEntity<Object> response = deliveryPersonController.loginDeliveryPerson(deliveryPerson);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid email or password", response.getBody());
    }

    @Test
    public void testLoginDeliveryPerson_Failure_PasswordMismatch() {
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        // Set delivery person properties
        String email = "test@example.com";
        String password = "password";
        deliveryPerson.setEmailId(email);
        deliveryPerson.setPassword(password);

        DeliveryPerson storedDeliveryPerson = new DeliveryPerson();
        storedDeliveryPerson.setEmailId(email);
        storedDeliveryPerson.setPassword("wrongpassword");

        when(deliveryPersonService.findByEmailId(email)).thenReturn(storedDeliveryPerson);

        ResponseEntity<Object> response = deliveryPersonController.loginDeliveryPerson(deliveryPerson);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid email or password", response.getBody());
    }

    @Test
    public void testGetPendingDonationsForDeliveryPerson_Success() {
        Long deliveryPersonId = 1L;
        List<Donation> pendingDonations = new ArrayList<>();
        // Add pending donations to the list

        when(deliveryPersonService.getPendingDonationsForDeliveryPerson(deliveryPersonId)).thenReturn(pendingDonations);

        ResponseEntity<List<Donation>> responseEntity = deliveryPersonController.getPendingDonationsForDeliveryPerson(deliveryPersonId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(pendingDonations, responseEntity.getBody());
    }

    @Test
    public void testGetPendingDonationsForDeliveryPerson_NotFound() {
        Long deliveryPersonId = 1L;

        when(deliveryPersonService.getPendingDonationsForDeliveryPerson(deliveryPersonId)).thenReturn(null);

        ResponseEntity<List<Donation>> responseEntity = deliveryPersonController.getPendingDonationsForDeliveryPerson(deliveryPersonId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
