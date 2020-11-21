package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class modifyProductController {

    @FXML
    private TextField modifyProductName;

    @FXML
    private TextField modifyProductId;

    @FXML
    private TextField modifyProductInv;

    @FXML
    private TextField modifyProductPrice;

    @FXML
    private TextField modifyProductMax;

    @FXML
    private TextField modifyProductMin;

    @FXML
    private TextField modifyProductSearch;

    @FXML
    private TableView<?> modifyProduct_PartTable;

    @FXML
    private TableColumn<?, ?> modifyProduct_PartId;

    @FXML
    private TableColumn<?, ?> ModifyProduct_PartName;

    @FXML
    private TableColumn<?, ?> modifyProduct_PartInv;

    @FXML
    private TableColumn<?, ?> modifyProduct_PartPrice;

    @FXML
    private TableView<?> modify_AssociatedPartTable;

    @FXML
    private TableColumn<?, ?> modify_AssociatedPartId;

    @FXML
    private TableColumn<?, ?> modify_AssociatedPartName;

    @FXML
    private TableColumn<?, ?> modify_AssociatedPartInv;

    @FXML
    private TableColumn<?, ?> modify_AssociatedPartPrice;

    @FXML
    private Button modifyProductAddPart;

    @FXML
    private Button modify_RemoveAssociatedPart;

    @FXML
    private Button modifyProductSave;

    @FXML
    private Button modifyProductCancel;

}
