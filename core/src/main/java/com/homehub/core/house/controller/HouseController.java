package com.homehub.core.house.controller;

import com.homehub.core.house.dto.HouseCreationRequest;
import com.homehub.core.house.service.HouseService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/house")
public class HouseController {

    private final HouseService houseService;

     HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping("/create")
    public void createHouse(@Valid @RequestBody HouseCreationRequest request,
                            Authentication authentication) {
         houseService.createSingleHouse(
                 request.getOrganisationId(),
                 request.getCreatorHouseId(),
                 request.getHouseName());
    }


}
