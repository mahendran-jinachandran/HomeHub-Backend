package com.homehub.core.login.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "admin")
@Entity
public class SuperAdmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
}
