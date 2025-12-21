package com.homehub.core.organisation.repo;

import com.homehub.core.organisation.entity.OrganisationMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganisationMemberRepository extends JpaRepository<OrganisationMember, Long> {
}
