package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
        primaryStage.setScene(new Scene(root, 1000, 425));
        primaryStage.show();
    }


    public static void main(String[] args) {

        InHouse Screws = new InHouse(1, "Screws", 0.99, 100, 50, 500, 1 );
        Outsourced Hammers = new Outsourced(2, "Hammers", 11.99, 20, 5, 50, "Hammer Company");

        Inventory.addPart(Screws);
        Inventory.addPart(Hammers);

        Product Bike = new Product(1, "Bike", 299.99, 15, 2, 20);
        Product Skateboard = new Product(2, "Skateboard", 39.99, 20, 5, 50);

        Inventory.addProduct(Bike);
        Inventory.addProduct(Skateboard);


        launch(args);
    }
}
