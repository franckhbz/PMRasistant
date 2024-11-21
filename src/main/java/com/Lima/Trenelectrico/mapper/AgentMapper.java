package com.Lima.Trenelectrico.mapper;

import com.Lima.Trenelectrico.dto.AgentRegisterDTO;
import com.Lima.Trenelectrico.dto.AgentResponseDTO;
import com.Lima.Trenelectrico.model.Agent;
import org.springframework.stereotype.Component;

@Component
public class AgentMapper {

    public Agent toEntity(AgentRegisterDTO dto) {
        Agent agent = new Agent();
        agent.setName(dto.getName());
        agent.setEmail(dto.getEmail());
        agent.setPassword(dto.getPassword());
        agent.setStation(dto.getStation());
        return agent;
    }

    public AgentResponseDTO toResponseDTO(Agent agent) {
        AgentResponseDTO dto = new AgentResponseDTO();
        dto.setName(agent.getName());
        dto.setEmail(agent.getEmail());
        dto.setStation(agent.getStation());
        return dto;
    }
}
