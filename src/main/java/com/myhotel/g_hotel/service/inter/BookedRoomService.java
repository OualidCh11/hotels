package com.myhotel.g_hotel.service.inter;


import com.myhotel.g_hotel.entity.BookedRoom;

import java.util.List;

public interface BookedRoomService {
    List<BookedRoom> getAllBookingByRoomsId(Long roomId);

    List<BookedRoom> getAllBoking();

    BookedRoom findByConfirmationCode(String confirmationCode);
}
