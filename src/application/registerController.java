package application;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class registerController {

    @FXML
    private JFXPasswordField passwordid;

    @FXML
    private JFXTextField firstnameid;

    @FXML
    private JFXToggleButton healthyid;

    @FXML
    private JFXTextField emailid;

    @FXML
    private JFXTextField lastnameid;

    @FXML
    void ff1616(ActionEvent event) {

    }
    @FXML
    void Back(ActionEvent event) throws IOException {
    	Pane	adminparent = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        Scene adminparentScene = new Scene(adminparent);
        adminparentScene.getStylesheets().addAll(Main.class.getResource("application.css").toExternalForm());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(adminparentScene);
        window.setResizable(false);
        window.show();
	}
    @FXML
    void submit(ActionEvent event) {
if(healthyid.isSelected()) {
    	String sql = "INSERT INTO Healthy_Users VALUES('%s','%s','%s','%s')";


        String statement = String.format(sql,
                firstnameid.getText(),
                lastnameid.getText(),
                passwordid.getText(),
                emailid.getText());
         
                
            
  

        try {
            System.out.println("Inserting in Database...");
            String url = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12339199";
			String username = "sql12339199";
			String password = "CT93ij2vnM";
			Connection conn;
			conn = DriverManager.getConnection(url, username,
					password);
            Statement stmnt = conn.createStatement();
            System.out.println(statement);
            stmnt.executeUpdate(statement);

            Exception e = new Exception("Success");
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Healthy User Registration is Successful!");

            alert.showAndWait();
            return;

        } catch (Exception e) {
        	 Exception b = new Exception("Error");
             StringWriter sw = new StringWriter();
             b.printStackTrace(new PrintWriter(sw));

             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setHeaderText("This Account is Taken! Try Again");

             alert.showAndWait();
            return;
        }
}

else {
	
	String sql = "INSERT INTO Patients VALUES('%s','%s')";
    String statement = String.format(sql,
            firstnameid.getText(),
            lastnameid.getText())
           ;
     
            
        


    try {
        
        String url = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12339199";
		String username = "sql12339199";
		String password = "CT93ij2vnM";
		Connection conn;
		conn = DriverManager.getConnection(url, username,
				password);
        Statement stmnt = conn.createStatement();
        
        stmnt.executeUpdate(statement);

        Exception e = new Exception("Success");
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Patient User Registration is Successful!");

        alert.showAndWait();

        return;

    } catch (Exception e) {
    	 Exception b = new Exception("Error");
         StringWriter sw = new StringWriter();
         b.printStackTrace(new PrintWriter(sw));

         Alert alert = new Alert(Alert.AlertType.ERROR);
         alert.setHeaderText("This Account is Taken! Try Again");

         alert.showAndWait();
        return;
    }

	
	
	
}
    	
    }

    @FXML
    void healthyaction(ActionEvent event) {

    }


}

