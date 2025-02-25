package repository;

import model.Fourniture;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import services.bdd.*;
import services.error.ExceptionStack;

public class FournitureRepository {
    private final Connection bdd;

    public FournitureRepository() {
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
    public Exception create(Fourniture fourniture) {
        String sql = "INSERT INTO Fourniture (libelle, description, quantiteDisponible) VALUES (?, ?, ?)";
        try {
            PreparedStatement query = bdd.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setString(1, fourniture.getLibelle());
            query.setString(2, fourniture.getDescription());
            query.setInt(3, fourniture.getQuantiteDisponible());
            query.executeUpdate();
            ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                fourniture.setIdFourniture(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return e;
        }
        return null;
    }

    public List<Fourniture> findAll() {
        List<Fourniture> fournitures = new ArrayList<>();
        String sql = "SELECT * FROM Fourniture";
        try {
            Statement query = bdd.createStatement();
            ResultSet resultSet = query.executeQuery(sql);
            while (resultSet.next()) {
                Fourniture fourniture = new Fourniture(
                        resultSet.getInt("idFourniture"),
                        resultSet.getString("libelle"),
                        resultSet.getString("description"),
                        resultSet.getInt("quantiteDisponible")
                );
                fournitures.add(fourniture);
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return fournitures;
    }

    public Fourniture findById(int id) {
        String sql = "SELECT * FROM Fourniture WHERE idFourniture = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                return new Fourniture(
                        resultSet.getInt("idFourniture"),
                        resultSet.getString("libelle"),
                        resultSet.getString("description"),
                        resultSet.getInt("quantiteDisponible")
                );
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return null;
    }

    public List<Fourniture> find(Integer id, String libelle, String description, Integer quantiteDisponible) {
        List<Fourniture> fournitures = new ArrayList<>();
        String sql = "SELECT * FROM Fourniture";
        boolean first = true;
        if (id != null || libelle != null || description != null || quantiteDisponible != null) {
            sql += " WHERE ";
            if (id != null) {
                sql += "idFourniture = ?";
                first = false;
            }
            if (libelle != null) {
                sql += (first ? "" : " AND ") + "libelle = ?";
                first = false;
            }
            if (description != null) {
                sql += (first ? "" : " AND ") + "description = ?";
                first = false;
            }
            if (quantiteDisponible != null) {
                sql += (first ? "" : " AND ") + "quantiteDisponible = ?";
            }
        }
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            int i = 1;
            if (id != null) {
                query.setInt(i++, id);
            }
            if (libelle != null) {
                query.setString(i++, libelle);
            }
            if (description != null) {
                query.setString(i++, description);
            }
            if (quantiteDisponible != null) {
                query.setInt(i++, quantiteDisponible);
            }
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                Fourniture fourniture = new Fourniture(
                        resultSet.getInt("idFourniture"),
                        resultSet.getString("libelle"),
                        resultSet.getString("description"),
                        resultSet.getInt("quantiteDisponible")
                );
                fournitures.add(fourniture);
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return fournitures;
    }

    public int update(Fourniture fourniture) {
        String sql = "UPDATE Fourniture SET libelle = ?, description = ?, quantiteDisponible = ? WHERE idFourniture = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setString(1, fourniture.getLibelle());
            query.setString(2, fourniture.getDescription());
            query.setInt(3, fourniture.getQuantiteDisponible());
            query.setInt(4, fourniture.getIdFourniture());
            return query.executeUpdate();
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM Fourniture WHERE idFourniture = ?";
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


