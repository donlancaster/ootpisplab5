package sample.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Compression1.Compression;
import sample.file.CompressionFileRW;
import sample.file.DefaultFileRW;
import sample.file.FileRW;
import sample.hierarchy.*;
import sample.serialization.*;

import static sample.Main.obslist;
import static sample.Main.serializeList;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;


    @FXML
    private TextField nameField;

    @FXML
    private TextField sizeField;

    @FXML
    private TextField massField;

    @FXML
    private TextField formField;

    @FXML
    private Label nameLabel;

    @FXML
    private Label sizeLabel;

    @FXML
    private Label massLabel;

    @FXML
    private Label formLabel;

    @FXML
    private ListView<CosmicBody> listView;

    @FXML
    private TextField rotationSpeedField;

    @FXML
    private TextField colorField;

    @FXML
    private TextField structureField;

    @FXML
    private Label rotSpeedLabel;

    @FXML
    private Label colorLabel;

    @FXML
    private Label structureLable;


    @FXML
    private Label xrayLabel;

    @FXML
    private TextField xrayField;

    @FXML
    private Label luminosityLabel;

    @FXML
    private TextField luminosityField;

    @FXML
    private Label evaporationLabel;

    @FXML
    private TextField evaporationField;

    @FXML
    private Label eventHorizontLabel;

    @FXML
    private TextField eventHorizontField;

    @FXML
    private Label galaxyTypeLabel;

    @FXML
    private TextField galaxyTypeField;

    @FXML
    private Label elementsLabel;

    @FXML
    private TextField elementsField;

    @FXML
    private Label blackHoleLabel;

    @FXML
    private ComboBox<BlackHole> blackHoleBox;

    @FXML
    private Label astQuantityLabel;

    @FXML
    private TextField astQuantityField;

    @FXML
    private Label cometTailLengthLabel;

    @FXML
    private TextField cometTailLengthField;

    @FXML
    private Button addButton;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;


    @FXML
    private Button serializeButton;

    @FXML
    private CheckBox compressionCheckBox;

    @FXML
    private ComboBox<String> serializeMethodBox;

    @FXML
    private Button deserializeButton;


    private Serialization serialization;

