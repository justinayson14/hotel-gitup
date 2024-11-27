package com.example.hotelproject2;

import java.io.IOException;
import java.util.ResourceBundle;

import com.example.hotelproject2.models.Customers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomerSceneController {
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private Spinner<Integer> guestAmtSpinner;

    /**
     * Initialize the guestAmtSpinner to retrieve values from it later
     */
    @FXML
    public void initialize() {
        guestAmtSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 99999, 1));
    }

    /**
     * Switches to HomeScene when "Cancel" button is clicked
     * @param event
     * @throws IOException
     */
    @FXML
    private void switchToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Switches to RoomDetailsScene when "Book Room" button is clicked
     * and passes customer data along.
     * @param event
     * @throws IOException
     */
    @FXML
    private void switchToRoomDetails(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RoomDetailsScene.fxml"));
        Parent root = loader.load();
        // creates an instance of the scene's controller to pass customer data to
        RoomDetailsController controller = loader.getController();
        // sets the customer's name
        String customerFullName = firstName.getText() + " " + lastName.getText();
        Customers customer = new Customers();
        customer.setName(customerFullName);
        // sets the customer's party count
        int customerGuestAmt = guestAmtSpinner.getValue();
        customer.setPartyCount(customerGuestAmt);
        
        // prints the customer data to console and passes it along
        System.out.println("\n---\nPassing along the following customer data: " + customer + "\n---"); // prints to console
        controller.getCustomer(customer); // passes it along

        // switch scene
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}