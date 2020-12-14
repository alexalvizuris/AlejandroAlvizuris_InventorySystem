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

/***
 * Controller for the Add Part screen
 */
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


    /***
     * Selecting this will add the Part to the Inventory, and switch screens to the Main Form screen
     * @param event variable created to initiate the save method in the Add Part screen
     * @throws IOException when criteria has not been met to fulfil all requirements to create a new Part
     */
    @FXML
    public void addPartSaveSelected(ActionEvent event) throws IOException {

    try {
        if (inHouseAdd.isSelected()) {
            flexLabel.setText("Machine ID");
            int id = Inventory.getAllParts().size() + 1;
            String name = addPartName.getText();
            double price = Double.parseDouble(addPartPrice.getText());
            int stock = Integer.parseInt(addPartInv.getText());
            int min = Integer.parseInt(addPartMin.getText());
            int max = Integer.parseInt(addPartMax.getText());
            int machineID = Integer.parseInt(flexTextField.getText());

            if (name.isEmpty() || name == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("An Error has occurred");
                alert.setContentText("Please use a valid input to name the part.");
                alert.showAndWait();
                return;
            }

            if (stock > max || stock < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("An Error has occurred");
                alert.setContentText("The Inventory does not meet the amount of parts required. Please try again.");
                alert.showAndWait();
                return;
            }

            if (max < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("An Error has occurred");
                alert.setContentText("The maximum amount of parts does not meet the minimum amount allowed. Please try again.");
                alert.showAndWait();
                return;
            }


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

            if (name.isEmpty() || name == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("An Error has occurred");
                alert.setContentText("Please use a valid input to name the part.");
                alert.showAndWait();
                return;
            }

            if (stock > max || stock < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("An Error has occurred");
                alert.setContentText("The Inventory does not meet the amount of parts required. Please try again.");
                alert.showAndWait();
                return;
            }

            if (max < min) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("An Error has occurred");
                alert.setContentText("The maximum amount of parts does not meet the minimum amount allowed. Please try again.");
                return;
            }

            Part outsourced = new Outsourced(id, name, price, stock, min, max, companyName);
            Inventory.addPart(outsourced);

        }


        Parent addPartSaveParent = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        Scene addPartSaveScene = new Scene(addPartSaveParent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(addPartSaveScene);
        stage.show();
    } catch (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("An Error has occurred");
        alert.setContentText("Please review your input and try again.");
        alert.showAndWait();
    }

    }



    /***
     * Selecting this will erase the input fields, and switch screens to the Main Form screen
     * @param event variable created to opt out of creating a new Part, and switch screens back to the Main Form
     * @throws IOException
     */
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


    /***
     * Selecting this will ensure the part is associated with the InHouse parts
     * @param event variable created to switch the flex label to 'Machine ID'
     */
    public void addPartInHouseToggled(ActionEvent event) {
        if (inHouseAdd.isSelected()) {
            flexLabel.setText("Machine ID");
        }
    }


    /***
     * Selecting this will ensure the part is associated with the Outsourced parts
     * @param event variable created to switch the flex label to 'Company Name'
     */
    public void addPartOutsourceToggled(ActionEvent event) {
        if (outsourcedAdd.isSelected()) {
            flexLabel.setText("Company Name");
        }
    }


}
