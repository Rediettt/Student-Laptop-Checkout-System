package Main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
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
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Rediet
 */
public class AddStudController implements Initializable {
    @FXML
    private Label studLbl;
    @FXML
    private TextField studTxt;
    @FXML
    private Label brandLbl;
    @FXML
    private TextField brandtxt;
    @FXML
    private Label serialLbl;
    @FXML
    private TextField serialTxt;

     @FXML
    private Label gidLbl;
    @FXML
    private TextField gidTxt;

    @FXML
    private Button backbtn;
    @FXML
    private Button logoutbtn;
    @FXML
    private Button registerbtn;

    
  
    private String studTxtdata;
    private String brandtxtData;
    private String serialTxtData;
    private String gidTxtData;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void buttonClicked(ActionEvent event) throws IOException {
        Parent viewParent=null;    
            if(event.getSource()==logoutbtn){
                System.out.println("logout Button Clicked");
                viewParent= FXMLLoader.load(getClass().getResource("LoginPageFXML.fxml"));

            } else if(event.getSource()==backbtn){
                        System.out.println("back Button Clicked");
                viewParent= FXMLLoader.load(getClass().getResource("GuardFXML.fxml"));
            }
            else{
            viewParent= FXMLLoader.load(getClass().getResource("SearchGuars.fxml"));
            }
            Scene newScene= new Scene(viewParent);
            Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(newScene);
            window.show();
    }

    @FXML
    private void changeScreenButtonPushed(ActionEvent event) throws ClassNotFoundException, SQLException {
        
        if(event.getSource()==registerbtn){
        studTxtdata=studTxt.getText();
        brandtxtData=brandtxt.getText();
        serialTxtData=serialTxt.getText();
        gidTxtData=gidTxt.getText();
    

        if(!studTxtdata.isEmpty() && !gidTxtData.equals("") && !brandtxtData.isEmpty()&& !serialTxtData.isEmpty()){
                Connection con =null;
                PreparedStatement p = null;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/laptop checkout","root", "Admin123");
                String sql = "INSERT INTO laptop_table(`student_id`, `laptop_brand`, `serial_number`,`staff_id`) VALUES (?,?,?,?)";
                SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");  
                Date date = new Date();  
                formatter.format(date);  
                p = con.prepareStatement(sql);
                            p.setInt(1,Integer.parseInt(studTxtdata));
                            p.setString(2,brandtxtData);
                            p.setString(3,serialTxtData);
                            p.setInt(4,Integer.parseInt(gidTxtData));       
                            
                    int affectedRows = p.executeUpdate();
                            if (affectedRows > 0) {
                                    Dialog<String> dialog = new Dialog<String>();
                                    dialog.setTitle("Successful Entry");
                                    ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                                    dialog.setContentText("New Laptop has been added Succesfully");
                                    dialog.getDialogPane().getButtonTypes().add(type);
                                    dialog.showAndWait();
                                    backbtn.fire();
                            }
                }
                 
            catch (SQLException ex) {
                System.out.println(ex.getMessage());
                    }
        }
        else{
            Dialog<String> dialog = new Dialog<String>();
                    dialog.setTitle("Empty Fields");
                    ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                    dialog.setContentText("Feilds can not be empty");
                    dialog.getDialogPane().getButtonTypes().add(type);
                    dialog.showAndWait();
        }
     }
    }
}

    

