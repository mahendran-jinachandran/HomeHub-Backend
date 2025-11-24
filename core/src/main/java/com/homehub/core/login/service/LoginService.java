package com.homehub.core.login.service;

import com.homehub.core.login.controller.LoginController;
import com.homehub.core.login.entity.Users;
import com.homehub.core.login.repo.LoginRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class LoginService {

    private static final Logger LOG = Logger.getLogger(LoginController.class.getName());

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private LoginRepo loginRepo;


    LoginService(final LoginRepo loginRepo) {
        this.loginRepo = loginRepo;
    }

    public Users register(Users user) {
        LOG.info(() -> "registering user");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return loginRepo.save(user);
    }

    public Users findByUserName(String username) {
        return loginRepo.findByUserName(username);
    }
}
