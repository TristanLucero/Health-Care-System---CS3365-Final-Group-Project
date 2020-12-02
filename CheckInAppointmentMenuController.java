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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Tristan
 */
public class CheckInAppointmentMenuController implements Initializable {

    @FXML
    private TextField patientName;
    @FXML
    private TextField address;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField ssn;
    @FXML
    private TextField healthInsurance;
    @FXML
    private TextField doctorName;
    @FXML
    private Button searchForPatientChartButton;
    @FXML
    private Button updateInfo;

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
    private void address(ActionEvent event) {
    }

    @FXML
    private void phoneNumber(ActionEvent event) {
    }

    @FXML
    private void ssn(ActionEvent event) {
    }

    @FXML
    private void healthInsurance(ActionEvent event) {
    }

    @FXML
    private void doctorName(ActionEvent event) {
    }

    @FXML
    private void searchForPatientChartButton(ActionEvent event) {
        try {
            String pName = patientName.getText();
            System.out.println(pName);
            File myObj = new File("./src/groupproject/patientChart.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrOfData = data.split(",");
                String updateText = "";
                if(arrOfData[0].equals(pName)){
                    address.setText(arrOfData[1]);
                    phoneNumber.setText(arrOfData[2]);
                    ssn.setText(arrOfData[3]);
                    healthInsurance.setText(arrOfData[4]);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MakeAppointmentMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void updateInfo(ActionEvent event) {
      System.out.println("Updating patient info");
      List<String> PatientData = new ArrayList<>();  
      List<String> PaymentData = new ArrayList<>();  
      Random rand = new Random();
        try {
        String pName = patientName.getText();
        File myObj = new File("./src/groupproject/patientChart.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            String[] arrOfData = data.split(",");
            if(arrOfData[0].equals(pName)){   
                   arrOfData[0] = patientName.getText() +",";
                   arrOfData[1] = address.getText() +",";
                   arrOfData[2] = phoneNumber.getText() +",";
                   arrOfData[3] = ssn.getText() +",";
                   arrOfData[4] = healthInsurance.getText() +"\n";                   
                   PatientData.add(arrOfData[0]);
                   PatientData.add(arrOfData[1]);
                   PatientData.add(arrOfData[2]);
                   PatientData.add(arrOfData[3]);
                   PatientData.add(arrOfData[4]);   
                   PaymentData.add("\n");
                   PaymentData.add(patientName.getText() + ",");
                   PaymentData.add(rand.nextInt(100) + ",");
                   PaymentData.add("11/28/20,"); //todays date
                   PaymentData.add(50 + ",");
                   PaymentData.add(0 + ",");
                   PaymentData.add("null" + ",");
                   PaymentData.add("null\n");
                }
            else{
                data = data + "\n";
                PatientData.add(data);
            }
        }          
        FileWriter Writer = new FileWriter(myObj);
        int size = PatientData.size();
        for(int i =0; i < size; i++)
            Writer.write(PatientData.get(i));
        Writer.close();
        myReader.close();    
        
        File myObj2 = new File("./src/groupproject/PaymentData.txt");
        FileWriter Writer2 = new FileWriter(myObj2, true);
        for(int i =0; i < PaymentData.size(); i++)
            Writer2.write(PaymentData.get(i));
        Writer2.close();
        
        
        }

       catch (IOException e) {
       e.printStackTrace();
      } 
    }
    
}
