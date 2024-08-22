package com.myhotel.g_hotel.response;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRespnse {


    private Long bookingId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String guestFullName;
    private String guestEmail;
    private int nbOfAdults;
    private int nbOfChildren;
    private int totalNbOfGuest;
    private String bookingConfirmationCode;
    private RoomResponse roomResponse;

    public BookingRespnse(Long bookingId, LocalDate checkInDate,
                          LocalDate checkOutDate,
                          String bookingConfirmationCode) {
        this.bookingId = bookingId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.bookingConfirmationCode = bookingConfirmationCode;
    }
}
