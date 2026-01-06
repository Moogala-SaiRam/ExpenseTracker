package com.springboot.ExpenseTracker.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component
@Aspect
public class ValidationAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);

    @Around("execution(* com.springboot.ExpenseTracker.service.ExpenseTrackService.getById(..)) && args(id) || execution(* com.springboot.ExpenseTracker.service.ExpenseTrackService.deleteExpense(..)) && args(id) || execution(* com.springboot.ExpenseTracker.service.ExpenseTrackService.updateById(..)) && args(id)")
    public Object validateAndUpdate(ProceedingJoinPoint jp,int id) throws Throwable {
        if(id < 0){
            LOGGER.info("Id is Negative");
            id = -id;
            LOGGER.info("Id is Updated"+id);
        }
        Object obj = jp.proceed(new Object[]{id});
        return obj;
    }



}
