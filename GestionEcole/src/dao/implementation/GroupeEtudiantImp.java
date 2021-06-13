package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.DBConection;
import dao.Interfaces.IGroupeEtudiant;
import dao.models.Etudiant;
import dao.models.Groupe;

public class GroupeEtudiantImp implements IGroupeEtudiant {

    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Connection conn = DBConection.conDB();

    @Override
    public List<Etudiant> getEtudiants(Groupe groupe) {
        String sql = "SElECT * FROM etudiant where groupe = ?";
        List<Etudiant> etudiants = new ArrayList<Etudiant>();

        try {

            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, groupe.getId());

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

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return etudiants;
    }

}
