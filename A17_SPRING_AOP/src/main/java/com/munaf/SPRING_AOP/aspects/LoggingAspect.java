package com.munaf.SPRING_AOP.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("execution(* com.munaf.SPRING_AOP.services.ProductService.*(..))")
    private void saveProductPointcut() {}


    @Pointcut("within(com.munaf.SPRING_AOP.services.ProductService)")
    private void allMethodsOfProductService() {}

    @Before("execution(* com.munaf.SPRING_AOP.services.ProductService.saveProduct(..))")
    public void beforeLogging() {
        System.out.println("BEFORE LOGGING ASPECT CALLED");
    }

    @After("saveProductPointcut()") // after method execution if method return or exception occurred anything
    public void afterLogging() {
        System.out.println("AFTER LOGGING ASPECT CALLED");
    }

    @AfterReturning("saveProductPointcut()") // only if method executed and return some value
    public void afterReturningLogging() {
        System.out.println("AFTER-RETURNING LOGGING ASPECT CALLED");
    }

    @AfterThrowing("saveProductPointcut()") // only if method gets exceptions
    public void afterThrowingLogging() {
        System.out.println("AFTER-THROWING LOGGING ASPECT CALLED");
    }

    @Around("saveProductPointcut()")
    public Object aroundLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("BEFORE-AROUND LOGGING ASPECT CALLED");
        Object proceed = joinPoint.proceed();
        System.out.println("AFTER-AROUND LOGGING ASPECT CALLED");

        return proceed;
    }

}
