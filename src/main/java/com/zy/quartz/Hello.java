package com.zy.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hello implements Job {

    private static Logger log = LoggerFactory.getLogger(Hello.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("开始执行xxxx!!!!!!!!!!");
    }

}
