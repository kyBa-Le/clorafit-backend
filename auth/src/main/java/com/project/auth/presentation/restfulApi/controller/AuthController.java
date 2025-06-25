package com.project.auth.presentation.restfulApi.controller;

import com.project.auth.presentation.restfulApi.dto.request.LoginDto;
import com.project.auth.presentation.security.jwt_utils.JwtProvider;
import com.project.auth.presentation.security.service.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;

    public AuthController(JwtProvider jwtProvider,
                          AuthenticationManager authenticationManager) {
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDto.phone(), loginDto.password());
        Authentication authentication = authenticationManager.authenticate(token);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String jwt = jwtProvider.generateToken(userDetails.getUsername());
        return ResponseEntity.status(200).body(jwt);
    }
}
