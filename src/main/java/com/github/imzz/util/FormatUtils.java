package com.github.imzz.util;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;


public class FormatUtils {
	/**
	 * 将要匹配的字段从模版中提取出来
	 * 
	 * @param template
	 * @return
	 */
	public static List<String> getAllPattern(String template) {
		List<String> params = new ArrayList<>();
		String regex = "(?<=\\{)[^(?=\\})]*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(template);
		while (matcher.find()) {
			params.add(matcher.group());
		}
		return params;
	}

	public static String chooseTemplate(String annotationTemplate, String propertiesTemplate) {
		if (!StringUtils.isEmpty(annotationTemplate) && !annotationTemplate.startsWith("default")) {
			return annotationTemplate;
		}
		return propertiesTemplate;
	}

	public static String getLog(String template, Method method, HttpServletRequest request,String timePattern) {
		List<String> patterns = getAllPattern(template);
		if(patterns.isEmpty()){
			return template;
		}else{
			for (String string : patterns) {
			}
		}
		return "";
	}
    
	public static String replacePattern (String pattern,String template, Method method, HttpServletRequest request,String timePattern) {
		timePattern = timePattern == null || "".equals(timePattern.trim()) ? "yyyy-MM-dd HH:mm:ss" : timePattern;
		if("ST".equals(pattern)){
	    	template.replace(pattern, LocalDateTime.now().format(DateTimeFormatter.ofPattern(timePattern)));
	    }else if(pattern.startsWith("HD")){
	    	String param = pattern.substring(pattern.indexOf("\\.")+1);
	    	if(param.isEmpty()){
	    		return template;
	    	}
	    	if("ALL".equals(param.trim())){
	    		 
	    	}else {
	    		String value = request.getHeader(param.trim());
	    		template.replace("${"+pattern+"}", value);
	    	}
	    	
	    }
		return template;
	}
	
	
	public static void main(String[] args) {
//		String template = "default startTime : ${ST} header :${HD.ALL} paramter :${P.ALL} requestBody : ${RB.ALL} endTime:${ET}";
//		List<String> params = FormatUtils.getAllPattern(template);
//		System.out.println(params);
		
		String a = "123.345";
		System.out.println(a.substring(a.indexOf(".")+1));;
	}
}
