package com.myhotel.g_hotel.repository;

import com.myhotel.g_hotel.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentDao extends JpaRepository<Payment,Long> {
    List<Payment> findByReservationId(Long reservationId);
    List<Payment> findByStatus(String status);
}
