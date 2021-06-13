package ui.login;

import java.net.URL;
import java.util.ResourceBundle;

import dao.Interfaces.IUser;
import dao.implementation.UserImp;
import dao.models.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import tools.helpers;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class LoginController implements Initializable {

    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    private IUser userdao = new UserImp();
    public static String userparams;

    @FXML
    public void getLogin(ActionEvent event) {
        String email = txtEmail.getText();
        String password = txtPassword.getText();

        if (email.equals("") || password.equals("")) {
            TrayNotification tray = new TrayNotification("Erreur", "Veuillez remplir tous les champs",
                    NotificationType.NOTICE);
            tray.showAndWait();

        } else {
            User user = userdao.getUser(email, password);

            if (user != null) {
                userparams = email;
                try {
                    helpers.load(event, "Bienvenue sur notre App", "/ui/dashboard/Dashboard.fxml");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } else {
                TrayNotification tray = new TrayNotification("Erreur", "email ou mot de passe incorrect",
                        NotificationType.ERROR);
                tray.showAndWait();
            }

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
