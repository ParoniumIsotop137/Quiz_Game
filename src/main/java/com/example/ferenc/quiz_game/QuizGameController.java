package com.example.ferenc.quiz_game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
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
    private MenuItem mnIAdatKezelo;

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

    private int joValaszokSzama = 0;

    private ABKezelo kezelo;

    private QuizGameHelper helper = new QuizGameHelper();

    private String connectionURL = "jdbc:mysql://localhost:3306/quizapp_db?useSSL=false";

    private String felhasznalo = "root";
    private String jelszo = "Plutonium-36";
    private Alert uzenet;

    @FXML
    private Label lblEredmenyek;

    @FXML
    private Label lblGameOver;

    private List<Kerdes> kerdesek = new ArrayList<Kerdes>();




    @FXML
    void KovetkezoKerdes(ActionEvent event) {

        kerdesekSzama++;

        if(kerdesekSzama < kerdesek.size()){
            KerdesBetoltes();
        }
        else {
            lblGameOver.setText("J??t??k v??ge!");
            lblEredmenyek.setText("??sszesen "+String.valueOf(kerdesek.size())+" k??rd??sb??l "+String.valueOf(joValaszokSzama)+" k??rd??sre adott helyes v??laszt!");
        }


    }

    @FXML
    void EredmenyKiiras(ActionEvent event) {

        joValaszokSzama += helper.Kiertekeles(chBoxA, chBoxB, chBoxC, chBoxD, lblAValasz, lblBValasz, lblCValasz, lblDValasz, kerdesek.get(kerdesekSzama));
        if(kerdesekSzama < kerdesek.size()){
            btnKovetkezo.setDisable(false);
        }


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
            uzenet.setContentText("Adatb??zis hiba: "+e.getMessage());
            uzenet.show();
        }
        ListaFeltoltes();
    }

    public void ListaFeltoltes() {

        uzenet = new Alert(Alert.AlertType.ERROR);
        try {
            kerdesek = kezelo.AdatBetoltes();
        } catch (SQLException e) {
            uzenet.setTitle("Hiba");
            uzenet.setContentText("Adatb??zis hiba: " + e.getMessage());
            uzenet.show();
        }
        KerdesBetoltes();


    }

    public void KerdesBetoltes() {

        CheckBoxAlapHelyzet();

        ValaszLabelAlapHelyzet();

        btnKovetkezo.setDisable(true);
        lblKerdes.setText(kerdesek.get(kerdesekSzama).getKerdes());
        lblAValasz.setText(kerdesek.get(kerdesekSzama).getValasz_A());
        lblBValasz.setText(kerdesek.get(kerdesekSzama).getValasz_B());
        lblCValasz.setText(kerdesek.get(kerdesekSzama).getValasz_C());
        lblDValasz.setText(kerdesek.get(kerdesekSzama).getValasz_D());

    }

    public void CheckBoxAlapHelyzet(){

        chBoxA.setSelected(false);
        chBoxB.setSelected(false);
        chBoxC.setSelected(false);
        chBoxD.setSelected(false);

        chBoxA.setDisable(false);
        chBoxB.setDisable(false);
        chBoxC.setDisable(false);
        chBoxD.setDisable(false);

    }

    public void ValaszLabelAlapHelyzet(){

        lblAValasz.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, null, null)));
        lblBValasz.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, null, null)));
        lblCValasz.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, null, null)));
        lblDValasz.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, null, null)));

    }
    @FXML
    void AValaszJelolve(ActionEvent event) {

        chBoxB.setDisable(true);
        chBoxC.setDisable(true);
        chBoxD.setDisable(true);

    }

    @FXML
    void BValaszJelolve(ActionEvent event) {

        chBoxA.setDisable(true);
        chBoxC.setDisable(true);
        chBoxD.setDisable(true);

    }

    @FXML
    void CValaszJelolve(ActionEvent event) {

        chBoxA.setDisable(true);
        chBoxB.setDisable(true);
        chBoxD.setDisable(true);

    }

    @FXML
    void DValaszJelolve(ActionEvent event) {

        chBoxA.setDisable(true);
        chBoxC.setDisable(true);
        chBoxB.setDisable(true);

    }

    @FXML
    void AdatKezeles(ActionEvent event) {

        helper.NewWindowOpening();

    }
}
