package com.project.auth.presentation.restfulApi.controller;

import com.project.auth.domain.entity.User;
import com.project.auth.domain.service.UserService;
import com.project.auth.presentation.restfulApi.dto.request.DataCreateUserDto;
import com.project.auth.presentation.restfulApi.dto.response.SuccessResponse;
import com.project.auth.presentation.restfulApi.dto.response.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class UseController {

    private final UserService userService;

    public UseController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<?> register(@Validated @RequestBody DataCreateUserDto dataCreateUserDto) {
        String password = dataCreateUserDto.password();
        String phone = dataCreateUserDto.phone();

        User user = this.userService.createUser(phone, password);

        if (user != null) {
            UserResponseDto response = new UserResponseDto(
                    user.getID(),
                    user.getUsername(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getDateOfBirth(),
                    user.getPhone()
            );
            return ResponseEntity.ok().body(new SuccessResponse<>("Sign up successful", response));
        }

        return ResponseEntity.badRequest().body("Create user failed");

    }

}