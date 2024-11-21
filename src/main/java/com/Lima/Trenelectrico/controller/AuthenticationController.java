package com.Lima.Trenelectrico.controller;
import com.Lima.Trenelectrico.dto.SigninDtoRequest;
import com.Lima.Trenelectrico.dto.JwtAuthenticationDtoResponse;
import com.Lima.Trenelectrico.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationDtoResponse> signin(@RequestBody SigninDtoRequest request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}
