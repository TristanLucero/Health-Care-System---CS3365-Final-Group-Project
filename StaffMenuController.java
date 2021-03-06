/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupproject;

import java.io.File;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tristan
 */
public class StaffMenuController implements Initializable {

    @FXML
    private Button makeAppointment;
    @FXML
    private Button changeAppointment;
    @FXML
    private Button cancelAppointment;
    @FXML
    private Button checkInPatient;
    @FXML
    private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void makeAppointment(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("makeAppointmentMenu.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StaffMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void PayMedicalFees(ActionEvent event){
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("paymentMenu.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));  
                        stage.show();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
}
        @FXML
    private void changeAppointment(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("changeAppointmentMenu.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StaffMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @FXML
    private void cancelAppointment(ActionEvent event) {
                try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cancelAppointment.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StaffMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void checkInPatient(ActionEvent event) {
               try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("checkInAppointmentMenu.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StaffMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void logout(ActionEvent event) {
        File myObj = new File("./src/groupproject/currentLogin.txt");
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Logout?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();
        if (myObj.delete()) { 
          System.out.println("Deleted the file: " + myObj.getName());
        } else {
          System.out.println("Failed to delete the file.");
        } 
        exit(1);
    }
    
}
