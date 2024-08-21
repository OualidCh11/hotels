package com.myhotel.g_hotel.repository;

import com.myhotel.g_hotel.entity.BookedRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookedRoomDao extends JpaRepository<BookedRoom , Long> {
    List<BookedRoom> findByRoomId(Long roomId);
    BookedRoom findByBookingConfirmationCode(String confirmationCode);
}
