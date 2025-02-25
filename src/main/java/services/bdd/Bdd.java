package services.bdd;

import services.env.Env;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bdd {
    public static Connection getBdd() {
        try {
            return DriverManager.getConnection(Env.dotenv.get("JDBC_URL")+Env.dotenv.get("JDBC_NAME"),Env.dotenv.get("JDBC_USER"),Env.dotenv.get("JDBC_PASSWORD"));
            //return DriverManager.getConnection("jdbc:mysql://localhost:3306/javalprs","root","");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnexion() {
        try {
            return DriverManager.getConnection(Env.dotenv.get("JDBC_URL"),Env.dotenv.get("JDBC_USER"),Env.dotenv.get("JDBC_PASSWORD"));
            //return DriverManager.getConnection("jdbc:mysql://localhost:3306/javalprs","root","");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
