package com.myhotel.g_hotel.response;

import com.myhotel.g_hotel.entity.Guest;
import com.myhotel.g_hotel.entity.Room;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceRqResponse {


    private Long id;
    private LocalDateTime requestDate;
    private String status;
    private Room room;
    private Guest guest;
    private ServiceResponse serviceResponse;
}
