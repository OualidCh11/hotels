package com.myhotel.g_hotel.controller;

import com.myhotel.g_hotel.entity.Guest;
import com.myhotel.g_hotel.repository.GuestDao;
import com.myhotel.g_hotel.response.LoginResponse;
import com.myhotel.g_hotel.response.Respnse;
import com.myhotel.g_hotel.service.inter.GuestService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
@RequiredArgsConstructor
public class AuthWS {

    @Autowired
    private final GuestService guestService;

    @PostMapping("/register")
    public ResponseEntity<Respnse> register(@RequestBody Guest guest){
        Respnse respnse = guestService.register(guest);
        return ResponseEntity.status(respnse.getStatusCode()).body(respnse);
    }

    public ResponseEntity<Respnse> login(@RequestBody LoginResponse loginResponse){
        Respnse respnse = guestService.login(loginResponse);
        return ResponseEntity.status(respnse.getStatusCode()).body(respnse);
    }


}
