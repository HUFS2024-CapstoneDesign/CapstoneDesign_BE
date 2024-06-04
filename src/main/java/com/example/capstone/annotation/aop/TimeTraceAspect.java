package com.example.capstone.annotation.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
@Aspect
public class TimeTraceAspect {

  @Pointcut("@annotation(com.example.capstone.annotation.aop.TimeTrace)")
  private void timeTracePointcut() {}

  @Around("timeTracePointcut()")
  public void traceTime(ProceedingJoinPoint joinPoint) throws Throwable {

    StopWatch stopWatch = new StopWatch();

    stopWatch.start();
    joinPoint.proceed(); // 조인포인트의 메서드 실행
    stopWatch.stop();

    long totalTimeMillis = stopWatch.getTotalTimeMillis();

    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    String methodName = signature.getMethod().getName();

    log.info("실행 메서드: {}, 실행시간 = {}ms", methodName, totalTimeMillis);
  }
}
