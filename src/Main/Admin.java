/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Rediet
 */
public class Admin extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
      Parent add = FXMLLoader.load(getClass().getResource("AdminFXML.fxml"));
      
        Scene scene = new Scene(add, 602, 404);
       primaryStage.setResizable(false);  
      
        primaryStage.setTitle("Admin");
        primaryStage.setScene(scene);
        primaryStage.show();
}
      public static void main(String[] args) {
        launch(args);
    }
}
