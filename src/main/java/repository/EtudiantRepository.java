package repository;

import model.Etudiant;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import services.bdd.*;
import services.error.ExceptionStack;

public class EtudiantRepository {

    private final Connection bdd;

    public EtudiantRepository() {
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

    public Exception create(Etudiant etudiant) {
        String sql = "INSERT INTO Etudiant (nom, prénom, dernier_diplome_obtenu, email, téléphone, adresse, ref_utilisateur) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement query = bdd.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setString(1, etudiant.getNom());
            query.setString(2, etudiant.getPrénom());
            query.setString(3, etudiant.getDernier_diplome_obtenu());
            query.setString(4, etudiant.getEmail());
            query.setString(5, etudiant.getTéléphone());
            query.setString(6, etudiant.getAdresse());
            query.setInt(7, etudiant.getRef_utilisateur());
            query.executeUpdate();
            ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                etudiant.setId_etudiant(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return e;
        }
        return null;
    }

    public List<Etudiant> findAll() {
        List<Etudiant> etudiants = new ArrayList<>();
        String sql = "SELECT * FROM Etudiant";
        try {
            Statement query = bdd.createStatement();
            ResultSet resultSet = query.executeQuery(sql);
            while (resultSet.next()) {
                Etudiant etudiant = new Etudiant(
                        resultSet.getInt("id_etudiant"),
                        resultSet.getString("nom"),
                        resultSet.getString("prénom"),
                        resultSet.getString("dernier_diplome_obtenu"),
                        resultSet.getString("email"),
                        resultSet.getString("téléphone"),
                        resultSet.getString("adresse"),
                        resultSet.getInt("ref_utilisateur")
                );
                etudiants.add(etudiant);
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return etudiants;
    }

    public Etudiant findById(int id) {
        String sql = "SELECT * FROM Etudiant WHERE id_etudiant = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                return new Etudiant(
                        resultSet.getInt("id_etudiant"),
                        resultSet.getString("nom"),
                        resultSet.getString("prénom"),
                        resultSet.getString("dernier_diplome_obtenu"),
                        resultSet.getString("email"),
                        resultSet.getString("téléphone"),
                        resultSet.getString("adresse"),
                        resultSet.getInt("ref_utilisateur")
                );
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return null;
    }

    public List<Etudiant> find(Integer id, String nom, String prénom, String dernier_diplome_obtenu, String email, String téléphone, String adresse, Integer ref_utilisateur) {
        List<Etudiant> etudiants = new ArrayList();
        String sql = "SELECT * FROM Etudiant";
        boolean first = true;
        if (id != null || nom != null || prénom != null || dernier_diplome_obtenu != null || email != null || téléphone != null || adresse != null || ref_utilisateur != null) {
            sql += " WHERE ";
            if (id != null) {
                sql += "id_etudiant = ?";
                first = false;
            }
            if (nom != null) {
                sql += (first ? "" : " AND ") + "nom = ?";
                first = false;
            }
            if (prénom != null) {
                sql += (first ? "" : " AND ") + "prénom = ?";
                first = false;
            }
            if (dernier_diplome_obtenu != null) {
                sql += (first ? "" : " AND ") + "dernier_diplome_obtenu = ?";
                first = false;
            }
            if (email != null) {
                sql += (first ? "" : " AND ") + "email = ?";
                first = false;
            }
            if (téléphone != null) {
                sql += (first ? "" : " AND ") + "téléphone = ?";
                first = false;
            }
            if (adresse != null) {
                sql += (first ? "" : " AND ") + "adresse = ?";
                first = false;
            }
            if (ref_utilisateur != null) {
                sql += (first ? "" : " AND ") + "ref_utilisateur = ?";
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
            if (prénom != null) {
                query.setString(i++, prénom);
            }
            if (dernier_diplome_obtenu != null) {
                query.setString(i++, dernier_diplome_obtenu);
            }
            if (email != null) {
                query.setString(i++, email);
            }
            if (téléphone != null) {
                query.setString(i++, téléphone);
            }
            if (adresse != null) {
                query.setString(i++, adresse);
            }
            if (ref_utilisateur != null) {
                query.setInt(i++, ref_utilisateur);
            }
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                Etudiant etudiant = new Etudiant(
                        resultSet.getInt("id_etudiant"),
                        resultSet.getString("nom"),
                        resultSet.getString("prénom"),
                        resultSet.getString("dernier_diplome_obtenu"),
                        resultSet.getString("email"),
                        resultSet.getString("téléphone"),
                        resultSet.getString("adresse"),
                        resultSet.getInt("ref_utilisateur")
                );
                etudiants.add(etudiant);
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return etudiants;
    }

    public int update(Etudiant etudiant) {
        String sql = "UPDATE Etudiant SET nom = ?, prénom = ?, dernier_diplome_obtenu = ?, email = ?, téléphone = ?, adresse = ?, ref_utilisateur = ? WHERE id_etudiant = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setString(1, etudiant.getNom());
            query.setString(2, etudiant.getPrénom());
            query.setString(3, etudiant.getDernier_diplome_obtenu());
            query.setString(4, etudiant.getEmail());
            query.setString(5, etudiant.getTéléphone());
            query.setString(6, etudiant.getAdresse());
            query.setInt(7, etudiant.getRef_utilisateur());
            query.setInt(8, etudiant.getId_etudiant());
            return query.executeUpdate();
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM Etudiant WHERE id_etudiant = ?";
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
