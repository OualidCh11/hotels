package com.myhotel.g_hotel.repository;

import com.myhotel.g_hotel.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelDao extends JpaRepository<Hotel,Long> {

    Hotel findByName(String name);
    Hotel findByEmail(String email);

}
