package com.homehub.core.login.controller;

import com.homehub.core.login.dto.AuthResult;
import com.homehub.core.login.dto.SignupRequestDTO;
import com.homehub.core.login.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Login", description = "API for registering and logging in")
public class LoginController {

    private final LoginService loginService;

    LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public AuthResult login(@RequestParam String userName, @RequestParam String password) {
        return loginService.login(userName, password);
    }

    @PutMapping("/signup")
    @Operation(summary = "Registering the user")
    public AuthResult register(@Valid @RequestBody SignupRequestDTO user) {
        return loginService.register(user);
    }
}

