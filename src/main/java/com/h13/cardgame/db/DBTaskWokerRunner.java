package com.h13.cardgame.db;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-18
 * Time: 下午2:41
 * To change this template use File | Settings | File Templates.
 */
public class DBTaskWokerRunner implements Runnable {

    private DBTaskWorker worker;

    public DBTaskWokerRunner(DBTaskWorker worker) {
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
