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
        Organisation org = organisationService.createOrganisation(currentUserId, req.getOrganisationName(), type);

        organisationMemberService.createOrganisationMember(
                    org.getId(),
                    org.getCreatedByUserId(),
                    OrganisationRole.SUPER_ADMIN,
                null
        );

        if (type == OrganisationType.SINGLE) {
           return createSingleHouse(org, currentUserId);
        }

        return createMultipleHouses(req, org.getId(), currentUserId);
    }

    private CreateOrganisationResponse createSingleHouse(Organisation org, Long currentUserId) {

        // Always create exactly one house and make creator house admin
        House house = houseService.createSingleHouse(org.getId(), currentUserId, "My House");
        houseMemberService.createHouseMembers(house.getId(), currentUserId, currentUserId, HouseRole.HOUSE_SUPER_ADMIN);
        return new CreateOrganisationResponse(org.getId(), OrganisationType.SINGLE, house.getId(), null);
    }

    private CreateOrganisationResponse createMultipleHouses(CreateOrganisationDTO req, Long orgId, Long currentUserId) {
        Integer houseCount = req.getHouseCount();
        if (houseCount != null) {
            int created = houseService.bulkCreatePlaceholderHouses(orgId, currentUserId, houseCount);
            return new CreateOrganisationResponse(orgId, OrganisationType.COMMUNITY, null, created);
        }

        return new CreateOrganisationResponse(orgId, OrganisationType.COMMUNITY, null, 0);
    }
}
