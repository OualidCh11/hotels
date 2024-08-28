package com.myhotel.g_hotel.response;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginResponse {

    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;
}
