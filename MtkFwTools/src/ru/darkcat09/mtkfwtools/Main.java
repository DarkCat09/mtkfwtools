package ru.darkcat09.mtkfwtools;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static String currentLang;
    public static Languages langs;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mtkfwtools.fxml"));
        primaryStage.setTitle("MTK Firmware Tools by DarkCat09");
        primaryStage.setScene(new Scene(root, 370, 176));
        primaryStage.setResizable(false);
        primaryStage.show();
        langs = new Languages();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
