package com.chaozhis.utils;

import com.mysql.jdbc.AbandonedConnectionCleanupThread;
import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

/**
 * @author fangying | 2016-09-19.
 */
public class McfContextLoaderListener extends ContextLoaderListener {

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        super.contextDestroyed(event);
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        Driver driver;
        try {
            while (drivers.hasMoreElements()) {
                driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
            AbandonedConnectionCleanupThread.shutdown();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
