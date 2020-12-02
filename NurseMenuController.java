/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package groupproject;

import java.util.*;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.io.IOException;
import static java.lang.System.exit;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author Nolan
 * 
 * added treatment field (read-only for nurses) and logout method - Jett
 */
public class NurseMenuController implements Initializable {
    @FXML
    private TextArea TextBoxArea;
    @FXML
    private TextField HeightBox;
    @FXML
    private TextField BloodBox;
    @FXML
    private TextField WeightBox;
    @FXML
    private TextField ReasonBox;
    @FXML
    private TextField PatientBox;
    @FXML
    private TextField TreatmentBox;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // make treatment read-only for nurses
        if(DoctorMenuController.doctorPermission != true){
        TreatmentBox.setEditable(false);
        }
    } 
    
    @FXML
    private void PatientSearch(ActionEvent event) {
        int FoundPatient=0;
               try {
            String NurseInputText = PatientBox.getText();
            
            File myObj = new File("./src/groupproject/PatientData.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrOfData = data.split(",");
                for (int i = 0; i < arrOfData.length; i++) { 
                    if(arrOfData[i].equals(NurseInputText)){
                        FoundPatient = 1;
                        TextBoxArea.setText("");
                        HeightBox.setText(arrOfData[i+1]);
                        WeightBox.setText(arrOfData[i+2]);
                        BloodBox.setText(arrOfData[i+3]);
                        ReasonBox.setText(arrOfData[i+4]);
                        TreatmentBox.setText(arrOfData[i+5]);
                        
                        
                        
                    }

                } 
            }
            
        if(FoundPatient == 0){
            TextBoxArea.setText("ERROR: Patient not found. Please try again.");
            HeightBox.setText("N/A");
            WeightBox.setText("N/A");
            BloodBox.setText("N/A");
            ReasonBox.setText("N/A");
            TreatmentBox.setText("N/A");
            }
        myReader.close();
        } 
          catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
    }
    
    @FXML
    private void PatientUpdate(ActionEvent event){
        
      List<String> PatientData = new ArrayList<>();  
      
               try {
                   
            String NurseInputText = PatientBox.getText();
            File myObj = new File("./src/groupproject/PatientData.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                
                String data = myReader.nextLine();
                String[] arrOfData = data.split(",");
                
                if(arrOfData[0].equals(NurseInputText)){
                        
                       arrOfData[0] =PatientBox.getText() +",";
                       arrOfData[1] =HeightBox.getText() +",";
                       arrOfData[2] =WeightBox.getText() +",";
                       arrOfData[3] =BloodBox.getText() +",";
                       arrOfData[4] =ReasonBox.getText() +",";                   
                       arrOfData[5] =TreatmentBox.getText() +"\n";
                       
                       
                       PatientData.add(arrOfData[0]);
                       PatientData.add(arrOfData[1]);
                       PatientData.add(arrOfData[2]);
                       PatientData.add(arrOfData[3]);
                       PatientData.add(arrOfData[4]);
                       PatientData.add(arrOfData[5]);
                       
                       
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
        PatientSearch(event);
               }

       catch (IOException e) {
       e.printStackTrace();
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
    
    

    
    
