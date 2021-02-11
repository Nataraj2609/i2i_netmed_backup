package com.netmed.usermodule.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * ExecutionTimeTrackerAdvice is an Aspect Advice around the @LogExecutionTime annotation to keep track of Execution time of a method
 *
 * @author Nataraj
 * @created 04/02/2021
 */
@Aspect
@Component
public class ExecutionTimeTrackerAdvice {
    public static final Logger logger = LoggerFactory.getLogger(ExecutionTimeTrackerAdvice.class);

    @Around("@annotation(com.netmed.usermodule.aop.LogExecutionTime)")
    public Object trackTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        StopWatch stopWatch = new StopWatch(className + "->" + methodName);
        stopWatch.start(methodName);
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();

        if (logger.isInfoEnabled()) {
            logger.info(String.valueOf(stopWatch.getTotalTimeSeconds()));
            logger.info(stopWatch.prettyPrint());
        }
        return result;
    }
}
