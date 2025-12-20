package com.homehub.core.organisation.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Table(name = "organisation_members",
        uniqueConstraints = @UniqueConstraint(name = "uq_org_member", columnNames = {"organisation_id", "user_id"})
)
@Data
public class OrganisationMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "organisation_id", nullable = false)
    private Long organisationId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private OrganisationRole role;

    @Column(nullable = false)
    private OffsetDateTime createdAt = OffsetDateTime.now();

    @Column(name = "invited_by_user_id")
    private Long invitedByUserId;

}
