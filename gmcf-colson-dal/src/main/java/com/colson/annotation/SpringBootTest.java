package com.colson.annotation;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = "classpath:/application.properties")
public class SpringBootTest {

	//注入普通字符串
	@Value("testValue")
	private String testValue;

	//注入操作系统属性
	@Value("#{systemProperties['os.name']}")
	private String systemPropertiesName;

	//注入表达式结果
	@Value("#{T(java.lang.Math).random()*100.0}")
	private double randomNumber;

	@Value("${domain.name}")
	private String domainName;




	@Test
	public void testAnnotation(){
		System.out.println(testValue);

		System.out.println(systemPropertiesName);

		System.out.println(randomNumber);

		System.out.println(domainName);
	}

}
