package fr.thomas;

import fr.thomas.utils.Utils;
import fr.thomas.vue.Donnee;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("vue.fxml"));

        Scene scene = new Scene(root, Infos.WIDTH, Infos.HEIGHT);

        primaryStage.setTitle(Infos.NAME);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
