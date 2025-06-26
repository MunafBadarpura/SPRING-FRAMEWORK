package com.munaf.A17_AOP_HOMEWORK.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ExceptionHandlerAspect {

    @Pointcut("within(com.munaf.A17_AOP_HOMEWORK..*)")
    private void allMethodsPointcut() {}


    @AfterThrowing(value = "allMethodsPointcut()", throwing = "ex")
    public void exceptionHandlerAdvice(JoinPoint joinPoint, Exception ex) {
        log.info("Exception caught in method: {}, message: {}", joinPoint.getSignature().getName(), ex.getMessage());
    }

    // for better version
    @Around(value = "allMethodsPointcut()")
    public Object exceptionHandlerAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            return proceedingJoinPoint.proceed();
        } catch (Exception ex) {
            log.info("Exception caught in method: {}, msg: {}", proceedingJoinPoint.getSignature().getName(), ex.getMessage());
            return ex.getMessage();
        }
    }


}
