package com.myhotel.g_hotel.repository;

import com.myhotel.g_hotel.entity.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRequestDao extends JpaRepository<ServiceRequest,Long> {
    List<ServiceRequest> findByRoomId(Long roomId);
    List<ServiceRequest> findByGuestId(Long guestId);
    List<ServiceRequest> findByStatus(String status);
}
