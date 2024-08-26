package com.myhotel.g_hotel.controller;

import com.myhotel.g_hotel.entity.Hotel;
import com.myhotel.g_hotel.response.HotelRespse;
import com.myhotel.g_hotel.service.inter.HotelService;
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
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hotels")
public class HotelWS {

    private final HotelService hotelService;

    @GetMapping
    public ResponseEntity<List<Hotel>> getAllHotels() {
        List<Hotel> hotels = hotelService.getAllHotels();
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("/{id}")
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

    @PutMapping("/{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @RequestBody Hotel hotelDetails) {
        Hotel updatedHotel = hotelService.updateHotel(id, hotelDetails);
        return ResponseEntity.ok(updatedHotel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}
