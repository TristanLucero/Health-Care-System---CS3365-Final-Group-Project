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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tristan
 */
public class EmployeeMenuController implements Initializable {
    char menuSelection;
    @FXML
    private TextField employeeTextboxText;
    @FXML
    private TextArea bigTextBox;
    @FXML
    private MenuButton employeeSelection;
    @FXML
    private TextField updatePassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        File temp = new File("./src/groupproject/currentLogin.txt");
        boolean exists = temp.exists();
        ActionEvent myAction = new ActionEvent();
        if(exists)
            try {
                employeeLogin(myAction);
        } catch (IOException ex) {
            Logger.getLogger(EmployeeMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }    

    @FXML
    private void staffSelection(ActionEvent event) {
        menuSelection = 's';
        employeeSelection.setText("Staff");
    }

    @FXML
    private void nurseSelection(ActionEvent event) {
        menuSelection = 'n';
        employeeSelection.setText("Nurse");
    }

    @FXML
    private void doctorSelection(ActionEvent event) {
        menuSelection = 'd';
        employeeSelection.setText("Doctor");
    }

    @FXML
    private void ceoSelection(ActionEvent event) {
        menuSelection = 'c';
        employeeSelection.setText("CEO");
    }

    @FXML
    private void updateEmployeeTextbox(ActionEvent event) {
        String myStr = employeeTextboxText.getText();
    }

    @FXML
    private void employeeLogin(ActionEvent event) throws IOException {
        try {
            String employeeInputText = employeeTextboxText.getText();
            String passwordInputText = updatePassword.getText();
            boolean validLogin = false;
            char permissionOfEmployee = 'a';
            
            File temp = new File("./src/groupproject/currentLogin.txt");
            if(!temp.exists()){
                File myObj = new File("./src/groupproject/employeeIDS.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] arrOfData = data.split(",");
                    for (int i = 0; i < arrOfData.length; i++) { 
                        if(arrOfData[i].equals(employeeInputText) && arrOfData[i+1].equals(passwordInputText)){
                            validLogin = true;
                            permissionOfEmployee = arrOfData[i+2].charAt(0);
                            System.out.println(permissionOfEmployee);
                            try{
                                FileWriter myWriter = new FileWriter("./src/groupproject/currentLogin.txt");
                                myWriter.write(data);
                                myWriter.close();
                            } catch (IOException e){}
                        }
                    } 
                }
                myReader.close();
            }
            else{
                File myObj = new File("./src/groupproject/currentLogin.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    String[] arrOfData = data.split(",");
                    validLogin = true;
                    permissionOfEmployee = arrOfData[2].charAt(0);
                    System.out.println(permissionOfEmployee);
                }
                myReader.close();
            }
            
            if(validLogin){
                if('s' == permissionOfEmployee){
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("staffMenu.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));  
                        stage.show();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
                               else if('n' == permissionOfEmployee){
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nurseMenu.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));  
                        stage.show();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
                               else if('d' == permissionOfEmployee){
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("doctorMenu.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));  
                        stage.show();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
                                else if('c' == permissionOfEmployee){
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CEOMenu.fxml"));
                        Parent root1 = (Parent) fxmlLoader.load();
                        Stage stage = new Stage();
                        stage.setScene(new Scene(root1));  
                        stage.show();
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            else{
                bigTextBox.setVisible(true);
                bigTextBox.setText("ERROR: Invalid employeeID or employee doesn't have permission to view this. Try again...");
            }
        } catch (FileNotFoundException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
    }

    @FXML
    private void updatePassword(ActionEvent event) {
    }
}
