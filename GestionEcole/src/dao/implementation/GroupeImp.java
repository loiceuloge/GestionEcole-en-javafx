package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.DBConection;
import dao.Interfaces.IGroupe;
import dao.models.Etudiant;
import dao.models.Groupe;

public class GroupeImp implements IGroupe {

    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Connection conn = DBConection.conDB();

    @Override
    public List<Etudiant> getEtudiantByGroupe(int id) {
        return null;
    }

    @Override
    public Groupe getById(int id) {
        String sql = "SElECT * FROM groupe WHERE id = ?";
        Groupe groupe = null;
        try {

            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                groupe = new Groupe();
                groupe.setId(resultSet.getInt(1));
                groupe.setNom(resultSet.getString(2));
                groupe.setDateDeCreation(LocalDate.parse(resultSet.getString(3)) );
               
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return groupe;
    }

    @Override
    public List<Groupe> getAll() {
        String sql = "SElECT * FROM groupe";
        List<Groupe> groupes = new ArrayList<Groupe>();
        try {

            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Groupe groupe = new Groupe();
                groupe.setId(resultSet.getInt(1));
                groupe.setNom(resultSet.getString(2));
                groupe.setDateDeCreation(LocalDate.parse(resultSet.getString(3)));

                groupes.add(groupe);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return groupes;
    }

    @Override
    public boolean addUpdate(Groupe groupe, boolean choice) {
        String sql;
        if (choice == false) {
            sql = "INSERT INTO `groupe`(`nom`,`dateDeCreation`) VALUES (?,?)";
        } else {
            sql = "UPDATE `groupe` SET " + "`nom`=?" + "WHERE id = ? ";
        }
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, groupe.getNom());

            if (!choice) {
                preparedStatement.setString(2, String.valueOf(groupe.getDateDeCreation()));
            } else {
                preparedStatement.setInt(2, groupe.getId());
            }

            return preparedStatement.execute();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM groupe WHERE id = ?";

        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int countGroupe() {
        int nombreGroupe = 0;
        String sql = "SElECT COUNT(*) FROM groupe";

        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                nombreGroupe = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombreGroupe;
    }

}
