package sample.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.hierarchy.*;

import static sample.Main.obslist;

public class AddingController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> chooseTypeBox;

    @FXML
    private TextField nameAddField;

    @FXML
    private TextField sizeAddField;

    @FXML
    private TextField massAddField;

    @FXML
    private TextField formAddField;

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField rotSpeedAddField;

    @FXML
    private TextField colorAddField;

    @FXML
    private TextField structureAddField;

    @FXML
    private TextField structureGarbageAddField;

    @FXML
    private TextField xrayAddField;

    @FXML
    private TextField luminosityAddField;

    @FXML
    private TextField evaporationAddField;

    @FXML
    private TextField ehRadiusAddField;

    @FXML
    private TextField galgxyTypeField;

    @FXML
    private TextField elementsAddField;

    @FXML
    private ComboBox<BlackHole> bhAddBox;

    @FXML
    private TextField quantityAddField;

    @FXML
    private TextField tailLengthAddField;


    @FXML
    void add(ActionEvent event) {
        CosmicBody adding;
        switch (chooseTypeBox.getSelectionModel().getSelectedItem().toString()) {
            case "Planet": {
                //  System.out.println(structureAddField.getText());
                adding = new Planet(Double.parseDouble(massAddField.getText()), nameAddField.getText(), Double.parseDouble(sizeAddField.getText()), formAddField.getText(), Double.parseDouble(rotSpeedAddField.getText()), colorAddField.getText(), structureAddField.getText());
                obslist.add(adding);
                break;
            }
            case "Star": {
                adding = new Star(Double.parseDouble(massAddField.getText()), nameAddField.getText(), Double.parseDouble(sizeAddField.getText()), formAddField.getText(), Double.parseDouble(rotSpeedAddField.getText()), colorAddField.getText(), structureAddField.getText(), Double.parseDouble(xrayAddField.getText()), Double.parseDouble(luminosityAddField.getText()));
                obslist.add(adding);
                break;
            }
            case "BlackHole": {
                adding = new BlackHole(Double.parseDouble(massAddField.getText()), nameAddField.getText(), Double.parseDouble(sizeAddField.getText()), formAddField.getText(), Double.parseDouble(rotSpeedAddField.getText()), colorAddField.getText(), structureAddField.getText(), Double.parseDouble(xrayAddField.getText()), Double.parseDouble(luminosityAddField.getText()), Double.parseDouble(evaporationAddField.getText()), Double.parseDouble(ehRadiusAddField.getText()));
                obslist.add(adding);
                break;

            }
            case "Galaxy" : {
                adding = new Galaxy(Double.parseDouble(massAddField.getText()), nameAddField.getText(), Double.parseDouble(sizeAddField.getText()), formAddField.getText(), galgxyTypeField.getText(), Integer.parseInt(elementsAddField.getText()), searchHole(bhAddBox.getSelectionModel().getSelectedItem().toString()));
                obslist.add(adding);
break;
            }
            case "Asteroids" : {
                adding = new Asteroids(Double.parseDouble(massAddField.getText()), nameAddField.getText(), Double.parseDouble(sizeAddField.getText()), formAddField.getText(), structureGarbageAddField.getText(), Integer.parseInt(quantityAddField.getText()));
                obslist.add(adding);
                break;
            }
            case "Comet" : {
                adding = new Comet(Double.parseDouble(massAddField.getText()), nameAddField.getText(), Double.parseDouble(sizeAddField.getText()), formAddField.getText(), structureGarbageAddField.getText(), Double.parseDouble(tailLengthAddField.getText()));
                obslist.add(adding);
                break;
            }

        }
        cancel(event);
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void initialize() {

        chooseTypeBox.getItems().clear();
        chooseTypeBox.getItems().addAll("Planet", "Star", "BlackHole", "Galaxy", "Asteroids", "Comet");
        updateBHElements();

    }

    void updateBHElements() {
        bhAddBox.getItems().clear();

        for (CosmicBody elem : obslist) {
            //   System.out.println(elem.getClass());
            if (String.valueOf(elem.getClass().toString()).substring(13).startsWith("BlackHole")) {
                bhAddBox.getItems().add((BlackHole) elem);

            }
        }
        //  System.out.println();
    }

    BlackHole searchHole(String name) {

        for (CosmicBody elem : obslist) {
            if (String.valueOf(elem.getClass().toString()).substring(13).startsWith("BlackHole")) {
                if (elem.toString().contains(name))
                    return (BlackHole) elem;
            }
        }
        return null;
    }
}
