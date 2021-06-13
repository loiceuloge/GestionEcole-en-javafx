package ui.etudiantforms;

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

public class VoirEtudiantControlleur implements Initializable {

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
    private Label lblGroupe;

    @FXML
    private Label lblTelephone;

    @FXML
    private Button btnBack;

    @FXML
    void returnToDashboard(ActionEvent event) {
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
        lblId.setText(String.valueOf(DashboardController.etudiantUp.getId()));
        lblNom.setText(DashboardController.etudiantUp.getNom());
        lblPrenom.setText(DashboardController.etudiantUp.getPrenom());
        lblSexe.setText(DashboardController.etudiantUp.getSexe());
        lblDateNaissance.setText(String.valueOf(DashboardController.etudiantUp.getDateDeNaissance()));
        lblLieuNaissance.setText(DashboardController.etudiantUp.getLieuDeNaissance());
        lblNationalite.setText(DashboardController.etudiantUp.getNationalite());
        lblMatricule.setText(DashboardController.etudiantUp.getMatricule());
        lblEmail.setText(DashboardController.etudiantUp.getEmail());
        lblTelephone.setText(DashboardController.etudiantUp.getTelephone());
        if (DashboardController.etudiantUp.getGroupe() == null) {
            lblGroupe.setText("Pas de groupe pour cet etudiant");
        } else {
            lblGroupe.setText(String.valueOf(DashboardController.etudiantUp.getGroupe().getNom()));

        }

    }

}
