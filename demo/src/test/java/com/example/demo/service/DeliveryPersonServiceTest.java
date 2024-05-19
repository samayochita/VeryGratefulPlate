package com.example.demo.service;
import com.example.demo.model.DeliveryPerson;
import com.example.demo.model.Donation;
import com.example.demo.repository.DeliveryPersonRepository;
import com.example.demo.repository.DonationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DeliveryPersonServiceTest {

    @Mock
    private DeliveryPersonRepository deliveryPersonRepository;

    @Mock
    private DonationRepository donationRepository;

    @InjectMocks
    private DeliveryPersonService deliveryPersonService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSaveDeliveryPerson() {
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        // Set delivery person properties

        when(deliveryPersonRepository.save(deliveryPerson)).thenReturn(deliveryPerson);

        DeliveryPerson result = deliveryPersonService.saveDeliveryPerson(deliveryPerson);

        assertEquals(deliveryPerson, result);
    }

    @Test
    public void testFindByEmailId() {
        String email = "test@example.com";
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        // Set delivery person properties
        deliveryPerson.setEmailId(email);

        when(deliveryPersonRepository.findByEmailId(email)).thenReturn(deliveryPerson);

        DeliveryPerson result = deliveryPersonService.findByEmailId(email);

        assertEquals(deliveryPerson, result);
    }

    @Test
    public void testGetPendingDonationsForDeliveryPerson() {
        Long deliveryPersonId = 1L;
        List<Donation> pendingDonations = new ArrayList<>();
        // Add pending donations to the list

        when(donationRepository.findPendingDonationsByDeliveryPersonId(deliveryPersonId)).thenReturn(pendingDonations);

        List<Donation> result = deliveryPersonService.getPendingDonationsForDeliveryPerson(deliveryPersonId);

        assertEquals(pendingDonations, result);
    }
}