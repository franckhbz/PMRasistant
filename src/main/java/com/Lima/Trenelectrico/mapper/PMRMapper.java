package com.Lima.Trenelectrico.mapper;

import com.Lima.Trenelectrico.dto.PMRDTO;
import com.Lima.Trenelectrico.model.PMR;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class PMRMapper {

    public PMRDTO toDTO(PMR pmr) {
        PMRDTO dto = new PMRDTO();
        dto.setId(pmr.getId());
        dto.setFoto(pmr.getFoto());
        dto.setNombre(pmr.getNombre());
        dto.setApellido(pmr.getApellido());
        dto.setDescripcion(pmr.getDescripcion());
        dto.setFechaRegistro(pmr.getFechaRegistro());
        return dto;
    }

    public PMR toEntity(PMRDTO dto) {
        PMR pmr = new PMR();
        pmr.setFoto(dto.getFoto());
        pmr.setNombre(dto.getNombre());
        pmr.setApellido(dto.getApellido());
        pmr.setDescripcion(dto.getDescripcion());
        pmr.setFechaRegistro(dto.getFechaRegistro() != null ? dto.getFechaRegistro() : LocalDateTime.now());
        return pmr;
    }
}
