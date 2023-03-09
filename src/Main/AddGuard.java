/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Rediet
 */
public class AddGuard extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        Parent add = FXMLLoader.load(getClass().getResource("AddGuardFXML.fxml"));
//    
//        
        Scene addGscene = new Scene(add, 602, 404);
       primaryStage.setResizable(false);  
      
        primaryStage.setTitle("Add New Guard");
        primaryStage.setScene(addGscene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

    

