package com.example.demo.service;

import com.example.demo.model.DeliveryPerson;
import com.example.demo.model.Donation;
import com.example.demo.repository.DeliveryPersonRepository;
import com.example.demo.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryPersonService {

    private final DonationRepository donationRepository;
    private final DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    public DeliveryPersonService(DonationService donationService, DeliveryPersonRepository deliveryPersonRepository, DonationRepository donationRepository) {
        this.deliveryPersonRepository = deliveryPersonRepository;
        this.donationRepository = donationRepository;
    }

    public DeliveryPerson saveDeliveryPerson(DeliveryPerson deliveryPerson) {
        return deliveryPersonRepository.save(deliveryPerson);
    }
    public DeliveryPerson findByEmailId(String email) {
        return deliveryPersonRepository.findByEmailId(email);
    }

    public List<Donation> getPendingDonationsForDeliveryPerson(Long deliveryPersonId) {
        // Retrieve pending donations associated with the delivery person ID
        return donationRepository.findPendingDonationsByDeliveryPersonId(deliveryPersonId);
    }
    public DeliveryPerson findByDeliveryPersonId(Long deliveryPersonId){
        return deliveryPersonRepository.findDeliveryPersonById(deliveryPersonId);
    }
    public void save(DeliveryPerson deliveryPerson) {
        deliveryPersonRepository.save(deliveryPerson);
    }
}
