package com.example.bomobomo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component @Aspect @Slf4j
public class LoggingAspect {

//    @Before("@annotation(com.example.app.aspect.annotation.LoggingPointcut)")
//    public void logBefore(JoinPoint joinPoint){
//        log.info("************************************************");
//        log.info("Method : {}", joinPoint.getSignature().toString() );
//        log.info("Args : {}", Arrays.stream(joinPoint.getArgs()).map(String::valueOf).collect(Collectors.toList()).toString());
//        log.info("************************************************");
//    }


}












