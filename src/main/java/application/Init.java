package application;

import model.Utilisateur;
import services.bdd.Bdd;
import services.env.Env;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Scanner;
import repository.UtilisateurRepository;

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

        UtilisateurRepository utilisateurRepository = new UtilisateurRepository();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Saisissez le nom de l'admin : ");
        String nomAdmin = scanner.nextLine();
        System.out.print("Saisissez le prenom de l'admin : ");
        String prenomAdmin = scanner.nextLine();
        System.out.print("Saisissez l'email de l'admin : ");
        String emailAdmin = scanner.nextLine();
        System.out.print("Saisissez le mot de passe de l'admin : ");
        String mdpAdmin = scanner.nextLine();
        utilisateurRepository.create(new Utilisateur(nomAdmin, prenomAdmin, emailAdmin, mdpAdmin, Utilisateur.Role.Admin));
    }
}
