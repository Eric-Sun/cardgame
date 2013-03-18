package com.h13.cardgame.core.listener;

import com.h13.cardgame.config.ConfigStarter;
import com.h13.cardgame.core.utils.WebApplicationContentHolder;
import com.h13.cardgame.queue.worker.DBTaskWokerRunner;
import com.h13.cardgame.queue.worker.DBTaskWorker;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created with IntelliJ IDEA.
 * User: sunbo
 * Date: 13-3-12
 * Time: 下午3:52
 * To change this template use File | Settings | File Templates.
 */
public class CardGameListener implements ServletContextListener {
    private static Log LOG = LogFactory.getLog(CardGameListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        WebApplicationContentHolder.setServletContext(sce.getServletContext());
        ConfigStarter configStarter = WebApplicationContentHolder.getApplicationContext().getBean(ConfigStarter.class);
        configStarter.init();
        LOG.info("card game config successfully.");
        DBTaskWorker worker = WebApplicationContentHolder.getApplicationContext().getBean(DBTaskWorker.class);
        DBTaskWokerRunner runner = new DBTaskWokerRunner(worker);
        Thread t = new Thread(runner);
        t.start();
        LOG.info("worker thread started successfully.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
