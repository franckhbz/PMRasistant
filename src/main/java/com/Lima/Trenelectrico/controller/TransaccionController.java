package com.Lima.Trenelectrico.controller;
import org.springframework.web.multipart.MultipartFile;
import com.Lima.Trenelectrico.dto.TransaccionDTO;
import com.Lima.Trenelectrico.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public ResponseEntity<TransaccionDTO> createTransaccion(
            @RequestParam("idEstacion") Long idEstacion,
            @RequestParam("idTren") Long idTren,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("idDestino") Long idDestino,
            @RequestParam(value = "fechaHora") String fechaHora,
            @RequestParam("foto") MultipartFile foto) {

        // Subir la imagen a Blob Storage y obtener la URL
        String fotoUrl = transaccionService.uploadImageToBlob(foto);

        // Crear el DTO con los datos proporcionados
        TransaccionDTO transaccionDTO = new TransaccionDTO();
        transaccionDTO.setIdEstacion(idEstacion);
        transaccionDTO.setIdTren(idTren);
        transaccionDTO.setDescripcion(descripcion);
        transaccionDTO.setIdDestino(idDestino);
        transaccionDTO.setFoto(fotoUrl);

        if (fechaHora != null && !fechaHora.isEmpty()) {
            transaccionDTO.setFechaHora(LocalDateTime.parse(fechaHora));
        } else {
            transaccionDTO.setFechaHora(LocalDateTime.now());
        }
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
