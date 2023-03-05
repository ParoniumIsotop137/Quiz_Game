package com.example.ferenc.quiz_game;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class QuizGameHelper {
    public void Kiertekeles(CheckBox chBoxA, CheckBox chBoxB, CheckBox chBoxC, CheckBox chBoxD, Label lblAValasz, Label lblBValasz, Label lblCValasz, Label lblDValasz, Kerdes kerdes, int joValaszokSzama) {

        if(chBoxA.isSelected() && kerdes.getHelyesValasz().equals("valasz_a")){
            lblAValasz.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
            joValaszokSzama++;
        }
        else if(chBoxA.isSelected() && !kerdes.getHelyesValasz().equals("valasz_a")){
            lblAValasz.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        }
        else if(!chBoxA.isSelected() && kerdes.getHelyesValasz().equals("valasz_a")){
            lblAValasz.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        }

        if(chBoxB.isSelected() && kerdes.getHelyesValasz().equals("valasz_b")){
            lblBValasz.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
            joValaszokSzama++;
        }
        else if(chBoxB.isSelected() && !kerdes.getHelyesValasz().equals("valasz_b")){
            lblBValasz.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        }
        else if(!chBoxB.isSelected() && kerdes.getHelyesValasz().equals("valasz_b")){
            lblBValasz.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        }

        if(chBoxC.isSelected() && kerdes.getHelyesValasz().equals("valasz_c")){
            lblCValasz.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
            joValaszokSzama++;
        }
        else if (chBoxC.isSelected() && !kerdes.getHelyesValasz().equals("valasz_c")){
            lblCValasz.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        }
        else if(!chBoxC.isSelected() && kerdes.getHelyesValasz().equals("valasz_c")){
            lblCValasz.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        }

        if(chBoxD.isSelected() && kerdes.getHelyesValasz().equals("valasz_d")){
            lblDValasz.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
            joValaszokSzama++;
        }
        else if(chBoxD.isSelected() && !kerdes.getHelyesValasz().equals("valasz_d")){
            lblDValasz.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        }
        else if(!chBoxD.isSelected() && kerdes.getHelyesValasz().equals("valasz_d")){
            lblDValasz.setBackground(new Background(new BackgroundFill(Color.GREEN, null, null)));
        }


    }
}
