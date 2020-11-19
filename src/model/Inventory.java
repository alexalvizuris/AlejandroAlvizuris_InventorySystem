package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {

    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public void addPart(Part newPart) {
        allParts.add(newPart);
    }

    public void addProduct(Product newProduct) {
        allProducts.add(newProduct);
    }

    public Part lookupPart(int partId) {
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).id == partId) {
                return allParts.get(i);
            }
        }
        return null;
    }

    public Product lookupProduct(int productId) {
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).id == productId) {
                return allProducts.get(i);
            }
        }
        return null;
    }

    public ObservableList<Part> lookupPart(String partName) {

        ObservableList<Part> partCopy = FXCollections.observableArrayList();
        for (int i = 0; i < allParts.size(); i++) {
            if (allParts.get(i).getName().contains(partName)) {
                partCopy.add(allParts.get(i));
            }
        }
        return partCopy;
    }

    public ObservableList<Product> lookupProduct(String productName) {

        ObservableList<Product> productCopy = FXCollections.observableArrayList();
        for (int i = 0; i < allProducts.size(); i++) {
            if (allProducts.get(i).name.contains(productName)) {
                productCopy.add(allProducts.get(i));
            }
        }
        return productCopy;
    }

    public void updatePart(int index, Part selectedPart) {
        for (int i = 0; i < allParts.size(); i++) {
            if ()
        }
    }



}
