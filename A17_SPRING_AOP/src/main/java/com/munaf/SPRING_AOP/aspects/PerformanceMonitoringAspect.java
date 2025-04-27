package com.munaf.SPRING_AOP.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceMonitoringAspect {

    @Pointcut("execution(* com.munaf.SPRING_AOP.services.ProductService.*(..))")
    private void ProductServiceAllMethods() {};

    @Around("ProductServiceAllMethods()")
    public Object PerformanceTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = proceedingJoinPoint.proceed();

        long end = System.currentTimeMillis();

        System.out.println(
                "FOR METHOD : " + proceedingJoinPoint.getSignature().getName() +
                ", TIME TAKEN IS : " + (end-start) +
                " MILE-SECONDS"
        );

        return proceed;
    }

}
