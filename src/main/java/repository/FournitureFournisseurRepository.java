package repository;

import model.FournitureFournisseur;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import services.bdd.*;
import services.error.ExceptionStack;

public class FournitureFournisseurRepository {

    private final Connection bdd;

    public FournitureFournisseurRepository() {
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

    public Exception create(FournitureFournisseur fournitureFournisseur) {
        String sql = "INSERT INTO FournitureFournisseur (ref_fourniture, ref_fournisseur, prix) VALUES (?, ?, ?)";
        try {
            PreparedStatement query = bdd.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setInt(1, fournitureFournisseur.getRefFourniture());
            query.setInt(2, fournitureFournisseur.getRefFournisseur());
            query.setDouble(3, fournitureFournisseur.getPrix());
            query.executeUpdate();
            ResultSet generatedKeys = query.getGeneratedKeys();
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return e;
        }
        return null;
    }

    public List<FournitureFournisseur> findAll() {
        List<FournitureFournisseur> fournitureFournisseurs = new ArrayList<>();
        String sql = "SELECT * FROM FournitureFournisseur";
        try {
            Statement query = bdd.createStatement();
            ResultSet resultSet = query.executeQuery(sql);
            while (resultSet.next()) {
                FournitureFournisseur fournitureFournisseur = new FournitureFournisseur(
                        resultSet.getInt("ref_fourniture"),
                        resultSet.getInt("ref_fournisseur"),
                        resultSet.getDouble("prix")
                );
                fournitureFournisseurs.add(fournitureFournisseur);
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return fournitureFournisseurs;
    }

    public FournitureFournisseur findById(int id) {
        String sql = "SELECT * FROM FournitureFournisseur WHERE id_fourniture_fournisseur = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                return new FournitureFournisseur(
                        resultSet.getInt("ref_fourniture"),
                        resultSet.getInt("ref_fournisseur"),
                        resultSet.getDouble("prix")
                );
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return null;
    }

    public List<FournitureFournisseur> findByFourniture(int id_fourniture) {
        List<FournitureFournisseur> fournitureFournisseurs = new ArrayList<>();
        String sql = "SELECT * FROM FournitureFournisseur WHERE ref_fourniture = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setInt(1, id_fourniture);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                FournitureFournisseur fournitureFournisseur = new FournitureFournisseur(
                        resultSet.getInt("ref_fourniture"),
                        resultSet.getInt("ref_fournisseur"),
                        resultSet.getDouble("prix")
                );
                fournitureFournisseurs.add(fournitureFournisseur);
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return fournitureFournisseurs;
    }

    public int update(FournitureFournisseur fournitureFournisseur) {
        String sql = "UPDATE FournitureFournisseur SET prix = ? WHERE ref_fourniture = ?, ref_fournisseur = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setDouble(1, fournitureFournisseur.getPrix());
            query.setInt(2, fournitureFournisseur.getRefFourniture());
            query.setInt(3, fournitureFournisseur.getRefFournisseur());
            return query.executeUpdate();
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM FournitureFournisseur WHERE id_fourniture_fournisseur = ?";
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

