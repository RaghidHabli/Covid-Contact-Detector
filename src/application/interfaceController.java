package application;

import java.awt.Desktop;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.jfoenix.controls.JFXTextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class interfaceController implements Initializable {

    @FXML
    private TableColumn col_date;

    @FXML
    private TableColumn col_firstname;

    @FXML
    private TableColumn col_lastname;

    @FXML
    private TableColumn col_health;
    @FXML
    private JFXTextField info;
    @FXML
    private JFXTextField info1;

    @FXML
    private TableView table;
    int y=0;
    String email=c.emailloggedin;
    static GuiAppController c = new GuiAppController();
    
    public void initialize(URL location, ResourceBundle resources) {
        Connector clientRequest = new Connector();
        info1.setText("Name: "+c.usernameloggedin+ " "+c.lastnameloggedin);
        info.setText("Email: "+email);
    	 
    }
   
    @FXML
    void goToEmergency(ActionEvent event) throws IOException {
    	
    	Exception e = new Exception("Calm Down");
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("You are being redirected to our emergency center website");
        alert.setContentText("Please follow the following instructions for your own safety!");
        alert.show();
        try {
			Thread.sleep(4000);
			
			
		} catch(Exception b) {
		}
    	alert.close();
    	String url = "https://staysafefromcorona.weebly.com/emergency-center.html";
        java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
    	
    }
    @FXML
    void addNewContact(ActionEvent event) throws IOException {
    	Pane	adminparent = FXMLLoader.load(getClass().getResource("newcontact.fxml"));
        Scene adminparentScene = new Scene(adminparent);
        adminparentScene.getStylesheets().addAll(Main.class.getResource("application.css").toExternalForm());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(adminparentScene);
        window.show();
    }
    @FXML
    void Back(ActionEvent event) throws IOException {
    	Pane	adminparent = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        Scene adminparentScene = new Scene(adminparent);
        adminparentScene.getStylesheets().addAll(Main.class.getResource("application.css").toExternalForm());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(adminparentScene);
        window.show();
	}
    

    @FXML
    void check(ActionEvent event) throws AddressException, MessagingException  {
    		String sql = "SELECT * FROM Patients";
    	

          try {

        	
              String url = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12339199";
  			String username = "sql12339199";
  			String password = "CT93ij2vnM";
  			Connection conn;
  			conn = DriverManager.getConnection(url, username,
  					password);
              Statement stmnt = conn.createStatement();
              ResultSet resultSet = stmnt.executeQuery(sql);
              GuiAppController b = new GuiAppController();
              String sql1 = "SELECT * FROM contacts WHERE HealthyFirstName="+"\"" +b.usernameloggedin+"\"" + " AND " + "HealtyLastName="+"\"" +b.lastnameloggedin+"\"";

              Statement stmnt1 = conn.createStatement();
                  ResultSet resultSet1= stmnt1.executeQuery(sql1);
boolean warning=false;
String health="Healthy"; 

ObservableList<Contacts_Values> l = FXCollections.observableArrayList();
              while (resultSet1.next() ) {
            	  resultSet = stmnt.executeQuery(sql);
            	  while(resultSet.next()) {
            		
            		  if(resultSet1.getString("ContactFirstName").equals(resultSet.getString("PatientFirstName")) && resultSet1.getString("ContactLastName").equals(resultSet.getString("PatientLastName"))) {
            			warning=true; 
            			y++;
            			if (warning=true) health="corona" ;
            			else health="Healthy";
            			 l.add(new Contacts_Values(
                         		resultSet1.getString("ContactFirstName"),
                                 resultSet1.getString("ContactLastName"),
                                 resultSet1.getString("day"),
                                health));
            			 warning=false;
            			 
            		  }
            		  
            	  }
              }
          
 col_firstname.setCellValueFactory(new PropertyValueFactory<Contacts_Values, String>("contactfirstname"));
              col_lastname.setCellValueFactory(new PropertyValueFactory<>("contactlastname"));
              col_date.setCellValueFactory(new PropertyValueFactory<Contacts_Values, String>("day"));
              col_health.setCellValueFactory(new PropertyValueFactory<Contacts_Values, String>("health"));
              
              table.setItems(l);}

             
          catch (Exception e) {
              e.printStackTrace();
              return;}    
          
          
          if(y==0) {
        	  Exception e = new Exception("Good News");
              StringWriter sw = new StringWriter();
              e.printStackTrace(new PrintWriter(sw));

              Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
              alert.setHeaderText("Good News! Non of your contacts are infected");
            
              alert.showAndWait();
          }
    	
          GuiAppController a=new GuiAppController();
              if(y!=0) {
            		String sql2 = "INSERT INTO Patients VALUES('%s','%s')";


            	    String statement = String.format(sql2,
            	    		a.usernameloggedin,
                            a.lastnameloggedin
            	           );
            	     
            	            
            	        


            	    try {
            	    	String url = "jdbc:mysql://sql12.freesqldatabase.com:3306/sql12339199";
              			String username = "sql12339199";
              			String password = "CT93ij2vnM";
              			Connection conn2;
              			conn2 = DriverManager.getConnection(url, username,
              					password);
            	        Statement stmnt2 = conn2.createStatement();
            	      
            	        stmnt2.executeUpdate(statement);

            	        Exception e = new Exception("Warning");
                        StringWriter sw = new StringWriter();
                        e.printStackTrace(new PrintWriter(sw));

                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Warning! It seems that you have contacted an infected user");
                        alert.setContentText("Please visit our emergency section by clicking on the button below");
                        alert.showAndWait();
 
               

            	    } catch (Exception e) {
            	    	 Exception d = new Exception("Warning");
            	            StringWriter sw = new StringWriter();
            	            d.printStackTrace(new PrintWriter(sw));

            	            Alert alert = new Alert(Alert.AlertType.WARNING);
            	            alert.setHeaderText("You are already a patient!");

            	            alert.showAndWait();
            	        return;
            	    }
              
            	    Properties emailProperties;
            		Session mailSession;
            		MimeMessage emailMessage;

            		
            			JavaEmail javaEmail = new JavaEmail();

            			javaEmail.setMailServerProperties();
            			javaEmail.createEmailMessage();
            			javaEmail.sendEmail();
            		

            		

            			String emailPort = "587";//gmail's smtp port

            			emailProperties = System.getProperties();
            			emailProperties.put("mail.smtp.port", emailPort);
            			emailProperties.put("mail.smtp.auth", "true");
            			emailProperties.put("mail.smtp.starttls.enable", "true");

            		

            	{
            		
            		
            			String[] toEmails = { email};
            			String emailSubject = "Corona Virus Tracker Application";
            			String emailBody = "Attention! You have contacted somebody with corona virus, please login the app for more information!";

            			mailSession = Session.getDefaultInstance(emailProperties, null);
            			emailMessage = new MimeMessage(mailSession);

            			for (int i = 0; i < toEmails.length; i++) {
            				emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
            			}

            			emailMessage.setSubject(emailSubject);
            			emailMessage.setContent(emailBody, "text/html");//for a html email
            			//emailMessage.setText(emailBody);// for a text email

            		
            		

            			String emailHost = "smtp.gmail.com";
            			String fromUser = "remixhabli";//just the id alone without @gmail.com
            			String fromUserEmailPassword = "raghidhanihabli70814394";

            			Transport transport = mailSession.getTransport("smtp");

            			transport.connect(emailHost, fromUser, fromUserEmailPassword);
            			transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
            			transport.close();
            		
            				
              
            	}
              
               
              
}}}
