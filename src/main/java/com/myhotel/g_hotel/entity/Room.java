package com.myhotel.g_hotel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roomType;
    private BigDecimal priceRoom;
    private boolean isBooked = false;
    @Lob
    private Blob photo;

    @OneToMany(mappedBy = "room",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<BookedRoom> bookedRoomList;

    public Room() {
        this.bookedRoomList = new ArrayList<>();
    }

    public void addBooking(BookedRoom bookedRoom){
        if(bookedRoomList == null){
            bookedRoomList = new ArrayList<>();
        }
        bookedRoomList.add(bookedRoom);
        bookedRoom.setRoom(this);
        isBooked = true;
        String BookingCode = RandomStringUtils.randomNumeric(10);
        bookedRoom.setBookingConfirmationCode(BookingCode);
    }


}
