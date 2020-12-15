package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/***
 * Creates the Inventory class
 */
public class Inventory {


    protected static ObservableList<Part> allParts = FXCollections.observableArrayList();
    protected static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /***
     * Adds a new  part to inventory
     * @param newPart variable for new Part being added to inventory
     */
    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    /***
     * Adds a new Product to inventory
     * @param newProduct variable for the new Product being added to inventory
     */
    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    /***
     * Looks up a Part in the Parts Array of the inventory by the Part ID
     * @param partId ID being used as input for the search
     * @return the results
     */
    public static Part lookupPart(int partId) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).id == partId) {
                return allParts.get(i);
            }
        }
        return null;
    }

    /***
     * Looks up a product in the Product Array of the inventory by the Product ID
     * @param productId ID being used as the input for the search
     * @return the results
     */
    public static Product lookupProduct(int productId) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).id == productId) {
                return allProducts.get(i);
            }
        }
        return null;
    }

    /***
     * Looks up a Part in the Inventory by the string or partial string
     * @param partName input being used for the search
     * @return the results
     */
    public static ObservableList<Part> lookupPart(String partName) {

        ObservableList<Part> partCopy = FXCollections.observableArrayList();
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getName().contains(partName)) {
                partCopy.add(allParts.get(i));
            }
        }
        return partCopy;
    }

    /***
     * Looks up a Product in the Inventory by the string or a partial string
     * @param productName input being used for the search
     * @return the results
     */
    public static ObservableList<Product> lookupProduct(String productName) {

        ObservableList<Product> productCopy = FXCollections.observableArrayList();
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).name.contains(productName)) {
                productCopy.add(allProducts.get(i));
            }
        }
        return productCopy;
    }

    /***
     * Updates the selected Part
     * @param index variable used to reference the index of the Part being updated
     * @param selectedPart Part selected to be updated
     */
    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    /***
     * Updates the selected product
     * @param index variable used to reference the index of the Product being updated
     * @param newProduct Product selected to be updated
     */
    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    /***
     * Removes a Part from inventory
     * @param selectedPart Part you would like to select
     * @return true
     */
    public static boolean deletePart(Part selectedPart) {

            if (allParts.contains(selectedPart)) {
                allParts.remove(selectedPart);
            }
        return false;
    }

    /***
     * Removes a Product from inventory
     * @param selectedProduct Product you would like to select
     * @return true
     */
    public static boolean deleteProduct(Product selectedProduct) {

        if (allProducts.contains(selectedProduct)) {
            allProducts.remove(selectedProduct);
        }
        return false;
    }

    /***
     * Returns the list of Parts in Inventory
     * @return the array list of Parts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /***
     * Returns the list of Products in Inventory
     * @return the array list of Products
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }





}
