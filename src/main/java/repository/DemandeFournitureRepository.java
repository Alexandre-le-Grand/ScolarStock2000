package repository;


import model.DemandeFourniture;
import services.bdd.Bdd;
import services.error.ExceptionStack;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DemandeFournitureRepository {

    private final Connection bdd;

    public DemandeFournitureRepository() {
        this.bdd = Bdd.getBdd();
    }

    public Exception create(DemandeFourniture demande_fourniture) {
        String sql = "INSERT INTO DemandeFourniture (ref_professeur, ref_fourniture, quantite, raison_demande, status_demande, ref_gestionnaire) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement query = bdd.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setInt(1, demande_fourniture.getRef_professeur());
            query.setInt(2, demande_fourniture.getRef_fourniture());
            query.setInt(3, demande_fourniture.getQuantite());
            query.setString(4, demande_fourniture.getRaison_demande());
            query.setString(5, demande_fourniture.getStatus_demande());
            query.setInt(6, demande_fourniture.getRef_gestionnaire());
            query.executeUpdate();
            ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                demande_fourniture.setId_demande(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return e;
        }
        return null;
    }

    public List<DemandeFourniture> findAll() {
        List<DemandeFourniture> demande_fournitures = new ArrayList<>();
        String sql = "SELECT * FROM DemandeFourniture";
        try {
            Statement query = bdd.createStatement();
            ResultSet resultSet = query.executeQuery(sql);
            while (resultSet.next()) {
                DemandeFourniture demande_fourniture = new DemandeFourniture(
                        resultSet.getInt("id_demande"),
                        resultSet.getInt("ref_professeur"),
                        resultSet.getInt("ref_fourniture"),
                        resultSet.getInt("quantite"),
                        resultSet.getString("raison_demande"),
                        resultSet.getString("status_demande"),
                        resultSet.getInt("ref_gestionnaire")
                );
                demande_fournitures.add(demande_fourniture);
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return demande_fournitures;
    }

    public DemandeFourniture findById(int id) {
        String sql = "SELECT * FROM DemandeFourniture WHERE id_demande = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                return new DemandeFourniture(
                        resultSet.getInt("id_demande"),
                        resultSet.getInt("ref_professeur"),
                        resultSet.getInt("ref_fourniture"),
                        resultSet.getInt("quantite"),
                        resultSet.getString("raison_demande"),
                        resultSet.getString("status_demande"),
                        resultSet.getInt("ref_gestionnaire")
                );
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return null;
    }

    public List<DemandeFourniture> find(Integer id, Integer ref_professeur, Integer ref_fourniture, Integer quantite, String raison_demande, String status_demande, Integer ref_gestionnaire) {
        List<DemandeFourniture> demande_fournitures = new ArrayList<>();
        String sql = "SELECT * FROM DemandeFourniture";
        boolean first = true;
        if (id != null || ref_professeur != null || ref_fourniture != null || quantite != null || raison_demande != null || status_demande != null || ref_gestionnaire != null) {
            sql += " WHERE ";
            if (id != null) {
                sql += "id_demande = ?";
                first = false;
            }
            if (ref_professeur != null) {
                sql += (first ? "" : " AND ") + "ref_professeur = ?";
                first = false;
            }
            if (ref_fourniture != null) {
                sql += (first ? "" : " AND ") + "ref_fourniture = ?";
                first = false;
            }
            if (quantite != null) {
                sql += (first ? "" : " AND ") + "quantite = ?";
                first = false;
            }
            if (raison_demande != null) {
                sql += (first ? "" : " AND ") + "raison_demande = ?";
                first = false;
            }
            if (status_demande != null) {
                sql += (first ? "" : " AND ") + "status_demande = ?";
                first = false;
            }
            if (ref_gestionnaire != null) {
                sql += (first ? "" : " AND ") + "ref_gestionnaire = ?";
            }
        }
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            int i = 1;
            if (id != null) {
                query.setInt(i++, id);
            }
            if (ref_professeur != null) {
                query.setInt(i++, ref_professeur);
            }
            if (ref_fourniture != null) {
                query.setInt(i++, ref_fourniture);
            }
            if (quantite != null) {
                query.setInt(i++, quantite);
            }
            if (raison_demande != null) {
                query.setString(i++, raison_demande);
            }
            if (status_demande != null) {
                query.setString(i++, status_demande);
            }
            if (ref_gestionnaire != null) {
                query.setInt(i++, ref_gestionnaire);
            }
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                DemandeFourniture demande_fourniture = new DemandeFourniture(
                        resultSet.getInt("id_demande"),
                        resultSet.getInt("ref_professeur"),
                        resultSet.getInt("ref_fourniture"),
                        resultSet.getInt("quantite"),
                        resultSet.getString("raison_demande"),
                        resultSet.getString("status_demande"),
                        resultSet.getInt("ref_gestionnaire")
                );
                demande_fournitures.add(demande_fourniture);
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return demande_fournitures;
    }

    public int update(DemandeFourniture demande_fourniture) {
        String sql = "UPDATE DemandeFourniture SET ref_professeur = ?, ref_fourniture = ?, quantite = ?, raison_demande = ?, status_demande = ?, ref_gestionnaire = ? WHERE id_demande = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setInt(1, demande_fourniture.getRef_professeur());
            query.setInt(2, demande_fourniture.getRef_fourniture());
            query.setInt(3, demande_fourniture.getQuantite());
            query.setString(4, demande_fourniture.getRaison_demande());
            query.setString(5, demande_fourniture.getStatus_demande());
            query.setInt(6, demande_fourniture.getRef_gestionnaire());
            query.setInt(7, demande_fourniture.getId_demande());
            return query.executeUpdate();
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM DemandeFourniture WHERE id_demande = ?";
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