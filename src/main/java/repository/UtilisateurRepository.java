package repository;

import model.Utilisateur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import services.bdd.*;
import services.error.ExceptionStack;

public class UtilisateurRepository {

    private final Connection bdd;

    public UtilisateurRepository() {
        this.bdd = Bdd.getBdd();
    }

    /*
     * create
     * findAll
     * findById
     * find
     * update
     * delete
     * */

    public Exception create(Utilisateur utilisateur) {
        String sql = "INSERT INTO Utilisateur (nom, prenom, email, mot_de_passe, role) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement query = bdd.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setString(1, utilisateur.getNom());
            query.setString(2, utilisateur.getPrenom());
            query.setString(3, utilisateur.getEmail());
            query.setString(4, utilisateur.getMot_de_passe());
            query.setString(5, utilisateur.getRole().toString());
            query.executeUpdate();
            ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                utilisateur.setId_utilisateur(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return e;
        }
        return null;
    }

    public List<Utilisateur> findAll() {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM Utilisateur";
        try {
            Statement query = bdd.createStatement();
            ResultSet resultSet = query.executeQuery(sql);
            while (resultSet.next()) {
                Utilisateur utilisateur = new Utilisateur(
                        resultSet.getInt("id_utilisateur"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("mot_de_passe"),
                        Utilisateur.Role.valueOf(resultSet.getString("role"))
                );
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return utilisateurs;
    }

    public Utilisateur findById(int id) {
        String sql = "SELECT * FROM Utilisateur WHERE id_utilisateur = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                return new Utilisateur(
                        resultSet.getInt("id_utilisateur"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("mot_de_passe"),
                        Utilisateur.Role.valueOf(resultSet.getString("role"))
                );
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return null;
    }

    public List<Utilisateur> find(Integer id, String nom, String prenom, String email, String mot_de_passe, Utilisateur.Role role) {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM Utilisateur";
        boolean first = true;
        if (id != null || nom != null || prenom != null || email != null || mot_de_passe != null || role != null) {
            sql += " WHERE ";
            if (id != null) {
                sql += "id_utilisateur = ?";
                first = false;
            }
            if (nom != null) {
                sql += (first ? "" : " AND ") + "nom = ?";
                first = false;
            }
            if (prenom != null) {
                sql += (first ? "" : " AND ") + "prenom = ?";
                first = false;
            }
            if (email != null) {
                sql += (first ? "" : " AND ") + "email = ?";
                first = false;
            }
            if (mot_de_passe != null) {
                sql += (first ? "" : " AND ") + "mot_de_passe = ?";
                first = false;
            }
            if (role != null) {
                sql += (first ? "" : " AND ") + "role = ?";
            }
        }
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            int i = 1;
            if (id != null) {
                query.setInt(i++, id);
            }
            if (nom != null) {
                query.setString(i++, nom);
            }
            if (prenom != null) {
                query.setString(i++, prenom);
            }
            if (email != null) {
                query.setString(i++, email);
            }
            if (mot_de_passe != null) {
                query.setString(i++, mot_de_passe);
            }
            if (role != null) {
                query.setString(i++, role.toString());
            }
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                Utilisateur utilisateur = new Utilisateur(
                        resultSet.getInt("id_utilisateur"),
                        resultSet.getString("nom"),
                        resultSet.getString("prenom"),
                        resultSet.getString("email"),
                        resultSet.getString("mot_de_passe"),
                        Utilisateur.Role.valueOf(resultSet.getString("role"))
                );
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return utilisateurs;
    }

    public int update(Utilisateur utilisateur) {
        String sql = "UPDATE Utilisateur SET nom = ?, prenom = ?, email = ?, mot_de_passe = ?, role = ? WHERE id_utilisateur = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setString(1, utilisateur.getNom());
            query.setString(2, utilisateur.getPrenom());
            query.setString(3, utilisateur.getEmail());
            query.setString(4, utilisateur.getMot_de_passe());
            query.setString(5, utilisateur.getRole().toString());
            query.setInt(6, utilisateur.getId_utilisateur());
            return query.executeUpdate();
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM Utilisateur WHERE id_utilisateur = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setInt(1, id);
            int rowsAffected = query.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return false;
    }
}


