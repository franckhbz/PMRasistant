package com.Lima.Trenelectrico.repository;

import com.Lima.Trenelectrico.model.PMR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PMRRepository extends JpaRepository<PMR, Long> {
    PMR findByFoto(String fotoUrl); // Método para encontrar PMR por foto
}
