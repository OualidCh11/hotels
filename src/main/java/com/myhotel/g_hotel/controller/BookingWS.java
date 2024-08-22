package com.myhotel.g_hotel.controller;

import com.myhotel.g_hotel.entity.Booking;
import com.myhotel.g_hotel.entity.Room;
import com.myhotel.g_hotel.exception.InvalidBookingException;
import com.myhotel.g_hotel.exception.ResourceNotFoundException;
import com.myhotel.g_hotel.response.BookingRespnse;
import com.myhotel.g_hotel.response.RoomResponse;
import com.myhotel.g_hotel.service.inter.BookingService;
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
public class BookingWS {

    private final BookingService bookingService;
    private final RoomService roomService;

    @GetMapping("all_booking")
    public ResponseEntity<List<BookingRespnse>> getAllBooking(){
        List<Booking> bookedRooms = bookingService.getAllBoking();
        List<BookingRespnse> bookingRespns = new ArrayList<>();
        for(Booking bookedRoom: bookedRooms){
            BookingRespnse bookingRespnse = getBookedRoomRespnse(bookedRoom);
            bookingRespns.add(bookingRespnse);
        }
        return ResponseEntity.ok(bookingRespns);
    }

    @GetMapping("/confirmation")
    public ResponseEntity<?> getBookingByConfirmationCode(String confirmationCode){
        try {
            Booking bookedRoom = bookingService.findByConfirmationCode(confirmationCode);
            BookingRespnse bookingRespnse = getBookingResponse(bookedRoom);
            return ResponseEntity.ok(bookingRespnse);
        }catch (ResourceNotFoundException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }


    @GetMapping("/room/{roomId}/booking ")
    public ResponseEntity<?> saveBooking(@PathVariable Long roomId ,
                                         @RequestBody Booking bookedRoomRequest){
        try {
            String confirmationCode = bookingService.saveBooking(roomId , bookedRoomRequest);
            return ResponseEntity.ok("Room booked successfully ,your code confirmation is:"+confirmationCode);
        }catch (InvalidBookingException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/booking/{bookingId}/delete")
    public void cancelBooking(Long bookingId){
           bookingService.cancelBooking(bookingId);
    }

    private BookingRespnse getBookedRoomRespnse(Booking bookedRoom) {
        Room theRoom = roomService.getRoomById(bookedRoom.getRoom().getId()).get();
        RoomResponse roomResponse = new RoomResponse(
                theRoom.getId(),
                theRoom.getRoomType(),
                theRoom.getPriceRoom()
        );
        return new BookingRespnse(
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

    private BookingRespnse getBookingResponse(Booking booking) {
        return null;
    }

}

