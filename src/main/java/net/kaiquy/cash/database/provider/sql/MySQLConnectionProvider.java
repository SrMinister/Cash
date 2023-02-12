package net.kaiquy.cash.database.provider.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnectionProvider implements SQLConnectionProvider {

    private static final String JDBC_CONNECTION_URL = "jdbc:<driver>://<hostname>:<port>/<database>?autoReconnect=true";

    private Connection connection;

    @Override
    public Connection getConnectionInstance() {
        return this.connection;
    }

    @Override
    public boolean isRunning() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException exception) {
            return false;
        }
    }

    @Override
    public void connect(Properties properties) {
        try {
            this.connection = DriverManager.getConnection(JDBC_CONNECTION_URL
                            .replace("<driver>", properties.getProperty("driver"))
                            .replace("<hostname>", properties.getProperty("hostname"))
                            .replace("<port>", properties.getProperty("port"))
                            .replace("<database>", properties.getProperty("database")),
                    properties.getProperty("username"), properties.getProperty("password")
            );
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void disconnect() {
        if (isRunning()) {
            try {
                connection.close();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
}
