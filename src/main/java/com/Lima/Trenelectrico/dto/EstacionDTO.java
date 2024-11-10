package com.Lima.Trenelectrico.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstacionDTO {

    @NotBlank
    private String nombre;

    @NotBlank
    private String ubicacion;
}
