package com.spring.learn;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class MessageListener implements ApplicationListener<MessageEvent> {

    // 容器事件发生的时候触发方法的执行
    @Override
    public void onApplicationEvent(MessageEvent event) {

        System.out.println("信息内容是xxxx:" + event.getMessageContent());

        /*if (event instanceof MessageEvent) {
            MessageEvent messageEvent = (MessageEvent) event;
            System.out.println("信息内容是:" + messageEvent.getMessageContent());
        } else {
            System.out.println("其他的信息！" + event.toString());
        }*/

    }

}