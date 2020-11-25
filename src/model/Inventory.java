package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    protected static ObservableList<Part> allParts = FXCollections.observableArrayList();
    protected static ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public static void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public static Part lookupPart(int partId) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).id == partId) {
                return allParts.get(i);
            }
        }
        return null;
    }

    public static Product lookupProduct(int productId) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).id == productId) {
                return allProducts.get(i);
            }
        }
        return null;
    }

    public static ObservableList<Part> lookupPart(String partName) {

        ObservableList<Part> partCopy = FXCollections.observableArrayList();
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getName().contains(partName)) {
                partCopy.add(allParts.get(i));
            }
        }
        return partCopy;
    }

    public static ObservableList<Product> lookupProduct(String productName) {

        ObservableList<Product> productCopy = FXCollections.observableArrayList();
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).name.contains(productName)) {
                productCopy.add(allProducts.get(i));
            }
        }
        return productCopy;
    }

    public static void updatePart(int index, Part selectedPart) {
        allParts.set(index, selectedPart);
    }

    public static void updateProduct(int index, Product newProduct) {
        allProducts.set(index, newProduct);
    }

    public static boolean deletePart(Part selectedPart) {
        while (true) {
            if (allParts.contains(selectedPart)) {
                allParts.remove(selectedPart);
            }
        }
    }

    public static boolean deleteProduct(Product selectedProduct) {
        while (true) {
            if (allProducts.contains(selectedProduct)) {
                allProducts.remove(selectedProduct);
            }
        }
    }

    public static ObservableList<Part> getAllParts() {
        ObservableList<Part> partsList = FXCollections.observableArrayList();
        for (int i = 0; i < allParts.size(); i++) {
            partsList.add(allParts.get(i));
        }
        return partsList;
    }

    public static ObservableList<Product> getAllProducts() {
        ObservableList<Product> productsList = FXCollections.observableArrayList();
        for (int i = 0; i < allProducts.size(); i++) {
            productsList.add(allProducts.get(i));
        }
        return productsList;
    }





}
