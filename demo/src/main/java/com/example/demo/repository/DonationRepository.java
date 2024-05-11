package com.example.demo.repository;

import com.example.demo.model.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Integer> {

<<<<<<< HEAD
=======

>>>>>>> Sam
    // Method to find donations by delivery person id and donation status
    List<Donation> findByDeliveryPersonIdAndDonationStatus(Long deliveryPersonId, String donationStatus);


    // Method to find donations by delivery person id
    @Query("SELECT d FROM Donation d WHERE d.deliveryPerson.id = :deliveryPersonId AND d.donationStatus = 'pending'")
    List<Donation> findPendingDonationsByDeliveryPersonId(Long deliveryPersonId);

}
