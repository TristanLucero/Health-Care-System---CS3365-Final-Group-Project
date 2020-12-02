/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Tristan
 */
public class GroupProject extends Application {
    public static ArrayList<String> reportInfo = new ArrayList<String>();
    public static ArrayList<String> appointmentInfo = new ArrayList<String>();
    
    @Override
    public void start(Stage stage) throws Exception {
        reportInfo.add("test report");
        //FOR TESTING TIMER
        //GroupProject.reportInfo.add("123");
        //GroupProject.reportInfo.add("321");
        //today.set(Calendar.HOUR_OF_DAY, 2);
        
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 21); //9pm
        today.set(Calendar.MINUTE, 00);
        today.set(Calendar.SECOND, 0);
        Timer timer = new Timer();
        TimerTask task = new generateReport(); 
        timer.schedule(task, today.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // period: 1 day
        
        TimerTask myTask = new generateReport(); 
        myTask.run();
        
        Calendar today2 = Calendar.getInstance();
        today2.set(Calendar.HOUR_OF_DAY, 20); //8pm
        today2.set(Calendar.MINUTE, 00);
        today2.set(Calendar.SECOND, 0);
        Timer timer2 = new Timer();
        TimerTask task2 = new clearNoShow(); 
        timer2.schedule(task2, today2.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)); // period: 1 day
        
        //TESTING FOR NO SHOW
        TimerTask myNoShow = new clearNoShow();
        myNoShow.run();
        
        Parent root = FXMLLoader.load(getClass().getResource("employeeMenu.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void timerFunc(){
        System.out.println("123");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
   public static void removeAppointment(String lineContent) throws IOException{
        File file = new File("./src/groupproject/appointmentData.txt");
        List<String> out = Files.lines(file.toPath())
                            .filter(line -> !line.contains(lineContent))
                            .collect(Collectors.toList());
        Files.write(file.toPath(), out, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
    }    
}


class generateReport extends TimerTask { 
    public static int i = 0; 
    @Override
    public void run() { 
        try (FileWriter writer = new FileWriter("./src/groupproject/report.txt")) {
            for(String str: GroupProject.reportInfo) {
                writer.write(str + System.lineSeparator());
            }
        } catch(IOException e){
            System.out.println("ERROR...");
        }
    } 
} 

//APPOINTMENT MUST BE 4TH ITEM OF [3] IN DATA LISTED IN APPOINTMENTDATA, OTHERWISE WON'T WORK
class clearNoShow extends TimerTask { 
    @Override
    public void run() { 
        try {
            String lineToRemove = "";
            do{
                lineToRemove = "";
                File myObj = new File("./src/groupproject/appointmentData.txt");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                    String[] arrOfData = data.split(",");
                    if(Integer.parseInt(arrOfData[2]) <= 11282020 && Integer.parseInt(arrOfData[3]) <= 20)
                        lineToRemove = data;
                }
                myReader.close();

                File inputFile = new File("./src/groupproject/appointmentData.txt"); 
                File tempFile = new File("./src/groupproject/myTempFile.txt"); 

                BufferedReader reader = new BufferedReader(new FileReader(inputFile)); 
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile)); 


                String currentLine; 

                while((currentLine = reader.readLine()) != null) { 
                // trim newline when comparing with lineToRemove 
                    String trimmedLine = currentLine.trim(); 
                    if(trimmedLine.equals(lineToRemove)) continue; 
                    writer.write(currentLine + System.getProperty("line.separator")); 
                } 
                writer.close();  
                reader.close();  
                inputFile.delete();
                boolean success = tempFile.renameTo(inputFile);
                System.out.println(success);
            } while(!"".equals(lineToRemove));
            
        } catch (FileNotFoundException ex) { 
        } catch (IOException ex) {
            Logger.getLogger(clearNoShow.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
} 
