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
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Create a connection to the database
            connection = DriverManager.getConnection("jdbc:sqlite:" + databaseUrl);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle any exceptions that may occur during initialization.
        }
    }

    public void createTable() {
        try {
            Statement statement = connection.createStatement();

            // Create a table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS my_table ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name TEXT NOT NULL,"
                    + "age INTEGER)";
            statement.execute(createTableSQL);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any exceptions that may occur during table creation.
        }
    }

    public void insertData(String name, int age) {
        try {
            Statement statement = connection.createStatement();

            // Insert data into the table
            String insertDataSQL = "INSERT INTO my_table (name, age) VALUES ('" + name + "', " + age + ")";
            statement.execute(insertDataSQL);

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any exceptions that may occur during data insertion.
        }
    }

    public void retrieveData() {
        try {
            Statement statement = connection.createStatement();

            // Retrieve data from the table
            ResultSet resultSet = statement.executeQuery("SELECT * FROM my_table");
            while (resultSet.next()) {
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Age: " + resultSet.getInt("age"));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any exceptions that may occur during data retrieval.
        }
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any exceptions that may occur during connection closing.
        }
    }

    // You can add more methods for other database operations as needed.

    public static void main(String[] args) {
        // Create an instance of your database class and perform operations
        Database database = new Database("your-database-file.db");
        database.createTable();
        database.insertData("John", 30);
        database.retrieveData();
        database.closeConnection();
    }
}