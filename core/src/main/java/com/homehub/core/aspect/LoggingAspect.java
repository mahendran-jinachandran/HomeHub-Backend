package com.homehub.core.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOG = Logger.getLogger(LoggingAspect.class.getName());

    @Before("execution(public * com.homehub.core.login.service.LoginService.register(..))")
    public void registeringUser() {
        LOG.info(() -> "registering user");
    }
}
