package iiDanto.iiSpawnersV2.db;

import iiDanto.iiSpawnersV2.utils.MathUtils;
import org.bukkit.Location;

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
    // UPDATE

    public void addSpawner(Location location, String type) throws SQLException{
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO spawners (location, type) VALUES (?, ?)")){
            preparedStatement.setString(1, MathUtils.locationToString(location));
            preparedStatement.setString(2, type);
            preparedStatement.executeUpdate();
        }
    }

    public void removeSpawner(Location location) throws SQLException{
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM spawners WHERE location = ?")){
            preparedStatement.setString(1, MathUtils.locationToString(location));
        }
    }

    public boolean isSpawner(Location location) throws SQLException{
        String loc = MathUtils.locationToString(location);
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM spawners WHERE location = ?")){
            preparedStatement.setString(1, loc);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
    }

    public void increaseStack(Location location, Integer integer) throws SQLException{
        String loc = MathUtils.locationToString(location);
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE spawners SET stack = ? WHERE location = ?")){
            preparedStatement.setInt(1, getStack(location) + integer);
        }
    }

    public int getStack(Location location) throws SQLException{
        String loc = MathUtils.locationToString(location);
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT stack FROM spawners WHERE location = ?")){
            preparedStatement.setString(1, loc);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getInt("stack");
        }
    }


}
