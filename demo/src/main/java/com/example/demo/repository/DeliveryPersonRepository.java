package com.example.demo.repository;
import com.example.demo.model.DeliveryPerson;
import com.example.demo.model.DeliveryPersonStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, Long> {

    // Custom query to find the first delivery person who is on duty and free
    DeliveryPerson findFirstByStatusOrderByIdAsc(DeliveryPersonStatus status);

    DeliveryPerson findByEmailId(String email);
    // Additional methods as needed

    DeliveryPerson findDeliveryPersonById(Long deliveryPersonId);
}

