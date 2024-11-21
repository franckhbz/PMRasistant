package com.Lima.Trenelectrico.service;

import com.Lima.Trenelectrico.model.Estacion;
import com.Lima.Trenelectrico.repository.EstacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstacionService {

    @Autowired
    private EstacionRepository estacionRepository;

    public List<Estacion> getAllEstaciones() {
        return estacionRepository.findAll();
    }

    public Estacion addEstacion(Estacion estacion) {
        return estacionRepository.save(estacion);
    }
}
