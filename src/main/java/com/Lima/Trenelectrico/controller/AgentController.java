package com.Lima.Trenelectrico.controller;

import com.Lima.Trenelectrico.dto.*;
import com.Lima.Trenelectrico.mapper.AgentMapper;
import com.Lima.Trenelectrico.model.Agent;
import com.Lima.Trenelectrico.service.AuthenticationService;
import com.Lima.Trenelectrico.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agents")
@CrossOrigin(origins = "*")

public class AgentController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private AgentMapper agentMapper;

    @Autowired
    private AuthenticationService authenticationService; // Inyección corregida

    // Registro de agentes
    @PostMapping("/register")
    public ResponseEntity<?> registerAgent(@RequestBody AgentRegisterDTO dto) {
        Agent agent = new Agent();
        agent.setName(dto.getName());
        agent.setEmail(dto.getEmail());
        agent.setPassword(dto.getPassword());
        agent.setStation(dto.getStation());

        Agent savedAgent = agentService.registerAgent(agent);
        return ResponseEntity.ok(agentMapper.toResponseDTO(savedAgent));
    }

    // Inicio de sesión
    @PostMapping("/login")
    public ResponseEntity<?> loginAgent(@RequestBody SigninDtoRequest dto) {
        JwtAuthenticationDtoResponse response = authenticationService.signin(dto);
        return ResponseEntity.ok(response);
    }
    // Obtener todos los agentes
    @GetMapping
    public ResponseEntity<?> getAllAgents() {
        // Obtenemos la lista de agentes desde el servicio
        List<Agent> agents = agentService.getAllAgents();

        // Mapeamos la lista de agentes a DTOs de respuesta
        List<AgentResponseDTO> agentResponseDTOs = agents.stream()
                .map(agentMapper::toResponseDTO)
                .toList();

        // Retornamos la lista de agentes en el ResponseEntity
        return ResponseEntity.ok(agentResponseDTOs);
    }

}
