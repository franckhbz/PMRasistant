package com.Lima.Trenelectrico.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class PMR {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String foto; // URL de la imagen en Azure Blob Storage, obligatorio en el registro inicial

    @Column
    private String nombre; // Opcional, puede añadirse después

    @Column
    private String apellido; // Opcional, puede añadirse después

    @Column
    private String descripcion; // Información opcional

    @Column(nullable = false)
    private LocalDateTime fechaRegistro;

    public PMR() {
        this.fechaRegistro = LocalDateTime.now();
    }
}
