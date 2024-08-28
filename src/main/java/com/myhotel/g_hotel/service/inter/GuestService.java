package com.myhotel.g_hotel.service.inter;

import com.myhotel.g_hotel.entity.Guest;
import com.myhotel.g_hotel.response.LoginResponse;
import com.myhotel.g_hotel.response.Respnse;

public interface GuestService {
    Respnse register(Guest guest);

    Respnse login(LoginResponse loginResponse);
}
