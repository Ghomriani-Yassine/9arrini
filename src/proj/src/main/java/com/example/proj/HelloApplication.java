package com.example.proj;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static String fname;
    private static String lname;

    public HelloApplication(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }
    public HelloApplication() {
        // Initialize fname and lname to default values if needed
        this.fname = "yassine";
        this.lname = "ghomriani";
    }

    public static String getFirstName() {
        return fname;
    }

    public static String getLastName() {
        return lname;
    }

    @Override
    public void start(Stage stage) throws Exception {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("enseignant.fxml"));

            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setTitle("enseignant");
            stage.setScene(scene);
            stage.show();

    }

    public static void  main(String[] args) {
        launch(args);
    }
}
