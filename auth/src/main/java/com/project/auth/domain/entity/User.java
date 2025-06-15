package com.project.auth.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

}
