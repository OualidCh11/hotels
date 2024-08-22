package com.myhotel.g_hotel.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse {


    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private List<ServiceRqResponse> serviceRqResponses;
}
