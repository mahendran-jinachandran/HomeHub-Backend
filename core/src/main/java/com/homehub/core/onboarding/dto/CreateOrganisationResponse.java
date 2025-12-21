package com.homehub.core.onboarding.dto;

import com.homehub.core.organisation.entity.OrganisationType;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CreateOrganisationResponse {

    private Long organisationId;
    private OrganisationType type;

    // For SINGLE, we return the created houseId to redirect user
    private Long primaryHouseId;

    // For COMMUNITY when bulk-created
    private Integer createdHouses;
}
