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

public class modifyPartController {

    @FXML
    private TextField modifyPartId;

    @FXML
    private TextField modifyPartName;

    @FXML
    private TextField modifyPartInv;

    @FXML
    private TextField modifyPartPrice;

    @FXML
    private TextField modifyPartMax;

    @FXML
    private TextField flexTextField2;

    @FXML
    private TextField modifyPartMin;

    @FXML
    private Label flexLabel2;

    @FXML
    private RadioButton inHouseModify;

    @FXML
    private RadioButton outsourcedModify;

    @FXML
    private Button modifyPartSave;

    @FXML
    private Button modifyPartCancel;


// These action events adjust the flex label to correlate to either InHouse or Outsourced
    public void inHouseModifyToggled(ActionEvent event) {
        if (inHouseModify.isSelected()) {
            flexLabel2.setText("Machine ID");
        }
    }

    public void outsourcedModifyToggled(ActionEvent event) {
        if (outsourcedModify.isSelected()) {
            flexLabel2.setText("Company Name");
        }
    }


// Selecting this will update the attributes to the selected Part, and switch screens to the Main Form screen
    public void modifyPartSaveSelected(ActionEvent event) throws IOException {

        if (inHouseModify.isSelected()) {
            flexLabel2.setText("Machine ID");

            int id = Integer.parseInt(modifyPartId.getText());
            String name = modifyPartName.getText();
            double price = Double.parseDouble(modifyPartPrice.getText());
            int stock = Integer.parseInt(modifyPartInv.getText());
            int min = Integer.parseInt(modifyPartMin.getText());
            int max = Integer.parseInt(modifyPartMax.getText());
            int machineID = Integer.parseInt(flexTextField2.getText());

            Part inHouse = new InHouse(id, name, price, stock, min, max, machineID);

            Inventory.updatePart(id - 1, inHouse);


        }


        if (outsourcedModify.isSelected()) {

            flexLabel2.setText("Company Name");

            int id = Integer.parseInt(modifyPartId.getText());
            String name = modifyPartName.getText();
            double price = Double.parseDouble(modifyPartPrice.getText());
            int stock = Integer.parseInt(modifyPartInv.getText());
            int min = Integer.parseInt(modifyPartMin.getText());
            int max = Integer.parseInt(modifyPartMax.getText());
            String companyName = flexTextField2.getText();

            Part outsourced = new Outsourced(id, name, price, stock, min, max, companyName);

            Inventory.updatePart(id - 1, outsourced);


        }


        Parent modifyPartSaveParent = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        Scene modifyPartSaveScene = new Scene(modifyPartSaveParent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(modifyPartSaveScene);
        stage.show();
    }


// Selecting this will erase the input fields, and switch screens to the Main Form screen
    public void  modifyPartCancelSelected(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "No data will be saved. Continue?");
        Optional<ButtonType> selectedButton = alert.showAndWait();

        if (selectedButton.isPresent() && selectedButton.get() == ButtonType.OK) {

            Parent modifyPartCancelParent = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
            Scene modifyPartCancelScene = new Scene(modifyPartCancelParent);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(modifyPartCancelScene);
            stage.show();
        }
    }

// This initializes the data for the Part being modified
    public void initPartData(Part part) {

            modifyPartId.setText(Integer.toString(part.getId()));
            modifyPartName.setText(part.getName());
            modifyPartPrice.setText(Double.toString(part.getPrice()));
            modifyPartInv.setText(Integer.toString(part.getStock()));
            modifyPartMax.setText(Integer.toString(part.getMax()));
            modifyPartMin.setText(Integer.toString(part.getMin()));



        if (part instanceof InHouse) {
            flexLabel2.setText("Machine ID");
            flexTextField2.setText(Integer.toString(((InHouse) part).getMachineId()));
            inHouseModify.setSelected(true);
        }

        if (part instanceof Outsourced) {
            flexLabel2.setText("Company Name");
            flexTextField2.setText(((Outsourced) part).getCompanyName());
            outsourcedModify.setSelected(true);
        }
    }



}
