package repository;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import services.bdd.Bdd;
import services.error.ExceptionStack;

import model.Rendezvous;

public class RendezvousRepository {

    private final Connection bdd;

    public RendezvousRepository() {
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

    public Exception create(Rendezvous rendezvous) {
        String sql = "INSERT INTO Rendezvous (date, heure, ref_professeur, ref_etudiant, ref_salle, statut) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement query = bdd.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            query.setDate(1, Date.valueOf(rendezvous.getDate()));
            query.setTime(2, Time.valueOf(rendezvous.getHeure()));
            query.setInt(3, rendezvous.getRef_professeur());
            query.setInt(4, rendezvous.getRef_etudiant());
            query.setInt(5, rendezvous.getRef_salle());
            query.setString(6, rendezvous.getStatut().toString());
            query.executeUpdate();
            ResultSet generatedKeys = query.getGeneratedKeys();
            if (generatedKeys.next()) {
                rendezvous.setId_rendezvous(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            return e;
        }
        return null;
    }

    public List<Rendezvous> findAll() {
        List<Rendezvous> rendezvous = new ArrayList<>();
        String sql = "SELECT * FROM Rendezvous";
        try {
            Statement query = bdd.createStatement();
            ResultSet resultSet = query.executeQuery(sql);
            while (resultSet.next()) {
                Rendezvous r = new Rendezvous(
                        resultSet.getInt("id_rendezvous"),
                        resultSet.getDate("date").toLocalDate(),
                        resultSet.getTime("heure").toLocalTime(),
                        resultSet.getInt("ref_professeur"),
                        resultSet.getInt("ref_etudiant"),
                        resultSet.getInt("ref_salle"),
                        Rendezvous.Statut.valueOf(resultSet.getString("statut"))
                );
                rendezvous.add(r);
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return rendezvous;
    }

    public Rendezvous findById(int id) {
        String sql = "SELECT * FROM Rendezvous WHERE id_rendezvous = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setInt(1, id);
            ResultSet resultSet = query.executeQuery();
            if (resultSet.next()) {
                return new Rendezvous(
                        resultSet.getInt("id_rendezvous"),
                        resultSet.getDate("date").toLocalDate(),
                        resultSet.getTime("heure").toLocalTime(),
                        resultSet.getInt("ref_professeur"),
                        resultSet.getInt("ref_etudiant"),
                        resultSet.getInt("ref_salle"),
                        Rendezvous.Statut.valueOf(resultSet.getString("statut"))
                );
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return null;
    }

    public List<Rendezvous> find(Integer id, LocalDate date, LocalTime heure, Integer ref_professeur, Integer ref_etudiant, Integer ref_salle, Rendezvous.Statut statut) {
        List<Rendezvous> rendezvous = new ArrayList<>();
        String sql = "SELECT * FROM Rendezvous";
        boolean first = true;
        if (id != null || date != null || heure != null || ref_professeur != null || ref_etudiant != null || ref_salle != null || statut != null) {
            sql += " WHERE ";
            if (id != null) {
                sql += "id_rendezvous = ?";
                first = false;
            }
            if (date != null) {
                sql += (first ? "" : " AND ") + "date = ?";
                first = false;
            }
            if (heure != null) {
                sql += (first ? "" : " AND ") + "heure = ?";
                first = false;
            }
            if (ref_professeur != null) {
                sql += (first ? "" : " AND ") + "ref_professeur = ?";
                first = false;
            }
            if (ref_etudiant != null) {
                sql += (first ? "" : " AND ") + "ref_etudiant = ?";
                first = false;
            }
            if (ref_salle != null) {
                sql += (first ? "" : " AND ") + "ref_salle = ?";
                first = false;
            }
            if (statut != null) {
                sql += (first ? "" : " AND ") + "statut = ?";
            }
        }
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            int i = 1;
            if (id != null) {
                query.setInt(i++, id);
            }
            if (date != null) {
                query.setDate(i++, Date.valueOf(date));
            }
            if (heure != null) {
                query.setTime(i++, Time.valueOf(heure));
            }
            if (ref_professeur != null) {
                query.setInt(i++, ref_professeur);
            }
            if (ref_etudiant != null) {
                query.setInt(i++, ref_etudiant);
            }
            if (ref_salle != null) {
                query.setInt(i++, ref_salle);
            }
            if (statut != null) {
                query.setString(i++, statut.toString());
            }
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                Rendezvous r = new Rendezvous(
                        resultSet.getInt("id_rendezvous"),
                        resultSet.getDate("date").toLocalDate(),
                        resultSet.getTime("heure").toLocalTime(),
                        resultSet.getInt("ref_professeur"),
                        resultSet.getInt("ref_etudiant"),
                        resultSet.getInt("ref_salle"),
                        Rendezvous.Statut.valueOf(resultSet.getString("statut"))
                );
                rendezvous.add(r);
            }
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return rendezvous;
    }

    public int update(Rendezvous rendezvous) {
        String sql = "UPDATE Rendezvous SET date = ?, heure = ?, ref_professeur = ?, ref_etudiant = ?, ref_salle = ?, statut = ? WHERE id_rendezvous = ?";
        try {
            PreparedStatement query = bdd.prepareStatement(sql);
            query.setDate(1, Date.valueOf(rendezvous.getDate()));
            query.setTime(2, Time.valueOf(rendezvous.getHeure()));
            query.setInt(3, rendezvous.getRef_professeur());
            query.setInt(4, rendezvous.getRef_etudiant());
            query.setInt(5, rendezvous.getRef_salle());
            query.setString(6, rendezvous.getStatut().toString());
            query.setInt(7, rendezvous.getId_rendezvous());
            return query.executeUpdate();
        } catch (SQLException e) {
            ExceptionStack.exception = e;
        }
        return 0;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM Rendezvous WHERE id_rendezvous = ?";
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

