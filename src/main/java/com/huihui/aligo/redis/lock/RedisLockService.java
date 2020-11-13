package com.huihui.aligo.redis.lock;

import lombok.Data;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author minghui.y
 * @create 2020-11-12 9:54 下午
 **/
public class RedisLockService {

    private static JedisPool jedisPool;
    /**
     * redis锁
     */
    private DistributionRedisLock lock = new DistributionRedisLock(jedisPool);

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        //最大连接数
        config.setMaxTotal(200);
        //最大空闲数
        config.setMaxIdle(8);
        //最大等待时间
        config.setMaxWaitMillis(100* 1000);
        //在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例都是可用的
        config.setTestOnBorrow(true);

        jedisPool = new JedisPool(config, "127.0.0.1", 6379, 3000);
    }

    /**
     * 构造方法私有化
     */
    private RedisLockService() {
    }

    public static JedisPool getJedisPool() {
        return jedisPool;
    }

    /**
     * 使用内部类方式的单例模式
     * @return
     */
    public static RedisLockService getInstance() {
        return RedisLockServiceHolder.redisLockService;
    }

    /**
     * 静态内部类
     */
    private static class RedisLockServiceHolder {
        private static RedisLockService redisLockService = new RedisLockService();
    }

    public DistributionRedisLock getLock() {
        return lock;
    }
}
