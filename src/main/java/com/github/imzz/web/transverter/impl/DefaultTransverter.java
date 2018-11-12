package com.github.imzz.web.transverter.impl;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.imzz.bean.TimePattern;
import com.github.imzz.config.EasyLogConfig;
import com.github.imzz.web.annotation.EasyLog;
import com.github.imzz.web.transverter.Transvertor;



@Service
public class DefaultTransverter implements Transvertor{
	//引入配置文件配置
    @Autowired
    EasyLogConfig easyLogConfig;
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
	@Override
	public Object transvert(ProceedingJoinPoint point)throws Throwable {
      try{  MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> clazz = point.getTarget().getClass();
        Method thisMethod = clazz.getMethod(method.getName(), method.getParameterTypes());
        String methodName = thisMethod.getName();
        Object [] params = point.getArgs();
        EasyLog easyLog = thisMethod.getAnnotation(EasyLog.class);
        //获取日式格式
        TimePattern [] timePattens = easyLog.pattern();
        String time = "";
        if(timePattens.length > 0){
        //设置fastjson默认日期格式 每次都设置不知道行不行 待考察
        JSONObject.DEFFAULT_DATE_FORMAT = timePattens[0].getValue();
        time = LocalDateTime.now().format(DateTimeFormatter.ofPattern(timePattens[0].getValue()));
        }
        
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes == null ? null : ((ServletRequestAttributes) requestAttributes).getRequest();
        String requestUrl = request == null ? null : request.getRequestURI();
        
        StringBuffer sbuffer = new StringBuffer("");
        sbuffer.append("      easylog -----"+time+"--------");
        sbuffer.append("requestUrl  :  ");
        sbuffer.append(requestUrl+"------------");
        sbuffer.append("invokeClasss  :  "+point.getTarget().getClass().getName());
        sbuffer.append("-------methodName  :  ");
        sbuffer.append(methodName);
        sbuffer.append("--------- params  :  ");
        sbuffer.append(JSON.toJSONString(params,SerializerFeature.WriteMapNullValue));
        logger.info(sbuffer.toString());
       }catch(Exception e){
    	   logger.error("easylog error ---------------->");
    	   e.printStackTrace();
       }	  
		return point.proceed();
	}

	
	
}
