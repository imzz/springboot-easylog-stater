package com.github.imzz.web.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.imzz.bean.TimePattern;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface EasyLog {
    /**
     * 时间格式化格式 如果未定义时间格式化为 默认格式yyyy-MM-dd HH:mm:ss
     * @return
     */
	TimePattern [] pattern() default {TimePattern.pattern1};
	/**
	 * 定义日志格式 
	 * 如果 注解template 值被指定 优先使用template的value作为模版
	 * 如果 注解template 值未被指定 properties 中 easylog.template 值被指定则使用easy.template 值
	 * 默认 default startTime : ${ST}  paramter :${P.ALL} requestBody : ${RB.ALL} endTime:${endTime}
	 * @return
	 */
	String [] template() default {"default startTime : ${ST} header :${HD.ALL} paramter :${P.ALL} requestBody : ${RB.ALL} endTime:${endTime}"};


}
