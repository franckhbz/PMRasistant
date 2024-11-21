package com.Lima.Trenelectrico.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtAuthenticationDtoResponse {
    private String token;
    private String email;
    private String name;
    private String station;
}
