package com.krishav.completesetup.completesetup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class LoginResponseDTO {

    private Long id;
    private String accessToken;
    private String refreshToken;
}
