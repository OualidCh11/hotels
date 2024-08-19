package com.myhotel.g_hotel.service.inter;

import com.myhotel.g_hotel.entity.Room;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface RoomService {
    Room addNewRoom(MultipartFile file, String roomType, BigDecimal priceRoom) throws IOException, SQLException;
    List<String> getAllRoomType();

    List<Room> getAllRooms();

    byte[] getRoomPhotoByRoomId(Long roomId) throws SQLException;

    void deleteRoom(Long roomId);
}
