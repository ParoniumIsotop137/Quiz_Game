package com.example.ferenc.quiz_game;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import java.util.List;

public class NewWindowController {

    @FXML
    private AnchorPane anPaneModositAblak;

    @FXML
    private ListView<String> lstVKerdesLista;

    @FXML
    private ScrollPane scrollPane;

    private Kerdes kerdes;

    public void ListaFeltoltes(List<Kerdes> kerdesek){


        ObservableList<String> items = FXCollections.observableArrayList();

        for (Kerdes kerdes : kerdesek) {
            items.add(kerdes.toString());
        }

        lstVKerdesLista.setItems(items);



    }

}
