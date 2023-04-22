package de.oliver.fancylib.databases;

import java.sql.Connection;
import java.sql.ResultSet;

public interface Database {

    /**
     * Connects to the database
     * @return true if success, false if failed
     */
    boolean connect();

    /**
     * Closes the database connection
     * @return true if success, false if failed
     */
    boolean close();

    /**
     * @return true if connected, false if not
     */
    boolean isConnected();

    /**
     * @return the connection object, null if not connected
     */
    Connection getConnection();

    /**
     * Executes a statement on the database
     * @return true if success, false if failed
     */
    boolean executeNonQuery(String sql);

    /**
     * Executes a query on the database
     * @return the result or null if failed
     */
    ResultSet executeQuery(String query);
}
