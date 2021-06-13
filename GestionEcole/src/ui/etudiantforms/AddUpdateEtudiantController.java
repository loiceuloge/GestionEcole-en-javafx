package ui.etudiantforms;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import dao.Interfaces.IEtudiant;
import dao.Interfaces.IGroupe;
import dao.implementation.EtudiantImp;
import dao.implementation.GroupeImp;
import dao.models.Etudiant;
import dao.models.Groupe;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tools.helpers;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import ui.dashboard.DashboardController;

public class AddUpdateEtudiantController implements Initializable {

    @FXML
    private TextField txtNom;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnReset;

    @FXML
    private Button btnBack;

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
    private ComboBox<Groupe> cmbGroupe;

    @FXML
    private DatePicker dprDateNaissnce;

    private IEtudiant etudiantdao = new EtudiantImp();
    private IGroupe groupedao = new GroupeImp();
    private boolean ok;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (DashboardController.etudiantUp != null) {
            txtNom.setText(DashboardController.etudiantUp.getNom());
            txtPrenom.setText(DashboardController.etudiantUp.getPrenom());
            txtSexe.setText(DashboardController.etudiantUp.getSexe());
            dprDateNaissnce.setValue(DashboardController.etudiantUp.getDateDeNaissance()) ; 
            txtLieuNaissance.setText(DashboardController.etudiantUp.getLieuDeNaissance());
            txtNationalite.setText(DashboardController.etudiantUp.getNationalite());
            txtMatricule.setText(DashboardController.etudiantUp.getMatricule());
            txtEmail.setText(DashboardController.etudiantUp.getEmail());
            txtTelephone.setText(DashboardController.etudiantUp.getTelephone());
            cmbGroupe.setValue(DashboardController.groupeUp);
        
        }
        load();
    }

    private void load() {
        ObservableList<Groupe> groupeList = FXCollections.observableArrayList();
        List<Groupe> groupes = groupedao.getAll();
        for (Groupe g : groupes) {
            groupeList.add(g);
        }
        cmbGroupe.setItems(groupeList);
    }

    @FXML
    void save(ActionEvent event) {

        if (txtNom.getText().equals("") || txtPrenom.getText().equals("") || txtSexe.getText().equals("")
                || txtPrenom.getText().equals("") || txtLieuNaissance.getText().equals("")
                || txtNationalite.getText().equals("") || txtMatricule.getText().equals("")
                || txtEmail.getText().equals("") || txtTelephone.getText().equals("")  ) {
            TrayNotification tray = new TrayNotification("Erreur", "Veuillez remplir tous les champs",
                    NotificationType.NOTICE);
            tray.showAndWait();
        } else {
            if (DashboardController.etudiantUp != null) {
                try {
                    DashboardController.etudiantUp.setNom(txtNom.getText());
                    DashboardController.etudiantUp.setPrenom(txtPrenom.getText());
                    DashboardController.etudiantUp.setSexe(txtSexe.getText());
                    DashboardController.etudiantUp.setNom(txtNom.getText());
                    DashboardController.etudiantUp.setDateDeNaissance(dprDateNaissnce.getValue());
                    DashboardController.etudiantUp.setLieuDeNaissance(txtLieuNaissance.getText());
                    DashboardController.etudiantUp.setNationalite(txtNationalite.getText());
                    DashboardController.etudiantUp.setMatricule(txtMatricule.getText());
                    DashboardController.etudiantUp.setEmail(txtEmail.getText());
                    DashboardController.etudiantUp.setTelephone(txtTelephone.getText());
                    DashboardController.etudiantUp.setGroupe(cmbGroupe.getValue());

                    ok = etudiantdao.addUpdate(DashboardController.etudiantUp, true);
                    if (!ok) {
                        System.out.println("update succesfully");
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                Etudiant etudiant = new Etudiant();

                etudiant.setNom(txtNom.getText());
                etudiant.setPrenom(txtPrenom.getText());
                etudiant.setSexe(txtSexe.getText());
                etudiant.setDateDeNaissance(dprDateNaissnce.getValue());

                etudiant.setLieuDeNaissance(txtLieuNaissance.getText());
                etudiant.setNationalite(txtNationalite.getText());
                etudiant.setMatricule(txtMatricule.getText());
                etudiant.setEmail(txtEmail.getText());
                etudiant.setTelephone(txtTelephone.getText());
                etudiant.setGroupe(cmbGroupe.getValue());

                try {
                    ok = etudiantdao.addUpdate(etudiant, false);
                    if(!ok){
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
            // cmbGroupe.setValue();
        }
    }


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
