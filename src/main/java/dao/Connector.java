package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connector {
    private static Connection connection;

    private static void loadDriver(){
        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("Postgresql Driver load!");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection(){
        if(connection == null) {
            loadDriver();
            Properties props = loadDatabaseProperties();
            try {
                connection = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));
                System.out.println("Get connection to DB!");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection(Connection connection){
        try {
            connection.close();
            System.out.println("Connection to DB close!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Properties loadDatabaseProperties() {
        InputStream in = Connector.class.getClassLoader().getResourceAsStream("database.properties");
        Properties props = new Properties();
        try {
            props.load(in);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return props;
    }
}
