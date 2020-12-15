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
 * Controller for the Modify Product Screen
 */
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


    /***
     * Selecting this will modify the Product and save the changes to the Inventory, and switch back to the Main Form screen
     * @param event variable created to initiate the save method in the Modify Product screen
     * @throws IOException when criteria has not been met to fulfil all requirements to create a new Product
     */
    public void modifyProductSaveSelected(ActionEvent event) throws IOException {

    try {
        int id = Integer.parseInt(modifyProductId.getText());
        String name = modifyProductName.getText();
        double price = Double.parseDouble(modifyProductPrice.getText());
        int stock = Integer.parseInt(modifyProductInv.getText());
        int max = Integer.parseInt(modifyProductMax.getText());
        int min = Integer.parseInt(modifyProductMin.getText());

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
            return;
        }

        if (max < 1 || min < 1 || price < 1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An Error has occurred");
            alert.setContentText("Your input must be greater than zero.");
            alert.showAndWait();
            return;
        }

        Product product = new Product(id, name, price, stock, max, min);

        double count = 0;

        if (associatedPartList.size() > 0) {
            for (int i = 0; i < associatedPartList.size(); i++) {
                product.addAssociatedPart(associatedPartList.get(i));
                count += associatedPartList.get(i).getPrice();
            }
        }

        if (price < count) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An Error has occurred");
            alert.setContentText("The price of the parts associated with this product exceed the price of the product. Please adjust this product's price.");
            alert.showAndWait();
            return;
        }

        Inventory.updateProduct(id - 1, product);

        Parent modifyProductSaveParent = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        Scene modifyProductSaveScene = new Scene(modifyProductSaveParent);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(modifyProductSaveScene);
        stage.show();
    } catch (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("An Error has occurred");
        alert.setContentText("Please review your input and try again.");
        alert.showAndWait();
    }
    }

    /***
     * Selecting this will opt out of modifying the Product, and switch screens to the Main Form screen
     * @param event variable created to opt out of creating a new Product, and switch screens back to the Main Form
     * @throws IOException
     */
    public void modifyProductCancelSelected(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "No data will be saved. Continue?");
        Optional<ButtonType> selectedButton = alert.showAndWait();

        if (selectedButton.isPresent() && selectedButton.get() == ButtonType.OK) {

            Parent modifyProductCancelParent = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
            Scene modifyProductCancelScene = new Scene(modifyProductCancelParent);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setScene(modifyProductCancelScene);
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
    } catch (Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("An Error has occurred");
        alert.setContentText("Please search using correct values.");
        alert.showAndWait();
    }
    }

    /***
     * Adds selected Part to the Associated Parts Table
     * @param event variable created to add selected Part to Associated Parts
     * @throws IOException when no Part is selected before selecting the Add Part button
     */
    public void addPartToAssociatedParts(ActionEvent event) throws IOException {

        if (modifyProduct_PartTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An Error has occurred");
            alert.setContentText("No Parts selected, plese try again.");
            alert.showAndWait();
            return;
        }

        if(modifyProduct_PartTable.getSelectionModel().getSelectedItem() != null) {
            associatedPartList.add(modifyProduct_PartTable.getSelectionModel().getSelectedItem());
        }
    }


    /***
     * Removes Parts from the Associated Parts Table
     * @param event variable created to remove Parts from the Associated Parts Table
     * @throws IOException if the Remove Associated Part button is selected without selecting a Part to remove
     */
    public void removeAssociatedPart(ActionEvent event) throws IOException {

        if (modify_AssociatedPartTable.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("An Error has occurred");
            alert.setContentText("Please select a Part to remove from this Product.");
            alert.showAndWait();
            return;
        }

        if (modify_AssociatedPartTable.getSelectionModel().getSelectedItem() != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "The selected part will no longer be associated with this product. Continue?");
            Optional<ButtonType> selectedButton = alert.showAndWait();

            if (selectedButton.isPresent() && selectedButton.get() == ButtonType.OK) {
                associatedPartList.remove(modify_AssociatedPartTable.getSelectionModel().getSelectedItem());
            }
        }
    }

    /***
     * Initialized the Tables on the Modify Product screen
     * @param product variable created to pull the data associated with the Product selected
     */
    public void initProductData(Product product) {

        modifyProductId.setText(Integer.toString(product.getId()));
        modifyProductName.setText(product.getName());
        modifyProductPrice.setText(Double.toString(product.getPrice()));
        modifyProductInv.setText(Integer.toString(product.getStock()));
        modifyProductMax.setText(Integer.toString(product.getMax()));
        modifyProductMin.setText(Integer.toString(product.getMin()));

        modifyProduct_PartTable.setItems(Inventory.getAllParts());

        modifyProduct_PartTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        modifyProduct_PartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        ModifyProduct_PartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        modifyProduct_PartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modifyProduct_PartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        associatedPartList = product.getAllAssociatedParts();
        modify_AssociatedPartTable.setItems(associatedPartList);

        modify_AssociatedPartTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        modify_AssociatedPartId.setCellValueFactory(new PropertyValueFactory<>("id"));
        modify_AssociatedPartName.setCellValueFactory(new PropertyValueFactory<>("name"));
        modify_AssociatedPartInv.setCellValueFactory(new PropertyValueFactory<>("stock"));
        modify_AssociatedPartPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }



}
