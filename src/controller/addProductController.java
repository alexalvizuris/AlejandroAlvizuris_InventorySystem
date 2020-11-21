package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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

}

