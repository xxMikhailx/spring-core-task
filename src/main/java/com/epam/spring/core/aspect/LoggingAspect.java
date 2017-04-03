package com.epam.spring.core.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Aspect
public class LoggingAspect {

    private Map<Class<?>, Integer> counter;

//    public void setCounter(Map<Class<?>, Integer> counter) {
//        this.counter = counter;
//    }

    @Pointcut("execution(* com.epam.spring.core.logger.*.logEvent(..))")
    private void allLogEventMethods(){}

    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint){
        System.out.println("BEFORE : " + joinPoint.getTarget().getClass().getSimpleName()
                + " " +
                joinPoint.getSignature().getName());
    }

    @AfterReturning("allLogEventMethods()")
    public void count(JoinPoint joinPoint){
        Class<?> clazz = joinPoint.getTarget().getClass();
        if (!counter.containsKey(clazz)){
            counter.put(clazz, 0);
        }
        counter.put(clazz, counter.get(clazz)+1);
    }

    @PostConstruct
    public void init(){
        counter = new HashMap<Class<?>, Integer>();
    }

    @PostConstruct
    public void destroy(){
        for (Map.Entry<Class<?>, Integer> entrySet : counter.entrySet()) {
            System.out.println(entrySet.getKey().getSimpleName() + " - " + entrySet.getValue());
        }
    }

    /*@AfterReturning(
            pointcut = "allLogEventMethods()",
            returning = "retVal")
    public void logAfter(Object retVal){
        System.out.println("Returned value = " + retVal);
    }*/

}
