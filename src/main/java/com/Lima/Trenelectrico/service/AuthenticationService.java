package com.Lima.Trenelectrico.service;
import com.Lima.Trenelectrico.dto.TransaccionDTO;
import com.Lima.Trenelectrico.dto.JwtAuthenticationDtoResponse;
import com.Lima.Trenelectrico.dto.SigninDtoRequest;
import com.Lima.Trenelectrico.model.Agent;
import com.Lima.Trenelectrico.repository.AgentRepository;
import com.Lima.Trenelectrico.security.JwtService;


import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.BadCredentialsException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final AgentRepository agentRepository;
    private final JwtService jwtService;

    public JwtAuthenticationDtoResponse signin(SigninDtoRequest request) {
        try {
            // Autenticar las credenciales
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            // Lanzar una excepción si las credenciales son incorrectas
            throw new BadCredentialsException("Credenciales incorrectas");
        }

        // Buscar el agente en la base de datos
        Agent agent = agentRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Correo no registrado"));

        // Generar el token JWT
        String jwt = jwtService.generateToken(agent);

        // Crear la respuesta
        return JwtAuthenticationDtoResponse.builder()
                .token(jwt)
                .email(agent.getEmail())
                .name(agent.getName())
                .station(agent.getStation())
                .build();
    }
}