package com.Lima.Trenelectrico.service;

import com.Lima.Trenelectrico.model.Agent;
import com.Lima.Trenelectrico.repository.AgentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AgentService {

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Agent registerAgent(Agent agent) {
        if (agentRepository.findByEmail(agent.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }
        agent.setPassword(passwordEncoder.encode(agent.getPassword()));
        return agentRepository.save(agent);
    }


    public Optional<Agent> authenticateAgent(String email, String password) {
        Optional<Agent> agent = agentRepository.findByEmail(email);
        if (agent.isPresent() && passwordEncoder.matches(password, agent.get().getPassword())) {
            return agent;
        }
        return Optional.empty();
    }
    public List<Agent> getAllAgents() {
        return agentRepository.findAll();
    }

}
