package com.myhotel.g_hotel.service.inter;

import com.myhotel.g_hotel.entity.Hotel;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface HotelService {
    Hotel addNewHotel(String name, String address, String phoneNumber, String email, Float rating, Blob photoBlob);
    List<Hotel> getAllHotels();
    Optional<Hotel> getHotelById(Long hotelId);
    void deleteHotel(Long hotelId);
    Hotel updateHotel(Long id, Hotel hotelDetails);

    byte[] getHotelPhotoByHotelId(Long id) throws SQLException;
}
