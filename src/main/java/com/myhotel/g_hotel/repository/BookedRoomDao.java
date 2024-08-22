package com.myhotel.g_hotel.repository;

import com.myhotel.g_hotel.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookedRoomDao extends JpaRepository<Booking, Long> {
    List<Booking> findByRoomId(Long roomId);
    Booking findByBookingConfirmationCode(String confirmationCode);
}
