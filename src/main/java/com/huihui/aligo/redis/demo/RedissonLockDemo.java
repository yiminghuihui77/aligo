package com.huihui.aligo.redis.demo;

import org.checkerframework.checker.units.qual.C;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * redissonLock的自动续期demo
 *
 * @author minghui.y
 * @create 2021-10-10 5:10 下午
 **/
public class RedissonLockDemo {

    private static final String REDISSON_LOCK_PREFIX = "redisson_lock";

    public static void main( String[] args ) throws InterruptedException {
        RedissonClient client = getRedissonClient();

        RLock lock = client.getLock( REDISSON_LOCK_PREFIX );

        lock.lock();

        System.out.println( LocalDateTime.now() + "add my test lock" );

        // TODO
        newAThread( client );

        //休眠41秒
        TimeUnit.SECONDS.sleep( 41 );

        lock.unlock();
        System.out.println( LocalDateTime.now() + "delete my test lock" );

    }


    public static RedissonClient getRedissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress( "redis://127.0.0.1:6379" );

        return Redisson.create();
    }


    public static void newAThread(RedissonClient redissonClient){
        new Thread(() ->{
            while (true){

                try {
                    RLock anotherLock = redissonClient.getLock(REDISSON_LOCK_PREFIX);
                    //在waitTime之前尝试获取锁，超时则放弃锁直接返回
                    boolean lockSuccess = anotherLock.tryLock(1, -1, TimeUnit.SECONDS);
                    if(!lockSuccess){
                        System.out.println(LocalDateTime.now() + "-> try lock failed");
                    }else{
                        System.out.println(LocalDateTime.now() + "-> try lock success");
                        anotherLock.unlock();
                        System.out.println(LocalDateTime.now() + "-> delete lock success");
                        return;
                    }
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }).start();


    }

}
