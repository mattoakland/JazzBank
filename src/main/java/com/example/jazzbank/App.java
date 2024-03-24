package com.example.jazzbank;

import com.example.jazzbank.Models.Model;
import com.example.jazzbank.Views.ViewFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}