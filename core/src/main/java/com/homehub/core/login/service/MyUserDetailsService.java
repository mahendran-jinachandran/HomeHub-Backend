package com.homehub.core.login.service;

import com.homehub.core.login.entity.UserPrincipal;
import com.homehub.core.login.entity.Users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class MyUserDetailsService implements UserDetailsService {

    private static final Logger LOG = Logger.getLogger(MyUserDetailsService.class.getName());

    private final LoginService loginService;

    MyUserDetailsService(final LoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = loginService.findByUserName(username);

        if (user == null) {
            LOG.log(Level.SEVERE, "user not found");
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrincipal(user);
    }
}
