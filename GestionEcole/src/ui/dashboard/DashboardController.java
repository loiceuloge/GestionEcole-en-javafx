package ui.dashboard;

import static ui.login.LoginController.userparams;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import dao.Interfaces.IEtudiant;
import dao.Interfaces.IGroupe;
import dao.Interfaces.IGroupeEtudiant;
import dao.Interfaces.Iprofesseur;
import dao.implementation.EtudiantImp;
import dao.implementation.GroupeEtudiantImp;
import dao.implementation.GroupeImp;
import dao.implementation.ProfesseurImp;
import dao.models.Etudiant;
import dao.models.Groupe;
import dao.models.Professeur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import tools.helpers;

public class DashboardController implements Initializable {

    public static Groupe groupeUp;
    public static Groupe groupeConsult;
    public static Etudiant etudiantUp;
    public static Professeur professeurUp;
    IGroupe groupedao = new GroupeImp();
    Iprofesseur professeurdao = new ProfesseurImp();
    IEtudiant etudiantdao = new EtudiantImp();
    IGroupeEtudiant groupeEtudiantdao = new GroupeEtudiantImp();

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnGroupe;

    @FXML
    private Button btnEtudiant;

    @FXML
    private Button btnProfesseur;

    @FXML
    private Button btnGroupeEtudiant;

    @FXML
    private Button btnDeconnexion;

    @FXML
    private GridPane grpGroupeEtudiant;

    @FXML
    private GridPane grpEtudiant;

    @FXML
    private GridPane grpProfesseur;

    @FXML
    private GridPane grpDashboard;

    @FXML
    private GridPane grpGroupe;

    @FXML
    private Pane pnlStatus;

    @FXML
    private Label lblStatus;

    @FXML
    private Label lblBreacumb;

    @FXML
    private Label lblUserConnected;

    // Groupe attributs;
    @FXML
    private TableView<Groupe> GroupeTable;

    @FXML
    private TableColumn<Groupe, Integer> idColGroupe;

    @FXML
    private TableColumn<Groupe, String> nomColGroupe;

    @FXML
    private TableColumn<Groupe, String> dateColGroupe;

    @FXML
    private TableColumn<Groupe, String> actionsColGroupe;

    @FXML
    private Button btnSearchByName;

    @FXML
    private TextField lblSearchByName;

    @FXML
    private ComboBox<?> cmbSearchById;

    @FXML
    private Button btnAddGroupe;

    // ETUDIANT ATTRIBUTS

    @FXML
    private TableView<Etudiant> EtudiantTable;

    @FXML
    private TableColumn<Etudiant, String> voirColEtudiant;

    @FXML
    private TableColumn<Etudiant, Integer> idcolEtudiant;

    @FXML
    private TableColumn<Etudiant, String> nomColEtudiant;

    @FXML
    private TableColumn<Etudiant, String> prenomColEtudiant;

    @FXML
    private TableColumn<Etudiant, String> actionsColEtudiant;

    @FXML
    private Button btnSerachByNameEtudiant;

    @FXML
    private TextField lblSearchByNameEtudiant;

    @FXML
    private ComboBox<Groupe> cmbSearchByIdEtudiant;

    @FXML
    private Button btnAddEtudiant;

    // PROFESSEUR ATTRIBUTS

    @FXML
    private TableView<Professeur> ProfesseurTable;

    @FXML
    private TableColumn<Professeur, String> VoirColProfesseur;

    @FXML
    private TableColumn<Professeur, Integer> idColProfesseur;

    @FXML
    private TableColumn<Professeur, String> nomColProfesseur;

    @FXML
    private TableColumn<Professeur, String> prenomColProfesseur;

    @FXML
    private TableColumn<Professeur, String> actionsColProfesseur;

    @FXML
    private Button btnSerachByNameProfesseur;

    @FXML
    private TextField lblSearchByNameProfesseur;

    @FXML
    private ComboBox<?> cmbSearchByIdProfesseur;

