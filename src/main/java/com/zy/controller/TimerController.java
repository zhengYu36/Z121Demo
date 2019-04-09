package com.zy.controller;

import com.zy.quartz.Task;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TimerController {

    @Autowired
    private StdSchedulerFactory s;

    @Autowired
    private Task task;


    @RequestMapping("/start")
    public void add() throws ClassNotFoundException, SchedulerException {
        Scheduler sche = s.getScheduler();

        task.addJob(sche, "测试", Class.forName("com.zy.quartz.Hello"), "0/3 * * * * ?");
    }


    //根据获取到的来修改定时器
    @RequestMapping("/modify")
    public void modify(String cron) throws ClassNotFoundException, SchedulerException {
        Scheduler sche = s.getScheduler();
        task.modifyJobTime(sche, "测试", cron);
    }

}




