package com.Lima.Trenelectrico.mapper;

import com.Lima.Trenelectrico.dto.EstacionDTO;
import com.Lima.Trenelectrico.model.Estacion;
import org.springframework.stereotype.Component;

@Component
public class EstacionMapper {

    public EstacionDTO toDTO(Estacion estacion) {
        EstacionDTO dto = new EstacionDTO();
        dto.setNombre(estacion.getNombre());
        dto.setUbicacion(estacion.getUbicacion());
        return dto;
    }

    public Estacion toEntity(EstacionDTO dto) {
        Estacion estacion = new Estacion();
        estacion.setNombre(dto.getNombre());
        estacion.setUbicacion(dto.getUbicacion());
        return estacion;
    }
}
