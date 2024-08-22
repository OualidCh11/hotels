package com.myhotel.g_hotel.repository;

import com.myhotel.g_hotel.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationDao extends JpaRepository<Reservation , Long> {

    List<Reservation> findByGuestId(Long guestId);
    List<Reservation> findByStatus(String status);
}
