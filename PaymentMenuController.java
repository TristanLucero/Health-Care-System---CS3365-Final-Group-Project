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
import java.util.Random;

/**
 *
 * @author Nolan
 */

public class PaymentMenuController implements Initializable {
    
    @FXML
    private TextField PaymentPatientBox;
    @FXML
    private TextField CashBox;
    @FXML
    private TextArea PaymentTextBoxArea;
    @FXML
    private TextArea ReceiptBoxArea;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
    @FXML
    private void PatientPaymentSearch(ActionEvent event) {
        int FoundPatient=0;
               try {
            String SearchInputText = PaymentPatientBox.getText();
            PaymentTextBoxArea.setText("");
            File myObj = new File("./src/groupproject/PaymentData.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arrOfData = data.split(",");
                for (int i = 0; i < arrOfData.length; i++) { 
                    if(arrOfData[i].equals(SearchInputText)){
                        FoundPatient = 1;
                        PaymentTextBoxArea.appendText(arrOfData[i]+" ID: " + arrOfData[i+1] + "\n");
                        PaymentTextBoxArea.appendText("    Date: " + arrOfData[i+2] + "\n");
                        if(arrOfData[i+5].equals("null"))
                            PaymentTextBoxArea.appendText("    Payment Type: N/A\n");
                        
                        else
                            PaymentTextBoxArea.appendText("    Payment Type: "+arrOfData[i+5]+"\n");
                        
                        PaymentTextBoxArea.appendText("    Amount Owed: $"+arrOfData[i+3]+"\n");
                        PaymentTextBoxArea.appendText("    Amount Paid: $"+arrOfData[i+4]+"\n");
                        if(arrOfData[i+6].equals("null")){
                            PaymentTextBoxArea.appendText("    Reference#: N/A\n");                       
                    }
                        else{
                              PaymentTextBoxArea.appendText("    Reference#: "+arrOfData[i+6]+"\n");                
            }
                }
                }
            }
                    
            
        if(FoundPatient == 0){
            PaymentTextBoxArea.appendText("ERROR: Patient not found. Please try again.");
            }
            
        
        myReader.close();
        } 
               
          catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
    }
    
