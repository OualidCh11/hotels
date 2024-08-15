package com.myhotel.g_hotel.entity;

import jakarta.persistence.*;
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
    @Column(name = "checkIn")
    private LocalDate checkInDate;
    @Column(name = "checkout")
    private LocalDate checkOutDate;
    @Column(name = "guest_FullName")
    private String guestFullName;
    @Column(name = "guest_Email")
    private String guestEmail;
    @Column(name = "adult")
    private int nbOfAdults;
    @Column(name = "children")
    private int nbOfChildren;
    @Column(name = "totsl_guest")
    private int totalNbOfGuest;
    @Column(name = "confirmation_code")
    private String bookingConfirmationCode;
    @ManyToOne(fetch = FetchType.LAZY )
    @JoinColumn(name = "room_id")
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
