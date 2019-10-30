package org.shop.aspect;



import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
    private final Logger logger = LoggerFactory.getLogger("Logger");

    @Pointcut("execution(public void org.shop.*Initializer.init*(..)) && !within(is(FinalType))")
    public void loggableInitializerMethods() { }

    @Pointcut("execution(* org.shop.api.*Service.*(..))")
    public void loggableServiceMethods() { }

    @Pointcut("execution(* org.shop.repository.*Repository.*(..))")
    public void loggableRepositoryMethods() { }

    @Pointcut("loggableInitializerMethods() || loggableServiceMethods() || loggableRepositoryMethods()")
    public void logAll() { }

    @Before("logAll()")
    public void logAfterMethodCall(JoinPoint jp) {
        logger.info("Method: {}",jp.getSignature().toString());
    }
}
