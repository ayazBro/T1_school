package com.T1school.task3.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component

public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.T1school.task3.service.*.*(..))")
    public void serviceExecution() {}

    @Before("serviceExecution()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        log.info("Before: " + joinPoint.getSignature().getName() + "| using arguments: " + java.util.Arrays.toString(joinPoint.getArgs()));
    }
    @After("serviceExecution()")
    public void logAfterMethod(JoinPoint joinPoint) {
        log.info("After: " + joinPoint.getSignature().getName());
    }
    @AfterReturning(pointcut = "serviceExecution()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("AfterReturning in: " + joinPoint.getSignature().getName() + "| result: " + result);
    }
    @AfterThrowing(pointcut = "serviceExecution()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.info("AfterThrowing in: " + joinPoint.getSignature().getName() + "| exception details: " + ex.getMessage());
    }
}