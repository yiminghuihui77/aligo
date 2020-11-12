package com.huihui.aligo.job;

import java.util.Timer;
import java.util.TimerTask;

public class JobByTimerTask {

    public static void main(String[] args) {
        //jdk自带的定时任务
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("我是一个定时任务");
            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 1000);

    }



}
