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
import java.util.Optional;

/***
 * Controller for the Add Product screen
 */
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

    /***
     * Selecting this will add the Product to the Inventory, and switch back to the Main Form screen
     * @param event variable created to initiate the save method in the Add Product screen
     * @throws IOException when criteria has not been met to fulfil all requirements to create a new Product
     */
    @FXML
    public void addProductSaveSelected(ActionEvent event) throws IOException {

    try {
        int id = Inventory.getAllProducts().size() + 1;
        String name = addProductName.getText();
        double price = Double.parseDouble(addProductPrice.getText());
        int stock = Integer.parseInt(addProductInv.getText());
        int max = Integer.parseInt(addProductMax.getText());
        int min = Integer.parseInt(addProductMin.getText());

        if (name.isEmpty() || name == null || name.equals(" ")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An Error has occurred");
            alert.setContentText("Please use a valid input to name the product.");
            alert.showAndWait();
            return;
        }

        if (stock > max || stock < min) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An Error has occurred");
            alert.setContentText("The Inventory does not meet the amount of products required. Please try again.");
            alert.showAndWait();
            return;
        }

        if (max < min) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An Error has occurred");
            alert.setContentText("The maximum amount of products does not meet the minimum amount allowed. Please try again.");
            alert.showAndWait();
            return;
        }

        if (max < 1 || min < 1 || price <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An Error has occurred");
            alert.setContentText("Your input must be greater than zero.");
            alert.showAndWait();
            return;
        }



        Product product = new Product(id, name, price, stock, max, min);

        double count = 0;

        if (associatedParts.size() > 0) {
            for (int i = 0; i < associatedParts.size(); i++) {
                product.addAssociatedPart(associatedParts.get(i));
                count += associatedParts.get(i).getPrice();
            }
        }
        if (price < count) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An Error has occurred");
            alert.setContentText("The price of the parts associated with this product exceed the price of the product. Please adjust this product's price.");
            alert.showAndWait();
            return;
        }


        Inventory.addProduct(product);


        Parent addProductSaveParent = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        Scene addProductSaveScene = new Scene(addProductSaveParent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(addProductSaveScene);
        stage.show();
    } catch (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("An Error has occurred");
        alert.setContentText("Please review your input and try again.");
        alert.showAndWait();
    }
    }

    /***
     * Selecting this will erase the input fields, and switch screens to the Main Form screen
     * @param event variable created to opt out of creating a new Product, and switch screens back to the Main Form
     * @throws IOException
     */
    public void addProductCancelSelected(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "No data will be saved. Continue?");
        Optional<ButtonType> selectedButton = alert.showAndWait();

        if (selectedButton.isPresent() && selectedButton.get() == ButtonType.OK) {

            Parent addProductCancelParent = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
            Scene addProductCancelScene = new Scene(addProductCancelParent);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(addProductCancelScene);
            stage.show();
        }
    }

    /***
     * Typing alphabetical or numerical text here will filter the Parts Table
     * @param event variable created to filter the Parts Table by what ever is typed into the text field
     * @throws IOException when characters are input which are not numbers or letters from the alphabet
     */
    public void searchParts(ActionEvent event) throws IOException {

    try {
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
    } catch (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("An Error has occurred");
        alert.setContentText("Please search using correct values.");
        alert.showAndWait();
    }
    }

    /***
     * Selecting this will add the Selected Part to the Associated Parts Table
     * @param event variable created to add the Part to the Associated Parts list
     * @throws IOException when no Parts have been selected before selecting Add Button
     */
    public void addPartToAssociatedParts(ActionEvent event) throws IOException {
        if (associatedParts.contains(addProduct_PartTable.getSelectionModel().getSelectedItem())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An Error has occurred");
            alert.setContentText("The selected Part is already associated with this Product.");
            alert.showAndWait();
            return;
        }
        if (addProduct_PartTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An Error has occurred");
            alert.setContentText("No Parts selected, please try again.");
            alert.showAndWait();
            return;
        }
        if (addProduct_PartTable.getSelectionModel().getSelectedItem() != null) {

            associatedParts.add(addProduct_PartTable.getSelectionModel().getSelectedItem());
        }
    }


    /***
     * Removes Parts from the Associated Parts Table
     * @param event variable created to remove Parts from the Associated Parts Table
     * @throws IOException if the Remove Associated Part button is selected without selecting a Part to remove
     *
     * Initially I tried to use Try/Catch in this method. Try the code, then shoot an error message when any exceptions
     * were found. Inside this i created the Confirmation window to remove the Part from the Associated Parts Table.
     * However, I found the Remove Button would not shoot out any messages unless a Part was selected to be removed. Then
     * I would get both the Catch/Error Message as well as the Confirmation message.
     *
     * To fix this I took away the Try/Catch, and created two IF statements. One to assess if selection was NULL, which controlled the
     * Error Message. The Second IF statement controlled the Confirmation Message for when the selection was not NULL.
     */
    public void removeAssociatedPart(ActionEvent event) throws IOException {

        if (associatedPartTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An Error has occurred");
            alert.setContentText("Please select a Part to remove from this Product.");
            alert.showAndWait();
            return;
        }

        if (associatedPartTable.getSelectionModel().getSelectedItem() != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "The selected part will no longer be associated with this product. Continue?");
            Optional<ButtonType> selectedButton = alert.showAndWait();

            if (selectedButton.isPresent() && selectedButton.get() == ButtonType.OK) {
                associatedParts.remove(associatedPartTable.getSelectionModel().getSelectedItem());
            }
        }

    }


    /***
     * Initializes the Part Table and the Associated Part Table
     */
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

