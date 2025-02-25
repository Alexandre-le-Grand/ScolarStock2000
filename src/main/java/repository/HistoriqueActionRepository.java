package repository;

import model.HistoriqueAction;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import services.bdd.*;
import services.error.ExceptionStack;

public class HistoriqueActionRepository {

    private final Connection bdd;

    public HistoriqueActionRepository() {
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

    public Exception create(HistoriqueAction historiqueAction) {
        String sql = "INSERT INTO HistoriqueAction (date_heure, action, ref_utilisateur) VALUES (?, ?, ?)";
        try {
            PreparedStatement query = bdd.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setTimestamp(1, Timestamp.valueOf(historiqueAction.getDate_heure()));
            query.setString(2, historiqueAction.getAction());
            query.setInt(3, historiqueAction.getRef_utilisateur());
            query.executeUpdate();
            ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                historiqueAction.setId_action(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return e;
        }
        return null;
    }

    public List<HistoriqueAction> findAll() {
        List<HistoriqueAction> historiqueActions = new ArrayList<>();
        String sql = "SELECT * FROM HistoriqueAction";
        try {
            Statement query = bdd.createStatement();
            ResultSet resultSet = query.executeQuery(sql);
            while (resultSet.next()) {
                HistoriqueAction historiqueAction = new HistoriqueAction(
                        resultSet.getInt("id_action"),
                        resultSet.getTimestamp("date_heure").toLocalDateTime(),
                        resultSet.getString("action"),
                        resultSet.getInt("ref_utilisateur")
                );
                historiqueActions.add(historiqueAction);
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return historiqueActions;
    }

    public HistoriqueAction findById(int id) {
        String sql = "SELECT * FROM HistoriqueAction WHERE id_action = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                return new HistoriqueAction(
                        resultSet.getInt("id_action"),
                        resultSet.getTimestamp("date_heure").toLocalDateTime(),
                        resultSet.getString("action"),
                        resultSet.getInt("ref_utilisateur")
                );
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return null;
    }

    public List<HistoriqueAction> find(Integer id, LocalDateTime date_heure, String action, Integer ref_utilisateur) {
        List<HistoriqueAction> historiqueActions = new ArrayList<>();
        String sql = "SELECT * FROM HistoriqueAction";
        boolean first = true;
        if (id != null || date_heure != null || action != null || ref_utilisateur != null) {
            sql += " WHERE ";
            if (id != null) {
                sql += "id_action = ?";
                first = false;
            }
            if (date_heure != null) {
                sql += (first ? "" : " AND ") + "date_heure = ?";
                first = false;
            }
            if (action != null) {
                sql += (first ? "" : " AND ") + "action = ?";
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
            if (date_heure != null) {
                query.setTimestamp(i++, Timestamp.valueOf(date_heure));
            }
            if (action != null) {
                query.setString(i++, action);
            }
            if (ref_utilisateur != null) {
                query.setInt(i++, ref_utilisateur);
            }
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                HistoriqueAction historiqueAction = new HistoriqueAction(
                        resultSet.getInt("id_action"),
                        resultSet.getTimestamp("date_heure").toLocalDateTime(),
                        resultSet.getString("action"),
                        resultSet.getInt("ref_utilisateur")
                );
                historiqueActions.add(historiqueAction);
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return historiqueActions;
    }

    public int update(HistoriqueAction historiqueAction) {
        String sql = "UPDATE HistoriqueAction SET date_heure = ?, action = ?, ref_utilisateur = ? WHERE id_action = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setTimestamp(1, Timestamp.valueOf(historiqueAction.getDate_heure()));
            query.setString(2, historiqueAction.getAction());
            query.setInt(3, historiqueAction.getRef_utilisateur());
            query.setInt(4, historiqueAction.getId_action());
            return query.executeUpdate();
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM HistoriqueAction WHERE id_action = ?";
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
