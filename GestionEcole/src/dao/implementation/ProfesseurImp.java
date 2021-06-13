package dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.DBConection;
import dao.Interfaces.Iprofesseur;
import dao.models.Professeur;

public class ProfesseurImp implements Iprofesseur {

    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Connection conn = DBConection.conDB();

    @Override
    public Professeur getByid(int id) {
        String sql = "SELECT * FROM professeur WHERE id = ?";
        Professeur professeur = null;

        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Pas d'professeur correspondant");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return professeur;
    }

    @Override
    public boolean addUpdate(Professeur professeur, boolean choice) {
        String sql;
        if (choice == false) {
            sql = "INSERT INTO `professeur`(nom,prenom,sexe,dateDeNaissance,lieuDeNaissance,nationalite,matricule,email,telephone,specialite) VALUES (?,?,?,?,?,?,?,?,?,?)";
        } else {
            sql = "UPDATE professeur SET nom=?, prenom=?, sexe=?, dateDeNaissance=?, lieuDeNaissance=?, nationalite=?, matricule=?, email=?, telephone=?, specialite=? WHERE id=?;";
        }
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, professeur.getNom());
            preparedStatement.setString(2, professeur.getPrenom());
            preparedStatement.setString(3, professeur.getSexe());
            preparedStatement.setString(4, professeur.getDateDeNaissance().toString());
            // preparedStatement.setString(5, String.valueOf(professeur.getDateDeNaissance()));
            preparedStatement.setString(5, professeur.getLieuDeNaissance());
            preparedStatement.setString(6, professeur.getNationalite());
            preparedStatement.setString(7, professeur.getMatricule());
            preparedStatement.setString(8, professeur.getEmail());
            preparedStatement.setString(9, professeur.getTelephone());
            preparedStatement.setString(10, professeur.getSpecialite());

            if (choice == true) {
                preparedStatement.setInt(11, professeur.getId());
            } 
            return preparedStatement.execute();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM professeur WHERE id = ?";

        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            return preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }        return false;
    }

    @Override
    public List<Professeur> getAll() {
        String sql = "SElECT * FROM professeur";
        List<Professeur> professeurs = new ArrayList<Professeur>();
        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Professeur professeur = new Professeur();
                professeur.setId(resultSet.getInt(1));
                professeur.setNom(resultSet.getString(2));
                professeur.setPrenom(resultSet.getString(3));
                professeur.setSexe(resultSet.getString(4));
                professeur.setDateDeNaissance(LocalDate.parse(resultSet.getString(5)));
                professeur.setLieuDeNaissance(resultSet.getString(6));
                professeur.setNationalite(resultSet.getString(7));
                professeur.setMatricule(resultSet.getString(8));
                professeur.setEmail(resultSet.getString(9));
                professeur.setTelephone(resultSet.getString(10));
                professeur.setSpecialite(resultSet.getString(11));


                professeurs.add(professeur);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return professeurs;
    }

    @Override
    public int countProfesseur() {
        int nombreProfesseur = 0 ;
        String sql = "SElECT COUNT(*) FROM professeur";

        try {
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                nombreProfesseur = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nombreProfesseur;
    }

   

}
