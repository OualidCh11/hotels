package com.myhotel.g_hotel.response;

import com.myhotel.g_hotel.entity.Room;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelRespse {


    private Long Id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private Float rating;
    private String photo;
    private List<Room> rooms;

    public HotelRespse( String name, String address, String phoneNumber, String email, Float rating, String photo, List<Room> rooms) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.rating = rating;
        this.photo = photo;
        this.rooms = rooms;
    }
    }
