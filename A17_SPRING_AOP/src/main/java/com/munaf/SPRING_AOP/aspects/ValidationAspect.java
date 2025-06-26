package com.munaf.SPRING_AOP.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ValidationAspect {


    @Around("execution(* com.munaf.SPRING_AOP.services.AnujAspectService.saveProduct(int))")
    public Object saveProductAroundAspect(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object[] args = proceedingJoinPoint.getArgs();
        int productId = (int) args[0];

        if (productId > 0) return proceedingJoinPoint.proceed(); // it is continue

        return "OrderId is Negative";
    }


    @Around("within(com.munaf.SPRING_AOP..*)")
    public Object methodExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object returnedValue = proceedingJoinPoint.proceed();

        long end = System.currentTimeMillis();
        long time = end -start;

        log.info("For Method : {}, Execution time is : {} ms", proceedingJoinPoint.getSignature().getName(), time);

        return returnedValue;
    }
}
