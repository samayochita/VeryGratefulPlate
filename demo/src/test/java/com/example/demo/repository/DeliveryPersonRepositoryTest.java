package com.example.demo.repository;

import com.example.demo.model.DeliveryPerson;
import com.example.demo.model.DeliveryPersonStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DataJpaTest
public class DeliveryPersonRepositoryTest {

    @Mock
    private DeliveryPersonRepository deliveryPersonRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindFirstByStatusOrderByIdAsc() {
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        // Set delivery person properties
        deliveryPerson.setStatus(DeliveryPersonStatus.ON_DUTY_FREE);

        when(deliveryPersonRepository.findFirstByStatusOrderByIdAsc(DeliveryPersonStatus.ON_DUTY_FREE)).thenReturn(deliveryPerson);

        DeliveryPerson result = deliveryPersonRepository.findFirstByStatusOrderByIdAsc(DeliveryPersonStatus.ON_DUTY_FREE);

        assertEquals(deliveryPerson, result);
    }

    @Test
    public void testFindByEmailId() {
        String email = "test@example.com";
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        // Set delivery person properties
        deliveryPerson.setEmailId(email);

        when(deliveryPersonRepository.findByEmailId(email)).thenReturn(deliveryPerson);

        DeliveryPerson result = deliveryPersonRepository.findByEmailId(email);

        assertEquals(deliveryPerson, result);
    }
}
