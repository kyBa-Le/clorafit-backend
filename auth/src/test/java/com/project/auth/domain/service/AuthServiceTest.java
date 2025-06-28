package com.project.auth.domain.service;

import com.project.auth.infrastructure.util.JwtProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
    @Mock
    private JwtProvider jwtProvider;
    @Mock
    private AuthenticationManager authenticationManager;
    @InjectMocks
    private AuthService authService;

    @Test
    void shouldReturnToken_whenCredentialIsValid() {
        // Arrange the data for the test
        var phone = "0869110503";
        var password = "password";
        var expectedToken = "expectedToken";

        // Mock the return value of the internal behavior
        Authentication mockAuthentication = mock(Authentication.class);
        UserDetails mockUserDetails = mock(UserDetails.class);

        // Mock the behavior
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(mockAuthentication);
        when(mockAuthentication.getPrincipal()).thenReturn(mockUserDetails);
        when(mockUserDetails.getUsername()).thenReturn(phone);
        when(jwtProvider.generateToken(phone)).thenReturn(expectedToken);

        // Act
        var token = authService.authenticate(phone, password);

        // Assert
        assertEquals(expectedToken, token);

        // Verify
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtProvider).generateToken(phone);

    }

    @Test
    void shouldThrowBadCredentialsException_whenInvalidCredentials() {
        var invalidPhone = "0869000000";
        var invalidPassword = "password";

        // Mock AuthenticationManager throw exception
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Bad credentials"));

        // Act & Assert
        assertThrows(BadCredentialsException.class, () -> authService.authenticate(invalidPhone, invalidPassword));

        // Verify interaction
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));

    }
}