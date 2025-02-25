package repository;

import model.Connexion;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import services.bdd.*;
import services.error.ExceptionStack;

public class ConnexionRepository {

    private final Connection bdd;

    public ConnexionRepository() {
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

    public Exception create(Connexion connexion) {
        String sql = "INSERT INTO Connexion (date_heure_connexion, ref_utilisateur) VALUES (?, ?)";
        try {
            PreparedStatement query = bdd.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setTimestamp(1, Timestamp.valueOf(connexion.getDate_heure_connexion()));
            query.setInt(2, connexion.getRef_utilisateur());
            query.executeUpdate();
            ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                connexion.setId_connexion(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return e;
        }
        return null;
    }

    public List<Connexion> findAll() {
        List<Connexion> connexions = new ArrayList<>();
        String sql = "SELECT * FROM Connexion";
        try {
            Statement query = bdd.createStatement();
            ResultSet resultSet = query.executeQuery(sql);
            while (resultSet.next()) {
                Connexion connexion = new Connexion(
                        resultSet.getInt("id_connexion"),
                        resultSet.getTimestamp("date_heure_connexion").toLocalDateTime(),
                        resultSet.getInt("ref_utilisateur")
                );
                connexions.add(connexion);
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return connexions;
    }

    public Connexion findById(int id) {
        String sql = "SELECT * FROM Connexion WHERE id_connexion = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                return new Connexion(
                        resultSet.getInt("id_connexion"),
                        resultSet.getTimestamp("date_heure_connexion").toLocalDateTime(),
                        resultSet.getInt("ref_utilisateur")
                );
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return null;
    }

    public List<Connexion> find(Integer id, LocalDateTime date_heure_connexion, Integer ref_utilisateur) {
        List<Connexion> connexions = new ArrayList<>();
        String sql = "SELECT * FROM Connexion";
        boolean first = true;
        if (id != null || date_heure_connexion != null || ref_utilisateur != null) {
            sql += " WHERE ";
            if (id != null) {
                sql += "id_connexion = ?";
                first = false;
            }
            if (date_heure_connexion != null) {
                sql += (first ? "" : " AND ") + "date_heure_connexion = ?";
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
            if (date_heure_connexion != null) {
                query.setTimestamp(i++, Timestamp.valueOf(date_heure_connexion));
            }
            if (ref_utilisateur != null) {
                query.setInt(i++, ref_utilisateur);
            }
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                Connexion connexion = new Connexion(
                        resultSet.getInt("id_connexion"),
                        resultSet.getTimestamp("date_heure_connexion").toLocalDateTime(),
                        resultSet.getInt("ref_utilisateur")
                );
                connexions.add(connexion);
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return connexions;
    }

    public int update(Connexion connexion) {
        String sql = "UPDATE Connexion SET date_heure_connexion = ?, ref_utilisateur = ? WHERE id_connexion = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setTimestamp(1, Timestamp.valueOf(connexion.getDate_heure_connexion()));
            query.setInt(2, connexion.getRef_utilisateur());
            query.setInt(3, connexion.getId_connexion());
            return query.executeUpdate();
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM Connexion WHERE id_connexion = ?";
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


