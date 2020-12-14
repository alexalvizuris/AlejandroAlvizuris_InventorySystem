package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Part;

import java.io.IOException;
import java.util.Optional;

public class addPartController {

    @FXML
    private TextField addPartMin;

    @FXML
    private TextField addPartId;

    @FXML
    private TextField addPartName;

    @FXML
    private TextField addPartInv;

    @FXML
    private TextField addPartPrice;

    @FXML
    private TextField addPartMax;

    @FXML
    private TextField flexTextField;

    @FXML
    private Label flexLabel;

    @FXML
    private RadioButton inHouseAdd;

    @FXML
    private RadioButton outsourcedAdd;

    @FXML
    private Button addPartSave;

    @FXML
    private Button addPartCancel;


// Selecting this will add the Part to the Part Table, and switch screens to the Main Form screen
    @FXML
    public void addPartSaveSelected(ActionEvent event) throws IOException {

// This takes in user input and associates the input with the Part being created
        if (inHouseAdd.isSelected()) {
            flexLabel.setText("Machine ID");
            int id = Inventory.getAllParts().size() + 1;
            String name = addPartName.getText();
            double price = Double.parseDouble(addPartPrice.getText());
            int stock = Integer.parseInt(addPartInv.getText());
            int min = Integer.parseInt(addPartMin.getText());
            int max = Integer.parseInt(addPartMax.getText());
            int machineID = Integer.parseInt(flexTextField.getText());

            Part inHouse = new InHouse(id, name, price, stock, min, max, machineID);
            Inventory.addPart(inHouse);
        }


        if (outsourcedAdd.isSelected()) {

            flexLabel.setText("Company Name");

            int id = Inventory.getAllParts().size() + 1;
            String name = addPartName.getText();
            double price = Double.parseDouble(addPartPrice.getText());
            int stock = Integer.parseInt(addPartInv.getText());
            int min = Integer.parseInt(addPartMin.getText());
            int max = Integer.parseInt(addPartMax.getText());
            String companyName = flexTextField.getText();

            Part outsourced = new Outsourced(id, name, price, stock, min, max, companyName);
            Inventory.addPart(outsourced);

        }


        Parent addPartSaveParent = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        Scene addPartSaveScene = new Scene(addPartSaveParent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(addPartSaveScene);
        stage.show();

    }


// Selecting this will erase the input fields, and switch screens to the Main Form screen
    public void addPartCancelSelected(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "No data will be saved. Continue?");
        Optional<ButtonType> selectedButton = alert.showAndWait();

        if (selectedButton.isPresent() && selectedButton.get() == ButtonType.OK) {

            Parent addPartCancelParent = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
            Scene addPartCancelScene = new Scene(addPartCancelParent);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(addPartCancelScene);
            stage.show();
        }
    }


// Selecting this will ensure the part is associated with the InHouse parts
    public void addPartInHouseToggled(ActionEvent event) {
        if (inHouseAdd.isSelected()) {
            flexLabel.setText("Machine ID");
        }
    }


// Selecting this will ensure the part is associated with the Outsourced parts
    public void addPartOutsourceToggled(ActionEvent event) {
        if (outsourcedAdd.isSelected()) {
            flexLabel.setText("Company Name");
        }
    }


}
