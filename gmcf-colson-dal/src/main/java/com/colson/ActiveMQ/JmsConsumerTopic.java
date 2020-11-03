package com.colson.ActiveMQ;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import java.io.IOException;

public class JmsConsumerTopic {
    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    public static final String TOPIC_NAME = "topic01";

    public static void main(String[] args) throws JMSException, IOException {

        System.out.println("我是2号消费者");
        //        1、创建连接工厂,按照给定的url地址，采用默认用户名和密码
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        // 2、通过连接工厂，获得连接connection并启动访问
        Connection connection = activeMQConnectionFactory.createConnection();

        connection.start();
        // 3、创建会话
        // 两个参数，第一个叫事务，第二个叫签收
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 4、创建目的地（具体是队列还是topic）
        Topic topic = session.createTopic(TOPIC_NAME);

//        同步阻塞方式
        // 5、创建消费者
        MessageConsumer consumer = session.createConsumer(topic);
//        while (true) {
//            TextMessage message = (TextMessage) consumer.receive(4000L);
//            if (null != message) {
//                System.out.println("消费者接收到消息" + message.getText());
//            }else{
//                break;
//            }
//        }
//        consumer.close();
//        session.close();
//        connection.close();

        // 通过监听的方式来消费
        // 异步非阻塞式（监听器onMessage()）
        // 订阅者或接收者通过MessageConsumer的setMessageListener(MessageListener(MessageListener listerer))注册一个消息监听器
        // 当消息到达之后，系统自动调用监听器MessageListener的onMessage(Message message)方法
        consumer.setMessageListener(message -> {
            if (null != message && message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("消费者接收消息" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        System.in.read();

    }
}
