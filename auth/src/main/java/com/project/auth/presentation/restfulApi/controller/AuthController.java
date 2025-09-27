package com.project.auth.presentation.restfulApi.controller;

import com.project.auth.domain.service.AuthService;
import com.project.auth.presentation.restfulApi.dto.base.CustomErrorResponse;
import com.project.auth.presentation.restfulApi.dto.base.SuccessResponse;
import com.project.auth.presentation.restfulApi.dto.request.LoginDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.management.InvalidAttributeValueException;
import java.util.Map;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/api/v1/login")
    public ResponseEntity<?> usernamePasswordLogin(@Validated @RequestBody LoginDto loginDto) {
        try {
            String token = authService.login(loginDto.phone(), loginDto.password());
            return ResponseEntity.ok(new SuccessResponse<>("Login Success", Map.of("token", token)));
        } catch (InvalidAttributeValueException ex) {
            CustomErrorResponse response = CustomErrorResponse.build("BAD_CREDENTIALS", "Invalid username or password", null);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

}
