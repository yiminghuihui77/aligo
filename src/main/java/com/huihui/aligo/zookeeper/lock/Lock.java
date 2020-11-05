package com.huihui.aligo.zookeeper.lock;

/**
 * 锁接口
 *
 * @author minghui.y BG358486
 * @create 2020-10-31 20:20
 **/
public interface Lock {

    void lock();

    void unLock();
}
