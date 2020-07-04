package org.yzl.java.frame.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsConsumer {

    public static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";

    public static final String QUEUE_NAME = "queue01";

    public static void main(String[] args) {

        try {
            //1.创建连接工厂,按照给定的url地址,采用默认用户名和密码
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
            //2.通过连接工场,获得连接connection
            Connection connection = activeMQConnectionFactory.createConnection();
            connection.start();
            //3.创建会话session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //4.创建目的地,具体是队列还是主题
            Queue queue = session.createQueue(QUEUE_NAME);
            //5.创建消息的消费者
            MessageConsumer consumer = session.createConsumer(queue);

            while(true) {

                TextMessage textMessage = (TextMessage) consumer.receive();

                if(textMessage != null) {
                    System.out.println("*****接受消息:" + textMessage.getText());
                }else {
                    break;
                }
            }

            consumer.close();
            session.close();
            connection.close();
            System.out.println("****消息发布到关闭成功");

        }catch (Exception e) {
            e.printStackTrace();
        }

    }
}
