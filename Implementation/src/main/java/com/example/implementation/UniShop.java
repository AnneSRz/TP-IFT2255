package com.example.implementation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class UniShop extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(UniShop.class.getResource("unishop.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1150, 750);
        stage.setTitle("UniShop");
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}