        @FXML
    private void PatientPaidCash(ActionEvent event){
        
      List<String> PatientData = new ArrayList<>();  
      
               try {
                   
            String NurseInputText = PaymentPatientBox.getText();
            File myObj = new File("./src/groupproject/PaymentData.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                
                String data = myReader.nextLine();
                String[] arrOfData = data.split(",");
                
                if(arrOfData[0].equals(NurseInputText)){
                   
                       arrOfData[0] =arrOfData[0] +",";
                       arrOfData[1] =arrOfData[1] +",";
                       arrOfData[2] =arrOfData[2] +",";
                       arrOfData[3] =arrOfData[3] +",";
                       arrOfData[4] =CashBox.getText() +",";
                       arrOfData[5] ="Cash,";
                       arrOfData[6] ="Paid with cash\n";                   
                       
                       PatientData.add(arrOfData[0]);
                       PatientData.add(arrOfData[1]);
                       PatientData.add(arrOfData[2]);
                       PatientData.add(arrOfData[3]);
                       PatientData.add(arrOfData[4]);
                       PatientData.add(arrOfData[5]);
                       PatientData.add(arrOfData[6]);          
                       
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
        PatientPaymentSearch(event);
               }

       catch (IOException e) {
       e.printStackTrace();
      } 
               
   }
    
 @FXML
    private void PatientPaidCredit(ActionEvent event){
      List<String> PatientData = new ArrayList<>();  
      
               try {
                   
            String NurseInputText = PaymentPatientBox.getText();
            File myObj = new File("./src/groupproject/PaymentData.txt");
            Scanner myReader = new Scanner(myObj);
            Random rand = new Random();
            int digit1 = rand.nextInt(9);
            int digit2 = rand.nextInt(9);
            int digit3 = rand.nextInt(9);
            int digit4 = rand.nextInt(9);
            int digit5 = rand.nextInt(9);
            String digit1s = Integer.toString(digit1);
            String digit2s = Integer.toString(digit2);
            String digit3s = Integer.toString(digit3);
            String digit4s = Integer.toString(digit4);
            String digit5s = Integer.toString(digit5);
            String referencenumber = digit1s + digit2s + digit3s + digit4s + digit5s + "RF";
            while (myReader.hasNextLine()) {
                
                String data = myReader.nextLine();
                String[] arrOfData = data.split(",");
                
                if(arrOfData[0].equals(NurseInputText)){
                   
                       arrOfData[0] =arrOfData[0] +",";
                       arrOfData[1] =arrOfData[1] +",";
                       arrOfData[2] =arrOfData[2] +",";
                       arrOfData[4] =arrOfData[3] +",";
                       arrOfData[3] =arrOfData[3] + ",";
                       arrOfData[5] ="Credit,";
                       arrOfData[6] =referencenumber + "\n";   
                       
                       PatientData.add(arrOfData[0]);
                       PatientData.add(arrOfData[1]);
                       PatientData.add(arrOfData[2]);
                       PatientData.add(arrOfData[3]);
                       PatientData.add(arrOfData[4]);
                       PatientData.add(arrOfData[5]);
                       PatientData.add(arrOfData[6]);   
                       
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
        PatientPaymentSearch(event);
        receipt(event);
               }

       catch (IOException e) {
       e.printStackTrace();
      } 

}
    
    @FXML
    private void PatientCredit(ActionEvent event){
    Random rand = new Random();
   int chance = rand.nextInt(100);
   if(chance > 50)
        PatientPaidCredit(event);
   else
       PaymentTextBoxArea.setText("Card Declined: Try again, or use different method");
        
}
    @FXML
    private void PatientPaidDebit(ActionEvent event){
      List<String> PatientData = new ArrayList<>();  
      
               try {
                   
            String NurseInputText = PaymentPatientBox.getText();
            File myObj = new File("./src/groupproject/PaymentData.txt");
            Scanner myReader = new Scanner(myObj);
            Random rand = new Random();
            int digit1 = rand.nextInt(9);
            int digit2 = rand.nextInt(9);
            int digit3 = rand.nextInt(9);
            int digit4 = rand.nextInt(9);
            int digit5 = rand.nextInt(9);
            String digit1s = Integer.toString(digit1);
            String digit2s = Integer.toString(digit2);
            String digit3s = Integer.toString(digit3);
            String digit4s = Integer.toString(digit4);
            String digit5s = Integer.toString(digit5);
            String referencenumber = digit1s + digit2s + digit3s + digit4s + digit5s + "RF";
            while (myReader.hasNextLine()) {
                
                String data = myReader.nextLine();
                String[] arrOfData = data.split(",");
                
                if(arrOfData[0].equals(NurseInputText)){
                   
                       arrOfData[0] =arrOfData[0] +",";
                       arrOfData[1] =arrOfData[1] +",";
                       arrOfData[2] =arrOfData[2] +",";
                       arrOfData[4] =arrOfData[3] +",";
                       arrOfData[3] =arrOfData[3] + ",";
                       arrOfData[5] ="Debit,";
                       arrOfData[6] =referencenumber + "\n";                   
                       
                       PatientData.add(arrOfData[0]);
                       PatientData.add(arrOfData[1]);
                       PatientData.add(arrOfData[2]);
                       PatientData.add(arrOfData[3]);
                       PatientData.add(arrOfData[4]);
                       PatientData.add(arrOfData[5]);
                       PatientData.add(arrOfData[6]);           
                       
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
        PatientPaymentSearch(event);
        receipt(event);
               }

       catch (IOException e) {
       e.printStackTrace();
      } 

}
    
    @FXML
    private void PatientDebit(ActionEvent event){
    Random rand = new Random();
   int chance = rand.nextInt(100);
   if(chance > 50)
        PatientPaidDebit(event);
   else
       PaymentTextBoxArea.setText("Card Declined: Try again, or use different method");
        
}
    @FXML
    private void receipt(ActionEvent event){
        ReceiptBoxArea.setText("Recipt Printed");
    }
}
    
