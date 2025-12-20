package com.homehub.core.onboarding.dto;

import com.homehub.core.organisation.entity.OrganisationType;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateOrganisationRequest {

    private Long userId;

    @NotBlank
    @Size(max = 255)
    private String organisationName;

    @NotNull
    private OrganisationType type; // SINGLE / COMMUNITY

    @Min(1)
    @Max(100)
    private Integer houseCount;
}
