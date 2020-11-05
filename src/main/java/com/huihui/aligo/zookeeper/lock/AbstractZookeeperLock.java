package com.huihui.aligo.zookeeper.lock;

import org.I0Itec.zkclient.ZkClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * zk锁抽象类
 *  原理：
 *  1、获取锁：
 *        zookeeper天然保证不能创建相同节点，否则抛出异常
 *        尝试创建目标节点（临时节点），若创建成功，说明获的锁，可以放行，执行业务代码
 *        若创建目标节点失败，说明其他线程获得了锁，则当前线程监听目标节点的删除事件，然后执行等待（CountDownLoath实现）
 *
 *  2、释放锁：
 *         关闭zookeeper连接，目标节点（临时节点）自动被删除，触发删除节点事件
 *         其他线程监听到该 删除事件，停止等待，并停止对目标节点的监听，然后重新尝试获取锁
 *
 * @author minghui.y BG358486
 * @create 2020-10-31 20:21
 **/
public abstract class AbstractZookeeperLock implements Lock {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractZookeeperLock.class);

    protected static final String CONNECTION_ADDRESS = "127.0.0.1:2181";
    protected static final String LOCK_PATH = "/zkLock";
    protected ZkClient client = new ZkClient(CONNECTION_ADDRESS);


    @Override
    public void lock() {
        //尝试创建节点，创建成功则放行，否则执行等待，同时监听目标节点的删除事件
        if (tryLock()) {
            LOGGER.info("成功获取zookeeper分布式锁...");
        } else {
            waitForLock();
            lock();
        }
    }

    @Override
    public void unLock() {
        //断开连接，临时节点自动删除
        if (client != null) {
            client.close();
        }
    }

    /**
     * 尝试获取锁
     * @return
     */
    protected abstract boolean tryLock();

    /**
     * 等待锁
     */
    protected abstract void waitForLock();

}
