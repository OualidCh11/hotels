package com.myhotel.g_hotel.response;

import com.myhotel.g_hotel.entity.Booking;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationResponse {


    private Long Id;
    private LocalDateTime reservationDate;
    private String status;
    private List<Booking> bookings;
    private List<PaymentResponse> payments;
    private GuestRespnse guest;




}
