package com.myhotel.g_hotel.response;

import com.myhotel.g_hotel.entity.BookedRoom;
import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.util.codec.binary.Base64;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponse {

    private Long id;
    private String roomType;
    private BigDecimal priceRoom;
    private boolean isBooked = false;
    private String photo;
    private List<BookedRoomRespnse> bookedRoomListRespnse;

    public RoomResponse(Long id, String roomType, BigDecimal priceRoom) {
        this.id = id;
        this.roomType = roomType;
        this.priceRoom = priceRoom;
    }

    public RoomResponse(Long id, String roomType, BigDecimal priceRoom,
                        boolean isBooked, byte[] photoByte,
                        List<BookedRoomRespnse> bookedRoomListRespnse) {
        this.id = id;
        this.roomType = roomType;
        this.priceRoom = priceRoom;
        this.isBooked = isBooked;
        this.photo = photoByte != null ? Base64.encodeBase64String(photoByte):null;
        this.bookedRoomListRespnse = bookedRoomListRespnse;
    }

}
