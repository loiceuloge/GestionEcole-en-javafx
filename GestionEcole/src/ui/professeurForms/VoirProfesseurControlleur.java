package ui.professeurForms;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import tools.helpers;
import ui.dashboard.DashboardController;

public class VoirProfesseurControlleur implements Initializable{

    @FXML
    private Label lblId;

    @FXML
    private Label lblPrenom;

    @FXML
    private Label lblNom;

    @FXML
    private Label lblSexe;

    @FXML
    private Label lblDateNaissance;

    @FXML
    private Label lblLieuNaissance;

    @FXML
    private Label lblNationalite;

    @FXML
    private Label lblMatricule;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblSpecialite;

    @FXML
    private Label lblTelephone;

    @FXML
    private Button btnBack;

    @FXML
    private void returnToDashboard(ActionEvent event) {
        if (event.getSource() == btnBack) {
            try {
                helpers.load(event, "Bienvenue sur notre App", "/ui/dashboard/Dashboard.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblId.setText( String.valueOf(DashboardController.professeurUp.getId()));
        lblNom.setText(DashboardController.professeurUp.getNom());
        lblPrenom.setText(DashboardController.professeurUp.getPrenom());
        lblSexe.setText(DashboardController.professeurUp.getSexe());
        lblDateNaissance.setText( String.valueOf(DashboardController.professeurUp.getDateDeNaissance()));
        lblLieuNaissance.setText(DashboardController.professeurUp.getLieuDeNaissance());
        lblNationalite.setText(DashboardController.professeurUp.getNationalite());
        lblMatricule.setText(DashboardController.professeurUp.getMatricule());
        lblEmail.setText(DashboardController.professeurUp.getEmail());
        lblTelephone.setText(DashboardController.professeurUp.getTelephone());
        lblSpecialite.setText(DashboardController.professeurUp.getSpecialite());
        
    }

}
