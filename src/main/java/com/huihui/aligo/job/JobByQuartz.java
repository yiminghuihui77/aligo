package com.huihui.aligo.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class JobByQuartz {

    public static void main(String[] args) throws SchedulerException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withDescription("我是job的描述")
                .withIdentity("printJob", "simpleJobGroup")
                .build();

        //开始执行时间
        long time = System.currentTimeMillis() + 3 * 1000;
        Date startTime = new Date(time);

        Trigger trigger = TriggerBuilder.newTrigger()
                .withDescription("我是trigger的描述")
                .withIdentity("printTrigger", "simpleTriggerGroup")
                .startAt(startTime)
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                .build();

        //注册任务和触发器
        scheduler.scheduleJob(jobDetail, trigger);

        //启动定时器
        scheduler.start();


    }


    public static class MyJob implements Job {

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            System.out.println("我是定时任务：" + new Date().getTime());
        }
    }
}
