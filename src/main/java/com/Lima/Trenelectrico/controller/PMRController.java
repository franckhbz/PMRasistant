package com.Lima.Trenelectrico.controller;

import com.Lima.Trenelectrico.dto.PMRDTO;
import com.Lima.Trenelectrico.service.PMRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pmrs")
public class PMRController {

    @Autowired
    private PMRService pmrService;

    @GetMapping
    public List<PMRDTO> getAllPMRs() {
        return pmrService.getAllPMRs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PMRDTO> getPMRById(@PathVariable Long id) {
        return ResponseEntity.ok(pmrService.getPMRById(id));
    }

    @PostMapping
    public ResponseEntity<PMRDTO> createPMR(@RequestBody PMRDTO pmrDTO) {
        PMRDTO newPMR = pmrService.createPMR(pmrDTO);
        return ResponseEntity.ok(newPMR);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PMRDTO> updatePMR(@PathVariable Long id, @RequestBody PMRDTO pmrDTO) {
        PMRDTO updatedPMR = pmrService.updatePMR(id, pmrDTO);
        return ResponseEntity.ok(updatedPMR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePMR(@PathVariable Long id) {
        pmrService.deletePMR(id);
        return ResponseEntity.noContent().build();
    }
}
