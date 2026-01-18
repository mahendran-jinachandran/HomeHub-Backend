package com.homehub.core.onboarding.dto;

import com.homehub.core.organisation.entity.OrganisationType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateOrganisationDTO {

    @NotNull
    private Long userId;

    @NotBlank
    @Size(max = 255)
    private String organisationName;

    @NotNull
    private OrganisationType type; // SINGLE / COMMUNITY

    @Min(value = 0)
    @Max(value = 50)
    private Integer houseCount;
}
