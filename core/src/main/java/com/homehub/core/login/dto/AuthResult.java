package com.homehub.core.login.dto;

import lombok.Data;

@Data
public class AuthResult {

    private final String token;
    private final long userId;
    private final String email;
    private final String userName;

    public AuthResult(String token, long userId, String email, String userName) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public long getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }
}
