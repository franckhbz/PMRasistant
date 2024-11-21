package com.Lima.Trenelectrico.repository;

import com.Lima.Trenelectrico.model.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {
    List<Transaccion> findByNecesitaAsistenciaTrue(); // Encuentra transacciones donde necesitaAsistencia es true
}
