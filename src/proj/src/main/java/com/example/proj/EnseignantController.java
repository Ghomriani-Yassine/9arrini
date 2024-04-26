package com.example.proj;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.proj.Cours;

import java.awt.*;
import java.awt.event.*;

import java.sql.*;

import static com.example.proj.config.URL;
import static com.example.proj.config.USERNAME;
import static com.example.proj.config.PASSWORD;

public class EnseignantController {
    @FXML
    private Label nomLabel;
    @FXML
    private Label prenomLabel;
    @FXML
    private TextField nomCoursTextField;

    @FXML
    private TextField descriptionTextArea;
 String req=null;
    @FXML
    private Button ajouterCoursbtn;
    @FXML
    private TextField idCoursTextField;
    private Connection conn ;

    @FXML
    private TableView<Cours> coursTableView;

    @FXML
    private TableColumn<Cours, String> nomCoursColumn;

    @FXML
    private TableColumn<Cours, String> refColumn;

    @FXML
    private TableColumn<Cours, String> descriptionColumn;

    ObservableList<Cours>  courstList= FXCollections.observableArrayList();


    private Statement st;
    private PreparedStatement ps;
    private ResultSet rs;
    String fname = HelloApplication.getFirstName();
    String lname = HelloApplication.getLastName();
    public void ajouterCours() {
        String req = "INSERT INTO cours"+"(idcours,nomcours,description,idenseignant)"+" VALUES (?, ?, ?, ?, (SELECT code FROM enseignant WHERE nom = 'yassine' AND prenom = 'ghomriani'))";


        conn = database.getConnection(URL, USERNAME, PASSWORD);
        String idcourstf=idCoursTextField.getText();
        String nomCourstf=nomCoursTextField.getText();
        String refCourstf=refColumn.getText();
        String descriptionCourstf=descriptionColumn.getText();

        try {
            Alert alert;
            if (nomCourstf.isEmpty() || refCourstf.isEmpty()|| descriptionCourstf.isEmpty()||idcourstf.isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Remplir tous les champs");
                alert.showAndWait();
            } else {
                String check = "SELECT idcours FROM cours WHERE idcours ='"
                        + nomCourstf + "'";

                st = conn.createStatement();
                rs = st.executeQuery(check);
                if (rs.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cours ID: " + idcourstf + " already exists!");
                    alert.showAndWait();
                } else {
                    ps = conn.prepareStatement(req);
                    ps.setString(1, idcourstf);
                    ps.setString(2, nomCourstf);
                    ps.setString(3, descriptionCourstf);
                    ps.executeLargeUpdate();
                    // Add the new course to the TableView


                    Cours newCourse = new Cours(idcourstf, nomCourstf, descriptionCourstf);
                    coursTableView.getItems().add(newCourse);

                    // Clear input fields
                    idCoursTextField.clear();
                    nomCoursTextField.clear();
                    descriptionTextArea.clear();
                    System.out.println("cours ajouté");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//    private void loadData() throws SQLException {
//        conn = database.getConnection(URL, USERNAME, PASSWORD);
//        refreshTable();
//
//        nomCoursColumn.setCellValueFactory(new PropertyValueFactory<>("nom cours"));
//        refColumn.setCellValueFactory(new PropertyValueFactory<>("ref"));
//        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
//
//    }
//    private void refreshTable() {
//        try {
//            courstList.clear();
//            req= " SELECT * FROM cours";
//
//            PreparedStatement preparedStatement = conn.prepareStatement(req);
//            rs=preparedStatement.executeQuery();
//            while(rs.next()){
//                courstList.add(new Cours(
//                        rs.getString("nom cours"),
//                        rs.getString("ref"),
//                        rs.getString("description")
//                        ));
//                coursTableView.setItems(courstList);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }



    public void initialize() {
        // Initialize your controller, setup event handlers, load data, etc.

    }


}


