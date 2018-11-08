package cn.easy.log.web.transverter;

import org.aspectj.lang.ProceedingJoinPoint;

public interface Transvertor {
	
      public Object transvert(ProceedingJoinPoint point) throws Throwable;	

}
