package com.myhotel.g_hotel.service;

import com.myhotel.g_hotel.exception.ResourceNotFoundException;
import com.myhotel.g_hotel.repository.GuestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private GuestDao guestDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) guestDao.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("Username/Email not Found"));
    }
}
