package com.example.demo.service;

import com.example.demo.model.DeliveryPerson;
import com.example.demo.repository.DeliveryPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryPersonService {

    private final DeliveryPersonRepository deliveryPersonRepository;

    @Autowired
    public DeliveryPersonService(DeliveryPersonRepository deliveryPersonRepository) {
        this.deliveryPersonRepository = deliveryPersonRepository;
    }

    public DeliveryPerson saveDeliveryPerson(DeliveryPerson deliveryPerson) {
        return deliveryPersonRepository.save(deliveryPerson);
    }
    public DeliveryPerson findByEmailId(String email) {
        return deliveryPersonRepository.findByEmailId(email);
    }

    // You can add more service methods as needed, such as finding a delivery person by ID or email.
}
