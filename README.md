# springboot-easylog-stater
帮助记录springboot项目日志

使用方法引入jar包 在需要添加日志的方法上添加 @EasyLog注解


	@EasyLog
	public String test1(String name,String id){
		return "test1";
	}
		
日志格式

 easylog -----2018-11-08 16:10:10--------requestUrl  :  /test1------------invokeClasss  :  com.example.demo.TestService-------methodName  :  test1--------- params  :  ["params1",null]
 
 
 中央仓库地址 
 <dependency>
        <groupId>com.github.imzz</groupId>
	<artifactId>spring-boot-easylog-stater</artifactId>
	<version>1.0</version>
</dependency>

