package com.ai.assistant.gui;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(Objects.requireNonNull(MainApplication.class.getResource("/fxml/mainwindow.fxml")));
        Scene scene = new Scene(parent);
        stage.setTitle("AI Assistant");
        stage.setScene(scene);
        stage.show();
    }

    public static void launchApplication() {
        MainApplication.launch(MainApplication.class,"");
    }
}
