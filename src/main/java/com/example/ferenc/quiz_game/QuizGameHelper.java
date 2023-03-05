package com.example.ferenc.quiz_game;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class QuizGameHelper {
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
}
