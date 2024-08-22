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
    private List<Room> rooms;


}
