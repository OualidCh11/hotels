package com.myhotel.g_hotel.repository;

import com.myhotel.g_hotel.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestDao extends JpaRepository<Guest,Long> {
    Guest findByEmail(String email);
    Guest findByIdentificationNumber(String identificationNumber);
}
