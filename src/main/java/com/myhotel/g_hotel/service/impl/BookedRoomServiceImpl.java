package com.myhotel.g_hotel.service.impl;

import com.myhotel.g_hotel.entity.BookedRoom;
import com.myhotel.g_hotel.repository.BookedRoomDao;
import com.myhotel.g_hotel.service.inter.BookedRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookedRoomServiceImpl implements BookedRoomService {

    private final BookedRoomDao bookedRoomDao;

    @Override
    public List<BookedRoom> getAllBookingByRoomsId(Long roomId) {

        return bookedRoomDao.findByRoomId(roomId);
    }

    @Override
    public List<BookedRoom> getAllBoking() {
        return bookedRoomDao.findAll();
    }

    @Override
    public BookedRoom findByConfirmationCode(String confirmationCode) {
        return null;
    }

    @Override
    public void cancelBooking(Long bookingId) {
        bookedRoomDao.deleteById(bookingId);

    }

    @Override
    public String saveBooking(Long roomId, BookedRoom bookedRoomRequest) {
        return null;
    }
}
