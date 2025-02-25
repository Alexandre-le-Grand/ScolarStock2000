package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.DossierInscription;
import services.bdd.*;
import services.error.ExceptionStack;

public class DossierInscriptionRepository {

    private final Connection bdd;

    public DossierInscriptionRepository() {
        this.bdd = Bdd.getBdd();
    }

    /*
     * create
     * findAll
     * findById
     * find
     * update
     * delete
     */
    public Exception create(DossierInscription dossierInscription) {
        String sql = "INSERT INTO DossierInscription (date, heure, filière, motivations, ref_etudiant, ref_secrétaire) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement query = bdd.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setDate(1, dossierInscription.getDate());
            query.setDate(2, dossierInscription.getHeure());
            query.setString(3, dossierInscription.getFilière());
            query.setString(4, dossierInscription.getMotivations());
            query.setInt(5, dossierInscription.getRef_etudiant());
            query.setInt(6, dossierInscription.getRef_secrétaire());
            query.executeUpdate();
            ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                dossierInscription.setId_dossier(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return e;
        }
        return null;
    }

    public List<DossierInscription> findAll() {
        List<DossierInscription> dossiersInscription = new ArrayList<>();
        String sql = "SELECT * FROM DossierInscription";
        try {
            Statement query = bdd.createStatement();
            ResultSet resultSet = query.executeQuery(sql);
            while (resultSet.next()) {
                DossierInscription dossierInscription = new DossierInscription(
                        resultSet.getInt("id_dossier"),
                        resultSet.getDate("date"),
                        resultSet.getDate("heure"),
                        resultSet.getString("filière"),
                        resultSet.getString("motivations"),
                        resultSet.getInt("ref_etudiant"),
                        resultSet.getInt("ref_secrétaire")
                );
                dossiersInscription.add(dossierInscription);
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return dossiersInscription;
    }

    public DossierInscription findById(int id) {
        String sql = "SELECT * FROM DossierInscription WHERE id_dossier = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                return new DossierInscription(
                        resultSet.getInt("id_dossier"),
                        resultSet.getDate("date"),
                        resultSet.getDate("heure"),
                        resultSet.getString("filière"),
                        resultSet.getString("motivations"),
                        resultSet.getInt("ref_etudiant"),
                        resultSet.getInt("ref_secrétaire")
                );
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return null;
    }

    
    public boolean update(DossierInscription dossierInscription) {
        String sql = "UPDATE DossierInscription SET date = ?, heure = ?, filière = ?, motivations = ?, ref_etudiant = ?, ref_secrétaire = ? WHERE id_dossier = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setDate(1, dossierInscription.getDate());
            query.setDate(2, dossierInscription.getHeure());
            query.setString(3, dossierInscription.getFilière());
            query.setString(4, dossierInscription.getMotivations());
            query.setInt(5, dossierInscription.getRef_etudiant());
            query.setInt(6, dossierInscription.getRef_secrétaire());
            query.setInt(7, dossierInscription.getId_dossier());
            int rowsAffected = query.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM DossierInscription WHERE id_dossier = ?";
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

