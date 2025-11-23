package com.homehub.core.login.service;

import com.homehub.core.login.repo.LoginRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final LoginRepo loginRepo;

    LoginService(final LoginRepo loginRepo) {
        this.loginRepo = loginRepo;
    }

    public void register(String username, String password) {
//       loginRepo
    }
}
