package com.Lima.Trenelectrico.service;
import com.azure.storage.blob.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import java.io.IOException;
import com.Lima.Trenelectrico.dto.TransaccionDTO;
import com.Lima.Trenelectrico.exception.TransaccionNotFoundException;
import com.Lima.Trenelectrico.mapper.TransaccionMapper;
import com.Lima.Trenelectrico.model.Transaccion;
import com.Lima.Trenelectrico.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransaccionService {
    @Value("${AZURE_STORAGE_CONNECTION_STRING}")
    private String connectionString;

    @Value("${AZURE_STORAGE_CONTAINER_NAME}")
    private String containerName;
    private final BlobContainerClient containerClient;
    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private TransaccionMapper transaccionMapper;

    @Autowired
    private PMRService pmrService;
    public TransaccionService() {

        this.containerClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient()
                .getBlobContainerClient(containerName);
    }
    public List<TransaccionDTO> getAllTransacciones() {
        return transaccionRepository.findAll()
                .stream()
                .map(transaccionMapper::toDTO)
                .collect(Collectors.toList());
    }
    public List<TransaccionDTO> getTransaccionesQueNecesitanAsistencia() {
        return transaccionRepository.findByNecesitaAsistenciaTrue()
                .stream()
                .map(transaccionMapper::toDTO)
                .collect(Collectors.toList());
    }
    public TransaccionDTO createTransaccion(TransaccionDTO transaccionDTO) {
        Transaccion transaccion = transaccionMapper.toEntity(transaccionDTO);
        Transaccion savedTransaccion = transaccionRepository.save(transaccion);

        return transaccionMapper.toDTO(savedTransaccion);
    }
    public String uploadImageToBlob(MultipartFile file) {
        try {
            String blobName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            BlobClient blobClient = containerClient.getBlobClient(blobName);

            blobClient.upload(file.getInputStream(), file.getSize(), true);

            return blobClient.getBlobUrl(); // Devuelve la URL pública del blob
        } catch (IOException e) {
            throw new RuntimeException("Error al subir la imagen a Blob Storage", e);
        }
    }
    public TransaccionDTO getTransaccionById(Long id) {
        Transaccion transaccion = transaccionRepository.findById(id)
                .orElseThrow(() -> new TransaccionNotFoundException("Transacción no encontrada con id: " + id));
        return transaccionMapper.toDTO(transaccion);
    }
    public TransaccionDTO updateTransaccion(Long id, TransaccionDTO transaccionDTO) {
        // Buscar la transacción existente por ID
        Transaccion existingTransaccion = transaccionRepository.findById(id)
                .orElseThrow(() -> new TransaccionNotFoundException("Transacción no encontrada con id: " + id));

        // Actualizar los campos de la transacción existente con los valores del DTO
        if (transaccionDTO.getIdEstacion() != null) existingTransaccion.setIdEstacion(transaccionDTO.getIdEstacion());
        if (transaccionDTO.getIdTren() != null) existingTransaccion.setIdTren(transaccionDTO.getIdTren());
        if (transaccionDTO.getFoto() != null) existingTransaccion.setFoto(transaccionDTO.getFoto());
        if (transaccionDTO.getDescripcion() != null) existingTransaccion.setDescripcion(transaccionDTO.getDescripcion());
        if (transaccionDTO.getFechaHora() != null) existingTransaccion.setFechaHora(transaccionDTO.getFechaHora());
        if (transaccionDTO.getIdDestino() != null) existingTransaccion.setIdDestino(transaccionDTO.getIdDestino());
        if (transaccionDTO.getNecesitaAsistencia() != null) existingTransaccion.setNecesitaAsistencia(transaccionDTO.getNecesitaAsistencia());

        // Guardar los cambios en la base de datos
        Transaccion updatedTransaccion = transaccionRepository.save(existingTransaccion);
        return transaccionMapper.toDTO(updatedTransaccion);
    }
    public void deleteTransaccion(Long id) {
        if (!transaccionRepository.existsById(id)) {
            throw new TransaccionNotFoundException("Transacción no encontrada con id: " + id);
        }
        transaccionRepository.deleteById(id);
    }


}
