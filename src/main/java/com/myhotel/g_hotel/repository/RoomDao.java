package com.myhotel.g_hotel.repository;

import com.myhotel.g_hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomDao extends JpaRepository<Room,Long> {
}
