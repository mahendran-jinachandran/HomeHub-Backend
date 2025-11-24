package com.homehub.core.login.controller;

import com.homehub.core.login.entity.Users;
import com.homehub.core.login.service.LoginService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    private final LoginService loginService;

    LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PutMapping("/signup")
    public Users register(@RequestBody Users user) {
        user.setRole("ADMIN");
        return loginService.register(user);
    }
}

