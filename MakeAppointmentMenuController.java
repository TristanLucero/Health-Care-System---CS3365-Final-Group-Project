/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
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
public class MakeAppointmentMenuController implements Initializable {
   

    @FXML
    private TextField doctorsName;
    @FXML
    private TextField appointmentNumber;
    @FXML
    private TextArea appointmentText;
    @FXML
    private Button makeAppointment;
    @FXML
    private Button searchAppointment;
    @FXML
    private TextField patientName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void doctorsName(ActionEvent event) {
    }

    @FXML
    private void appointmentNumber(ActionEvent event) {
    }

    @FXML
    private void makeAppointment(ActionEvent event) throws IOException {
        String aNumber = appointmentNumber.getText();
        String searchText = appointmentText.getText();
        System.out.println(appointmentText);
        String[] lastSearchText = searchText.split("\n");
        String myLine = "";
        for (int i = 0; i < lastSearchText.length; i++) {
            String[] arrOfData = lastSearchText[i].split(",");
            if(arrOfData[0].equals(aNumber)){
                myLine = lastSearchText[i];
            }
        }
        GroupProject.removeAppointment(myLine + "," + patientName.getText());
        try (FileWriter writer = new FileWriter("./src/groupproject/appointmentData.txt", true)) {
            writer.write(myLine + "," + patientName.getText() + "\n");
        } catch(IOException e){
            System.out.println("ERROR...");
        }  
    }

    @FXML
    private void searchAppointment(ActionEvent event) {
        try {
            int counter = 1;
            String dName = doctorsName.getText();
            System.out.println(dName);
            File myObj = new File("./src/groupproject/doctorAppointments.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrOfData = data.split(",");
                String updateText = "";
                if(arrOfData[0].equals(dName)){
                    updateText = appointmentText.getText() + counter + "," + data + "\n";
                    appointmentText.setText(updateText);
                    counter++;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MakeAppointmentMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void patientName(ActionEvent event) {
    }
    
}
