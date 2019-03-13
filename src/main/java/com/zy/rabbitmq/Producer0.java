package com.zy.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * <ul>
 * <li>文件包名 : com.zy.rabbitmq</li>
 * <li>创建时间 : 2018/8/6 11:27</li>
 * <li>修改记录 : 无</li>
 * </ul>
 * 类说明：
 *
 * @author zhengyu
 */
public class Producer0 {

    private static final String QUENE_NAME = "rabbitmq-test1";

    public static void main(String[] args) throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUENE_NAME,false,false,false,null);
        String msg = "hello,dogxxx!";
        channel.basicPublish("",QUENE_NAME,null,msg.getBytes("UTF-8"));
        System.out.println("producer msg is:"+msg);
        channel.close();
        connection.close();

    }

}
