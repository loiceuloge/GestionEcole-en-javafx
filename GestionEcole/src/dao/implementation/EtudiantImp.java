package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.DBConection;
import dao.Interfaces.IEtudiant;
import dao.models.Etudiant;

public class EtudiantImp implements IEtudiant {

    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Connection conn = DBConection.conDB();

    @Override
    public Etudiant getByid(int id) {
        String sql = "SELECT * FROM etudiant WHERE id = ?";
        Etudiant etudiant = null;

        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Pas d'etudiant correspondant");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return etudiant;
    }

    @Override
    public boolean addUpdate(Etudiant etudiant, boolean choice) {
        String sql;
        if (choice == false) {
            sql = "INSERT INTO `etudiant`(nom,prenom,sexe,dateDeNaissance,lieuDeNaissance,nationalite,matricule,email,telephone,groupe) VALUES (?,?,?,?,?,?,?,?,?,?)";
        } else {
            sql = "UPDATE etudiant SET nom=?, prenom=?, sexe=?, dateDeNaissance=?, lieuDeNaissance=?, nationalite=?, matricule=?, email=?, telephone=?, groupe=? WHERE id=?;";
        }
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, etudiant.getNom());
            preparedStatement.setString(2, etudiant.getPrenom());
            preparedStatement.setString(3, etudiant.getSexe());
            preparedStatement.setString(4, etudiant.getDateDeNaissance().toString());
            // preparedStatement.setString(5,
            // String.valueOf(etudiant.getDateDeNaissance()));
            preparedStatement.setString(5, etudiant.getLieuDeNaissance());
            preparedStatement.setString(6, etudiant.getNationalite());
            preparedStatement.setString(7, etudiant.getMatricule());
            preparedStatement.setString(8, etudiant.getEmail());
            preparedStatement.setString(9, etudiant.getTelephone());
            if (etudiant.getGroupe() == null) {
                preparedStatement.setNull(10, java.sql.Types.NULL);
            } else {
                preparedStatement.setInt(10, etudiant.getGroupe().getId());
            }

            if (choice == true) {
                preparedStatement.setInt(11, etudiant.getId());
            }
            return preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM etudiant WHERE id = ?";

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
    public List<Etudiant> getAll() {
        String sql = "SElECT * FROM etudiant";
        List<Etudiant> etudiants = new ArrayList<Etudiant>();
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setId(resultSet.getInt(1));
                etudiant.setNom(resultSet.getString(2));
                etudiant.setPrenom(resultSet.getString(3));
                etudiant.setSexe(resultSet.getString(4));
                etudiant.setDateDeNaissance(LocalDate.parse(resultSet.getString(5)));
                etudiant.setLieuDeNaissance(resultSet.getString(6));
                etudiant.setNationalite(resultSet.getString(7));
                etudiant.setMatricule(resultSet.getString(8));
                etudiant.setEmail(resultSet.getString(9));
                etudiant.setTelephone(resultSet.getString(10));
                etudiant.setGroupe(new GroupeImp().getById(resultSet.getInt(11)));

                etudiants.add(etudiant);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return etudiants;
    }

    @Override
    public int countEtudiant() {
        int nombreEtudiant = 0;
        String sql = "SElECT COUNT(*) FROM etudiant";

        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                nombreEtudiant = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombreEtudiant;
    }

}
