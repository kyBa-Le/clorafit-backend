package com.project.auth.presentation.restfulApi.controller;

import com.project.auth.domain.service.AuthService;
import com.project.auth.presentation.restfulApi.dto.base.SuccessResponse;
import com.project.auth.presentation.restfulApi.dto.request.LoginDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/api/login")
    public ResponseEntity<SuccessResponse<Map<String, String>>> usernamePasswordLogin(@RequestBody LoginDto loginDto) {
        String token = authService.authenticate(loginDto.phone(), loginDto.password());
        return ResponseEntity.ok(new SuccessResponse<>("Login Success", Map.of("token", token)));
    }

}
