package com.example.ferenc.quiz_game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class QuizGameController implements Initializable {

    @FXML
    private AnchorPane anPaneKeret;

    @FXML
    private ImageView imVHatter;

    @FXML
    private Label lblCimSor;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu mnAdatkezeles;

    @FXML
    private MenuItem mnIAdatTorles;

    @FXML
    private MenuItem mnIAdatfelvitel;

    @FXML
    private MenuItem mnIModositas;

    @FXML
    private Label lblKerdes;

    @FXML
    private CheckBox chBoxA;

    @FXML
    private CheckBox chBoxB;

    @FXML
    private CheckBox chBoxC;

    @FXML
    private CheckBox chBoxD;

    @FXML
    private Label lblAValasz;

    @FXML
    private Label lblBValasz;

    @FXML
    private Label lblCValasz;

    @FXML
    private Label lblDValasz;

    @FXML
    private Button btnEredmeny;

    @FXML
    private Button btnKovetkezo;

    private int kerdesekSzama = 0;

    private int joValaszokSzama;

    private ABKezelo kezelo;

    private String connectionURL = "jdbc:mysql://localhost:3306/quizapp_db?useSSL=false";

    private String felhasznalo = "root";
    private String jelszo = "Plutonium-36";
    private Alert uzenet;

    private List<Kerdes> kerdesek = new ArrayList<Kerdes>();


    @FXML
    void AdatFelvitel(ActionEvent event) {

    }


    @FXML
    void AdatModositas(ActionEvent event) {

    }

    @FXML
    void AdatTorles(ActionEvent event) {

    }

    @FXML
    void KovetkezoKerdes(ActionEvent event) {

        kerdesekSzama++;
        KerdesBetoltes();

    }

    @FXML
    void EredmenyKiiras(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Csatlakozas();


    }

    public void Csatlakozas() {

        uzenet = new Alert(Alert.AlertType.ERROR);
        try {

            kezelo = new ABKezelo(connectionURL,felhasznalo, jelszo);


        } catch (SQLException e) {
            uzenet.setTitle("Hiba");
            uzenet.setContentText("Adatbázis hiba: "+e.getMessage());
            uzenet.show();
        }
        ListaFeltoltes();
    }

    public void ListaFeltoltes() {

        try {
            kerdesek = kezelo.AdatBetoltes();
        } catch (SQLException e) {
            uzenet.setTitle("Hiba");
            uzenet.setContentText("Adatbázis hiba: " + e.getMessage());
            uzenet.show();
        }
        KerdesBetoltes();


    }

    public void KerdesBetoltes() {


        lblKerdes.setText(kerdesek.get(kerdesekSzama).getKerdes());
        lblAValasz.setText(kerdesek.get(kerdesekSzama).getValasz_A());
        lblBValasz.setText(kerdesek.get(kerdesekSzama).getValasz_B());
        lblCValasz.setText(kerdesek.get(kerdesekSzama).getValasz_C());
        lblDValasz.setText(kerdesek.get(kerdesekSzama).getValasz_D());

    }
}
