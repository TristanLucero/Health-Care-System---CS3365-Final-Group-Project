/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupproject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tristan
 */
public class MainMenu1Controller implements Initializable {

    @FXML
    private Button employeeClientButton;
    @FXML
    private Button patientClientButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void employeeClientButton(ActionEvent event) {
        System.out.println("employee");
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("employeeMenu.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    } catch(Exception e) {
        e.printStackTrace();
    }
    }

    @FXML
    private void patientClientButton(ActionEvent event) {
        System.out.println("patient");
    }
    
}
