package com.taxiservice.model;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for connection with DB.
 * Simplifies performing basic operations with the connection to DB.
 */
public class DBManager {
    private static final Logger LOGGER = Logger.getLogger(DBManager.class);
    private static DBManager instance;
    public DataSource dataSource;

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup(
                    "java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/taxiservice");
        } catch (NamingException e) {
            LOGGER.error("Cannot init DBManager", e);
        }
    }

    /**
     * Returns DBConnection object from Connection Pool.
     * @return DBConnection
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * Commits and closes given connection.
     * @param connection has to be committed and closed.
     */
    public void commitAndClose(Connection connection) {
        try {
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Cannot commit and close", e);
        }
    }

    /**
     * Rollbacks and closes given connection.
     * @param connection has to be rollbacked and closed.
     */
    public void rollbackAndClose(Connection connection) {
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Cannot rollback and close", e);
        }
    }

    /**
     * Close the given prepared statement.
     *
     * @param statement has to be closed.
     */
    public void close(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error("Cannot close statement", e);
            }
        }
    }

    /**
     * Close the given result set.
     *
     * @param resultSet has to be closed.
     */
    public void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.error("Cannot close resultSet", e);
            }
        }
    }
}
