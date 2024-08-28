package com.myhotel.g_hotel.service.impl;

import com.myhotel.g_hotel.entity.Booking;
import com.myhotel.g_hotel.entity.Room;
import com.myhotel.g_hotel.exception.InvalidBookingException;
import com.myhotel.g_hotel.exception.ResourceNotFoundException;
import com.myhotel.g_hotel.repository.BookingDao;
import com.myhotel.g_hotel.response.Respnse;
import com.myhotel.g_hotel.service.inter.BookingService;
import com.myhotel.g_hotel.service.inter.RoomService;
import com.myhotel.g_hotel.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingDao bookingDao;
    private final RoomService roomService;

    @Override
    public List<Booking> getAllBookingByRoomsId(Long roomId) {

        return bookingDao.findByRoomId(roomId);
    }

    @Override
    public List<Booking> getAllBoking() {
        return bookingDao.findAll();
    }

    @Override
    public Booking findByConfirmationCode(String confirmationCode) {
        return bookingDao.findByBookingConfirmationCode(confirmationCode);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        bookingDao.deleteById(bookingId);

    }

    @Override
    public String saveBooking(Long roomId, Booking bookedRoomRequest) {
        if (bookedRoomRequest.getCheckOutDate().isBefore(bookedRoomRequest.getCheckInDate())) {
            throw new InvalidBookingException("Check-out date cannot be before check-in date.");
        }

        Room room = roomService.getRoomById(roomId).orElseThrow(() ->
                new ResourceNotFoundException("Room not found for this id: " + roomId));

        List<Booking> bookedRooms = room.getBookedRoomList();
        boolean roomIsAvailable = roomIsAvilable(bookedRoomRequest, bookedRooms);

        if (roomIsAvailable) {
            String confirmationCode = Utils.generateRandomConfirmationCode(8); // Generate an 8-character code
            bookedRoomRequest.setBookingConfirmationCode(confirmationCode);

            room.addBooking(bookedRoomRequest);
            bookingDao.save(bookedRoomRequest);
        } else {
            throw new InvalidBookingException("Sorry, this room has been booked.");
        }

        return bookedRoomRequest.getBookingConfirmationCode();
    }

    private boolean roomIsAvilable(Booking bookedRoomRequest, List<Booking> bookedRoomss) {
        return bookedRoomss.stream()
                .noneMatch(bookedRooms ->
                        bookedRoomRequest.getCheckInDate().equals(bookedRooms.getCheckInDate())
                        || bookedRoomRequest.getCheckOutDate().isBefore(bookedRooms.getCheckOutDate())

                        || (bookedRoomRequest.getCheckInDate().isAfter(bookedRooms.getCheckInDate())
                        && bookedRoomRequest.getCheckInDate().isBefore(bookedRooms.getCheckOutDate()))

                        || (bookedRoomRequest.getCheckInDate().isBefore(bookedRooms.getCheckInDate())
                        && bookedRoomRequest.getCheckOutDate().equals(bookedRooms.getCheckOutDate()))

                        || (bookedRoomRequest.getCheckInDate().isBefore(bookedRooms.getCheckInDate())
                        && bookedRoomRequest.getCheckOutDate().isAfter(bookedRooms.getCheckOutDate()))

                        || (bookedRoomRequest.getCheckInDate().equals(bookedRooms.getCheckOutDate())
                        && bookedRoomRequest.getCheckOutDate().equals(bookedRooms.getCheckInDate()))

                        || (bookedRoomRequest.getCheckInDate().equals(bookedRooms.getCheckInDate())
                        && bookedRoomRequest.getCheckOutDate().equals(bookedRoomRequest.getCheckInDate()))
                );

    }
}
