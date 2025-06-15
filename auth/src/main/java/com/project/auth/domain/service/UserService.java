package com.project.auth.domain.service;

import com.project.auth.domain.entity.User;
import com.project.auth.infrastructure.PasswordEncoder;
import com.project.auth.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(String phone, String password) {
        String encryptedPassword = this.passwordEncoder.encoder().encode(password);
        User user = new User(phone, encryptedPassword);
        //todo: validation

        return userRepository.save(user);
    }
}