    @FXML
    private Button btnAddProfesseur;

    // Dashboard count

    @FXML
    private Label lblGroupeCount;

    @FXML
    private Label lblProfesseurCount;

    @FXML
    private Label lblEtudiantCount;

    // GroupeEtuidnant

    @FXML
    private TableView<Groupe> GroupeEtudiantTable;

    @FXML
    private TableColumn<Groupe, Integer> idColGroupeEtudiant;

    @FXML
    private TableColumn<Groupe, String> nomColGroupeEtudiant;

    @FXML
    private TableColumn<Groupe, String> dateColGroupeEtudiant;

    @FXML
    private TableColumn<Groupe, String> actionsColGroupeEtudiant;

    // GroupeEtudiantConsulter Attributs

    @FXML
    private GridPane grpGroupeEtudiantConsulter;

    @FXML
    private TableView<Etudiant> GroupeEtudiantConsulterTable;

    @FXML
    private TableColumn<Etudiant, String> matriculeGroupeEtudiantConsulter;

    @FXML
    private TableColumn<Etudiant, String> nomGroupeEtudiantConsulter;

    @FXML
    private TableColumn<Etudiant, String> prenomGroupeEtudiantConsulter;

    private void CountTotal() {
        lblEtudiantCount.setText(String.valueOf(etudiantdao.countEtudiant()));
        lblProfesseurCount.setText(String.valueOf(professeurdao.countProfesseur()));
        lblGroupeCount.setText(String.valueOf(groupedao.countGroupe()));
    }

