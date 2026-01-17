package com.homehub.core.house.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HouseCreationRequest {

    @NotNull
    private Long organisationId;

    @NotNull
    private Long creatorHouseId;

    private String houseName;
}
