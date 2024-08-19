package com.myhotel.g_hotel.service.impl;

import com.myhotel.g_hotel.entity.Room;
import com.myhotel.g_hotel.exception.ResourceNotFoundException;
import com.myhotel.g_hotel.repository.RoomDao;
import com.myhotel.g_hotel.service.inter.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomDao roomDao;
    @Override
    public Room addNewRoom(MultipartFile file, String roomType, BigDecimal priceRoom) throws IOException, SQLException {
        Room room = new Room();
        room.setRoomType(roomType);
        room.setPriceRoom(priceRoom);
        if (!file.isEmpty()){
            byte[] photoBytes = file.getBytes();
            Blob photoBlob = new SerialBlob(photoBytes);
            room.setPhoto(photoBlob);
        }
        return roomDao.save(room);
    }

    @Override
    public List<String> getAllRoomType() {
        return roomDao.findByRoomType();
    }

    @Override
    public List<Room> getAllRooms() {
        return roomDao.findAll();
    }

    @Override
    public byte[] getRoomPhotoByRoomId(Long roomId) throws SQLException {
        Optional<Room> theRoom = roomDao.findById(roomId);
        if (theRoom.isEmpty()){
            throw new ResourceNotFoundException("Room not found");
        }
        Blob photoBlob = theRoom.get().getPhoto();
        if (photoBlob != null){
            return photoBlob.getBytes(1,(int) photoBlob.length());
        }
        return null;
    }
}
