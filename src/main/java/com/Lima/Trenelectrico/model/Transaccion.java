package com.Lima.Trenelectrico.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long idEstacion;

    @Column(nullable = false)
    private Long idTren;

    @Column(nullable = false)
    private String foto; // URL de la imagen almacenada en Azure Blob Storage

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false)
    private Long idDestino;

    @Column(nullable = false)
    private Boolean necesitaAsistencia = true; // Inicia en true por defecto

}
