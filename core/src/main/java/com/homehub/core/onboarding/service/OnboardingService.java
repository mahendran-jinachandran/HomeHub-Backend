package com.homehub.core.onboarding.service;

import com.homehub.core.house.entity.House;
import com.homehub.core.house.entity.HouseMember;
import com.homehub.core.house.entity.HouseRole;
import com.homehub.core.house.service.HouseMemberService;
import com.homehub.core.house.service.HouseService;
import com.homehub.core.onboarding.dto.CreateOrganisationDTO;
import com.homehub.core.onboarding.dto.CreateOrganisationResponse;
import com.homehub.core.organisation.entity.Organisation;
import com.homehub.core.organisation.entity.OrganisationRole;
import com.homehub.core.organisation.entity.OrganisationType;
import com.homehub.core.organisation.service.OrganisationMemberService;
import com.homehub.core.organisation.service.OrganisationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OnboardingService {

    private final OrganisationService organisationService;
    private final OrganisationMemberService organisationMemberService;
    private final HouseService houseService;
    private final HouseMemberService houseMemberService;

    public OnboardingService(OrganisationService organisationService, OrganisationMemberService organisationMemberService,
                             HouseService houseService, HouseMemberService houseMemberService) {
        this.organisationService = organisationService;
        this.organisationMemberService = organisationMemberService;
        this.houseService = houseService;
        this.houseMemberService = houseMemberService;
    }

    @Transactional
    public CreateOrganisationResponse startOrganisation(Long currentUserId, CreateOrganisationDTO req) {
        OrganisationType type = req.getType();

        // Create organisation + SUPER_ADMIN membership
        Organisation org = organisationService.createOrganisation(currentUserId, req.getOrganisationName(), type);
        organisationMemberService.createOrganisationMember(org.getId(),org.getCreatedByUserId(), OrganisationRole.MEMBER);
        if (type == OrganisationType.SINGLE) {

            // Always create exactly one house and make creator house admin
            House house = houseService.createSingleHouse(org.getId(), currentUserId, "My House");
            houseMemberService.createHouseMembers(house.getId(), currentUserId, currentUserId, HouseRole.HOUSE_ADMIN);
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
