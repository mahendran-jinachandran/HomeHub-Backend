package com.homehub.core.organisation.repo;

import com.homehub.core.organisation.entity.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface OrganisationRepository extends JpaRepository<Organisation, Long> {

    Boolean existsByName(String name);


}
