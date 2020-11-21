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
        Parent modifyPartParent = FXMLLoader.load(getClass().getResource("/view/modifyPart.fxml"));
        Scene modifyPartScene = new Scene(modifyPartParent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(modifyPartScene);
        stage.show();
    }

    public void addProductButtonSelected(ActionEvent event) throws IOException {
        Parent addProductParent = FXMLLoader.load(getClass().getResource("/view/addProduct.fxml"));
        Scene addProductScene = new Scene(addProductParent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(addProductScene);
        stage.show();
    }

    public void modifyProductButtonSelected(ActionEvent event) throws IOException {
        Parent modifyProductParent = FXMLLoader.load(getClass().getResource("/view/modifyProduct.fxml"));
        Scene modifyProductScene = new Scene(modifyProductParent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(modifyProductScene);
        stage.show();
    }



}

