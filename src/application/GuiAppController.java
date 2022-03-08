package application;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GuiAppController {

    @FXML
    private JFXPasswordField passwordid;

    @FXML
    private JFXTextField firstnameid;

    @FXML
    private JFXTextField lastnameid;

public static String usernameloggedin;
public static String lastnameloggedin;
public static String emailloggedin;




    @FXML
    void Register(ActionEvent event) throws IOException {
    	Pane	adminparent = FXMLLoader.load(getClass().getResource("register.fxml"));
        Scene adminparentScene = new Scene(adminparent);
        adminparentScene.getStylesheets().addAll(Main.class.getResource("application.css").toExternalForm());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(adminparentScene);
        window.show();
        
        
    }

 

 

    @FXML
    void NormalUserLogin(ActionEvent event) throws SQLException, IOException {
    	String sql="SELECT * FROM Healthy_Users WHERE firstname='"+firstnameid.getText()+"' AND password='"+passwordid.getText()+"'";
    	
    	String url = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12339199";
		String username = "sql12339199";
		String password = "CT93ij2vnM";
    	Connection conn;
    	conn = DriverManager.getConnection(url, username,
    			password);
        Statement stmnt = conn.createStatement();
        ResultSet resultSet = stmnt.executeQuery(sql);
    if(!resultSet.first()) {
    	Exception e = new Exception("An exception");
    StringWriter sw = new StringWriter();
    e.printStackTrace(new PrintWriter(sw));

    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setHeaderText("Wrong Username or Password");

    alert.showAndWait();
    	;}


    else {     
    	usernameloggedin=firstnameid.getText();
    	lastnameloggedin=lastnameid.getText();

	String getemail="SELECT email FROM Healthy_Users WHERE FirstName="+"\""+ usernameloggedin+ "\""+ " AND " +"LastName="+"\""+ lastnameloggedin +"\"";
    	
    	String url2 = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12339199";
		String username2 = "sql12339199";
		String password2 = "CT93ij2vnM";
    	Connection conn2;
    	conn2 = DriverManager.getConnection(url2, username2,
    			password2);
        Statement stmnt2 = conn2.createStatement();
        ResultSet resultSet2 = stmnt2.executeQuery(getemail);
        if(resultSet2.next()) {
    	emailloggedin =resultSet2.getString("email");}
       
    	
    	
    	
    	Pane	adminparent = FXMLLoader.load(getClass().getResource("interface.fxml"));
            Scene adminparentScene = new Scene(adminparent);
            adminparentScene.getStylesheets().addAll(Main.class.getResource("application.css").toExternalForm());
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(adminparentScene);
            window.show();}
      
    }


	
	public void start(Stage primaryStage) throws Exception {
		Connector.getConnection();
		
	}

}
