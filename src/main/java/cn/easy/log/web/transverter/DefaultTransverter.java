package cn.easy.log.web.transverter;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import cn.easy.log.bean.TimePattern;
import cn.easy.log.config.EasyLogConfig;
import cn.easy.log.web.annotation.EasyLog;

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
        Object [] params = thisMethod.getParameters();
        EasyLog easyLog = thisMethod.getAnnotation(EasyLog.class);
        TimePattern [] timePattens = easyLog.pattern();
        String time = "";
        if(timePattens.length > 0){
        JSONObject.DEFFAULT_DATE_FORMAT = timePattens[0].getValue();
        time = LocalDateTime.now().format(DateTimeFormatter.ofPattern(timePattens[0].getValue()));
        }
        StringBuffer sbuffer = new StringBuffer("");
        sbuffer.append("      easylog -----"+time+"--------");
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
