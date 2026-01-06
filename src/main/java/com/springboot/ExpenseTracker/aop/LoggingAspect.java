package com.springboot.ExpenseTracker.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.springboot.ExpenseTracker.service.ExpenseTrackService.*(..))")
    public void logMethodCall(JoinPoint jp){
        LOGGER.info("Method Called " + jp.getSignature().getName());
    }

    @After("execution(* com.springboot.ExpenseTracker.service.ExpenseTrackService.*(..))")
    public void logMethodExecuted(JoinPoint jp){
        LOGGER.info("Method Executed "+ jp.getSignature().getName());
    }

    @AfterThrowing("execution(* com.springboot.ExpenseTracker.service.ExpenseTrackService.*(..))")
    public void logMethodCrash(JoinPoint jp){
        LOGGER.info("Method Crashed "+ jp.getSignature().getName());
    }

    @AfterReturning("execution(* com.springboot.ExpenseTracker.service.ExpenseTrackService.*(..))")
    public void logMethodReturn(JoinPoint jp){
        LOGGER.info("Method Returned Successfully "+ jp.getSignature().getName());
    }



}
