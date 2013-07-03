package com.h13.cardgame.queue;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-7-2
 * Time: 下午4:56
 * To change this template use File | Settings | File Templates.
 */
public class CacheUpdateWorkerRunner implements Runnable {

    private CacheUpdateWorker worker;

    public CacheUpdateWorkerRunner(CacheUpdateWorker worker) {
        this.worker = worker;
    }

    @Override
    public void run() {
        while (true) {
            worker.doProcess();
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

    }
}

