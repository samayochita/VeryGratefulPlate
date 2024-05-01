package com.example.demo.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "donations")
public class Donation{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "donation_id")
    private Long donationId;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "user_id", nullable = false) // Change to reference the id column
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "delivery_person_id")
    private DeliveryPerson deliveryPerson;

    @Column(name = "donor_name")
    private String donorName;

    @Column(name = "donor_phone_number")
    private String donorPhoneNumber;

    @Column(name = "pickup_address")
    private String pickupAddress;

    @Column(name = "category")
    private String category;

    @Column(name = "quantity_in_kg")
    private double quantityInKg;

    @Column(name = "food_preparation_datetime")
    private LocalDateTime foodPreparationDateTime;

    @Column(name = "additional_notes",  nullable = true)
    private String additionalNotes;

    @Column(name = "donation_status")
    private String donationStatus;

    public void setDeliveryPerson(DeliveryPerson deliveryPerson) {
        this.deliveryPerson = deliveryPerson;
    }

    public DeliveryPerson getDeliveryPerson() {
        return deliveryPerson;
    }

    public Integer getUserId() {
        if (user != null) {
            return user.getUserId();
        } else {
            return null; // or handle the case where user is null
        }
    }

    public Donation() {
        // Default constructor
    }

    public Donation(Long donationId, User user, DeliveryPerson deliveryPerson, String donorName, String donorPhoneNumber, String pickupAddress, String category, double quantityInKg, LocalDateTime foodPreparationDateTime, String additionalNotes, String donationStatus) {
        this.donationId = donationId;
        this.user = user;
        this.deliveryPerson = deliveryPerson;
        this.donorName = donorName;
        this.donorPhoneNumber = donorPhoneNumber;
        this.pickupAddress = pickupAddress;
        this.category = category;
        this.quantityInKg = quantityInKg;
        this.foodPreparationDateTime = foodPreparationDateTime;
        this.additionalNotes = additionalNotes;
        this.donationStatus = donationStatus;
    }

    public Donation(User user, DeliveryPerson deliveryPerson, String donorName, String donorPhoneNumber, String pickupAddress, String category, double quantityInKg, LocalDateTime foodPreparationDateTime, String additionalNotes, String donationStatus) {
        this.user = user;
        this.deliveryPerson = deliveryPerson;
        this.donorName = donorName;
        this.donorPhoneNumber = donorPhoneNumber;
        this.pickupAddress = pickupAddress;
        this.category = category;
        this.quantityInKg = quantityInKg;
        this.foodPreparationDateTime = foodPreparationDateTime;
        this.additionalNotes = additionalNotes;
        this.donationStatus = donationStatus;
    }

    public Long getDonationId() {
        return donationId;
    }

    public void setDonationId(Long donationId) {
        this.donationId = donationId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonorPhoneNumber() {
        return donorPhoneNumber;
    }

    public void setDonorPhoneNumber(String donorPhoneNumber) {
        this.donorPhoneNumber = donorPhoneNumber;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getQuantityInKg() {
        return quantityInKg;
    }

    public void setQuantityInKg(double quantityInKg) {
        this.quantityInKg = quantityInKg;
    }

    public LocalDateTime getFoodPreparationDateTime() {
        return foodPreparationDateTime;
    }

    public void setFoodPreparationDateTime(LocalDateTime foodPreparationDateTime) {
        this.foodPreparationDateTime = foodPreparationDateTime;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public String getDonationStatus() {
        return donationStatus;
    }

    public void setDonationStatus(String donationStatus) {
        this.donationStatus = donationStatus;
    }



}
