package com.myhotel.g_hotel.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Respnse {


    private int statusCode;
    private String message;

    private String token;
    private String role;
    private String expirationTime;
    private String bookingConfirmationCode;

    private GuestRespnse guestRespnse;
    private RoomResponse roomResponse;
    private BookingRespnse bookingRespnse;
    private HotelRespse hotelRespse;
    private List<GuestRespnse> guestRespnseList;
    private List<RoomResponse> roomResponseList;
    private List<BookingRespnse> bookingRespnseList;
    private List<HotelRespse> hotelRespseList;

}
