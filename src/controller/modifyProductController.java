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

public class modifyProductController {

    @FXML
    private ObservableList<Part> associatedPartList = FXCollections.observableArrayList();

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
    private TableView<Part> modifyProduct_PartTable;

    @FXML
    private TableColumn<Part, Integer> modifyProduct_PartId;

    @FXML
    private TableColumn<Part, String> ModifyProduct_PartName;

    @FXML
    private TableColumn<Part, Integer> modifyProduct_PartInv;

    @FXML
    private TableColumn<Part, Double> modifyProduct_PartPrice;

    @FXML
    private TableView<Part> modify_AssociatedPartTable;

    @FXML
    private TableColumn<Part, Integer> modify_AssociatedPartId;

    @FXML
    private TableColumn<Part, String> modify_AssociatedPartName;

    @FXML
    private TableColumn<Part, Integer> modify_AssociatedPartInv;

    @FXML
    private TableColumn<Part, Double> modify_AssociatedPartPrice;

    @FXML
    private Button modifyProductAddPart;

    @FXML
    private Button modify_RemoveAssociatedPart;

    @FXML
    private Button modifyProductSave;

    @FXML
    private Button modifyProductCancel;

    public void modifyProductSaveSelected(ActionEvent event) throws IOException {

        int id = Inventory.getAllProducts().size() + 2;
        String name = modifyProductName.getText();
        double price = Double.parseDouble(modifyProductPrice.getText());
        int stock = Integer.parseInt(modifyProductInv.getText());
        int max = Integer.parseInt(modifyProductMax.getText());
        int min = Integer.parseInt(modifyProductMin.getText());

        Product product = new Product(id, name, price, stock, max, min);
        Inventory.updateProduct(id - 1, product);

        Parent modifyProductSaveParent = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        Scene modifyProductSaveScene = new Scene(modifyProductSaveParent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(modifyProductSaveScene);
        stage.show();
    }

    public void modifyProductCancelSelected(ActionEvent event) throws IOException {
        Parent modifyProductCancelParent = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        Scene modifyProductCancelScene = new Scene(modifyProductCancelParent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(modifyProductCancelScene);
        stage.show();
    }

    public void searchParts() {
        String searching = modifyProductSearch.getText();
        ObservableList<Part> partsCopied = Inventory.lookupPart(searching);

        if (partsCopied.size() == 0) {
            int idNum = Integer.parseInt(searching);
            Part part = Inventory.lookupPart(idNum);
            if (part != null) {
                partsCopied.add(part);
            }
        }

        modifyProduct_PartTable.setItems(partsCopied);
    }

    public void addPartToAssociatedParts() {

        associatedPartList.add(modifyProduct_PartTable.getSelectionModel().getSelectedItem());

    }

    public void removeAssociatedPart() {
        ObservableList<Part> allParts, selectedPart;
        allParts = modify_AssociatedPartTable.getItems();
        selectedPart = modify_AssociatedPartTable.getSelectionModel().getSelectedItems();
        for (Part part: selectedPart) {
            allParts.remove(part);
        }
    }

    public void initProductData(Product product) {

        modifyProductId.setText(Integer.toString(product.getId()));
        modifyProductName.setText(product.getName());
        modifyProductPrice.setText(Double.toString(product.getPrice()));
        modifyProductInv.setText(Integer.toString(product.getStock()));
        modifyProductMax.setText(Integer.toString(product.getMax()));
        modifyProductMin.setText(Integer.toString(product.getMin()));

    }

    public void initialize() {
        modifyProduct_PartTable.setItems(Inventory.getAllParts());

        modifyProduct_PartTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        modifyProduct_PartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ModifyProduct_PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProduct_PartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProduct_PartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        modify_AssociatedPartTable.setItems(associatedPartList);


    }

}
