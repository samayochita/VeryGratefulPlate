package com.example.demo.repository;
import com.example.demo.model.DeliveryPerson;
import com.example.demo.model.PasswordResetToken;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long>
{
    PasswordResetToken findByUserAndActiveTrue(User user);
    PasswordResetToken findByUserAndTokenAndActiveTrue(User user, String token);

    PasswordResetToken  findByDeliveryPersonAndTokenAndActiveTrue(DeliveryPerson deliveryPerson, String token);

    PasswordResetToken findByDeliveryPersonAndActiveTrue(DeliveryPerson deliveryPerson);


}