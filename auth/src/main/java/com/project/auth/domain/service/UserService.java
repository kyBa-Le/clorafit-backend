package com.project.auth.domain.service;

import com.project.auth.domain.enums.Provider;
import com.project.auth.domain.enums.Role;
import com.project.auth.domain.entity.User;
import com.project.auth.domain.exception.ExistedPhoneException;
import com.project.auth.persistence.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(String phone, String password, Role role, Provider provider) {
        User checkedUser = this.userRepository.findByPhone(phone);
        if (checkedUser != null) throw new ExistedPhoneException();

        String encryptedPassword = passwordEncoder.encode(password);
        User user = new User(phone, encryptedPassword, null, null, null, role, provider, null);

        return userRepository.save(user);
    }
}
