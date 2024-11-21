package com.Lima.Trenelectrico.dto;

import lombok.Data;

@Data
public class AgentRegisterDTO {
    private String name;
    private String email;
    private String password;
    private String station;
}