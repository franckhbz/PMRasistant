package com.Lima.Trenelectrico.repository;

import com.Lima.Trenelectrico.model.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionRepository extends JpaRepository<Estacion, Long> {

}
