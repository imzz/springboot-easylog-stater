package cn.easy.log.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.easy.log.web.annotation.EasyLog;

@RestController
@RequestMapping("/")
public class HomeController {

	
	@EasyLog
	@RequestMapping("easyLog/default/test")
	public void easyLog(){
		
	}
	
}
