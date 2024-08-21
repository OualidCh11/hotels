package com.myhotel.g_hotel.service.impl;

import com.myhotel.g_hotel.entity.BookedRoom;
import com.myhotel.g_hotel.service.inter.BookedRoomService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookedRoomServiceImpl implements BookedRoomService {
    @Override
    public List<BookedRoom> getAllBookingByRoomsId(Long roomId) {
        return new ArrayList<>();
    }

    @Override
    public List<BookedRoom> getAllBoking() {
        return null;
    }

    @Override
    public BookedRoom findByConfirmationCode(String confirmationCode) {
        return null;
    }

    @Override
    public void cancelBooking(Long bookingId) {

    }

    @Override
    public String saveBooking(Long roomId, BookedRoom bookedRoomRequest) {
        return null;
    }
}
