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
 */
public class CEOMenuController implements Initializable {
    @FXML
    private TextArea ReportArea;
    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    private void ViewReport(ActionEvent event){
                         
               try {
                   
            File myObj = new File("./src/groupproject/report.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                
                String data = myReader.nextLine();              
                ReportArea.appendText(data+"\n");
            }          
        
        myReader.close();         
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
