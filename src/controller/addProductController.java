package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Part;
import model.Product;

import java.io.IOException;

public class addProductController {

    @FXML
    private TextField addProductName;

    @FXML
    private TextField addProductId;

    @FXML
    private TextField addProductInv;

    @FXML
    private TextField addProductPrice;

    @FXML
    private TextField addProductMax;

    @FXML
    private TextField addProductMin;

    @FXML
    private TextField addProductSearch;

    @FXML
    private TableView<?> addProduct_PartTable;

    @FXML
    private TableColumn<?, ?> addProduct_IdColumn;

    @FXML
    private TableColumn<?, ?> addProduct_NameColumn;

    @FXML
    private TableColumn<?, ?> addProduct_InvColumn;

    @FXML
    private TableColumn<?, ?> addProduct_PriceColumn;

    @FXML
    private TableView<?> associatedPartTable;

    @FXML
    private TableColumn<?, ?> associatedPartId;

    @FXML
    private TableColumn<?, ?> associatedPartName;

    @FXML
    private TableColumn<?, ?> associatedPartInv;

    @FXML
    private TableColumn<?, ?> associatedPartPrice;

    @FXML
    private Button addPartToProduct;

    @FXML
    private Button removeAssociatedPart;

    @FXML
    private Button addProductSave;

    @FXML
    private Button addProductCancel;

    public void addProductSaveSelected(ActionEvent event) throws IOException {
        Parent addProductSaveParent = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        Scene addProductSaveScene = new Scene(addProductSaveParent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(addProductSaveScene);
        stage.show();
    }

    public void addProductCancelSelected(ActionEvent event) throws IOException {
        Parent addProductCancelParent = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        Scene addProductCancelScene = new Scene(addProductCancelParent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(addProductCancelScene);
        stage.show();
    }

}

