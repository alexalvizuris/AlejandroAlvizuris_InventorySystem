package controller;

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
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class mainFormController {

    @FXML
    private TableView<Part> partInventoryTable;

    @FXML
    private TableColumn<Part,Integer> partIdColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Integer> partInvColumn;

    @FXML
    private TableColumn<Part, Integer> partPriceColumn;

    @FXML
    private TextField searchPartField;

    @FXML
    private Button addPartButton;

    @FXML
    private Button modifyPartButton;

    @FXML
    private Button deletePartButton;

    @FXML
    private TableView<Product> productInventoryTable;

    @FXML
    private TableColumn<Product, Integer> productIdColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Integer> productInvColumn;

    @FXML
    private TableColumn<Product, Integer> productPriceColumn;

    @FXML
    private TextField searchProductField;

    @FXML
    private Button addProductButton;

    @FXML
    private Button modifyProductButton;

    @FXML
    private Button deleteProductButton;

    @FXML
    private Button mainExitButton;


    public void addPartButtonSelected(ActionEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("/view/addPart.fxml"));
        Scene addPartScene = new Scene(addPartParent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(addPartScene);
        stage.show();
    }

    public void modifyPartButtonSelected(ActionEvent event) throws IOException {

    try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/modifyPart.fxml"));
        Parent modifyPartParent = loader.load();

        Scene modifyPartScene = new Scene(modifyPartParent);

        modifyPartController control = loader.getController();
        control.initPartData(partInventoryTable.getSelectionModel().getSelectedItem());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(modifyPartScene);
        stage.show();
    } catch (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("An Error has occurred");
        alert.setContentText("Please select a part to modify.");
        alert.showAndWait();
    }
    }

    public void addProductButtonSelected(ActionEvent event) throws IOException {
        Parent addProductParent = FXMLLoader.load(getClass().getResource("/view/addProduct.fxml"));
        Scene addProductScene = new Scene(addProductParent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(addProductScene);
        stage.show();
    }

    public void modifyProductButtonSelected(ActionEvent event) throws IOException {

    try {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/modifyProduct.fxml"));
        Parent modifyProductParent = loader.load();

        Scene modifyProductScene = new Scene(modifyProductParent);

        modifyProductController control = loader.getController();
        control.initProductData(productInventoryTable.getSelectionModel().getSelectedItem());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(modifyProductScene);
        stage.show();
    } catch (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("An Error has occurred");
        alert.setContentText("Please select a product to modify.");
        alert.showAndWait();
    }
    }

    public void searchParts(ActionEvent event) {


    try {
        String searching = searchPartField.getText();
        ObservableList<Part> partsCopied = Inventory.lookupPart(searching);

        if (partsCopied.size() == 0) {
            int idNum = Integer.parseInt(searching);
            Part part = Inventory.lookupPart(idNum);
            if (part != null) {
                partsCopied.add(part);
            }
        }

        partInventoryTable.setItems(partsCopied);
    } catch (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("An Error has occurred");
        alert.setContentText("Please search using correct values.");
        alert.showAndWait();
    }

    }

    public void searchProducts(ActionEvent event) {

    try {
        String searching = searchProductField.getText();
        ObservableList<Product> productsCopied = Inventory.lookupProduct(searching);

        if (productsCopied.size() == 0) {
            int idNum = Integer.parseInt(searching);
            Product product = Inventory.lookupProduct(idNum);
            if (product != null) {
                productsCopied.add(product);
            }
        }

        productInventoryTable.setItems(productsCopied);
    } catch (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("An Error has occurred");
        alert.setContentText("Please search using correct values.");
        alert.showAndWait();
    }
    }

    public void deletePartButtonSelected() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You are deleting an item from Inventory. Continue?");
        Optional<ButtonType> selectedButton = alert.showAndWait();

        if (selectedButton.isPresent() && selectedButton.get() == ButtonType.OK) {

            ObservableList<Part> allParts, selectedPart;
            allParts = partInventoryTable.getItems();
            selectedPart = partInventoryTable.getSelectionModel().getSelectedItems();
            for (Part part : selectedPart) {
                allParts.remove(part);
            }
        }
    }

    public void deleteProductButtonSelected() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You are deleting an item from Inventory. Continue?");
        Optional<ButtonType> selectedButton = alert.showAndWait();

        if (selectedButton.isPresent() && selectedButton.get() == ButtonType.OK) {

            ObservableList<Product> allProducts, selectedProduct;
            allProducts = productInventoryTable.getItems();
            selectedProduct = productInventoryTable.getSelectionModel().getSelectedItems();
            for (Product product : selectedProduct) {
                allProducts.remove(product);
            }
        }
    }




    public void initialize() {
        partInventoryTable.setItems(Inventory.getAllParts());

        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));


        productInventoryTable.setItems(Inventory.getAllProducts());

        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productInvColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        partInventoryTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        productInventoryTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);




    }

    public void exitButtonSelected(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You are now EXITING the program. Continue?");
        Optional<ButtonType> selectedButton = alert.showAndWait();

        if (selectedButton.isPresent() && selectedButton.get() == ButtonType.OK) {

            Stage stage = (Stage) mainExitButton.getScene().getWindow();
            stage.close();
        }
    }



}

