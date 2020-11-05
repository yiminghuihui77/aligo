package com.huihui.aligo.zookeeper.lock;

import org.I0Itec.zkclient.IZkDataListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * zookeeper实现分布式锁
 *
 * @author minghui.y BG358486
 * @create 2020-10-31 20:30
 **/
public class DistributionZookeeperLock extends AbstractZookeeperLock {

    private static final Logger LOGGER = LoggerFactory.getLogger(DistributionZookeeperLock.class);

    private CountDownLatch countDownLatch = null;


    private IZkDataListener listener = new IZkDataListener() {
        //监听数据变化
        @Override
        public void handleDataChange(String dataPath, Object data) throws Exception {

        }
        //监听数据被删除
        @Override
        public void handleDataDeleted(String dataPath) throws Exception {
            LOGGER.info("监听到目标节点：{}被删除...", LOCK_PATH);
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    };


    /**
     * 尝试获取锁
     * @return
     */
    @Override
    protected boolean tryLock() {
        try {
            client.createEphemeral(LOCK_PATH);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    protected void waitForLock() {
            //监听目标节点被删除
            client.subscribeDataChanges(LOCK_PATH, listener);

            if (client.exists(LOCK_PATH)) {
                countDownLatch = new CountDownLatch(1);

                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //放弃监听目标节点
            client.unsubscribeDataChanges(LOCK_PATH, listener);
    }


}
