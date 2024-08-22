package com.myhotel.g_hotel.service.impl;

import com.myhotel.g_hotel.entity.Booking;
import com.myhotel.g_hotel.entity.Room;
import com.myhotel.g_hotel.exception.InvalidBookingException;
import com.myhotel.g_hotel.repository.BookedRoomDao;
import com.myhotel.g_hotel.service.inter.BookedRoomService;
import com.myhotel.g_hotel.service.inter.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookedRoomServiceImpl implements BookedRoomService {

    private final BookedRoomDao bookedRoomDao;
    private final RoomService roomService;

    @Override
    public List<Booking> getAllBookingByRoomsId(Long roomId) {

        return bookedRoomDao.findByRoomId(roomId);
    }

    @Override
    public List<Booking> getAllBoking() {
        return bookedRoomDao.findAll();
    }

    @Override
    public Booking findByConfirmationCode(String confirmationCode) {
        return bookedRoomDao.findByBookingConfirmationCode(confirmationCode);
    }

    @Override
    public void cancelBooking(Long bookingId) {
        bookedRoomDao.deleteById(bookingId);

    }

    @Override
    public String saveBooking(Long roomId, Booking bookedRoomRequest) {

        if(bookedRoomRequest.getCheckOutDate().isBefore(bookedRoomRequest.getCheckInDate())){
            throw new InvalidBookingException("check in-date");
        }
        Room room = roomService.getRoomById(roomId).get();
        List<Booking> bookedRooms = room.getBookedRoomList();
        boolean roomIsAvilable = roomIsAvilable(bookedRoomRequest,bookedRooms);
        if(roomIsAvilable){
            room.addBooking(bookedRoomRequest);
            bookedRoomDao.save(bookedRoomRequest);
        }else {
            throw new InvalidBookingException("sorry this room has been booked");
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
