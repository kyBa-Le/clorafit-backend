package com.project.auth.domain.service;

import com.project.auth.domain.enums.Provider;
import com.project.auth.domain.enums.Role;
import com.project.auth.domain.entity.User;
import com.project.auth.domain.exception.ExistedPhoneException;
import com.project.auth.persistence.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    @Test
    void shouldCreateUser_whenPhoneIsNew() {
        var phone = "0869110503";
        var rawPassword = "password";
        var role = Role.CONSUMER;

        when(userRepository.findByPhone(phone)).thenReturn(null);
        when(passwordEncoder.encode(rawPassword)).thenReturn("thisIsAnEncodedPassword");

        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));
        var result = userService.createUser(phone, rawPassword, role, Provider.GOOGLE);

        assertNotNull(result);
        assertEquals(phone, result.getPhone());
        assertEquals("thisIsAnEncodedPassword", result.getPassword());
        assertEquals(role, result.getRole());

        verify(userRepository).findByPhone(phone);
        verify(passwordEncoder).encode(rawPassword);
        verify(userRepository).save(any(User.class));

    }

    @Test
    void shouldThrowExistedPhoneException_whenPhoneAlreadyExits() {
        var phone = "0869110503";
        var rawPassword = "password";
        var role = Role.CONSUMER;

        var existingUser = new User(phone, rawPassword, null, null, null, role, Provider.GOOGLE, null);
        when(userRepository.findByPhone(phone)).thenReturn(existingUser);

        assertThrows(ExistedPhoneException.class, () ->
                userService.createUser(phone, rawPassword, role, Provider.GOOGLE)
        );

        verify(userRepository).findByPhone(phone);
        verify(userRepository, never()).save(any());
    }

}