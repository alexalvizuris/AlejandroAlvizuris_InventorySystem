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
    private TableView<Part> addProduct_PartTable;

    @FXML
    private TableColumn<Part, Integer> addProduct_IdColumn;

    @FXML
    private TableColumn<Part, String> addProduct_NameColumn;

    @FXML
    private TableColumn<Part, Integer> addProduct_InvColumn;

    @FXML
    private TableColumn<Part, Double> addProduct_PriceColumn;

    @FXML
    private TableView<Part> associatedPartTable;

    @FXML
    private TableColumn<Part, Integer> associatedPartId;

    @FXML
    private TableColumn<Part, String> associatedPartName;

    @FXML
    private TableColumn<Part, Integer> associatedPartInv;

    @FXML
    private TableColumn<Part, Double> associatedPartPrice;

    @FXML
    private Button addPartToProduct;

    @FXML
    private Button removeAssociatedPart;

    @FXML
    private Button addProductSave;

    @FXML
    private Button addProductCancel;

    @FXML
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

