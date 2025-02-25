package application;

import services.bdd.Bdd;
import services.env.Env;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class Init {
    public static void main(String[] args) throws IOException, SQLException {

        String sqlFilePath = Env.dotenv.get("sqlFilePath");
        Connection bdd = Bdd.getConnexion();
        Statement stmt = bdd.createStatement();
        FileReader fileReader = new FileReader(sqlFilePath);
        BufferedReader reader = new BufferedReader(fileReader);
        
        StringBuilder sqlQuery = new StringBuilder();
        int linecount = 1;
        String line = reader.readLine();
        System.out.println("ligne ("+linecount+") Execution de : " + line);
        stmt.executeUpdate(line);
        bdd = Bdd.getBdd();
        stmt = bdd.createStatement();

        while ((line = reader.readLine()) != null) {
            linecount ++;
            if (!line.trim().startsWith("--")) {
                sqlQuery.append(line);
                sqlQuery.append(" ");
                if (line.trim().endsWith(";")) {
                    System.out.println("ligne ("+linecount+") Execution de : " + sqlQuery.toString()); // pour le debug
                    stmt.executeUpdate(sqlQuery.toString());
                    sqlQuery.setLength(0);
                }
            }
        }
        System.out.println("Les requêtes SQL ont été exécutées avec succès !");

    }
}
