package com.myhotel.g_hotel.controller;

import com.myhotel.g_hotel.entity.BookedRoom;
import com.myhotel.g_hotel.entity.Room;
import com.myhotel.g_hotel.exception.InvalidBookingException;
import com.myhotel.g_hotel.exception.ResourceNotFoundException;
import com.myhotel.g_hotel.response.BookedRoomRespnse;
import com.myhotel.g_hotel.response.RoomResponse;
import com.myhotel.g_hotel.service.inter.BookedRoomService;
import com.myhotel.g_hotel.service.inter.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequestMapping("/booking")
@RestController
@RequiredArgsConstructor
public class BookedRoomWS {

    private final BookedRoomService bookedRoomService;
    private final RoomService roomService;

    @GetMapping("all_booking")
    public ResponseEntity<List<BookedRoomRespnse>> getAllBooking(){
        List<BookedRoom> bookedRooms = bookedRoomService.getAllBoking();
        List<BookedRoomRespnse> bookedRoomRespnses = new ArrayList<>();
        for(BookedRoom bookedRoom: bookedRooms){
            BookedRoomRespnse bookedRoomRespnse = getBookedRoomRespnse(bookedRoom);
            bookedRoomRespnses.add(bookedRoomRespnse);
        }
        return ResponseEntity.ok(bookedRoomRespnses);
    }

    @GetMapping("/confirmation")
    public ResponseEntity<?> getBookingByConfirmationCode(String confirmationCode){
        try {
            BookedRoom bookedRoom = bookedRoomService.findByConfirmationCode(confirmationCode);
            BookedRoomRespnse bookedRoomRespnse = getBookingResponse(bookedRoom);
            return ResponseEntity.ok(bookedRoomRespnse);
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }


    @GetMapping("/room/{roomId}/booking ")
    public ResponseEntity<?> saveBooking(@PathVariable Long roomId ,
                                         @RequestBody BookedRoom bookedRoomRequest){
        try {
            String confirmationCode = bookedRoomService.saveBooking(roomId , bookedRoomRequest);
            return ResponseEntity.ok("Room booked successfully ,your code confirmation is:"+confirmationCode);
        }catch (InvalidBookingException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/booking/{bookingId}/delete")
    public void cancelBooking(Long bookingId){
           bookedRoomService.cancelBooking(bookingId);
    }

    private BookedRoomRespnse getBookedRoomRespnse(BookedRoom bookedRoom) {
        Room theRoom = roomService.getRoomById(bookedRoom.getRoom().getId()).get();
        RoomResponse roomResponse = new RoomResponse(
                theRoom.getId(),
                theRoom.getRoomType(),
                theRoom.getPriceRoom()
        );
        return new BookedRoomRespnse(
                bookedRoom.getBookingId(),
                bookedRoom.getCheckInDate(),
                bookedRoom.getCheckOutDate(),
                bookedRoom.getGuestFullName(),
                bookedRoom.getGuestEmail(),
                bookedRoom.getNbOfAdults(),
                bookedRoom.getNbOfChildren(),bookedRoom.getTotalNbOfGuest(),
                bookedRoom.getBookingConfirmationCode(),roomResponse
        );
    }

    private BookedRoomRespnse getBookingResponse(BookedRoom bookedRoom) {
        return null;
    }

}

