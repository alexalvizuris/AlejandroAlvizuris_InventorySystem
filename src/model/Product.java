package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    protected ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    protected int id;
    protected String name;
    protected double price;
    protected int stock;
    protected int min;
    protected int max;

    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public void addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        while (true) {
            if (associatedParts.contains(selectedAssociatedPart)) {
                associatedParts.remove(selectedAssociatedPart);
            }
        }
    }

    public ObservableList<Part> getAllAssociatedParts() {
        ObservableList<Part> partsContained = FXCollections.observableArrayList();
        for (int i = 0; i < associatedParts.size(); i++) {
            partsContained.add(associatedParts.get(i));
        }
        return partsContained;
    }
}


