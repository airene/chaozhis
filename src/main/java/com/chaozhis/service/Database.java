package com.chaozhis.service;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

import java.math.BigDecimal;
import java.sql.*;

/**
 * 数据库操作类 只在生成DTO的时候用
 *
 * @author fangying | 2014-08-15
 */
public class Database {

    private static BasicDataSource dataSource;
    private final Logger logger = Logger.getLogger(this.getClass());
    private Connection conn = null;
    private Statement stmt = null;
    private PreparedStatement ps = null;
    private boolean isAutoCommit;

    static {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("spring-config.xml"));
        PropertyPlaceholderConfigurer cfg = new PropertyPlaceholderConfigurer();
        cfg.setLocation(new ClassPathResource("appConfig.properties"));
        cfg.postProcessBeanFactory(factory);
        dataSource = factory.getBean("dataSource", BasicDataSource.class);
    }

    public Database() throws SQLException {
        conn = null;
        stmt = null;
        ps = null;
        isAutoCommit = false;
        conn = newConnection();
        conn.setAutoCommit(true);
    }

    public void addBatch() throws SQLException {
        ps.addBatch();
    }

    public void beginTrans() throws SQLException {
        isAutoCommit = conn.getAutoCommit();
        conn.setAutoCommit(false);
    }

    public void close() {
        try {
            if (ps != null) {
                ps.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public void commit() throws SQLException {
        conn.commit();
        conn.setAutoCommit(isAutoCommit);
    }

    public void executeBatch() throws SQLException {
        ps.executeBatch();
    }

    public ResultSet executeQuery() throws SQLException {
        if (ps != null) {
            return ps.executeQuery();
        } else {
            return null;
        }
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt.executeQuery(sql);
    }

    public int executeUpdate() throws SQLException {
        if (ps != null) {
            return ps.executeUpdate();
        }
        return -1;
    }

    public int executeUpdate(String sql) throws SQLException {
        int n = 0;
        try {
            stmt = conn.createStatement();
            n = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n;
    }

    public Connection getConnection() throws SQLException {
        return conn;
    }

    public PreparedStatement getPreparedStatement() {
        return ps;
    }

    private Connection newConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void prepareStatement(String sql) throws SQLException {
        ps = conn.prepareStatement(sql);
    }

    /**
     * 需要回退的异常直接吃掉
     */
    public void rollback() {
        try {
            conn.rollback();
            conn.setAutoCommit(isAutoCommit);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public void setBigDecimal(int index, BigDecimal value) throws SQLException {
        ps.setBigDecimal(index, value);
    }


    public void setBoolean(int index, boolean value) throws SQLException {
        ps.setBoolean(index, value);
    }

    public void setDate(int index, Date value) throws SQLException {
        ps.setDate(index, value);
    }

    public void setDouble(int index, double value) throws SQLException {
        ps.setDouble(index, value);
    }

    public void setFloat(int index, float value) throws SQLException {
        ps.setFloat(index, value);
    }

    public void setInt(int index, Integer value) throws SQLException {
        ps.setInt(index, value);
    }

    public void setLong(int index, long value) throws SQLException {
        ps.setLong(index, value);
    }

    public void setObject(int index, Object value) throws SQLException {
        ps.setObject(index, value);
    }

    public void setString(int index, String value) throws SQLException {
        ps.setString(index, value);
    }

    public void setTimestamp(int index, Timestamp value) throws SQLException {
        ps.setTimestamp(index, value);
    }

}
