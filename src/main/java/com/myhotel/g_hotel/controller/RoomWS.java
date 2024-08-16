package com.myhotel.g_hotel.controller;

import com.myhotel.g_hotel.entity.Room;
import com.myhotel.g_hotel.response.RoomResponse;
import com.myhotel.g_hotel.service.impl.RoomServiceImpl;
import com.myhotel.g_hotel.service.inter.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rooms")
public class RoomWS {

    private final RoomService roomService;
    @PostMapping("/add/new_room")
    public ResponseEntity<RoomResponse> addNewRoom(
            @RequestParam("photo")MultipartFile photo,
            @RequestParam("roomType")String roomType,
            @RequestParam("roomPrice")BigDecimal roomPrice) throws SQLException, IOException {
        Room saveRoom = roomService.addNewRoom(photo,roomType,roomPrice);
        RoomResponse roomResponse = new RoomResponse(saveRoom.getId(),
                saveRoom.getRoomType(),saveRoom.getPriceRoom());
        return ResponseEntity.ok(roomResponse);
    }
}
