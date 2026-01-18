package com.homehub.core.organisation.service;

import com.homehub.core.exception.InvalidInputException;
import com.homehub.core.login.entity.Users;
import com.homehub.core.organisation.entity.*;
import com.homehub.core.organisation.repo.OrganisationMemberRepository;
import com.homehub.core.organisation.repo.OrganisationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganisationService {

    private final OrganisationRepository organisationRepository;

    public OrganisationService(OrganisationRepository organisationRepository) {
        this.organisationRepository = organisationRepository;
    }

    @Transactional
    public Organisation createOrganisation(Long creatorUserId, String name, OrganisationType type) {

        Optional<Organisation> fetchedUser = organisationRepository.findById(creatorUserId);

        if(fetchedUser.isPresent()) {
            Organisation user = fetchedUser.get();
            boolean isUserActiveInOrganisation = user.getStatus().equals(OrganisationStatus.ACTIVE);

            if(isUserActiveInOrganisation) {
                throw new InvalidInputException("An organisation for this userId " + creatorUserId + " already exists");
            }
        }

        Boolean isOrgNameTaken = organisationRepository.existsByName(name);
        if(isOrgNameTaken) {
            throw new InvalidInputException("An organisation with the name already exists");
        }

        Organisation org = new Organisation();
        org.setName(name);
        org.setType(type);
        org.setCreatedByUserId(creatorUserId);
        org.setStatus(OrganisationStatus.ACTIVE);

        return organisationRepository.save(org);
    }
}
