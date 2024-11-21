package com.Lima.Trenelectrico.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TransaccionDTO {

    private Long id;

    @NotNull
    private Long idEstacion;

    @NotNull
    private Long idTren;

    @NotNull
    private String foto; // URL de la imagen en Azure Blob Storage

    @NotNull
    private String descripcion;

    @NotNull
    private LocalDateTime fechaHora;

    @NotNull
    private Long idDestino;

    private Boolean necesitaAsistencia = true; // Inicialización en true
}
