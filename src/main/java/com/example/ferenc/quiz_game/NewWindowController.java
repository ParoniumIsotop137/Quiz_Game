package com.example.ferenc.quiz_game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class NewWindowController implements Initializable {

    @FXML
    private AnchorPane anPaneModositAblak;

    @FXML
    private Button btnFelvitel;

    @FXML
    private Button btnMentes;

    @FXML
    private Button btnModositas;

    @FXML
    private Button btnTorles;

    @FXML
    private ComboBox<String> cmbHelyesValasz;

    @FXML
    private ListView<String> lstVKerdesLista;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField txtA_valasz;

    @FXML
    private TextField txtB_valasz;

    @FXML
    private TextField txtC_valasz;

    @FXML
    private TextField txtD_valasz;

    @FXML
    private TextField txtKerdes;

    private int selectedIndex;

    private Alert uzenet;

    private Kerdes kerdes;

    private ABKezelo kezelo;

    private String connectionURL = "jdbc:mysql://localhost:3306/quizapp_db?useSSL=false";

    private String felhasznalo = "root";
    private String jelszo = "Plutonium-36";

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public Kerdes getKerdes() {
        return kerdes;
    }



    public void ListaFeltoltes(List<Kerdes> kerdesek){


        ObservableList<String> items = FXCollections.observableArrayList();

        for (Kerdes kerdes : kerdesek) {
            items.add(kerdes.toString());
        }

        lstVKerdesLista.setItems(items);



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        uzenet = new Alert(Alert.AlertType.ERROR);
        try {

            kezelo = new ABKezelo(connectionURL, felhasznalo, jelszo);

        } catch (SQLException e) {
            uzenet.setTitle("Hiba");
            uzenet.setContentText("Adatbázis hiba: "+e.getMessage());
            uzenet.show();
        }

        ObservableList<String> items = FXCollections.observableArrayList("A válasz", "B válasz", "C válasz", "D válasz");
        cmbHelyesValasz.setItems(items);
    }
    @FXML
    void Felvitel(ActionEvent event) {

    }



    @FXML
    void Modositas(ActionEvent event) {

    }

    @FXML
    void Torles(ActionEvent event) {

    }


}
