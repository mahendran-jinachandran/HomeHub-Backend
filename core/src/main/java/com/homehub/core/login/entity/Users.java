package com.homehub.core.login.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.homehub.core.organisation.entity.OrganisationRole;
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

    @Column(unique = true)
    private String userName;

    @JsonIgnore
    private String password;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private OrganisationRole role;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private LocalDate created_at;
    private LocalDate updated_at;
}
