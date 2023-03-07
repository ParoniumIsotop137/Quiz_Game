package com.example.ferenc.quiz_game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
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

        ObservableList<String> items = FXCollections.observableArrayList("A válasz", "B válasz", "C válasz", "D válasz");
        cmbHelyesValasz.setItems(items);
    }
    @FXML
    void Felvitel(ActionEvent event) {

    }

    @FXML
    void Mentes(ActionEvent event) {

    }

    @FXML
    void Modositas(ActionEvent event) {

    }

    @FXML
    void Torles(ActionEvent event) {

    }


}
