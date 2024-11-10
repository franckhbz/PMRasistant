package com.Lima.Trenelectrico.mapper;

import com.Lima.Trenelectrico.dto.TransaccionDTO;
import com.Lima.Trenelectrico.model.Transaccion;
import org.springframework.stereotype.Component;

@Component
public class TransaccionMapper {

    public TransaccionDTO toDTO(Transaccion transaccion) {
        TransaccionDTO dto = new TransaccionDTO();
        dto.setId(transaccion.getId());
        dto.setIdEstacion(transaccion.getIdEstacion());
        dto.setIdTren(transaccion.getIdTren());
        dto.setFoto(transaccion.getFoto()); // URL de la imagen en Azure
        dto.setDescripcion(transaccion.getDescripcion()); // Nueva propiedad descripción
        dto.setFechaHora(transaccion.getFechaHora());
        dto.setIdDestino(transaccion.getIdDestino()); // Nueva propiedad idDestino
        dto.setNecesitaAsistencia(transaccion.getNecesitaAsistencia());
        return dto;
    }

    public Transaccion toEntity(TransaccionDTO dto) {
        Transaccion transaccion = new Transaccion();
        transaccion.setIdEstacion(dto.getIdEstacion());
        transaccion.setIdTren(dto.getIdTren());
        transaccion.setFoto(dto.getFoto()); // URL de la imagen en Azure
        transaccion.setDescripcion(dto.getDescripcion()); // Nueva propiedad descripción
        transaccion.setFechaHora(dto.getFechaHora());
        transaccion.setIdDestino(dto.getIdDestino()); // Nueva propiedad idDestino
        transaccion.setNecesitaAsistencia(dto.getNecesitaAsistencia());
        return transaccion;
    }
}
