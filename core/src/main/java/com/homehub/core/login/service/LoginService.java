package com.homehub.core.login.service;

import com.homehub.core.login.dto.AuthResult;
import com.homehub.core.login.dto.SignupRequestDTO;
import com.homehub.core.login.entity.Users;
import com.homehub.core.login.exception.InvalidCredentialsException;
import com.homehub.core.login.mapper.SignupMapper;
import com.homehub.core.login.repo.LoginRepo;
import com.homehub.core.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    private final LoginRepo loginRepo;
    private final JwtUtil jwtUtil;

    LoginService(LoginRepo loginRepo, JwtUtil jwtUtil) {
        this.loginRepo = loginRepo;
        this.jwtUtil = jwtUtil;
    }

    public AuthResult register(SignupRequestDTO user) {
        Users mappedUser = SignupMapper.toDTO(user);
        Users savedUser = loginRepo.save(mappedUser);

        String token = jwtUtil.generateToken(mappedUser.getUserName());
        return new AuthResult(token, savedUser.getId(), savedUser.getEmail(), savedUser.getUserName());
    }

    public Users findByUserName(String username) {
        return loginRepo.findByUserName(username);
    }

    public AuthResult login(String userName, String password) {
        Users user = findByUserName(userName);

        if (user == null) {
            throw new InvalidCredentialsException();
        }

        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException();
        }

        String token = jwtUtil.generateToken(userName);

        return new AuthResult(token, user.getId(), user.getEmail(), userName);
    }
}
