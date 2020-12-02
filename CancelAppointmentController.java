/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Tristan
 */
public class CancelAppointmentController implements Initializable {

    @FXML
    private TextField patientName;
    @FXML
    private Button checkAppointments;
    @FXML
    private Button cancelAppointment;
    @FXML
    private TextArea bigText;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void patientName(ActionEvent event) {
    }

    @FXML
    private void checkAppointments(ActionEvent event) {
        try {
            String pName = patientName.getText();
            File myObj = new File("./src/groupproject/appointmentData.txt");
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrOfData = data.split(",");
                if(arrOfData[4].equals(pName)){
                    bigText.setText(data);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MakeAppointmentMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cancelAppointment(ActionEvent event) {
        try {
            String toDel = bigText.getText();
            File myObj = new File("./src/groupproject/appointmentData.txt");
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.equals(bigText.getText())){
                    try {
                        GroupProject.removeAppointment(data);
                    } catch (IOException ex) {
                        Logger.getLogger(CancelAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MakeAppointmentMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
