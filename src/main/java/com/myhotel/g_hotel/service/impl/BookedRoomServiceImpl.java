package com.myhotel.g_hotel.service.impl;

import com.myhotel.g_hotel.entity.BookedRoom;
import com.myhotel.g_hotel.service.inter.BookedRoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookedRoomServiceImpl implements BookedRoomService {
    @Override
    public List<BookedRoom> getAllBookingByRoomsId(Long roomId) {
        return null;
    }
}
