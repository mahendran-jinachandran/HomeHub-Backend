package com.homehub.core.organisation.service;

import com.homehub.core.organisation.entity.Organisation;
import com.homehub.core.organisation.entity.OrganisationMember;
import com.homehub.core.organisation.entity.OrganisationRole;
import com.homehub.core.organisation.entity.OrganisationType;
import com.homehub.core.organisation.repo.OrganisationMemberRepository;
import com.homehub.core.organisation.repo.OrganisationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OrganisationService {

    private final OrganisationRepository organisationRepository;
//    private final OrganisationMemberRepository organisationMemberRepository;

    public OrganisationService(OrganisationRepository organisationRepository) {

        this.organisationRepository = organisationRepository;
//        this.organisationMemberRepository = organisationMemberRepository;
    }


    @Transactional
    public Organisation createOrganisation(Long creatorUserId, String name, OrganisationType type) {
        Organisation org = new Organisation();
        org.setName(name);
        org.setType(type);
        org.setCreatedByUserId(creatorUserId);

//        OrganisationMember member = new OrganisationMember();
//        member.setOrganisationId(saved.getId());
//        member.setUserId(creatorUserId);
//        member.setRole(OrganisationRole.SUPER_ADMIN);
//
//        organisationMemberRepository.save(member);

        return organisationRepository.save(org);
    }
}
