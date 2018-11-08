package cn.easy.log.bean;

public enum TimePattern {
	pattern1("yyyy-MM-dd HH:mm:ss"),pattern2("yyyyMMddHHmmss"),pattern3("yyyy/MM/dd");
	
	private final String value;
	 
	private TimePattern (String pattern){
	this.value = pattern;
	}

	public String getValue() {
		return value;
	}
	
}
