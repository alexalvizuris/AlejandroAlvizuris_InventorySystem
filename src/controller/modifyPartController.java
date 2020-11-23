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

    public void modifyPartSaveSelected(ActionEvent event) throws IOException {
        Parent modifyPartSaveParent = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        Scene modifyPartSaveScene = new Scene(modifyPartSaveParent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(modifyPartSaveScene);
        stage.show();
    }

    public void  modifyPartCancelSelected(ActionEvent event) throws IOException {
        Parent modifyPartCancelParent = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        Scene modifyPartCancelScene = new Scene(modifyPartCancelParent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(modifyPartCancelScene);
        stage.show();
    }



}
