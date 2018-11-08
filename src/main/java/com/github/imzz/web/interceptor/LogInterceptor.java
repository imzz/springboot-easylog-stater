package com.github.imzz.web.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.imzz.web.transverter.Transvertor;

@Aspect
@Component
public class LogInterceptor {

@Autowired
Transvertor transvertor;
	
Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("@annotation(com.github.imzz.web.annotation.EasyLog)")
	public void Pointcut (){
		
	}
	
	@Around("Pointcut()")
    public Object transactionRunning(ProceedingJoinPoint point)throws Throwable{        
        return transvertor.transvert(point);
    }

}
