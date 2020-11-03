package com.colson.ActiveMQ.spring;

import org.apache.xbean.spring.context.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.TextMessage;

@Service
public class SpringMQ_Produce {
    @Resource
    private JmsTemplate jmsTemplate;

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        SpringMQ_Produce produce = (SpringMQ_Produce)ctx.getBean("springMQ_Produce");

        produce.jmsTemplate.send((session)->{
            TextMessage message = session.createTextMessage("spring和ActiveMQ整合111--Listener");
            return message;
        });
        System.out.println("发送完成");
    }
}
