package com.project.auth.presentation.restfulApi.controller;

import com.project.auth.domain.service.AuthService;
import com.project.auth.presentation.restfulApi.dto.base.CustomErrorResponse;
import com.project.auth.presentation.restfulApi.dto.base.SuccessResponse;
import com.project.auth.presentation.restfulApi.dto.request.LoginDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
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
    public ResponseEntity<?> usernamePasswordLogin(@RequestBody LoginDto loginDto) {
        try {
            String token = authService.authenticate(loginDto.phone(), loginDto.password());
            return ResponseEntity.ok(new SuccessResponse<>("Login Success", Map.of("token", token)));

        } catch (BadCredentialsException ex) {
            return responseError("BAD_CREDENTIALS", "Invalid username or password");
        } catch (DisabledException ex) {
            return responseError("ACCOUNT_DISABLED", "Your account is disabled");
        } catch (LockedException ex) {
            return responseError("ACCOUNT_LOCKED", "Your account is locked");
        } catch (AuthenticationException ex) {
            return responseError("AUTH_ERROR", ex.getMessage());
        }
    }

    private ResponseEntity<CustomErrorResponse> responseError(String code, String message) {
        CustomErrorResponse response = CustomErrorResponse.build(code, message, null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

}
