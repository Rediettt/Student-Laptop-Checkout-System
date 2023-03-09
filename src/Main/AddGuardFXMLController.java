/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Main;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rediet
 */
public class AddGuardFXMLController implements Initializable {

    
    @FXML
    private TextField nametxt;
    @FXML
    private Button backbtn;
    @FXML
    private Label namelbl;
    @FXML
    private Label passlbl;
    @FXML
    private PasswordField passtxt;
    @FXML
    private ComboBox<String> gatecbo;
    @FXML
    private Button logoutbtn;
    @FXML
    private Button registerbtn;
    @FXML
    private Label gatelbl;
    private String nameTextdata;
    private String passTextdata;
    private int gateCbodata;
    
    ObservableList<String> gateList=FXCollections.observableArrayList("1","2","3");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       gatecbo.setValue("1");
       gatecbo.setItems(gateList);
    }    
    public void buttonClicked(ActionEvent event) throws IOException{
        Parent viewParent=null;    
            if(event.getSource()==logoutbtn){
                System.out.println("logout Button Clicked");
                viewParent= FXMLLoader.load(getClass().getResource("LoginPageFXML.fxml"));

            }
            else if(event.getSource()==backbtn){
                        System.out.println("back Button Clicked");
                viewParent= FXMLLoader.load(getClass().getResource("AdminFXML.fxml"));
            }
            else{
            viewParent= FXMLLoader.load(getClass().getResource("AddGuars.fxml"));
            }
            Scene newScene= new Scene(viewParent);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.show();
    }
    @FXML
    private void changeScreenButtonPushed(ActionEvent event) throws IOException, ClassNotFoundException{
        
        if(event.getSource()==registerbtn){
           nameTextdata=nametxt.getText();
           passTextdata=passtxt.getText();
           gateCbodata= Integer.parseInt(gatecbo.getValue());
           Connection con =null;
           PreparedStatement p = null;
        
           Parent viewParent=null;
           if(!nameTextdata.equals("") && !passTextdata.equals("")){
                Pattern letter = Pattern.compile("[a-zA-z]");
                Pattern digit = Pattern.compile("[0-9]");
                Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

                Matcher hasLetter = letter.matcher(passTextdata);
                Matcher hasDigit = digit.matcher(passTextdata);
                Matcher hasSpecial = special.matcher(passTextdata);
                
                if(passTextdata.length()<5){
                           Dialog<String> dialog = new Dialog<String>();
                               dialog.setTitle("Wrong Credentials");
                               ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                               dialog.setContentText("Password can't be shorter than five charachters");
                               dialog.getDialogPane().getButtonTypes().add(type);
                               dialog.showAndWait();
                               return;
                }
                else if(!(hasLetter.find() && hasDigit.find() && hasSpecial.find())){
                    Dialog<String> dialog = new Dialog<String>();
                               dialog.setTitle("Wrong Credentials");
                               ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                               dialog.setContentText("Password should conatain atleast one Capital Letter, one Number and one Special charachter");
                               dialog.getDialogPane().getButtonTypes().add(type);
                               dialog.showAndWait();
                               return;
                }
                else{
                     int id = 0;
                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/laptop checkout","root", "Admin123");
                            String sql = "INSERT INTO staff_table(`full_name`,`password`,`position`,`gate_number`) VALUES (?,?,1,?)";
                            p = con.prepareStatement(sql, p.RETURN_GENERATED_KEYS);
                            p.setString(1,nameTextdata);
                            p.setString(2, passTextdata);
                            p.setInt(3, gateCbodata);

                            int affectedRows = p.executeUpdate();
                            if (affectedRows > 0) {
                                try (ResultSet rs=p.getGeneratedKeys()){
                                        if (rs.next()) {
                                                id = rs.getInt(1);
                                        }
                                    Dialog<String> dialog = new Dialog<String>();
                                    dialog.setTitle("Successful Entry");
                                    ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                                    dialog.setContentText("New Guard has been added. His/her id is: "+id+"\n\tPLEASE REMEMBER THE ID!");
                                    dialog.getDialogPane().getButtonTypes().add(type);
                                    dialog.showAndWait();
                                    backbtn.fire();
                                }
                                catch (SQLException ex) {
                                        System.out.println(ex.getMessage());
                                }                                
                            }
                            else{

                            }
                        }
                    catch (SQLException ex) {
                                System.out.println(ex.getMessage());
                    }
                      
                }
           }
           else{
               Dialog<String> dialog = new Dialog<String>();
                       dialog.setTitle("Empty Fields");
                       ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                       dialog.setContentText("Username and Password can not be empty");
                       dialog.getDialogPane().getButtonTypes().add(type);
                       dialog.showAndWait();
           }
        }
    }
  
}
