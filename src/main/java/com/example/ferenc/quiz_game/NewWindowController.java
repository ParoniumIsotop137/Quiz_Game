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
import java.util.ArrayList;
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

    private String [] helyesValaszok = new String[]{"valasz_a","valasz_b", "valasz_c", "valasz_d"};

    private List<Kerdes> aBKerdesek = new ArrayList<Kerdes>();

    private ObservableList<String> items;

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public Kerdes getKerdes() {
        return kerdes;
    }



    public void ABListaFeltoltes(){


        items = FXCollections.observableArrayList();

        for (Kerdes kerdes : aBKerdesek) {
            items.add(kerdes.toString());
        }

        lstVKerdesLista.setItems(items);



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        uzenet = new Alert(Alert.AlertType.ERROR);
        try {

            kezelo = new ABKezelo(connectionURL, felhasznalo, jelszo);
            ListaFeltoltes();
            ABListaFeltoltes();
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

        uzenet = new Alert(Alert.AlertType.WARNING);
        Alert hibaUzenet = new Alert(Alert.AlertType.ERROR);

        selectedIndex = cmbHelyesValasz.getSelectionModel().getSelectedIndex();

        try {

            if(kerdes == null){
                if(txtKerdes.getText() != null && !txtKerdes.getText().isEmpty()){

                    kerdes = new Kerdes(txtKerdes.getText(), txtA_valasz.getText(), txtB_valasz.getText(), txtC_valasz.getText(), txtD_valasz.getText(), helyesValaszok[selectedIndex]);
                    kezelo.AdatFelvitel(kerdes);
                    aBKerdesek.add(kerdes);
                    items.add(kerdes.toString());
                    lstVKerdesLista.setItems(items);

                }
                else{
                    uzenet.setTitle("Figyelmeztetés");
                    uzenet.setContentText("A kérdés mező nem maradhat üresen!");
                    uzenet.show();
                }
            }


        } catch(IllegalArgumentException e){

        } catch (SQLException e){
            hibaUzenet.setTitle("Hiba");
            hibaUzenet.setContentText("Adatbázis hiba: "+e.getMessage());
            hibaUzenet.show();
        }
        uzenet = new Alert(Alert.AlertType.INFORMATION);
        uzenet.setTitle("Információ");
        uzenet.setContentText("Az új kérdás/válaszok felvitele sikeresen megtörtént!");
        uzenet.show();


    }



    @FXML
    void Modositas(ActionEvent event) {

    }

    @FXML
    void Torles(ActionEvent event) {

    }

    public void ListaFeltoltes() {

        uzenet = new Alert(Alert.AlertType.ERROR);
        try {
            aBKerdesek = kezelo.AdatBetoltes();
        } catch (SQLException e) {
            uzenet.setTitle("Hiba");
            uzenet.setContentText("Adatbázis hiba: " + e.getMessage());
            uzenet.show();
        }

    }
}
