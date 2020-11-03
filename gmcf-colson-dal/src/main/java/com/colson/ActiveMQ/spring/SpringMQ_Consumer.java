package com.colson.ActiveMQ.spring;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SpringMQ_Consumer {
    @Resource
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        SpringMQ_Consumer consumer = (SpringMQ_Consumer)applicationContext.getBean("springMQ_Consumer");
        String returnValue = (String)consumer.jmsTemplate.receiveAndConvert();
        System.out.println(returnValue);


    }
}
