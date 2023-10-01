

package com.example.travel_inten;

import org.sqlite.JDBC;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Database {

    private Connection connection;

    public Database(String databaseUrl) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTables() {
        try {
            Statement statement = connection.createStatement();

            String createBudgetTableSQL = "CREATE TABLE IF NOT EXISTS budget_table ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "budget INTEGER,"
                    + "plane_ticket INTEGER,"
                    + "hotel_price INTEGER,"
                    + "food INTEGER,"
                    + "miscellaneous INTEGER,"
                    + "total INTEGER)";
            statement.execute(createBudgetTableSQL);

            String createFlightTableSQL = "CREATE TABLE IF NOT EXISTS Flight ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "locations_going TEXT,"
                    + "locations_return TEXT,"
                    + "going_times INTEGER,"
                    + "return_times INTEGER,"
                    + "difference_hours INTEGER)";
            statement.execute(createFlightTableSQL);

            String createWeatherTableSQL = "CREATE TABLE IF NOT EXISTS weather_table ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "location TEXT,"
                    + "time TEXT,"
                    + "temperature INTEGER,"
                    + "humidity INTEGER,"
                    + "wind_speed INTEGER)";
            statement.execute(createWeatherTableSQL);

            String createTravelAdvisoryTableSQL = "CREATE TABLE IF NOT EXISTS travel_advisory_table ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "location TEXT,"
                    + "advis_level TEXT,"
                    + "travel_advisory_description TEXT)";
            statement.execute(createTravelAdvisoryTableSQL);

            String createHotelTableSQL = "CREATE TABLE IF NOT EXISTS hotel_table ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "hotel_name TEXT,"
                    + "date_staying INTEGER,"
                    + "amenities TEXT)";
            statement.execute(createHotelTableSQL);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertBudgetData(int budget, int planeTicket, int hotelPrice, int food, int miscellaneous, int total) {
        try {
            Statement statement = connection.createStatement();

            String insertDataSQL = "INSERT INTO budget_table (budget, plane_ticket, hotel_price, food, miscellaneous, total) " +
                    "VALUES (" + budget + ", " + planeTicket + ", " + hotelPrice + ", " + food + ", " + miscellaneous + ", " + total + ")";
            statement.execute(insertDataSQL);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertFlightData(String locationsGoing, String locationsReturn, int goingTimes, int returnTimes, int differenceHours) {
        try {
            Statement statement = connection.createStatement();

            String insertDataSQL = "INSERT INTO Flight (locations_going, locations_return, going_times, return_times, difference_hours) " +
                    "VALUES ('" + locationsGoing + "', '" + locationsReturn + "', " + goingTimes + ", " + returnTimes + ", " + differenceHours + ")";
            statement.execute(insertDataSQL);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertWeatherData(String location, String time, int temperature, int humidity, int windSpeed) {
        try {
            Statement statement = connection.createStatement();

            String insertDataSQL = "INSERT INTO weather_table (location, time, temperature, humidity, wind_speed) " +
                    "VALUES ('" + location + "', '" + time + "', " + temperature + ", " + humidity + ", " + windSpeed + ")";
            statement.execute(insertDataSQL);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTravelAdvisoryData(String location, String advisLevel, String advisoryDescription) {
        try {
            Statement statement = connection.createStatement();

            String insertDataSQL = "INSERT INTO travel_advisory_table (location, advis_level, travel_advisory_description) " +
                    "VALUES ('" + location + "', '" + advisLevel + "', '" + advisoryDescription + "')";
            statement.execute(insertDataSQL);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertHotelData(String hotelName, int dateStaying, String amenities) {
        try {
            Statement statement = connection.createStatement();

            String insertDataSQL = "INSERT INTO hotel_table (hotel_name, date_staying, amenities) " +
                    "VALUES ('" + hotelName + "', " + dateStaying + ", '" + amenities + "')";
            statement.execute(insertDataSQL);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void retrieveData() {
        System.out.println("Budget Table");


    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Database database = new Database("your-database-file.db");
        database.createTables();
        database.insertBudgetData(1000, 500, 300, 100, 100, 1000); // Example data insertion
        // Insert data into other tables as needed
        database.closeConnection();
    }
}
