package com.hillel.reziapov.homeworks.homework17.connectionSingleton;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionSingleton {

    private static Connection connection;
    private static final String name = "postgres";
    private static final String password = "postgres";

    public static Connection getConnection(){
        try {
            if (connection == null || connection.isClosed()){
                connection = DriverManager.
                        getConnection("jdbc:postgresql://localhost:5432/JavaPro_2023", name, password);
            }
        } catch (SQLException e) {
            System.out.println("There was a problem processing your request. " +
                    "Please contact the system administrator for more information");
        }
        return connection;
    }
}
