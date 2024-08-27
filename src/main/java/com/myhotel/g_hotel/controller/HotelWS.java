package com.myhotel.g_hotel.controller;

import com.myhotel.g_hotel.entity.Hotel;
import com.myhotel.g_hotel.entity.Room;
import com.myhotel.g_hotel.exception.PhotoRetrievalException;
import com.myhotel.g_hotel.response.HotelRespse;
import com.myhotel.g_hotel.service.inter.HotelService;
import com.myhotel.g_hotel.service.inter.RoomService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hotels")
public class HotelWS {

    @Autowired
    private final HotelService hotelService;
    @Autowired
    private final RoomService roomService;



    @GetMapping("/all_hotels")
    public ResponseEntity<List<HotelRespse>> getAllHotels() throws SQLException {
        List<Hotel> hotels = hotelService.getAllHotels();
        List<HotelRespse> hotelResponses = new ArrayList<>();
        for (Hotel hotel : hotels) {
            hotelResponses.add(getHotelRespnse(hotel));
        }
        return ResponseEntity.ok(hotelResponses);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        Optional<Hotel> hotel = hotelService.getHotelById(id);
        return hotel.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<HotelRespse> addNewHotel(
            @RequestParam("name") String name,
            @RequestParam("address") String address,
            @RequestParam("phoneNumber") String phoneNumber,
            @RequestParam("email") String email,
            @RequestParam("rating") Float rating,
            @RequestParam("photo") MultipartFile photo) throws SQLException, IOException {

        Blob photoBlob = new SerialBlob(photo.getBytes());
        Hotel savedHotel = hotelService.addNewHotel(name, address, phoneNumber, email, rating, photoBlob);
        HotelRespse hotelResponse = new HotelRespse();
        hotelResponse.setName(savedHotel.getName());
        hotelResponse.setAddress(savedHotel.getAddress());
        hotelResponse.setPhoneNumber(savedHotel.getPhoneNumber());
        hotelResponse.setEmail(savedHotel.getEmail());
        hotelResponse.setRating(savedHotel.getRating());
        hotelResponse.setPhoto(Base64.encodeBase64String(photo.getBytes()));
        return ResponseEntity.ok(hotelResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotelDetails) {
        Hotel updatedHotel = hotelService.updateHotel(id, hotelDetails);
        return ResponseEntity.ok(updatedHotel);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }




    private HotelRespse getHotelRespnse(Hotel hotel) {
        List<Room> rooms = roomService.getAllRooms();
        byte[] photoBytes = null;
        Blob photoBlob = hotel.getPhoto();
        if (photoBlob != null) {
            try {
                photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());
            } catch (SQLException e) {
                throw new PhotoRetrievalException("Error retrieving photo");
            }
        }
        String base64Photo = photoBytes != null ? Base64.encodeBase64String(photoBytes) : null;

        return new HotelRespse(hotel.getId(), hotel.getName(), hotel.getAddress(),
                hotel.getPhoneNumber(), hotel.getEmail(), hotel.getRating(), base64Photo, rooms);
    }

}
