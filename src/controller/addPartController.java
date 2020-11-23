package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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

    public void addPartSaveSelected(ActionEvent event) throws IOException {
        Parent addPartSaveParent = FXMLLoader.load(getClass().getResource("/view/mainForm/"));
        Scene addPartSaveScene = new Scene(addPartSaveParent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(addPartSaveScene);
        stage.show();
    }

    public void addPartCancelSelected(ActionEvent event) throws IOException {
        Parent addPartCancelParent = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        Scene addPartCancelScene = new Scene(addPartCancelParent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(addPartCancelScene);
        stage.show();
    }

    public void addPartInHouseToggled(ActionEvent event) {
        if (inHouseAdd.isSelected()) {
            flexLabel.setText("Machine ID");
        }
    }

    public void addPartOutsourceToggled(ActionEvent event) {
        if (outsourcedAdd.isSelected()) {
            flexLabel.setText("Company Name");
        }
    }


}
