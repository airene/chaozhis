package com.chaozhis.service;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.util.Assert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BaseService extends JdbcTemplate {

    public <T> List<T> executeQuery(String sql, Class<T> clazz, Object... params) {
        return query(sql, new BeanPropertyRowMapper<>(clazz), params);
    }

    public int executeQueryOneColumnToNumber(String sql, Object... params) {
        SqlRowSet row = queryForRowSet(sql, params);
        if (row.next()) {
            return row.getInt(1);
        } else {
            return 0;
        }
    }

    public String executeQueryOneColumnToString(String sql, Object... params) {
        SqlRowSet row = queryForRowSet(sql, params);
        if (row.next()) {
            return row.getString(1);
        } else {
            return "";
        }
    }

    public <T> T executeQueryOneRecord(String sql, Class<T> clazz, Object... params) {
        try {
            return queryForObject(sql, new BeanPropertyRowMapper<>(clazz), params);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public int executeUpdate(String sql, Object... params) {
        return update(sql, params);
    }

    public int executeUpdateReturnId(String sql, Object... params) {
        update(sql, params);
        sql = "SELECT LAST_INSERT_ID()";
        SqlRowSet row = queryForRowSet(sql);
        if (row.next()) {
            return row.getInt(1);
        } else {
            return 0;
        }
    }

    private static String getSql(Object sqlProvider) {
        return sqlProvider instanceof SqlProvider ? ((SqlProvider) sqlProvider).getSql() : null;
    }

    @Override
    public <T> T query(PreparedStatementCreator psc,
                       final PreparedStatementSetter pss, final ResultSetExtractor<T> rse)
            throws DataAccessException {

        Assert.notNull(rse, "ResultSetExtractor must not be null");
        return execute(psc, new PreparedStatementCallback<T>() {
            public T doInPreparedStatement(PreparedStatement ps)
                    throws SQLException {
                ResultSet rs = null;
                try {
                    if (pss != null) {
                        pss.setValues(ps);
                    }
                    long beginTime = System.currentTimeMillis();
                    rs = ps.executeQuery();
                    long endTime = System.currentTimeMillis();
                    if (logger.isDebugEnabled()) {
                        String sql = ps.toString();
                        sql = sql.substring(sql.indexOf(":"), sql.length());
                        logger.debug("SQL" + sql);
                        logger.debug("SQL execution time[ " + (endTime - beginTime) + "ms]");
                    }
                    ResultSet rsToUse = rs;
                    if (getNativeJdbcExtractor() != null) {
                        rsToUse = getNativeJdbcExtractor().getNativeResultSet(rs);
                    }
                    return rse.extractData(rsToUse);
                } finally {
                    JdbcUtils.closeResultSet(rs);
                    if (pss instanceof ParameterDisposer) {
                        ((ParameterDisposer) pss).cleanupParameters();
                    }
                }
            }
        });
    }

    @Override
    protected int update(final PreparedStatementCreator psc,
                         final PreparedStatementSetter pss) throws DataAccessException {

        return execute(psc, new PreparedStatementCallback<Integer>() {
            public Integer doInPreparedStatement(PreparedStatement ps)
                    throws SQLException {

                try {
                    if (pss != null) {
                        pss.setValues(ps);
                    }

                    if (logger.isDebugEnabled()) {
                        String sql = ps.toString();
                        sql = sql.substring(sql.indexOf(":"), sql.length());
                        logger.debug("SQL" + sql);
                    }
                    long beginTime = System.currentTimeMillis();
                    int rows = ps.executeUpdate();
                    long endTime = System.currentTimeMillis();
                    if (logger.isDebugEnabled()) {
                        logger.debug("SQL execution time[ " + (endTime - beginTime) + "ms]");
                    }
                    return rows;
                } finally {
                    if (pss instanceof ParameterDisposer) {
                        ((ParameterDisposer) pss).cleanupParameters();
                    }
                }
            }
        });
    }

    @Override
    public <T> T execute(PreparedStatementCreator psc, PreparedStatementCallback<T> action)
            throws DataAccessException {

        Assert.notNull(psc, "PreparedStatementCreator must not be null");
        Assert.notNull(action, "Callback object must not be null");

        Connection con = DataSourceUtils.getConnection(getDataSource());
        PreparedStatement ps = null;
        try {
            Connection conToUse = con;
            if (this.getNativeJdbcExtractor() != null &&
                    this.getNativeJdbcExtractor().isNativeConnectionNecessaryForNativePreparedStatements()) {
                conToUse = this.getNativeJdbcExtractor().getNativeConnection(con);
            }
            ps = psc.createPreparedStatement(conToUse);
            applyStatementSettings(ps);
            PreparedStatement psToUse = ps;
            if (this.getNativeJdbcExtractor() != null) {
                psToUse = this.getNativeJdbcExtractor().getNativePreparedStatement(ps);
            }
            T result = action.doInPreparedStatement(psToUse);
            handleWarnings(ps);
            return result;
        } catch (SQLException ex) {
            if (psc instanceof ParameterDisposer) {
                ((ParameterDisposer) psc).cleanupParameters();
            }
            String sql = getSql(psc);
            psc = null;
            JdbcUtils.closeStatement(ps);
            ps = null;
            DataSourceUtils.releaseConnection(con, getDataSource());
            con = null;
            throw getExceptionTranslator().translate("PreparedStatementCallback", sql, ex);
        } finally {
            if (psc instanceof ParameterDisposer) {
                ((ParameterDisposer) psc).cleanupParameters();
            }
            JdbcUtils.closeStatement(ps);
            DataSourceUtils.releaseConnection(con, getDataSource());
        }
    }
}
