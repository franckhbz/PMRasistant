package com.Lima.Trenelectrico.service;

import com.Lima.Trenelectrico.dto.PMRDTO;
import com.Lima.Trenelectrico.exception.PMRNotFoundException;
import com.Lima.Trenelectrico.mapper.PMRMapper;
import com.Lima.Trenelectrico.model.PMR;
import com.Lima.Trenelectrico.repository.PMRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PMRService {

    @Autowired
    private PMRRepository pmrRepository;

    @Autowired
    private PMRMapper pmrMapper;

    public void createOrVerifyPMRByFoto(String fotoUrl) {
        // Verificar si existe un PMR con la misma URL de foto
        PMR existingPMR = pmrRepository.findByFoto(fotoUrl);

        // Si no existe, crear uno nuevo
        if (existingPMR == null) {
            PMRDTO newPMRDTO = new PMRDTO();
            newPMRDTO.setFoto(fotoUrl); // Solo se guarda la foto en el registro inicial
            PMR newPMR = pmrMapper.toEntity(newPMRDTO);
            pmrRepository.save(newPMR);
        }
    }

    public List<PMRDTO> getAllPMRs() {
        return pmrRepository.findAll()
                .stream()
                .map(pmrMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PMRDTO getPMRById(Long id) {
        PMR pmr = pmrRepository.findById(id)
                .orElseThrow(() -> new PMRNotFoundException("PMR no encontrado con id: " + id));
        return pmrMapper.toDTO(pmr);
    }

    public PMRDTO createPMR(PMRDTO pmrDTO) {
        PMR pmr = pmrMapper.toEntity(pmrDTO);
        PMR savedPMR = pmrRepository.save(pmr);
        return pmrMapper.toDTO(savedPMR);
    }

    public PMRDTO updatePMR(Long id, PMRDTO pmrDTO) {
        PMR pmr = pmrRepository.findById(id)
                .orElseThrow(() -> new PMRNotFoundException("PMR no encontrado con id: " + id));

        if (pmrDTO.getNombre() != null) pmr.setNombre(pmrDTO.getNombre());
        if (pmrDTO.getApellido() != null) pmr.setApellido(pmrDTO.getApellido());
        if (pmrDTO.getDescripcion() != null) pmr.setDescripcion(pmrDTO.getDescripcion());

        PMR updatedPMR = pmrRepository.save(pmr);
        return pmrMapper.toDTO(updatedPMR);
    }

    public void deletePMR(Long id) {
        if (!pmrRepository.existsById(id)) {
            throw new PMRNotFoundException("PMR no encontrado con id: " + id);
        }
        pmrRepository.deleteById(id);
    }
}
