package com.github.imzz.web.transverter;

import org.aspectj.lang.ProceedingJoinPoint;

public interface Transvertor {
	
      public Object transvert(ProceedingJoinPoint point) throws Throwable;	

}
