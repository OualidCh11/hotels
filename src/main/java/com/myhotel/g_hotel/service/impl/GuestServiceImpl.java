package com.myhotel.g_hotel.service.impl;

import com.myhotel.g_hotel.entity.Guest;
import com.myhotel.g_hotel.exception.IntenalServiceException;
import com.myhotel.g_hotel.repository.GuestDao;
import com.myhotel.g_hotel.response.GuestRespnse;
import com.myhotel.g_hotel.response.LoginResponse;
import com.myhotel.g_hotel.response.Respnse;
import com.myhotel.g_hotel.service.inter.GuestService;
import com.myhotel.g_hotel.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuestServiceImpl implements GuestService {

    @Autowired
    private final GuestDao guestDao;
//    @Autowired
//    private final PasswordEncoder passwordEncoder;
//    @Autowired
//    private AuthenticationManager authenticationManager;
    @Override
    public Respnse register(Guest guest) {
//        Respnse respnse =new Respnse();
//        try {
//            if (guest.getRole() == null || guest.getRole().isBlank()) {
//                guest.setRole("USER");
//            }
//            if (guestDao.findByEmail(guest.getEmail())) {
//                throw new IntenalServiceException(guest.getEmail() + "Already Exists");
//            }
//            guest.setPassword(passwordEncoder.encode(guest.getPassword()));
//            Guest savedGuest = guestDao.save(guest);
//            GuestRespnse userDTO = Utils.mapUserEntityToUserDTO(savedGuest);
//            response.setStatusCode(200);
//            response.setUser(userDTO);
//        } catch (OurException e) {
//            response.setStatusCode(400);
//            response.setMessage(e.getMessage());
//        } catch (Exception e) {
//            response.setStatusCode(500);
//            response.setMessage("Error Occurred During USer Registration " + e.getMessage());
//
//        }
        return null;
    }

    @Override
    public Respnse login(LoginResponse loginResponse) {
        return null;
    }
}
