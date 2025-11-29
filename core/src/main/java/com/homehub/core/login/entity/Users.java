package com.homehub.core.login.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Table(name = "users")
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;

    @JsonIgnore
    private String password;
    private String email;
    private String role;
    private String status;
    private LocalDate created_at;
    private LocalDate updated_at;
}
