package ui.groupeForms;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dao.Interfaces.IGroupe;
import dao.implementation.GroupeImp;
import dao.models.Groupe;
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

public class AddUpdateController implements Initializable {

    @FXML
    private TextField txtNomGroupe;

    @FXML
    private DatePicker dpDateCreation;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnReset;

    private IGroupe groupedao = new GroupeImp();
    private boolean ok;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtNomGroupe.setText("");

        if(DashboardController.groupeUp != null){
            txtNomGroupe.setText(DashboardController.groupeUp.getNom());
        }
    }

    @FXML
    public void save(ActionEvent event) {

        if (txtNomGroupe.getText().equals("")) {
            TrayNotification tray = new TrayNotification("Erreur", "Veuillez remplir tous les champs",
                    NotificationType.NOTICE);
            tray.showAndWait();

        } else {
            if (DashboardController.groupeUp != null) {
                try {
                    DashboardController.groupeUp.setNom(txtNomGroupe.getText());
                    ok = groupedao.addUpdate(DashboardController.groupeUp, true);
                    if(!ok){
                        System.out.println("update succesfully");
                    }


                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                Groupe groupe = new Groupe();
                groupe.setNom(txtNomGroupe.getText());
                groupe.setDateDeCreation(LocalDate.now());
                txtNomGroupe.setText("");
                try {
                    ok = groupedao.addUpdate(groupe, false);
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
        txtNomGroupe.setText("");
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
