package com.myhotel.g_hotel.controller;

import com.myhotel.g_hotel.entity.Room;
import com.myhotel.g_hotel.response.RoomResponse;
import com.myhotel.g_hotel.service.impl.RoomServiceImpl;
import com.myhotel.g_hotel.service.inter.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rooms")
public class RoomWS {

    private final RoomService roomService;
    @PostMapping("/add/new_room")
    public ResponseEntity<RoomResponse> addNewRoom(
            @RequestParam("photo")MultipartFile photo,
            @RequestParam("roomType")String roomType,
            @RequestParam("priceRoom")BigDecimal priceRoom) throws SQLException, IOException {
        Room saveRoom = roomService.addNewRoom(photo,roomType,priceRoom);
        RoomResponse roomResponse = new RoomResponse(saveRoom.getId(),
                saveRoom.getRoomType(),saveRoom.getPriceRoom());
        return ResponseEntity.ok(roomResponse);
    }

    @GetMapping("/get/room_types")
    public List<String> getRoomTypes(){

        return roomService.getAllRoomType();
    }
}
