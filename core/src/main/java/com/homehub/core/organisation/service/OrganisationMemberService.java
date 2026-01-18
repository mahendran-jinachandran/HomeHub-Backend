package com.homehub.core.organisation.service;

import com.homehub.core.organisation.entity.OrganisationMember;
import com.homehub.core.organisation.entity.OrganisationRole;
import com.homehub.core.organisation.repo.OrganisationMemberRepository;
import org.springframework.stereotype.Service;

@Service
public class OrganisationMemberService {

    private final OrganisationMemberRepository organisationMemberRepository;

    public OrganisationMemberService(OrganisationMemberRepository organisationMemberRepository) {
        this.organisationMemberRepository = organisationMemberRepository;
    }

    public OrganisationMember createOrganisationMember(Long orgId,
                                                       Long userId,
                                                       OrganisationRole role,
                                                       Long invitedByUserId) {

        OrganisationMember member = new OrganisationMember();
        member.setOrganisationId(orgId);
        member.setUserId(userId);
        member.setRole(role);
        member.setInvitedByUserId(invitedByUserId);

       return organisationMemberRepository.save(member);
    }
}
