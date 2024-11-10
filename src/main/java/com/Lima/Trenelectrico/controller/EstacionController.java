package com.Lima.Trenelectrico.controller;

import com.Lima.Trenelectrico.dto.EstacionDTO;
import com.Lima.Trenelectrico.mapper.EstacionMapper;
import com.Lima.Trenelectrico.model.Estacion;
import com.Lima.Trenelectrico.service.EstacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/estaciones")
public class EstacionController {
    @Autowired
    private EstacionService estacionService;

    @Autowired
    private EstacionMapper estacionMapper;

    @GetMapping
    public List<EstacionDTO> getAllEstaciones() {
        List<Estacion> estaciones = estacionService.getAllEstaciones();
        return estaciones.stream()
                         .map(estacionMapper::toDTO)
                         .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<EstacionDTO> addEstacion(@RequestBody EstacionDTO estacionDTO) {
        Estacion estacion = estacionMapper.toEntity(estacionDTO);
        Estacion nuevaEstacion = estacionService.addEstacion(estacion);
        return ResponseEntity.ok(estacionMapper.toDTO(nuevaEstacion));
    }
}
