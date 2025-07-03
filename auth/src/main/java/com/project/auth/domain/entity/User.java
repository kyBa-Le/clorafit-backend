package com.project.auth.domain.entity;

import com.project.auth.domain.enums.Provider;
import com.project.auth.domain.enums.Role;
import com.project.auth.domain.enums.Status;
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
    @NotNull
    private Provider provider;
    private String address;
    private Status status = Status.ACTIVE;


    public User(String phone, String encryptedPassword, String email, String username,
                Date dateOfBirth, Role role, Provider provider, String address) {
        this.phone = phone;
        this.password = encryptedPassword;
        this.email = email;
        this.username = username;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.provider = provider;
        this.address = address;
    }
}
