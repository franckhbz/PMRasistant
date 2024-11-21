package com.Lima.Trenelectrico.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class PMRDTO {

    private Long id;

    @NotBlank
    private String foto; // URL de la imagen en Azure Blob Storage, obligatorio

    private String nombre; // Opcional

    private String apellido; // Opcional

    private String descripcion; // Información opcional

    private LocalDateTime fechaRegistro;

    // Getters y Setters
}
