package iiDanto.iiSpawnersV2.db;

import iiDanto.iiSpawnersV2.utils.MathUtils;
import org.bukkit.Location;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyPermission;

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
            preparedStatement.executeUpdate();
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
            preparedStatement.executeUpdate();
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

    public void setStack(Location location, Integer amt) throws SQLException{
        String loc = MathUtils.locationToString(location);
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE spawners SET stack = ? WHERE location = ?")){
            preparedStatement.setInt(1, amt);
            preparedStatement.setString(2, loc);
            preparedStatement.executeUpdate();
        }
    }

    public String getType(Location location) throws SQLException{
        String loc = MathUtils.locationToString(location);
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT type FROM spawners WHERE location = ?")){
            preparedStatement.setString(1, loc);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getString("type");
        }
    }

    public void setMoney(Location location, Integer money) throws SQLException{
        String loc = MathUtils.locationToString(location);
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE spawners SET money = ? WHERE location = ?")){
            preparedStatement.setInt(1, money);
            preparedStatement.setString(2, loc);
            preparedStatement.executeUpdate();
        }
    }

    public List<Location> getAllSpawnerLocations() throws SQLException {
        List<Location> spawnerLocations = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT location FROM spawners")) {
            while (resultSet.next()) {
                spawnerLocations.add(MathUtils.str2loc(resultSet.getString("location")));
            }
        }
        return spawnerLocations;
    }

    public int getMoney(Location location) throws SQLException{
        String loc = MathUtils.locationToString(location);
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT money FROM spawners WHERE location = ?")){
            preparedStatement.setString(1, loc);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getInt("money");
        }
    }

    public int getEXP(Location location) throws SQLException{
        String loc = MathUtils.locationToString(location);
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT exp FROM spawners WHERE location = ?")){
            preparedStatement.setString(1, loc);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.getInt("exp");
        }
    }

    public void setEXP(Location location, Integer amt) throws SQLException{
        String loc = MathUtils.locationToString(location);
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE spawners SET exp = ? WHERE location = ?")){
            preparedStatement.setInt(1, amt);
            preparedStatement.setString(2, loc);
            preparedStatement.executeUpdate();
        }
    }

    public int getProcentFilled(Location location) throws SQLException{
        String loc = MathUtils.locationToString(location);
        int money = getMoney(location);
        int stack = getStack(location);
        int procentfilled = money / (32783 * stack) * 100;
        return procentfilled;
    }
}
