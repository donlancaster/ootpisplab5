package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.hierarchy.*;
import sample.serialization.BinarySerialization;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 850, 650));
        primaryStage.show();
    }

    public static ObservableList<CosmicBody> obslist = FXCollections.observableArrayList();
    public static LinkedList<Galaxy> serializeList = new LinkedList<>();

    public static void main(String[] args) {
        BlackHole bh = new BlackHole(123, "anme", 123, "form", 123, "red", "structure", 12, 15, 123, 100);
        BlackHole bh2 = new BlackHole(123, "2", 123, "form", 123, "red", "structure", 12, 15, 123, 100);
        BlackHole bh3 = new BlackHole(123, "3", 123, "form", 123, "red", "structure", 12, 15, 123, 100);

        obslist.add(bh);
        obslist.add(bh2);
        obslist.add(bh3);
        obslist.add(new Star(123, "star1", 1, "form1", 1, "first", "structure1", 1, 1));
        obslist.add(new Planet(123, "planet1", 2, "form2", 2, "second", "structur2e"));
        obslist.add(new Galaxy(123, "andromeda", 3, "form3", "galaxyy1", 12412141, bh));
        obslist.add(new Galaxy(123, "alpha", 3, "form3", "galaxyy1", 12412141, bh3));
        obslist.add(new Asteroids(999, "ast", 11111, "asteroid", "asaasaaa", 4990));
        obslist.add(new Comet(9, "com", 2222, "comet", "assssssssssssa", 228));


        serializeList.add(new Galaxy(123, "1", 1, "123", "typeee", 123, bh));
        serializeList.add(new Galaxy(111, "2", 23, "123", "typeee", 123, bh2));
        serializeList.add(new Galaxy(222, "3", 3, "123", "typeee", 123, bh3));
        serializeList.add(new Galaxy(333, "4", 4, "123", "typeee", 123, bh2));

        System.out.println(obslist);

        System.out.println(25 % 7); //4 Decorator
        System.out.println(7 - 25 % 7); //3 Command
        launch(args);
    }
}
