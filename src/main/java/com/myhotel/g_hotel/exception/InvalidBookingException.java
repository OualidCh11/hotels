package com.myhotel.g_hotel.exception;

public class InvalidBookingException extends RuntimeException{

    public InvalidBookingException(String messages){
        super(messages);
    }
}
