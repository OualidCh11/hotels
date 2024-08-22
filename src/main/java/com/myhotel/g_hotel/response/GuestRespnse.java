package com.myhotel.g_hotel.response;

import com.myhotel.g_hotel.entity.Booking;
import com.myhotel.g_hotel.entity.ServiceRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestRespnse {


    private Long Id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String identificationNumber;
    private List<Booking> bookedRooms;
    private List<ReservationResponse> reservations;
    private List<ServiceRequest> serviceRequests;

}
