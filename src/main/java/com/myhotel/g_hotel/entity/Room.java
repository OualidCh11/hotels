package com.myhotel.g_hotel.entity;

import jakarta.persistence.Entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Room {

    private Long id;
    private String roomType;
    private BigDecimal priceRoom;
    private boolean isBooked = false;

    private List<BookedRoom> bookedRoomList;

    public Room() {
        this.bookedRoomList = new ArrayList<>();
    }
}
