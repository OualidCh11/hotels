package com.myhotel.g_hotel.repository;

import com.myhotel.g_hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomDao extends JpaRepository<Room,Long> {

    @Query("SELECT r.roomType FROM Room r")
    List<String> findByRoomType();
}
