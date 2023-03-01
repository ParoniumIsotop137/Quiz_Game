module com.example.ferenc.quiz_game {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires mysql.connector.java;
    requires java.sql;
    opens com.example.ferenc.quiz_game to javafx.fxml;
    exports com.example.ferenc.quiz_game;
}