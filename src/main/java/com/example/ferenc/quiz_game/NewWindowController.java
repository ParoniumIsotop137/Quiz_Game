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
        btnMentes.setDisable(true);
        btnMentes.setVisible(false);
        try {

            kezelo = new ABKezelo(connectionURL, felhasznalo, jelszo);
            ListaFeltoltes();
            ABListaFeltoltes();
        } catch (SQLException e) {
            uzenet.setTitle("Hiba");
            uzenet.setContentText("Adatb??zis hiba: "+e.getMessage());
            uzenet.show();
        }

        ObservableList<String> items = FXCollections.observableArrayList("A v??lasz", "B v??lasz", "C v??lasz", "D v??lasz");
        cmbHelyesValasz.setItems(items);
    }
    @FXML
    void Felvitel(ActionEvent event) {

        uzenet = new Alert(Alert.AlertType.WARNING);
        Alert hibaUzenet = new Alert(Alert.AlertType.ERROR);

        selectedIndex = cmbHelyesValasz.getSelectionModel().getSelectedIndex();

        try {

            if(kerdes == null){
                if(txtKerdes.getText() != null && !txtKerdes.getText().isEmpty() && selectedIndex != -1){

                    kerdes = new Kerdes(txtKerdes.getText(), txtA_valasz.getText(), txtB_valasz.getText(), txtC_valasz.getText(), txtD_valasz.getText(), helyesValaszok[selectedIndex]);
                    kezelo.AdatFelvitel(kerdes);
                    aBKerdesek.add(kerdes);
                    items.add(kerdes.toString());
                    lstVKerdesLista.setItems(items);
                    uzenet = new Alert(Alert.AlertType.INFORMATION);
                    uzenet.setTitle("Inform??ci??");
                    uzenet.setContentText("Az ??j k??rd??s/v??laszok felvitele sikeresen megt??rt??nt!");
                    uzenet.show();
                    MezokAlaphelyzetbeAllitasa();


                }
                else{
                    uzenet.setTitle("Figyelmeztet??s");
                    uzenet.setContentText("A k??rd??s mez?? nem maradhat ??resen!");
                    uzenet.show();
                }
            }


        } catch(IllegalArgumentException e){
            uzenet = new Alert(Alert.AlertType.WARNING);
            uzenet.setTitle("Figyelmeztet??s");
            uzenet.setContentText(e.getMessage());
            uzenet.show();
        } catch (SQLException e){
            hibaUzenet.setTitle("Hiba");
            hibaUzenet.setContentText("Adatb??zis hiba: "+e.getMessage());
            hibaUzenet.show();
        }



    }



    @FXML
    void Modositas(ActionEvent event) {

        Ellenorzes();
        btnMentes.setDisable(false);
        btnMentes.setVisible(true);

    }

    @FXML
    void Torles(ActionEvent event) {

       if(lstVKerdesLista.getSelectionModel().getSelectedIndex() != -1){
            selectedIndex = lstVKerdesLista.getSelectionModel().getSelectedIndex();

            uzenet = new Alert(Alert.AlertType.CONFIRMATION);
            uzenet.setTitle("T??rl??s");
            uzenet.setContentText("Biztosan t??rli a kijel??lt k??rd??st?");
            ButtonType igenButton = new ButtonType("Igen", ButtonBar.ButtonData.YES);
            ButtonType nemButton = new ButtonType("M??gsem", ButtonBar.ButtonData.NO);
            uzenet.getButtonTypes().setAll(igenButton, nemButton);
            uzenet.showAndWait().ifPresent(buttonType -> {
                if (buttonType == igenButton) {
                    try {

                        kezelo.AdatTorles(aBKerdesek.get(selectedIndex));
                        items.remove(selectedIndex);
                        lstVKerdesLista.setItems(items);
                        aBKerdesek.remove(selectedIndex);

                        uzenet = new Alert(Alert.AlertType.INFORMATION);
                        uzenet.setTitle("Inform??ci??");
                        uzenet.setContentText("A kijel??lt sor t??rl??se sikeresen megt??rt??nt!");
                        uzenet.show();

                    } catch (SQLException e) {
                        uzenet = new Alert(Alert.AlertType.ERROR);
                        uzenet.setTitle("Hiba");
                        uzenet.setContentText("Adatb??zis hiba: " + e.getMessage());
                        uzenet.show();
                    }
                }
            });

        }
        else if(lstVKerdesLista.getSelectionModel().getSelectedIndex() == -1){
           uzenet = new Alert(Alert.AlertType.WARNING);
           uzenet.setTitle("Figyelmeztet??s");
           uzenet.setContentText("Nincsen t??rl??sre kijel??lt sor!");
           uzenet.show();
        }



    }

    public void ListaFeltoltes() {

        uzenet = new Alert(Alert.AlertType.ERROR);
        try {
            aBKerdesek = kezelo.AdatBetoltes();
        } catch (SQLException e) {
            uzenet.setTitle("Hiba");
            uzenet.setContentText("Adatb??zis hiba: " + e.getMessage());
            uzenet.show();
        }

    }

    public void Ellenorzes(){

        uzenet = new Alert(Alert.AlertType.WARNING);

        if(lstVKerdesLista.getSelectionModel().getSelectedIndex() != -1){
            selectedIndex = lstVKerdesLista.getSelectionModel().getSelectedIndex();

            kerdes = aBKerdesek.get(selectedIndex);
            txtKerdes.setText(kerdes.getKerdes());
            txtA_valasz.setText(kerdes.getValasz_A());
            txtB_valasz.setText(kerdes.getValasz_B());
            txtC_valasz.setText(kerdes.getValasz_C());
            txtD_valasz.setText(kerdes.getValasz_D());

            if(kerdes.getHelyesValasz().equals(helyesValaszok[0])){
                cmbHelyesValasz.getSelectionModel().select(0);
            }
            else if(kerdes.getHelyesValasz().equals(helyesValaszok[1])){
                cmbHelyesValasz.getSelectionModel().select(1);
            }
            else if(kerdes.getHelyesValasz().equals(helyesValaszok[2])){
                cmbHelyesValasz.getSelectionModel().select(2);
            }
            else if(kerdes.getHelyesValasz().equals(helyesValaszok[3])){
                cmbHelyesValasz.getSelectionModel().select(3);
            }

        }
        else{
            uzenet.setTitle("Figyelmeztet??s");
            uzenet.setContentText("Nincsen m??dos??t??sra kijel??lt sor!");
            uzenet.show();
        }
    }

    @FXML
    void Mentes(ActionEvent event) {

        uzenet = new Alert(Alert.AlertType.WARNING);
        btnMentes.setDisable(true);
        btnMentes.setVisible(false);
        int cmbIndex = cmbHelyesValasz.getSelectionModel().getSelectedIndex();
        Alert hibaUzenet = new Alert(Alert.AlertType.ERROR);
        try {

            if(txtKerdes.getText() != null && !txtKerdes.getText().isEmpty() && selectedIndex != -1){

                kerdes.setKerdes(txtKerdes.getText());
                kerdes.setValasz_A(txtA_valasz.getText());
                kerdes.setValasz_B(txtB_valasz.getText());
                kerdes.setValasz_C(txtC_valasz.getText());
                kerdes.setValasz_D(txtD_valasz.getText());
                kerdes.setHelyesValasz(helyesValaszok[cmbIndex]);

                kezelo.AdatModositas(kerdes);
                aBKerdesek.set(selectedIndex, kerdes);
                items.set(selectedIndex, kerdes.toString());
                lstVKerdesLista.setItems(items);
                uzenet = new Alert(Alert.AlertType.INFORMATION);
                uzenet.setTitle("Inform??ci??");
                uzenet.setContentText("Az ??j k??rd??s/v??laszok m??dos??t??sa sikeresen megt??rt??nt!");
                uzenet.show();
                MezokAlaphelyzetbeAllitasa();

            }
            else{
                uzenet = new Alert(Alert.AlertType.WARNING);
                uzenet.setTitle("Figyelmeztet??s");
                uzenet.setContentText("A k??rd??s mez?? nem maradhat ??resen");
                uzenet.show();
            }

        } catch (IllegalArgumentException e){
            uzenet = new Alert(Alert.AlertType.WARNING);
            uzenet.setTitle("Figyelmeztet??s");
            uzenet.setContentText(e.getMessage());
            uzenet.show();
        } catch (SQLException e){
            hibaUzenet.setTitle("Hiba");
            hibaUzenet.setContentText("Adatb??zis hiba: "+e.getMessage());
            hibaUzenet.show();
        }


    }
    public void MezokAlaphelyzetbeAllitasa(){

        txtKerdes.setText("");
        txtA_valasz.setText("");
        txtB_valasz.setText("");
        txtC_valasz.setText("");
        txtD_valasz.setText("");
        cmbHelyesValasz.getSelectionModel().select(0);

    }
}
