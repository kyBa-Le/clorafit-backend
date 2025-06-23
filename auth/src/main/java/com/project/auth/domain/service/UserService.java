package com.project.auth.domain.service;

import com.project.auth.domain.entity.User;
import com.project.auth.domain.exception.ExistedPhoneException;
import com.project.auth.infrastructure.util.PasswordEncoder;
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
        User checkedUser = this.userRepository.findByPhone(phone);
        if (checkedUser != null) throw new ExistedPhoneException();

        String encryptedPassword = this.passwordEncoder.encoder().encode(password);
        User user = new User(phone, encryptedPassword);

        return userRepository.save(user);
    }
}
