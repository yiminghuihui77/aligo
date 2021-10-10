package com.huihui.aligo.job;

import lombok.Setter;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.HolidayCalendar;
import org.quartz.spi.ThreadPool;

import java.util.Date;

public class JobByQuartz {

    public static void main(String[] args) throws SchedulerException {

        //创建一个调度器
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.getContext().put( "sKey", 11 );

        //Calendar用于从trigger的调度计划中排除时间段
        //Calendar必须先实例化，然后通过addCalendar()方法注册到scheduler
        HolidayCalendar calendar = new HolidayCalendar();
        calendar.addExcludedDate( new Date(1111) );
        scheduler.addCalendar( "myHolidayCalendar", calendar, false, false );

        //创建一个job
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                //当job没有与之关联的活跃的trigger时，schedule是否保留job: 非持久的job的生命期是由trigger的存在与否决定
                .storeDurably(true)
                .withDescription("我是job的描述")
                .usingJobData( "jv1", 1 )
                .usingJobData( "name", "张三" )
                .withIdentity("printJob", "simpleJobGroup")
                .build();
        jobDetail.getJobDataMap().put( "jv2", 2 );

        //开始执行时间
        long time = System.currentTimeMillis() + 3 * 1000;
        Date startTime = new Date(time);

        //创建一个触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                //触发器的优先级：只有同时触发的trigger之间才会比较优先级。10:59触发的trigger总是在11:00触发的trigger之前执行
                //quartz线程池有限的情况下，保证优先级高的trigger被优先出发
                .withPriority( 1 )
                .withDescription("我是trigger的描述")
                .usingJobData( "tv1", 3 )
                .usingJobData( "age", 23 )
                .withIdentity("printTrigger", "simpleTriggerGroup")
                .startAt(startTime)
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
                //trigger与calendar建立关联
                .modifiedByCalendar( "myHolidayCalendar" )
                .build();
        trigger.getJobDataMap().put( "tv2", 4 );



        //注册任务和触发器
        scheduler.scheduleJob(jobDetail, trigger);

        //启动定时器
        scheduler.start();


    }


    @Setter
    public static class MyJob implements Job {

        /**
         * job的实例属性只要提供setter方法，当JobExecutionContext中有对应的key是，则自动赋值
         * 因此少了：jobExecutionContext.getMergedJobDataMap().get( "name" );代码的编写
         */
        private String name;
        private Integer age;



    @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            Integer sKey = null;
            try {
                sKey = (Integer) jobExecutionContext.getScheduler().getContext().get( "sKey" );
            } catch (SchedulerException e) {
                e.printStackTrace();
            }

            //从job中获取信息
            Integer jv1 = (Integer) jobExecutionContext.getJobDetail().getJobDataMap().get( "jv1" );
            Integer jv2 = (Integer) jobExecutionContext.getJobDetail().getJobDataMap().get( "jv2" );

            //从trigger中获取信息
            Integer tv1 = (Integer) jobExecutionContext.getTrigger().getJobDataMap().get( "tv1" );
            Integer tv2 = (Integer) jobExecutionContext.getTrigger().getJobDataMap().get( "tv2" );
            //这说明job和trigger的jobDataMap不是同一个map
            Integer tvj3 = (Integer) jobExecutionContext.getJobDetail().getJobDataMap().get( "tv1" );

            //从JobExecutionContext的jobDataMap中获取信息（job和trigger的jobDataMap的并集，重复则后者覆盖前者）
            Integer j = (Integer) jobExecutionContext.getMergedJobDataMap().get( "tv1" );

            System.out.println("我是定时任务：" + new Date().getTime());
            System.out.println("sKey: " + sKey);
            System.out.println("jv1: " + jv1);
            System.out.println("jv2: " + jv2);
            System.out.println("tv1: " + tv1);
            System.out.println("tv2: " + tv2);
            System.out.println("tvj3: " + tvj3);
            System.out.println("j: " + j);
            System.out.println("----------无情的分割线-----------");
            System.out.println("name: " + name + "， age: " + age);
        }
    }
}
