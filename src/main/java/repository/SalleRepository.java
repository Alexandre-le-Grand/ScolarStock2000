package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Salle;
import services.bdd.*;
import services.error.ExceptionStack;

public class SalleRepository {

    private final Connection bdd;

    public SalleRepository() {
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

    public Exception create(Salle salle) {
        String sql = "INSERT INTO Salle (nom, capacité) VALUES (?, ?)";
        try {
            PreparedStatement query = bdd.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setString(1, salle.getNom());
            query.setInt(2, salle.getCapacité());
            query.executeUpdate();
            ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                salle.setId_salle(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return e;
        }
        return null;
    }

    public List<Salle> findAll() {
        List<Salle> salles = new ArrayList<>();
        String sql = "SELECT * FROM Salle";
        try {
            Statement query = bdd.createStatement();
            ResultSet resultSet = query.executeQuery(sql);
            while (resultSet.next()) {
                Salle salle = new Salle(
                        resultSet.getInt("id_salle"),
                        resultSet.getString("nom"),
                        resultSet.getInt("capacité")
                );
                salles.add(salle);
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return salles;
    }

    public Salle findById(int id) {
        String sql = "SELECT * FROM Salle WHERE id_salle = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                return new Salle(
                        resultSet.getInt("id_salle"),
                        resultSet.getString("nom"),
                        resultSet.getInt("capacité")
                );
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return null;
    }

    public List<Salle> find(Integer id, String nom, Integer capacité) {
        List<Salle> salles = new ArrayList<>();
        String sql = "SELECT * FROM Salle";
        boolean first = true;
        if (id != null || nom != null || capacité != null) {
            sql += " WHERE ";
            if (id != null) {
                sql += "id_salle = ?";
                first = false;
            }
            if (nom != null) {
                sql += (first ? "" : " AND ") + "nom = ?";
                first = false;
            }
            if (capacité != null) {
                sql += (first ? "" : " AND ") + "capacité = ?";
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
            if (capacité != null) {
                query.setInt(i++, capacité);
            }
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                Salle salle = new Salle(
                        resultSet.getInt("id_salle"),
                        resultSet.getString("nom"),
                        resultSet.getInt("capacité")
                );
                salles.add(salle);
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return salles;
    }

    public int update(Salle salle) {
        String sql = "UPDATE Salle SET nom = ?, capacité = ? WHERE id_salle = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setString(1, salle.getNom());
            query.setInt(2, salle.getCapacité());
            query.setInt(3, salle.getId_salle());
            return query.executeUpdate();
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM Salle WHERE id_salle = ?";
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

