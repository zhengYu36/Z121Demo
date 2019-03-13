package com.zy.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * <ul>
 * <li>文件包名 : com.zy.rabbitmq</li>
 * <li>创建时间 : 2018/8/6 13:42</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class Customer0 {
    private static final String QUENE_NAME = "rabbitmq-test1";

    public static void main(String[] args) throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(QUENE_NAME,false,false,false,null);

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                String str = new String(body,"UTF-8");
                System.out.println("customer is:"+str);
            }
        };

        channel.basicConsume(QUENE_NAME,true,consumer);



    }
}
