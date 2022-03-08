package application;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class newcontactController {

    @FXML
    private DatePicker date;

    @FXML
    private JFXTextField firstnameid;

    @FXML
    private JFXTextField lastnameid;

  


    
    @FXML
    void back(ActionEvent event) throws IOException {
    	Pane	adminparent = FXMLLoader.load(getClass().getResource("interface.fxml"));
        Scene adminparentScene = new Scene(adminparent);
        adminparentScene.getStylesheets().addAll(Main.class.getResource("application.css").toExternalForm());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(adminparentScene);
        window.show();
	}
    @FXML
    void submit(ActionEvent event) {
 GuiAppController a=new GuiAppController();
    	String sql = "INSERT INTO contacts VALUES('%s','%s','%s','%s','%s')";
        String statement = String.format(sql,
                firstnameid.getText(),
                lastnameid.getText(),
                date.getValue().toString(),
                a.usernameloggedin,
                a.lastnameloggedin
                );
        
        try {
            
            String url = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12339199";
			String username = "sql12339199";
			String password = "CT93ij2vnM";
			Connection conn;
			conn = DriverManager.getConnection(url, username,
					password);
            Statement stmnt = conn.createStatement();
       
            stmnt.executeUpdate(statement);

            Exception b = new Exception("Success");
            StringWriter sw = new StringWriter();
            b.printStackTrace(new PrintWriter(sw));
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("Contact Added Successfully");
            alert.showAndWait();
           return;

          

        } catch (Exception e) {
        	  Exception b = new Exception("Error");
              StringWriter sw = new StringWriter();
              b.printStackTrace(new PrintWriter(sw));

              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setHeaderText("You already contacted this person!");

              alert.showAndWait();
            return;
        }

    	
    }

}
