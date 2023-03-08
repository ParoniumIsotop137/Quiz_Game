package com.example.ferenc.quiz_game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class QuizGameApp extends Application {

    private static Alert alert;
    @Override
    public void start(Stage stage) throws IOException {

        try {

            FXMLLoader fxmlLoader = new FXMLLoader(QuizGameApp.class.getResource("Quiz-App.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            String css = this.getClass().getResource("QuizGameAppStyle.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setTitle("Legyen Ön is valami!");
            stage.setScene(scene);

            stage.show();


        } catch (Exception e){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Hiba");
            alert.setContentText("Sikertelen csatlakozás az adatbázishoz! "+e.getMessage());
            alert.show();
        }


    }

    public static void main(String[] args) {
        launch();
    }
}