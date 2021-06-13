package ui.professeurForms;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dao.Interfaces.Iprofesseur;
import dao.implementation.ProfesseurImp;
import dao.models.Professeur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tools.helpers;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import ui.dashboard.DashboardController;

public class AddUptadeProfesseurController implements Initializable {

    @FXML
    private TextField txtNom;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnReset;

    @FXML
    private TextField txtPrenom;

    @FXML
    private TextField txtSexe;

    @FXML
    private TextField txtLieuNaissance;

    @FXML
    private TextField txtNationalite;

    @FXML
    private TextField txtMatricule;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtTelephone;

    @FXML
    private DatePicker dprDateNaissnce;

    @FXML
    private TextField txtSpecialite;

    private Iprofesseur professeurdao = new ProfesseurImp();
    private boolean ok;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (DashboardController.professeurUp != null) {
            txtNom.setText(DashboardController.professeurUp.getNom());
            txtPrenom.setText(DashboardController.professeurUp.getPrenom());
            txtSexe.setText(DashboardController.professeurUp.getSexe());
            dprDateNaissnce.setValue(DashboardController.professeurUp.getDateDeNaissance());
            txtLieuNaissance.setText(DashboardController.professeurUp.getLieuDeNaissance());
            txtNationalite.setText(DashboardController.professeurUp.getNationalite());
            txtMatricule.setText(DashboardController.professeurUp.getMatricule());
            txtEmail.setText(DashboardController.professeurUp.getEmail());
            txtTelephone.setText(DashboardController.professeurUp.getTelephone());
            txtSpecialite.setText(DashboardController.professeurUp.getSpecialite());

        }

    }

    @FXML
    void clean(ActionEvent event) {
        if(event.getSource() == btnReset){
            txtNom.setText("");
            txtPrenom.setText("");
            txtSexe.setText("");
            dprDateNaissnce.setValue(LocalDate.now()) ; 
            txtLieuNaissance.setText("");
            txtNationalite.setText("");
            txtMatricule.setText("");
            txtEmail.setText("");
            txtTelephone.setText("");
            txtSpecialite.setText("");
        }
    }

    @FXML
    void save(ActionEvent event) {
        if (txtNom.getText().equals("") || txtPrenom.getText().equals("") || txtSexe.getText().equals("")
                || txtPrenom.getText().equals("") || txtLieuNaissance.getText().equals("")
                || txtNationalite.getText().equals("") || txtMatricule.getText().equals("")
                || txtEmail.getText().equals("") || txtTelephone.getText().equals("")
                || txtSpecialite.getText().equals("")) {
            TrayNotification tray = new TrayNotification("Erreur", "Veuillez remplir tous les champs",
                    NotificationType.NOTICE);
            tray.showAndWait();
        } else {
            if (DashboardController.professeurUp != null) {
                try {
                    DashboardController.professeurUp.setNom(txtNom.getText());
                    DashboardController.professeurUp.setPrenom(txtPrenom.getText());
                    DashboardController.professeurUp.setSexe(txtSexe.getText());
                    DashboardController.professeurUp.setDateDeNaissance(dprDateNaissnce.getValue());
                    DashboardController.professeurUp.setLieuDeNaissance(txtLieuNaissance.getText());
                    DashboardController.professeurUp.setNationalite(txtNationalite.getText());
                    DashboardController.professeurUp.setMatricule(txtMatricule.getText());
                    DashboardController.professeurUp.setEmail(txtEmail.getText());
                    DashboardController.professeurUp.setTelephone(txtTelephone.getText());
                    DashboardController.professeurUp.setSpecialite(txtSpecialite.getText());


                    ok = professeurdao.addUpdate(DashboardController.professeurUp, true);
                    if (!ok) {
                        System.out.println("update succesfully");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                Professeur professeur = new Professeur();

                professeur.setNom(txtNom.getText());
                professeur.setPrenom(txtPrenom.getText());
                professeur.setSexe(txtSexe.getText());
                professeur.setDateDeNaissance(dprDateNaissnce.getValue());

                professeur.setLieuDeNaissance(txtLieuNaissance.getText());
                professeur.setNationalite(txtNationalite.getText());
                professeur.setMatricule(txtMatricule.getText());
                professeur.setEmail(txtEmail.getText());
                professeur.setTelephone(txtTelephone.getText());
                professeur.setSpecialite(txtSpecialite.getText());

                try {
                    ok = professeurdao.addUpdate(professeur, false);
                    if (!ok) {
                        System.out.println("insert succesfully");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (!ok) {
                try {
                    helpers.load(event, "Bienvenue sur notre App", "/ui/dashboard/Dashboard.fxml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        
    }


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

}
