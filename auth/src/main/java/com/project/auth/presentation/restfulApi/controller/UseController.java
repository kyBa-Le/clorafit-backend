package com.project.auth.presentation.restfulApi.controller;

import com.project.auth.domain.enums.Provider;
import com.project.auth.domain.enums.Role;
import com.project.auth.domain.entity.User;
import com.project.auth.domain.service.UserService;
import com.project.auth.presentation.restfulApi.dto.request.DataCreateUserDto;
import com.project.auth.presentation.restfulApi.dto.base.SuccessResponse;
import com.project.auth.presentation.restfulApi.dto.response.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UseController {

    private final UserService userService;

    public UseController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/v1/users")
    public ResponseEntity<?> register(@Validated @RequestBody DataCreateUserDto dataCreateUserDto) {
        String password = dataCreateUserDto.password();
        String phone = dataCreateUserDto.phone();
        Role role = dataCreateUserDto.role();

        User user = this.userService.createUser(phone, password, role, Provider.LOCAL);

        if (user != null) {
            UserResponseDto response = new UserResponseDto(
                    user.getID(),
                    user.getUsername(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getDateOfBirth(),
                    user.getPhone(),
                    user.getRole()
            );
            return ResponseEntity.ok().body(new SuccessResponse<>("Sign up successful", response));
        }

        return ResponseEntity.badRequest().body("Create user failed");
    }

}