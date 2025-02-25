package repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.Fournisseur;
import services.bdd.*;
import services.error.ExceptionStack;

public class FournisseurRepository {

    private final Connection bdd;

    public FournisseurRepository() {
        this.bdd = Bdd.getBdd();
    }

    /**
     * create
     * findAll
     * findById
     * find
     * update
     * delete
     * */
    public Exception create(Fournisseur fournisseur) {
        String sql = "INSERT INTO Fournisseur (nom, adresse, contact) VALUES (?, ?, ?)";
        try {
            PreparedStatement query = bdd.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setString(1, fournisseur.getNom());
            query.setString(2, fournisseur.getAdresse());
            query.setString(3, fournisseur.getContact());
            query.executeUpdate();
            ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                fournisseur.setIdFournisseur(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return e;
        }
        return null;
    }

    public List<Fournisseur> findAll() {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        String sql = "SELECT * FROM Fournisseur";
        try {
            Statement query = bdd.createStatement();
            ResultSet resultSet = query.executeQuery(sql);
            while (resultSet.next()) {
                Fournisseur fournisseur = new Fournisseur(
                        resultSet.getInt("id_fournisseur"),
                        resultSet.getString("nom"),
                        resultSet.getString("adresse"),
                        resultSet.getString("contact")
                );
                fournisseurs.add(fournisseur);
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return fournisseurs;
    }

    public Fournisseur findById(int id) {
        String sql = "SELECT * FROM Fournisseur WHERE id_fournisseur = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                return new Fournisseur(
                        resultSet.getInt("id_fournisseur"),
                        resultSet.getString("nom"),
                        resultSet.getString("adresse"),
                        resultSet.getString("contact")
                );
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return null;
    }

    public List<Fournisseur> find(Integer id, String nom, String adresse, String contact) {
        List<Fournisseur> fournisseurs = new ArrayList<>();
        String sql = "SELECT * FROM Fournisseur";
        boolean first = true;
        if (id != null || nom != null || adresse != null || contact != null) {
            sql += " WHERE ";
            if (id != null) {
                sql += "id_fournisseur = ?";
                first = false;
            }
            if (nom != null) {
                sql += (first ? "" : " AND ") + "nom = ?";
                first = false;
            }
            if (adresse != null) {
                sql += (first ? "" : " AND ") + "adresse = ?";
                first = false;
            }
            if (contact != null) {
                sql += (first ? "" : " AND ") + "contact = ?";
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
            if (adresse != null) {
                query.setString(i++, adresse);
            }
            if (contact != null) {
                query.setString(i++, contact);
            }
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                Fournisseur fournisseur = new Fournisseur(
                        resultSet.getInt("id_fournisseur"),
                        resultSet.getString("nom"),
                        resultSet.getString("adresse"),
                        resultSet.getString("contact")
                );
                fournisseurs.add(fournisseur);
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return fournisseurs;
    }

    public int update(Fournisseur fournisseur) {
        String sql = "UPDATE Fournisseur SET nom = ?, adresse = ?, contact = ? WHERE id_fournisseur = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setString(1, fournisseur.getNom());
            query.setString(2, fournisseur.getAdresse());
            query.setString(3, fournisseur.getContact());
            query.setInt(4, fournisseur.getIdFournisseur());
            return query.executeUpdate();
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM Fournisseur WHERE id_fournisseur = ?";
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


