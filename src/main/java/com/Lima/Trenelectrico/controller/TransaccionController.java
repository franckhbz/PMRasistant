package com.Lima.Trenelectrico.controller;

import com.Lima.Trenelectrico.dto.TransaccionDTO;
import com.Lima.Trenelectrico.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    // Obtener todas las transacciones
    @GetMapping
    public List<TransaccionDTO> getAllTransacciones() {
        return transaccionService.getAllTransacciones();
    }

    // Obtener una transacción por su ID
    @GetMapping("/{id}")
    public ResponseEntity<TransaccionDTO> getTransaccionById(@PathVariable Long id) {
        return ResponseEntity.ok(transaccionService.getTransaccionById(id));
    }

    // Crear una nueva transacción
    @PostMapping
    public ResponseEntity<TransaccionDTO> createTransaccion(@RequestBody TransaccionDTO transaccionDTO) {
        TransaccionDTO newTransaccion = transaccionService.createTransaccion(transaccionDTO);
        return ResponseEntity.ok(newTransaccion);
    }
    @GetMapping("/necesitan-asistencia")
    public List<TransaccionDTO> getTransaccionesQueNecesitanAsistencia() {
        return transaccionService.getTransaccionesQueNecesitanAsistencia();
    }
    // Actualizar una transacción existente
    @PutMapping("/{id}")
    public ResponseEntity<TransaccionDTO> updateTransaccion(@PathVariable Long id, @RequestBody TransaccionDTO transaccionDTO) {
        TransaccionDTO updatedTransaccion = transaccionService.updateTransaccion(id, transaccionDTO);
        return ResponseEntity.ok(updatedTransaccion);
    }

    // Eliminar una transacción por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaccion(@PathVariable Long id) {
        transaccionService.deleteTransaccion(id);
        return ResponseEntity.noContent().build();
    }
}
