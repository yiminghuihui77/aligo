package com.huihui.aligo.redis.lock;

import com.huihui.aligo.zookeeper.lock.Lock;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Redis实现分布式锁
 *
 * @author minghui.y
 * @create 2020-11-12 5:44 下午
 **/
public class DistributionRedisLock{

    public static final String COMMON_LOCK_KEY = "LOCK_KEY";

    private JedisPool jedisPool;

    public DistributionRedisLock(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 获取锁
     * @param acquireSeconds 尝试获取锁的时间（秒）
     * @param expireSeconds  锁的过期时间（秒）
     * @return
     */
    public boolean lock(int acquireSeconds, int expireSeconds) {

        //获取redis连接
        Jedis conn = null;

        try {
            conn = jedisPool.getResource();

            //设置尝试获取锁的截止时间
            long endAcquireTime = System.currentTimeMillis() + acquireSeconds * 1000;

            //在截止时间之前不断尝试获取锁
            while (System.currentTimeMillis() < endAcquireTime) {
                //key:COMMON_LOCK_KEY; value:当前线程的名称
                if (conn.setnx(COMMON_LOCK_KEY, Thread.currentThread().getName()) == 1) {
                    //成功获取锁
                    //设置过期时间
                    conn.expire(COMMON_LOCK_KEY, expireSeconds);
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
       //在截止时间之前获取锁失败
        return false;
    }

    /**
     * 释放锁
     */
    public void unLock() {
        //若锁是当前线程创建创建的，则删除锁
        Jedis conn = null;

        try {
            conn = jedisPool.getResource();

            String value = conn.get(COMMON_LOCK_KEY);
            if (StringUtils.isNotEmpty(value) && value.equals(Thread.currentThread().getName())) {
                conn.del(COMMON_LOCK_KEY);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.close();
            }
        }
    }
}
