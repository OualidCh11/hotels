package com.myhotel.g_hotel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookedRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String guestFullName;
    private String guestEmail;
    private int nbOfAdults;
    private int nbOfChildren;
    private int totalNbOfGuest;
    private String bookingConfirmationCode;
    private Room room;

    private void calculateTotalNbOfGuast(){
        this.totalNbOfGuest = this.nbOfAdults + this.nbOfChildren;
    }

    public void setNbOfAdults(int nbOfAdults) {
        this.nbOfAdults = nbOfAdults;
        calculateTotalNbOfGuast();
    }

    public void setNbOfChildren(int nbOfChildren) {
        this.nbOfChildren = nbOfChildren;
        calculateTotalNbOfGuast();
    }

    public void setBookingConfirmationCode(String bookingConfirmationCode) {
        this.bookingConfirmationCode = bookingConfirmationCode;
    }
}
