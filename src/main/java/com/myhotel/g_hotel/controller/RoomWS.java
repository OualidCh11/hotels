package com.myhotel.g_hotel.controller;

import com.myhotel.g_hotel.entity.BookedRoom;
import com.myhotel.g_hotel.entity.Room;
import com.myhotel.g_hotel.exception.PhotoRetrievalException;
import com.myhotel.g_hotel.exception.ResourceNotFoundException;
import com.myhotel.g_hotel.response.BookedRoomRespnse;
import com.myhotel.g_hotel.response.RoomResponse;
import com.myhotel.g_hotel.service.inter.BookedRoomService;
import com.myhotel.g_hotel.service.inter.RoomService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rooms")
public class RoomWS {

    private final RoomService roomService;
    private final BookedRoomService bookedRoomService;
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

    @GetMapping("/all_rooms")
    public ResponseEntity<List<RoomResponse>> getAllRooms() throws SQLException {
        List<Room> rooms = roomService.getAllRooms();
        List<RoomResponse> roomResponses = new ArrayList<>();
        for (Room room: rooms){
            byte[]photoByte = roomService.getRoomPhotoByRoomId(room.getId());
            if(photoByte != null && photoByte.length > 0){
                String base64Photo = Base64.encodeBase64String(photoByte);
                RoomResponse roomResponse = getRoomRespnse(room);
                roomResponse.setPhoto(base64Photo);
                roomResponses.add(roomResponse);
            }
        }
        return ResponseEntity.ok((roomResponses));
    }

    @DeleteMapping("/delete/room/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId){
        roomService.deleteRoom(roomId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{roomId}")
    public ResponseEntity<RoomResponse> updateRoom(@PathVariable Long roomId ,@RequestParam(required = false) String roomType ,@RequestParam(required = false) BigDecimal priceRoom ,@RequestParam(required = false) MultipartFile photo ) throws IOException, SQLException {

        byte[] photoBytes = photo != null && !photo.isEmpty()?
                photo.getBytes(): roomService.getRoomPhotoByRoomId(roomId);
        Blob photoBlob = photoBytes != null && photoBytes.length > 0?new SerialBlob(photoBytes): null;
        Room theroom = roomService.updateRoom(roomId,roomType,priceRoom,photoBytes);
        theroom.setPhoto(photoBlob);
        RoomResponse roomResponse = getRoomRespnse(theroom);
        return ResponseEntity.ok(roomResponse);

    }

    @GetMapping("/get/rooms/{id}")
    public ResponseEntity<Optional<RoomResponse>> getRoomById(@PathVariable Long roomId){
        Optional<Room> thrRoom = roomService.getRoomById(roomId);
        return thrRoom.map(room -> {
            RoomResponse roomResponse = getRoomRespnse(room);
            return ResponseEntity.ok(Optional.of(roomResponse));
        }).orElseThrow(() -> new ResourceNotFoundException("not found room"));
    }

    private RoomResponse getRoomRespnse(Room room) {
        List<BookedRoom> bookedRoomList = getAllBookingByRoomId(room.getId());
        List<BookedRoomRespnse> bookedRoomRespnses = new ArrayList<>();
        if (bookedRoomList != null) {
            bookedRoomRespnses = bookedRoomList.stream()
                    .map(bookedRoom -> new BookedRoomRespnse(bookedRoom.getBookingId(),
                            bookedRoom.getCheckInDate(),
                            bookedRoom.getCheckOutDate(),
                            bookedRoom.getBookingConfirmationCode()))
                    .toList();
        }
        byte[] photBytes = null;
        Blob photoBlob = room.getPhoto();
        if (photoBlob != null) {
            try {
                photBytes = photoBlob.getBytes(1, (int) photoBlob.length());
            } catch (SQLException e) {
                throw new PhotoRetrievalException("Error retrieving photo");
            }
        }
        return new RoomResponse(room.getId(), room.getRoomType(), room.getPriceRoom(), room.isBooked(), photBytes, bookedRoomRespnses);
    }

    private List<BookedRoom> getAllBookingByRoomId(Long roomId) {
        return bookedRoomService.getAllBookingByRoomsId(roomId);
    }
}
