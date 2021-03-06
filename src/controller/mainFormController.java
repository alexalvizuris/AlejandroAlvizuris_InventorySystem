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

/***
 * Controller for the Main Form
 */
public class mainFormController {

    @FXML
    private TableView<Part> partInventoryTable;

    @FXML
    private TableColumn<Part, Integer> partIdColumn;

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


    /***
     * Selecting this will switch screens to the Add Part screen
     * @param event variable created to initiate creating a new Part, switches over to Add Part screen
     * @throws IOException
     */
    public void addPartButtonSelected(ActionEvent event) throws IOException {
        Parent addPartParent = FXMLLoader.load(getClass().getResource("/view/addPart.fxml"));
        Scene addPartScene = new Scene(addPartParent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(addPartScene);
        stage.show();
    }


    /***
     * Selecting this will switch screens to the Modify Part screen and populate the selected Part's attributes
     * @param event variable created to initiate switching over to the Modify Part screen as well as populating the screen
     * @throws IOException when a Part has not been selected prior to selecting the Modify button
     */
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
            alert.setContentText("Please select a Part to modify.");
            alert.showAndWait();

        }
    }


    /***
     * Selecting this will switch screens to the Add Products screen
     * @param event variable created to initiate creating a new Product, switches over to Add Product screen
     * @throws IOException
     */
    public void addProductButtonSelected(ActionEvent event) throws IOException {
        Parent addProductParent = FXMLLoader.load(getClass().getResource("/view/addProduct.fxml"));
        Scene addProductScene = new Scene(addProductParent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(addProductScene);
        stage.show();
    }


    /***
     * Selecting this will switch screens to the Modify Products screen and populate the selected Product's attributes
     * @param event variable created to switch to Modify Product screen as well as populating the screen
     * @throws IOException when a Product has not been selected prior to selecting the Modify button
     */
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
            alert.setContentText("Please select a Product to modify.");
            alert.showAndWait();
        }
    }


    /***
     * Typing alphabetical or numerical text here will filter the Parts Table
     * @param event variable created to filter the Parts Table by what ever is typed into the text field
     * @throws IOException when characters are input which are not numbers or letters from the alphabet
     */
    public void searchParts(ActionEvent event) throws IOException {
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

    /***
     * Typing alphabetical or numerical text here will filter the Products Table
     * @param event created to filter the Products Table by what ever is typed into the text field
     * @throws IOException when characters are input which are not numbers or letters from the alphabet
     */

    public void searchProducts(ActionEvent event) throws IOException {
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

    /***
     * Selecting this will delete a part from inventory
     * @param event called when deleting a Part from Inventory
     * @throws IOException when no Parts are selected before selecting Delete Button
     */
    @FXML
    public void deletePartButtonSelected(ActionEvent event) throws IOException {

        if (partInventoryTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An Error has occurred");
            alert.setContentText("Please select a Part to delete from the Inventory");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You are deleting an item from Inventory. Continue?");
        Optional<ButtonType> selectedButton = alert.showAndWait();

        if (selectedButton.isPresent() && selectedButton.get() == ButtonType.OK) {
            Inventory.deletePart(partInventoryTable.getSelectionModel().getSelectedItem());
        }
    }


    /***
     * Selecting this will delete a product from Inventory
     * @param event called when deleting a Product from Inventory
     * @throws IOException when no Products are selected before pressing Delete Button
     */
    @FXML
    public void deleteProductButtonSelected(ActionEvent event) throws IOException {

        if (productInventoryTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An Error has occurred");
            alert.setContentText("Please select a Product to delete from the Inventory");
            alert.showAndWait();
            return;
        }

        if (productInventoryTable.getSelectionModel().getSelectedItem().getAllAssociatedParts().size() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An Error has occurred");
            alert.setContentText("Products with Parts associated with them cannot be deleted.");
            alert.showAndWait();
            return;
        }

        if (productInventoryTable.getSelectionModel().getSelectedItem().getAllAssociatedParts().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You are deleting an item from Inventory. Continue?");
            Optional<ButtonType> selectedButton = alert.showAndWait();

            if (selectedButton.isPresent() && selectedButton.get() == ButtonType.OK) {

                Inventory.deleteProduct(productInventoryTable.getSelectionModel().getSelectedItem());

            }
        }

    }


    /***
     * This initializes the items in the Parts Table and Products Table, as well as the associated columns
     */
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


    /***
     * Selecting this will exit the program
     * @param event variable created to terminate the program
     */
    public void exitButtonSelected(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "You are now EXITING the program. Continue?");
        Optional<ButtonType> selectedButton = alert.showAndWait();

        if (selectedButton.isPresent() && selectedButton.get() == ButtonType.OK) {

            Stage stage = (Stage) mainExitButton.getScene().getWindow();
            stage.close();
        }
    }


}

