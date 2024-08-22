package com.myhotel.g_hotel.service.inter;


import com.myhotel.g_hotel.entity.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> getAllBookingByRoomsId(Long roomId);

    List<Booking> getAllBoking();

    Booking findByConfirmationCode(String confirmationCode);

    void cancelBooking(Long bookingId);

    String saveBooking(Long roomId, Booking bookedRoomRequest);
}
