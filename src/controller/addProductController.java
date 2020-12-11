package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

import java.io.IOException;

public class addProductController {

    @FXML
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

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

        int id = Inventory.getAllProducts().size() + 1;
        String name = addProductName.getText();
        double price = Double.parseDouble(addProductPrice.getText());
        int stock = Integer.parseInt(addProductInv.getText());
        int max = Integer.parseInt(addProductMax.getText());
        int min = Integer.parseInt(addProductMin.getText());

        Product product = new Product(id, name, price, stock, max, min);
        Inventory.addProduct(product);



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

    public void searchParts() {
        String searching = addProductSearch.getText();
        ObservableList<Part> partsCopied = Inventory.lookupPart(searching);

        if (partsCopied.size() == 0) {
            int idNum = Integer.parseInt(searching);
            Part part = Inventory.lookupPart(idNum);
            if (part != null) {
                partsCopied.add(part);
            }
        }

        addProduct_PartTable.setItems(partsCopied);
    }

    public void addPartToAssociatedParts(ActionEvent event) throws IOException {

        associatedParts.add(addProduct_PartTable.getSelectionModel().getSelectedItem());

    }

    public void removeAssociatedPart() {
        ObservableList<Part> allParts, selectedPart;
        allParts = associatedPartTable.getItems();
        selectedPart = associatedPartTable.getSelectionModel().getSelectedItems();
        for (Part part: selectedPart) {
            allParts.remove(part);
        }
    }

    public void initialize() {
        addProduct_PartTable.setItems(Inventory.getAllParts());

        addProduct_PartTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        addProduct_IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        addProduct_NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        addProduct_InvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        addProduct_PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartTable.setItems(associatedParts);

        associatedPartTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        associatedPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        associatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        associatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        associatedPartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));






    }

}

