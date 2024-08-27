package com.myhotel.g_hotel.service.impl;

import com.myhotel.g_hotel.entity.Hotel;
import com.myhotel.g_hotel.entity.Room;
import com.myhotel.g_hotel.exception.ResourceNotFoundException;
import com.myhotel.g_hotel.repository.HotelDao;
import com.myhotel.g_hotel.service.inter.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    @Autowired
    private final HotelDao hotelDao;

    @Override
    public Hotel addNewHotel(String name, String address, String phoneNumber, String email, Float rating, Blob photoBlob) {
        Hotel hotel = new Hotel();
        hotel.setName(name);
        hotel.setAddress(address);
        hotel.setPhoneNumber(phoneNumber);
        hotel.setEmail(email);
        hotel.setRating(rating);
        hotel.setPhoto(photoBlob);
        return hotelDao.save(hotel);
    }

    @Override
    public List<Hotel> getAllHotels() {
        return hotelDao.findAll();
    }

    @Override
    public Optional<Hotel> getHotelById(Long hotelId) {
        return hotelDao.findById(hotelId);
    }

    @Override
    public Hotel updateHotel(Long hotelId, Hotel hotelDetails) {
        Hotel existingHotel = hotelDao.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + hotelId));

        if (hotelDetails.getName() != null) existingHotel.setName(hotelDetails.getName());
        if (hotelDetails.getAddress() != null) existingHotel.setAddress(hotelDetails.getAddress());
        if (hotelDetails.getPhoneNumber() != null) existingHotel.setPhoneNumber(hotelDetails.getPhoneNumber());
        if (hotelDetails.getEmail() != null) existingHotel.setEmail(hotelDetails.getEmail());
        if (hotelDetails.getRating() != null) existingHotel.setRating(hotelDetails.getRating());
        if (hotelDetails.getPhoto() != null) existingHotel.setPhoto(hotelDetails.getPhoto());

        return hotelDao.save(existingHotel);
    }

    @Override
    public byte[] getHotelPhotoByHotelId(Long id) throws SQLException {
        Optional<Hotel> theHotel = hotelDao.findById(id);
        if (theHotel.isEmpty()){
            throw new ResourceNotFoundException("Hotel not found");
        }
        Blob photoBlob = theHotel.get().getPhoto();
        if (photoBlob != null){
            return photoBlob.getBytes(1,(int) photoBlob.length());
        }
        return null;
    }

    @Override
    public void deleteHotel(Long hotelId) {
        Hotel existingHotel = hotelDao.findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with ID: " + hotelId));
        hotelDao.delete(existingHotel);
    }

}
