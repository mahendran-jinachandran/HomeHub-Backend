package com.homehub.core.onboarding.service;

import com.homehub.core.house.entity.House;
import com.homehub.core.house.service.HouseService;
import com.homehub.core.onboarding.dto.CreateOrganisationDTO;
import com.homehub.core.onboarding.dto.CreateOrganisationResponse;
import com.homehub.core.organisation.entity.Organisation;
import com.homehub.core.organisation.entity.OrganisationType;
import com.homehub.core.organisation.service.OrganisationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OnboardingService {

    private final OrganisationService organisationService;
    private final HouseService houseService;

    public OnboardingService(OrganisationService organisationService, HouseService houseService) {
        this.organisationService = organisationService;
        this.houseService = houseService;
    }

    @Transactional
    public CreateOrganisationResponse startOrganisation(Long currentUserId, CreateOrganisationDTO req) {
        OrganisationType type = req.getType();

        // Create organisation + SUPER_ADMIN membership
        Organisation org = organisationService.createOrganisation(currentUserId, req.getOrganisationName(), type);
        if (type == OrganisationType.SINGLE) {
            // Always create exactly one house and make creator house admin
            House house = houseService.createSingleHouse(org.getId(), currentUserId);
            return new CreateOrganisationResponse(org.getId(), type, house.getId(), null);
        }

        // COMMUNITY
        Integer houseCount = req.getHouseCount();
        if (houseCount != null) {
            int created = houseService.bulkCreatePlaceholderHouses(org.getId(), currentUserId, houseCount);
            return new CreateOrganisationResponse(org.getId(), type, null, created);
        }

        // Recommended path: create houses later from "Manage Houses" UI
        return new CreateOrganisationResponse(org.getId(), type, null, 0);
    }
}