    @FXML
    void addProfesseur(ActionEvent event) {
        try {
            helpers.load(event, "Bienvenue sur notre App", "/ui/professeurForms/AddUpdateProfesseur.fxml");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void addEtudiant(ActionEvent event) {
        try {
            helpers.load(event, "Bienvenue sur notre App", "/ui/etudiantforms/etudiantForm.fxml");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void addGroupe(ActionEvent event) throws IOException {
        try {
            helpers.load(event, "Bienvenue sur notre App", "/ui/groupeForms/AddUpdate.fxml");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void refreshGroupe() {
        ObservableList<Groupe> groupeList = FXCollections.observableArrayList();
        List<Groupe> groupes = groupedao.getAll();
        for (Groupe g : groupes) {
            groupeList.add(g);
        }
        GroupeTable.setItems(groupeList);
    }

    private void refreshGroupeEtudiant() {
        ObservableList<Groupe> groupeList = FXCollections.observableArrayList();
        List<Groupe> groupes = groupedao.getAll();
        for (Groupe g : groupes) {
            groupeList.add(g);
        }
        GroupeEtudiantTable.setItems(groupeList);
    }

    private void refreshEtudiant() {
        ObservableList<Etudiant> etudiantList = FXCollections.observableArrayList();
        List<Etudiant> etudiants = etudiantdao.getAll();
        for (Etudiant e : etudiants) {
            etudiantList.add(e);

        }
        EtudiantTable.setItems(etudiantList);
    }

    private void refreshGroupeEtudiantConsulter(Groupe groupe) {
        ObservableList<Etudiant> etudiantList = FXCollections.observableArrayList();
        List<Etudiant> etudiants = groupeEtudiantdao.getEtudiants(groupe);
        for (Etudiant e : etudiants) {
            etudiantList.add(e);
        }
        GroupeEtudiantConsulterTable.setItems(etudiantList);
    }

    private void refreshProfesseur() {
        ObservableList<Professeur> professeurList = FXCollections.observableArrayList();
        List<Professeur> professeurs = professeurdao.getAll();
        for (Professeur p : professeurs) {
            professeurList.add(p);
        }
        ProfesseurTable.setItems(professeurList);
    }

    public void loadProfesseurTable() {

        // for Professeur
        // add cell of button edit
        Callback<TableColumn<Professeur, String>, TableCell<Professeur, String>> cellFactory = (
                TableColumn<Professeur, String> param) -> {
            // make cell containing buttons
            final TableCell<Professeur, String> cell = new TableCell<Professeur, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    // that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        final Button btnvoir = new Button("Voir");

                        setGraphic(btnvoir);

                        btnvoir.setOnAction((ActionEvent event) -> {
                            professeurUp = ProfesseurTable.getSelectionModel().getSelectedItem();
                            try {
                                helpers.load(event, "Bienvenue sur notre App",
                                        "/ui/professeurForms/voirProfesseur.fxml");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

                        HBox managebtn = new HBox(btnvoir);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(btnvoir, new Insets(2, 2, 0, 3));
                        setGraphic(managebtn);

                        setText(null);
                    }
                }

            };
            return cell;

        };
        VoirColProfesseur.setCellFactory(cellFactory);

        idColProfesseur.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColProfesseur.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColProfesseur.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        // add cell of button edit
        Callback<TableColumn<Professeur, String>, TableCell<Professeur, String>> cellFactory1 = (
                TableColumn<Professeur, String> param) -> {
            // make cell containing buttons
            final TableCell<Professeur, String> cell = new TableCell<Professeur, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    // that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        final Button btnUpdate = new Button("Modifier");
                        final Button btnDelete = new Button("SUpprimer");

                        setGraphic(btnUpdate);
                        setGraphic(btnDelete);

                        btnUpdate.setOnAction((ActionEvent event) -> {
                            professeurUp = ProfesseurTable.getSelectionModel().getSelectedItem();
                            try {
                                helpers.load(event, "Bienvenue sur notre App",
                                        "/ui/professeurForms/AddUpdateProfesseur.fxml");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

                        btnDelete.setOnAction((ActionEvent event) -> {
                            professeurUp = ProfesseurTable.getSelectionModel().getSelectedItem();
                            professeurdao.delete(professeurUp.getId());
                            refreshProfesseur();
                        });

                        HBox managebtn = new HBox(btnUpdate, btnDelete);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(btnUpdate, new Insets(2, 2, 0, 3));
                        HBox.setMargin(btnDelete, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);
                    }
                }

            };
            return cell;

        };
        actionsColProfesseur.setCellFactory(cellFactory1);
        refreshProfesseur();

    }

    public void loadGroupeTable() {

        // for groupe
        idColGroupe.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColGroupe.setCellValueFactory(new PropertyValueFactory<>("nom"));
        dateColGroupe.setCellValueFactory(new PropertyValueFactory<>("dateDeCreation"));

        // add cell of button edit
        Callback<TableColumn<Groupe, String>, TableCell<Groupe, String>> cellFactory = (
                TableColumn<Groupe, String> param) -> {
            // make cell containing buttons
            final TableCell<Groupe, String> cell = new TableCell<Groupe, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    // that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        final Button btnUpdate = new Button("Modifier");
                        final Button btnDelete = new Button("SUpprimer");

                        setGraphic(btnUpdate);
                        setGraphic(btnDelete);

                        btnUpdate.setOnAction((ActionEvent event) -> {
                            groupeUp = GroupeTable.getSelectionModel().getSelectedItem();
                            try {
                                helpers.load(event, "Bienvenue sur notre App", "/ui/groupeForms/AddUpdate.fxml");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            grpGroupe.toFront();

                        });

                        btnDelete.setOnAction((ActionEvent event) -> {
                            groupeUp = GroupeTable.getSelectionModel().getSelectedItem();
                            groupedao.delete(groupeUp.getId());
                            refreshGroupe();
                        });

                        HBox managebtn = new HBox(btnUpdate, btnDelete);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(btnUpdate, new Insets(2, 2, 0, 3));
                        HBox.setMargin(btnDelete, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);
                    }
                }

            };
            return cell;

        };
        actionsColGroupe.setCellFactory(cellFactory);
        refreshGroupe();

    }

    public void loadEtudiantTable() {

        // for Etudiant
        // add cell of button edit
        Callback<TableColumn<Etudiant, String>, TableCell<Etudiant, String>> cellFactory = (
                TableColumn<Etudiant, String> param) -> {
            // make cell containing buttons
            final TableCell<Etudiant, String> cell = new TableCell<Etudiant, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    // that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        final Button btnvoir = new Button("Voir");

                        setGraphic(btnvoir);

                        btnvoir.setOnAction((ActionEvent event) -> {
                            etudiantUp = EtudiantTable.getSelectionModel().getSelectedItem();

                            try {
                                helpers.load(event, "Bienvenue sur notre App", "/ui/etudiantforms/voirEtudiant.fxml");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        });

                        HBox managebtn = new HBox(btnvoir);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(btnvoir, new Insets(2, 2, 0, 3));
                        setGraphic(managebtn);

                        setText(null);
                    }
                }

            };
            return cell;

        };
        voirColEtudiant.setCellFactory(cellFactory);

        idcolEtudiant.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColEtudiant.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomColEtudiant.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        // add cell of button edit
        Callback<TableColumn<Etudiant, String>, TableCell<Etudiant, String>> cellFactory1 = (
                TableColumn<Etudiant, String> param) -> {
            // make cell containing buttons
            final TableCell<Etudiant, String> cell = new TableCell<Etudiant, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    // that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        final Button btnUpdate = new Button("Modifier");
                        final Button btnDelete = new Button("SUpprimer");

                        setGraphic(btnUpdate);
                        setGraphic(btnDelete);

                        btnUpdate.setOnAction((ActionEvent event) -> {
                            etudiantUp = EtudiantTable.getSelectionModel().getSelectedItem();
                            try {
                                helpers.load(event, "Bienvenue sur notre App", "/ui/etudiantforms/etudiantForm.fxml");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

                        btnDelete.setOnAction((ActionEvent event) -> {
                            etudiantUp = EtudiantTable.getSelectionModel().getSelectedItem();
                            etudiantdao.delete(etudiantUp.getId());
                            refreshEtudiant();
                        });

                        HBox managebtn = new HBox(btnUpdate, btnDelete);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(btnUpdate, new Insets(2, 2, 0, 3));
                        HBox.setMargin(btnDelete, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);
                    }
                }

            };
            return cell;

        };
        actionsColEtudiant.setCellFactory(cellFactory1);
        refreshEtudiant();

    }

    public void loadGroupeEtudiantTable() {

        // for groupe
        idColGroupeEtudiant.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColGroupeEtudiant.setCellValueFactory(new PropertyValueFactory<>("nom"));
        dateColGroupeEtudiant.setCellValueFactory(new PropertyValueFactory<>("dateDeCreation"));

        // add cell of button edit
        Callback<TableColumn<Groupe, String>, TableCell<Groupe, String>> cellFactory = (
                TableColumn<Groupe, String> param) -> {
            // make cell containing buttons
            final TableCell<Groupe, String> cell = new TableCell<Groupe, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    // that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        final Button btnConsulter = new Button("Consulter");

                        setGraphic(btnConsulter);

                        btnConsulter.setOnAction((ActionEvent event) -> {
                            groupeConsult = GroupeEtudiantTable.getSelectionModel().getSelectedItem();
                            System.out.println("Loicccccccccccccccccccccccccccccccccc");
                            System.out.println(groupeConsult);
                            loadGroupeEtudiantConsulterTable();
                            grpGroupeEtudiantConsulter.toFront();
                        });

                        HBox managebtn = new HBox(btnConsulter);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(btnConsulter, new Insets(2, 2, 0, 3));
                        setGraphic(managebtn);

                        setText(null);
                    }
                }

            };
            return cell;

        };
        actionsColGroupeEtudiant.setCellFactory(cellFactory);
        refreshGroupeEtudiant();

    }

    public void loadGroupeEtudiantConsulterTable() {

        matriculeGroupeEtudiantConsulter.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        nomGroupeEtudiantConsulter.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomGroupeEtudiantConsulter.setCellValueFactory(new PropertyValueFactory<>("prenom"));

        refreshGroupeEtudiantConsulter(groupeConsult);

    }

    @FXML
    private void handlecliks(ActionEvent event) {
        CountTotal();
        lblUserConnected.setText(userparams);
        lblBreacumb.setText("/home/dashboard");
        lblStatus.setText("Dashboard");
        pnlStatus.setBackground(
                new Background(new BackgroundFill(Color.rgb(135, 206, 235), CornerRadii.EMPTY, Insets.EMPTY)));
        grpDashboard.toFront();
        if (event.getSource() == btnDashboard) {
            CountTotal();
            lblUserConnected.setText(userparams);
            lblBreacumb.setText("/home/dashboard");
            lblStatus.setText("Dashboard");
            pnlStatus.setBackground(
                    new Background(new BackgroundFill(Color.rgb(135, 206, 235), CornerRadii.EMPTY, Insets.EMPTY)));
            grpDashboard.toFront();
        } else if (event.getSource() == btnGroupe) {
            lblUserConnected.setText(userparams);
            lblBreacumb.setText("/home/groupes");
            lblStatus.setText("Groupes");
            // pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(60, 150,
            // 30),CornerRadii.EMPTY,Insets.EMPTY)));
            pnlStatus.setBackground(
                    new Background(new BackgroundFill(Color.rgb(135, 206, 235), CornerRadii.EMPTY, Insets.EMPTY)));
            grpGroupe.toFront();

        } else if (event.getSource() == btnEtudiant) {
            lblUserConnected.setText(userparams);
            lblBreacumb.setText("/home/etudiant");
            lblStatus.setText("Etudiants");
            // pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(150, 70,
            // 50),CornerRadii.EMPTY,Insets.EMPTY)));
            pnlStatus.setBackground(
                    new Background(new BackgroundFill(Color.rgb(135, 206, 235), CornerRadii.EMPTY, Insets.EMPTY)));
            grpEtudiant.toFront();

        } else if (event.getSource() == btnProfesseur) {
            lblUserConnected.setText(userparams);
            lblBreacumb.setText("/home/professeurs");
            lblStatus.setText("Professeurs");
            // pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(90, 150,
            // 70),CornerRadii.EMPTY,Insets.EMPTY)));
            pnlStatus.setBackground(
                    new Background(new BackgroundFill(Color.rgb(135, 206, 235), CornerRadii.EMPTY, Insets.EMPTY)));
            grpProfesseur.toFront();

        } else if (event.getSource() == btnGroupeEtudiant) {
            lblUserConnected.setText(userparams);
            lblBreacumb.setText("/home/groupeEtudiant");
            lblStatus.setText("Groupe-Etudiant");
            // pnlStatus.setBackground(new Background(new BackgroundFill(Color.rgb(100, 90,
            // 150),CornerRadii.EMPTY,Insets.EMPTY)));
            pnlStatus.setBackground(
                    new Background(new BackgroundFill(Color.rgb(135, 206, 235), CornerRadii.EMPTY, Insets.EMPTY)));
            grpGroupeEtudiant.toFront();
        }

    }

    @FXML
    void Logout(ActionEvent event) {
        if (event.getSource() == btnDeconnexion) {
            try {
                helpers.load(event, "Bienvenue sur notre App", "/ui/login/Login.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        groupeUp = null;
        etudiantUp = null;
        professeurUp = null;
        CountTotal();
        lblUserConnected.setText(userparams);
        lblBreacumb.setText("/home/dashboard");
        lblStatus.setText("Dashboard");
        pnlStatus.setBackground(
                new Background(new BackgroundFill(Color.rgb(135, 206, 235), CornerRadii.EMPTY, Insets.EMPTY)));
        grpDashboard.toFront();

        loadGroupeTable();
        loadEtudiantTable();
        loadProfesseurTable();
        loadGroupeEtudiantTable();

    }

}
