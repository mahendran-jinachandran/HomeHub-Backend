package com.homehub.core.onboarding.service;

import com.homehub.core.login.repo.LoginRepo;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    private final LoginRepo repo;

    public CurrentUserService(LoginRepo repo) {
        this.repo = repo;
    }
}
