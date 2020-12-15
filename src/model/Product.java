package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/***
 * Creates the Product class
 */
public class Product {
    protected ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    protected int id;
    protected String name;
    protected double price;
    protected int stock;
    protected int min;
    protected int max;

    /***
     * Creates the Product class Constructor
     * @param id variable for the ID number assigned to the Product
     * @param name variable for the Name assigned to the Product
     * @param price variable for the price assigned to the Product
     * @param stock variable for the Inventory level of the product
     * @param min variable for the minimum amount of Products allowed in inventory
     * @param max variable for the maximum amount of Products allowed in inventory
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /***
     * Gets the Product Id number
     * @return the Product ID
     */
    public int getId() {
        return id;
    }

    /***
     * Sets the Product ID number
     * @param id references the input given for the ID number
     */
    public void setId(int id) {
        this.id = id;
    }

    /***
     * Gets the name of the Product
     * @return name of Product
     */
    public String getName() {
        return name;
    }

    /***
     * Sets the name of the Product
     * @param name references the input to be used as the name of the Product
     */
    public void setName(String name) {
        this.name = name;
    }

    /***
     * Gets the price of the Product
     * @return the price of the Product
     */
    public double getPrice() {
        return price;
    }

    /***
     * Sets the price of the Product
     * @param price references the input to be used as the price of the Product
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /***
     * Gets the Inventory level of the selected Product
     * @return inventory level of the selected Product
     */
    public int getStock() {
        return stock;
    }

    /***
     * Sets the Inventory level of the selected Product
     * @param stock references the input to be used as the Inventory level of the Product
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /***
     * Gets the minimum amount of Products allowed in inventory
     * @return the minimum amount
     */
    public int getMin() {
        return min;
    }

    /***
     * Sets the minimum amount of Products allowed in inventory
     * @param min references the input to be used as the minimum amount of Products
     */
    public void setMin(int min) {
        this.min = min;
    }

    /***
     * Gets the maximum amount of Products allowed in Inventory
     * @return the maximum amount
     */
    public int getMax() {
        return max;
    }

    /***
     *  Sets the maximum amount of Products allowed in Inventory
     * @param max references the input to be used as the maximum amount
     */
    public void setMax(int max) {
        this.max = max;
    }

    /***
     * Adds a part to the Associated Parts List
     * @param part references the selected Part
     */
    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /***
     * Removes a Part from the Associated Parts Table
     * @param selectedAssociatedPart references the selected Part
     * @return true
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        while (true) {
            if (associatedParts.contains(selectedAssociatedPart)) {
                associatedParts.remove(selectedAssociatedPart);
            }
        }
    }

    /***
     * Returns all associated parts for a aproduct
     * @return the list of associated parts
     */
    public ObservableList<Part> getAllAssociatedParts() {
        ObservableList<Part> partsContained = FXCollections.observableArrayList();
        for (int i = 0; i < associatedParts.size(); i++) {
            partsContained.add(associatedParts.get(i));
        }
        return partsContained;
    }
}


