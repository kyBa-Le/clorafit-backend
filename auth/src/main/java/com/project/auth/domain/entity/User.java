package com.project.auth.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class User {

    @Id
    private final String ID = UUID.randomUUID().toString();
    private String username;
    private String fullName;
    private String password;
    private String email;
    @Column(unique = true)
    private String phone;
    private Date dateOfBirth;
    @NotNull
    private Role role;

    public User(String phone, String encryptedPassword, String email, String username, Date dateOfBirth, Role role) {
        this.phone = phone;
        this.password = encryptedPassword;
        this.email = email;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
    }
}
