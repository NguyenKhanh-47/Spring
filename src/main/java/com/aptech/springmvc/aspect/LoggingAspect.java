package com.aptech.springmvc.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    // setup Logger
    private static final Logger myLogger = LogManager.getLogger(LoggingAspect.class);

//    @Pointcut("execution(* com.aptech.springmvc.controller.*.*(..))")
//    private void forControllerPackage() {}
//
//    @Pointcut("execution(* com.aptech.springmvc.dao.*.*(..))")
//    private void forDaoPackage() {}
//
//    @Pointcut("execution(* com.aptech.springmvc.service.*.*(..))")
//    private void forServicePackage() {}
//
//    @Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
//    private void forLoggingAspect() {}
//
//    @Before("forLoggingAspect()")
//    public void beforeLogging(JoinPoint theJoinPoint) {
//        myLogger.info("===> in @Before advice: calling method: " + theJoinPoint.getSignature().toShortString());
//
//        Object[] args = theJoinPoint.getArgs();
//        for (Object tempArg : args) {
//            myLogger.info("===> argument: " + tempArg);
//        }
//    }
//
//    @AfterReturning(pointcut = "forLoggingAspect()", returning = "theResult")
//    public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
//        myLogger.info("===> in @AfterReturning advice: calling method: " + theJoinPoint.getSignature().toShortString());
//
//        // display return data
//        myLogger.info("===> Result: " + theResult);
//    }
}
