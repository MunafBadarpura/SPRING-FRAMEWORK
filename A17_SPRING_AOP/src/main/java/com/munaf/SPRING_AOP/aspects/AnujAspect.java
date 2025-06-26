package com.munaf.SPRING_AOP.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Slf4j
//@Aspect
public class AnujAspect {

    @Pointcut("execution(* com.munaf.SPRING_AOP.services.AnujAspectService.saveProduct(int))")
    private void saveProductWithIdPointcut() {}


    @Pointcut("within(* com.munaf.SPRING_AOP..*) && @within(org.springframework.stereotype.Service)")
    void allServiceInApplication() {}


//    @Before("saveProductWithIdPointcut()")
//    void saveProductAdvice(JoinPoint joinPoint) {
//        log.info("save product with id called");
//        log.info("method kind : {}", joinPoint.getKind()); // output : method-execution
//        log.info("method signature: {}", joinPoint.getSignature()); // output : String com.munaf.SPRING_AOP.services.AnujAspectService.saveProduct(int)
//        log.info("method sourceLocation: {}", joinPoint.getSourceLocation()); // output : org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@68e6856c
//    }



    @Before("within(* com.munaf.SPRING_AOP..*)")
    void allPackageInsideMainFile(JoinPoint joinPoint) {
        log.info("Any method called");
    }



    @AfterReturning(value = "execution(* com.munaf.SPRING_AOP.services.AnujAspectService.saveProduct(int))", returning = "returnValue")
    void saveProductAdvice2(JoinPoint joinPoint, Object returnValue) {
        log.info("save product with id called");
        log.info("returned valure : {}", returnValue);
    }


    

}
