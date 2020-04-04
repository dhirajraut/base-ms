package com.galaxy.spring.aspect;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

import lombok.extern.log4j.Log4j2;

@Aspect
@Configuration
@Log4j2
public class LoggingAspect {

	/**
	 * Aspect to log controller time.
	 * @param joinPoint
	 * @throws Throwable
	 */
	@Around("execution(* com.galaxy.spring.*.*.*(..))")
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("Method {} with args {}", joinPoint, Arrays.toString(joinPoint.getArgs()));
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object returnedValue = joinPoint.proceed(joinPoint.getArgs());
		stopWatch.stop();
		log.info("Method {} returned {} within {} ms", joinPoint, returnedValue, stopWatch.getTotalTimeMillis());
		return returnedValue;
	}
}
