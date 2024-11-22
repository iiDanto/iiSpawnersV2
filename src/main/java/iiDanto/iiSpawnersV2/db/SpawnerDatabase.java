package iiDanto.iiSpawnersV2.db;

import java.sql.*;

public class SpawnerDatabase {
    private final Connection connection;

    public SpawnerDatabase(String path) throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite" + path);
        try (
                Statement statement = connection.createStatement()) {
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS spawners (
                    location TEXT PRIMARY KEY,
                    type TEXT NOT NULL,
                    stack INTEGER NOT NULL DEFAULT 1,
                    money INTEGER NOT NULL DEFAULT 0,
                    exp INTEGER NOT NULL DEFAULT 0)""");
        }
    }

    public void closeConnection() throws SQLException{
        if (connection != null && !connection.isClosed()){
            connection.close();
        }
    }
}