//    private void changeCompression() {
//        if (compressionCheckBox.isSelected()) {
//            Class<? extends Compression> compressionClass = CompressionFileRW.compressionClasses.get(0);
//            try {
//                FileRW.setFileJob(new CompressionFileRW(compressionClass.getDeclaredConstructor().newInstance()));
//            } catch (InstantiationException | InvocationTargetException |
//                    NoSuchMethodException | IllegalAccessException e) {
//                e.printStackTrace();
//            }
//            System.out.println("compress");
//        } else FileRW.setFileJob(new DefaultFileRW());
//    }

    @FXML
    void add(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setTitle("add");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxml/adding.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.toFront();
        stage.setResizable(false);
        stage.showAndWait();
        initialize();
    }

    @FXML
    void delete(ActionEvent event) {
        System.out.println(obslist);
        obslist.remove(obslist.get(listView.getSelectionModel().getSelectedIndex()));
        System.out.println(obslist);
        initialize();
    }

    @FXML
    void edit(ActionEvent event) {
        if (listView.getSelectionModel().getSelectedIndex() == -1) return;
        CosmicBody editing;
        //  System.out.println(obslist.get(listView.getSelectionModel().getSelectedIndex()).getClass().toString());
        int index;
        switch (obslist.get(listView.getSelectionModel().getSelectedIndex()).getClass().toString().substring(23)) {

            case "Planet":
                editing = new Planet(Double.parseDouble(massField.getText()), nameField.getText(), Double.parseDouble(sizeField.getText()), formField.getText(), Double.parseDouble(rotationSpeedField.getText()), colorField.getText(), structureField.getText());
                index = listView.getSelectionModel().getSelectedIndex();
                obslist.remove(index);
                obslist.add(index, editing);
                break;

            case "Star":
                editing = new Star(Double.parseDouble(massField.getText()), nameField.getText(), Double.parseDouble(sizeField.getText()), formField.getText(), Double.parseDouble(rotationSpeedField.getText()), colorField.getText(), structureField.getText(), Double.parseDouble(xrayField.getText()), Double.parseDouble(luminosityField.getText()));
                index = listView.getSelectionModel().getSelectedIndex();
                obslist.remove(index);
                obslist.add(index, editing);
                break;

            case "BlackHole":
                editing = new BlackHole(Double.parseDouble(massField.getText()), nameField.getText(), Double.parseDouble(sizeField.getText()), formField.getText(), Double.parseDouble(rotationSpeedField.getText()), colorField.getText(), structureField.getText(), Double.parseDouble(xrayField.getText()), Double.parseDouble(luminosityField.getText()), Double.parseDouble(evaporationField.getText()), Double.parseDouble(eventHorizontField.getText()));
                index = listView.getSelectionModel().getSelectedIndex();
                obslist.remove(index);
                obslist.add(index, editing);
                break;

            case "Galaxy":
                //  System.out.println(blackHoleBox.getSelectionModel().getSelectedItem().toString());
                editing = new Galaxy(Double.parseDouble(massField.getText()), nameField.getText(), Double.parseDouble(sizeField.getText()), formField.getText(), galaxyTypeField.getText(), Integer.parseInt(elementsField.getText()), searchHole(blackHoleBox.getSelectionModel().getSelectedItem().toString()));
                 index = listView.getSelectionModel().getSelectedIndex();
                obslist.remove(index);
                obslist.add(index, editing);
                break;

            case "Asteroids":
                editing = new Asteroids(Double.parseDouble(massField.getText()), nameField.getText(), Double.parseDouble(sizeField.getText()), formField.getText(), structureField.getText(), Integer.parseInt(astQuantityField.getText()));
                 index = listView.getSelectionModel().getSelectedIndex();
                obslist.remove(index);
                obslist.add(index, editing);
                break;

            case "Comet":
                editing = new Comet(Double.parseDouble(massField.getText()), nameField.getText(), Double.parseDouble(sizeField.getText()), formField.getText(), structureField.getText(), Double.parseDouble(cometTailLengthField.getText()));
                 index = listView.getSelectionModel().getSelectedIndex();
                obslist.remove(index);
                obslist.add(index, editing);
                break;

        }
        initialize();

    }

    BlackHole searchHole(String name) {

        for (CosmicBody elem : obslist) {
            System.out.println(String.valueOf(elem.getClass().toString()).substring(23));
            if (String.valueOf(elem.getClass().toString()).substring(23).startsWith("BlackHole")) {
                if (elem.toString().contains(name))
                    return (BlackHole) elem;
            }
        }
        return null;
    }


    void updateBHElements() {
        blackHoleBox.getItems().clear();
        if (listView.getSelectionModel().isEmpty()) return;

        for (CosmicBody elem : obslist) {
               System.out.println(elem.getClass());
            if (String.valueOf(elem.getClass().toString()).substring(23).startsWith("BlackHole")) {
                blackHoleBox.getItems().add((BlackHole) elem);

            }
        }
        //  System.out.println();
    }


    @FXML
    void initialize() {
        //  System.out.println(obslist.size());
        listView.getItems().clear();
        listView.getItems().addAll(obslist);
        setSerializeMethodBox();
        listView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<CosmicBody>() {
                    @Override
                    public void changed(ObservableValue<? extends CosmicBody> observableValue, CosmicBody cosmicBody, CosmicBody t1) {

                        updateBHElements();
                        if (listView.getSelectionModel().getSelectedIndex() == -1) return;
                        nameField.setVisible(true);
                        nameLabel.setVisible(true);
                        formField.setVisible(true);
                        formLabel.setVisible(true);
                        sizeField.setVisible(true);
                        sizeLabel.setVisible(true);
                        massField.setVisible(true);
                        massLabel.setVisible(true);

                        rotationSpeedField.setVisible(false);
                        rotSpeedLabel.setVisible(false);
                        structureField.setVisible(false);
                        structureLable.setVisible(false);
                        colorField.setVisible(false);
                        colorLabel.setVisible(false);
                        xrayField.setVisible(false);
                        xrayLabel.setVisible(false);
                        luminosityField.setVisible(false);
                        luminosityLabel.setVisible(false);
                        evaporationField.setVisible(false);
                        evaporationLabel.setVisible(false);
                        eventHorizontField.setVisible(false);
                        eventHorizontLabel.setVisible(false);
                        galaxyTypeLabel.setVisible(false);
                        galaxyTypeField.setVisible(false);
                        elementsLabel.setVisible(false);
                        elementsField.setVisible(false);
                        blackHoleLabel.setVisible(false);
                        blackHoleBox.setVisible(false);
                        astQuantityField.setVisible(false);
                        astQuantityLabel.setVisible(false);
                        cometTailLengthField.setVisible(false);
                        cometTailLengthLabel.setVisible(false);


                        nameField.setText(String.valueOf(obslist.get(listView.getSelectionModel().getSelectedIndex()).getName()));
                        sizeField.setText(String.valueOf(obslist.get(listView.getSelectionModel().getSelectedIndex()).getSize()));
                        massField.setText(String.valueOf(obslist.get(listView.getSelectionModel().getSelectedIndex()).getMass()));
                        formField.setText(String.valueOf(obslist.get(listView.getSelectionModel().getSelectedIndex()).getForm()));

                        System.out.println(String.valueOf((obslist.get(listView.getSelectionModel().getSelectedIndex()).getClass())).substring(23));
                        switch (String.valueOf((obslist.get(listView.getSelectionModel().getSelectedIndex()).getClass())).substring(23)) {
//done
                            case "Planet":
                                Planet p = (Planet) obslist.get(listView.getSelectionModel().getSelectedIndex());
                                rotationSpeedField.setVisible(true);
                                rotSpeedLabel.setVisible(true);
                                rotationSpeedField.setText(String.valueOf(p.getRotationSpeed()));
                                structureField.setVisible(true);
                                structureLable.setVisible(true);

                                System.out.println("structure   " + p.getStructure());

                                structureField.setText(String.valueOf(p.getStructure()));
                                colorField.setVisible(true);
                                colorLabel.setVisible(true);
                                colorField.setText(String.valueOf(p.getColor()));
                                break;

                            case "Star":
                                Star s = (Star) obslist.get(listView.getSelectionModel().getSelectedIndex());
                                rotationSpeedField.setVisible(true);
                                rotSpeedLabel.setVisible(true);
                                rotationSpeedField.setText(String.valueOf(s.getRotationSpeed()));
                                structureField.setVisible(true);
                                structureLable.setVisible(true);
                                structureField.setText(String.valueOf(s.getStructure()));
                                colorField.setVisible(true);
                                colorLabel.setVisible(true);
                                colorField.setText(String.valueOf(s.getColor()));
                                xrayField.setVisible(true);
                                xrayLabel.setVisible(true);
                                xrayField.setText(String.valueOf(s.getXrayCoefficient()));
                                luminosityField.setVisible(true);
                                luminosityLabel.setVisible(true);
                                luminosityField.setText(String.valueOf(s.getLuminosity()));
                                break;

                            case "Galaxy":
                                Galaxy g = (Galaxy) obslist.get(listView.getSelectionModel().getSelectedIndex());
                                galaxyTypeLabel.setVisible(true);
                                galaxyTypeField.setVisible(true);
                                galaxyTypeField.setText(String.valueOf(g.getGalaxyType()));
                                elementsLabel.setVisible(true);
                                elementsField.setVisible(true);
                                elementsField.setText(String.valueOf(g.getElements()));
                                blackHoleLabel.setVisible(true);
                                blackHoleBox.setVisible(true);
                                BlackHole bhGalaxy = g.getCenterBlackHole();
                                System.out.println(bhGalaxy);
                                for (int i = 0; i < blackHoleBox.getItems().size(); i++) {
                                    System.out.println(blackHoleBox.getItems().get(i).toString());
                                    if (blackHoleBox.getItems().get(i).toString().equals(bhGalaxy.toString())) {
                                        System.out.println(true);
                                        blackHoleBox.setValue(blackHoleBox.getItems().get(i));
                                        break;
                                    }
                                }
                                break;

                            case "BlackHole":
                                BlackHole b = (BlackHole) obslist.get(listView.getSelectionModel().getSelectedIndex());
                                rotationSpeedField.setVisible(true);
                                rotSpeedLabel.setVisible(true);
                                rotationSpeedField.setText(String.valueOf(b.getRotationSpeed()));
                                structureField.setVisible(true);
                                structureLable.setVisible(true);
                                structureField.setText(String.valueOf(b.getStructure()));
                                colorField.setVisible(true);
                                colorLabel.setVisible(true);
                                colorField.setText(String.valueOf(b.getColor()));
                                xrayField.setVisible(true);
                                xrayLabel.setVisible(true);
                                xrayField.setText(String.valueOf(b.getXrayCoefficient()));
                                luminosityField.setVisible(true);
                                luminosityLabel.setVisible(true);
                                luminosityField.setText(String.valueOf(b.getLuminosity()));
                                evaporationField.setVisible(true);
                                evaporationField.setText(String.valueOf(b.getXrayCoefficient()));
                                evaporationLabel.setVisible(true);
                                eventHorizontField.setVisible(true);
                                eventHorizontField.setText(String.valueOf(b.getXrayCoefficient()));
                                eventHorizontLabel.setVisible(true);
                                break;

                            case "Asteroids":
                                Asteroids a = (Asteroids) obslist.get(listView.getSelectionModel().getSelectedIndex());
                                astQuantityLabel.setVisible(true);
                                astQuantityField.setVisible(true);
                                astQuantityField.setText(String.valueOf(a.getQuantity()));
                                break;

                            case "Comet":
                                Comet c = (Comet) obslist.get(listView.getSelectionModel().getSelectedIndex());
                                cometTailLengthField.setVisible(true);
                                cometTailLengthLabel.setVisible(true);
                                cometTailLengthField.setText(String.valueOf(c.getTailLength()));
                                break;

                        }
                    }
                }
        );
    }

    void setSerializeMethodBox() {
        serializeMethodBox.getItems().clear();
        serializeMethodBox.getItems().addAll("Binary", "Text", "XML","JSON");
    }

    @FXML
    void serialize(ActionEvent event) {
        String fileName = "";
        switch (serializeMethodBox.getValue()) {
            case "Binary":
                fileName = "output.bin";
                serialization = BinarySerialization.getInstance();
                break;

            case "Text":
                fileName = "output.txt";
                serialization = TextSerialization.getInstance();
                break;
            case "XML":
                fileName = "output.xml";
                serialization = XMLSerialization.getInstance();
                break;
            case "JSON":
                fileName = "output.json";
                serialization = JSONAdapter.getInstance();
                break;
        }
        serialization.serialize(new File(fileName), serializeList);
    }

    @FXML
    void deserialize(ActionEvent event) {
        String fileName = "";
        switch (serializeMethodBox.getValue()) {
            case "Binary":
                fileName = "output.bin";
                serialization = BinarySerialization.getInstance();
                break;

            case "Text":
                fileName = "output.txt";
                serialization = TextSerialization.getInstance();
                break;
            case"XML":
                fileName = "output.xml";
                serialization = XMLSerialization.getInstance();
                break;
            case "JSON":
                fileName = "output.json";
                serialization = JSONAdapter.getInstance();
                break;

        }
        obslist.addAll(serialization.deserialize(new File(fileName)));
        System.out.println(Arrays.toString(obslist.toArray()));
         initialize();
    }
}
