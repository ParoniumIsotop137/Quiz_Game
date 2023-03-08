package com.example.ferenc.quiz_game;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;



public class QuizGameHelper {

    private Alert uzenet;
    public int Kiertekeles(CheckBox chBoxA, CheckBox chBoxB, CheckBox chBoxC, CheckBox chBoxD, Label lblAValasz, Label lblBValasz, Label lblCValasz, Label lblDValasz, Kerdes kerdes) {

        int joValaszokSzama = 0;

        if(chBoxA.isSelected() && kerdes.getHelyesValasz().equals("valasz_a")){

            joValaszokSzama++;
            lblAValasz.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));

        }
        else if(chBoxA.isSelected() && !kerdes.getHelyesValasz().equals("valasz_a")){
            lblAValasz.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        }
        else if(!chBoxA.isSelected() && kerdes.getHelyesValasz().equals("valasz_a")){
            lblAValasz.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        }

        if(chBoxB.isSelected() && kerdes.getHelyesValasz().equals("valasz_b")){
            joValaszokSzama++;
            lblBValasz.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));

        }
        else if(chBoxB.isSelected() && !kerdes.getHelyesValasz().equals("valasz_b")){
            lblBValasz.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        }
        else if(!chBoxB.isSelected() && kerdes.getHelyesValasz().equals("valasz_b")){
            lblBValasz.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        }

        if(chBoxC.isSelected() && kerdes.getHelyesValasz().equals("valasz_c")){
            joValaszokSzama++;
            lblCValasz.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));

        }
        else if (chBoxC.isSelected() && !kerdes.getHelyesValasz().equals("valasz_c")){
            lblCValasz.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        }
        else if(!chBoxC.isSelected() && kerdes.getHelyesValasz().equals("valasz_c")){
            lblCValasz.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        }

        if(chBoxD.isSelected() && kerdes.getHelyesValasz().equals("valasz_d")){
            joValaszokSzama++;
            lblDValasz.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));

        }
        else if(chBoxD.isSelected() && !kerdes.getHelyesValasz().equals("valasz_d")){
            lblDValasz.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        }
        else if(!chBoxD.isSelected() && kerdes.getHelyesValasz().equals("valasz_d")){
            lblDValasz.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        }

        return joValaszokSzama;

    }


    public void NewWindowOpening() {

        uzenet = new Alert(Alert.AlertType.ERROR);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DataHandling-Window.fxml"));
            Parent root = loader.load();

            NewWindowController newController = loader.getController();
            Stage thisStage = new Stage();

            Scene scene = new Scene(root);

            thisStage.setScene(scene);
            thisStage.setTitle("Adatkezelés");
            thisStage.initModality(Modality.APPLICATION_MODAL);
            thisStage.show();

        } catch (Exception e) {
            uzenet.setTitle("Hiba");
            uzenet.setContentText("Hiba az új ablak megnyitásakor: "+e.getMessage());
            uzenet.show();
        }


    }
}
