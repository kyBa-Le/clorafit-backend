package com.project.auth.domain.service;

import com.project.auth.domain.entity.User;
import com.project.auth.infrastructure.util.JwtProvider;
import com.project.auth.persistence.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.management.InvalidAttributeValueException;

@Service
public class AuthService {
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(JwtProvider jwtProvider, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtProvider = jwtProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String login(String phone, String password) throws InvalidAttributeValueException {
        User user = userRepository.findByPhone(phone);
        if (user == null) {
            throw new InvalidAttributeValueException("phone or password is incorrect");
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidAttributeValueException("phone or password is incorrect");
        }
        return jwtProvider.generateToken(user.getID(), user.getPhone());
    }
}
