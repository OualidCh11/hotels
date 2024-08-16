package com.myhotel.g_hotel.service.inter;

import com.myhotel.g_hotel.entity.Room;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

public interface RoomService {
    Room addNewRoom(MultipartFile file, String roomType, BigDecimal roomPrice) throws IOException, SQLException;
}
