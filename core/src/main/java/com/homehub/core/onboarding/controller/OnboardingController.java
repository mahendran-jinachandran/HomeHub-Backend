package com.homehub.core.onboarding.controller;

import com.homehub.core.onboarding.dto.CreateOrganisationDTO;
import com.homehub.core.onboarding.dto.CreateOrganisationResponse;
import com.homehub.core.onboarding.service.CurrentUserService;
import com.homehub.core.onboarding.service.OnboardingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/onboarding")
public class OnboardingController {

    private final OnboardingService onboardingService;
    private final CurrentUserService currentUserService;

    public OnboardingController(OnboardingService onboardingService, CurrentUserService currentUserService) {
        this.onboardingService = onboardingService;
        this.currentUserService = currentUserService;
    }

    @PostMapping("/organisation")
    public ResponseEntity<CreateOrganisationResponse> createOrganisation(
            Authentication authentication,
            @Valid @RequestBody CreateOrganisationDTO req
    ) {

        Long userId = req.getUserId();

        CreateOrganisationResponse response = onboardingService.startOrganisation(userId, req);
        return ResponseEntity.ok(response);
    }
}
