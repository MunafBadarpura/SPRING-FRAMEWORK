package com.munaf.A17_AOP_HOMEWORK.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class JwtValidationAspect {

    // let say we want only for controller
    @Pointcut("within(com.munaf.A17_AOP_HOMEWORK.controllers..*)")
    private void allMethodsInControllersPointcut() {}

    @Around("allMethodsInControllersPointcut()")
    public Object jwtValidationAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("ENTERD");
        Object[] args = proceedingJoinPoint.getArgs();
        String jwtToken = args[0].toString();


        System.out.println(jwtToken);
        if (jwtToken.equals("jwtToken")) {
            return proceedingJoinPoint.proceed();
        }

        return "TOKEN IS NOT VALID";
    }

}
