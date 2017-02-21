package thegreatest;

import java.io.IOException;
import java.util.ResourceBundle;
import javax.print.DocFlavor.URL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.beans.property.StringProperty;


public class loginController implements Initializable {

    Account login;
    
	 @FXML
	 private Button fx_gotoregister_register_btn;
	 @FXML
	 private Button fx_login_btn;
	 @FXML
	 private TextField fx_txtbox_login_username;
	 @FXML
	 private TextField fx_pwd_login_password;
	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		assert fx_gotoregister_register_btn != null : "fx:id=\"myButton\" was not injected: check your FXML file 'simple.fxml'.";

        // initialize your logic here: all @FXML variables will have been injected
		fx_gotoregister_register_btn.setOnAction(new EventHandler<ActionEvent>() {
	         @Override
	         public void handle(ActionEvent event) {
	        	 try{
	        		 Parent parent = FXMLLoader.load(getClass().getResource("/View/register.fxml"));	        	 
	        		 parent.getStylesheets().add("View/application.css");
	        		 // Use casting to point to specific window
	        		 ((javafx.scene.Node)(event.getSource())).getScene().getWindow().hide();
                     Scene scence = new Scene(parent);
                     main.pStage.setScene(scence);

	        	 } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 	 
	        	/* System.out.println("?!?!?!");*/	
	         }
     });
        fx_login_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int loginType = 0; // 0 Student 1 Teacher 2 Admin
                boolean loginSuccess = false;
                String uniID = fx_txtbox_login_username.getText();
                String pw = fx_pwd_login_password.getText();
                System.out.println("[LOGIN]USER: "+fx_txtbox_login_username.getText());
                System.out.println("[LOGIN]PASS: "+fx_pwd_login_password.getText());

                main.login = new Account();
                main.login.setUniqID(uniID);
                main.login.setPassword(pw);

                loginSuccess = main.login.getLogin(uniID, pw);
                if(loginSuccess) {
                    loginType = main.login.getType();
                    System.out.println("[LOGIN]Account Name: " + main.login.getName());
                    System.out.println("[LOGIN]Account Email: " + main.login.getEmail());
                    System.out.println("[LOGIN]Account UID: " + main.login.getUniqID());
                    System.out.println("[LOGIN]Account AID: " + main.login.getaID());

                    if (loginType == 0) { // Student
                        System.out.println("[LOGIN]Account Flag: student!");
                        try {
                            Parent parent = FXMLLoader.load(getClass().getResource("/View/StudentQuiz.fxml"));
                            parent.getStylesheets().add("View/application.css");
                            // Use casting to point to specific window
                            Scene scence = new Scene(parent);
                            main.pStage.setScene(scence);

                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    if (loginType == 1) { // Teacher
                        System.out.println("[LOGIN]Account flag Teacher!");
                        try {
                            Parent parent = FXMLLoader.load(getClass().getResource("/View/teacherViewQuiz.fxml"));
                            parent.getStylesheets().add("View/application.css");
                            // Use casting to point to specific window
                            Scene scence = new Scene(parent);
                            main.pStage.setScene(scence);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    if (loginType == 2) { // Admin
                        System.out.println("[LOGIN]Account flag Admin!");
                        try {
                            Parent parent = FXMLLoader.load(getClass().getResource("/View/register.fxml"));
                            parent.getStylesheets().add("View/application.css");
                            // Use casting to point to specific window
                            Scene scence = new Scene(parent);
                            main.pStage.setScene(scence);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        
        fx_txtbox_login_username.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int loginType = 0; // 0 Student 1 Teacher 2 Admin
                boolean loginSuccess = false;
                String uniID = fx_txtbox_login_username.getText();
                String pw = fx_pwd_login_password.getText();
                System.out.println("USER: "+fx_txtbox_login_username.getText());
                System.out.println("PASS: "+fx_pwd_login_password.getText());

                main.login = new Account();
                main.login.setUniqID(uniID);
                main.login.setPassword(pw);

                loginSuccess = main.login.getLogin(uniID, pw);
                if(loginSuccess) {
                    loginType = main.login.getType();
                    System.out.println("Account Name: " + main.login.getName());
                    System.out.println("Account Email: " + main.login.getEmail());
                    System.out.println("Account UID: " + main.login.getUniqID());
                    System.out.println("Account AID: " + main.login.getaID());

                    if (loginType == 0) { // Student
                        System.out.println("Account Flag: student!");
                        try {
                            Parent parent = FXMLLoader.load(getClass().getResource("/View/studentQuiz.fxml"));
                            parent.getStylesheets().add("View/application.css");
                            // Use casting to point to specific window
                            Scene scence = new Scene(parent);
                            main.pStage.setScene(scence);

                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    if (loginType == 1) { // Teacher
                        System.out.println("Account flag Teacher!");
                        try {
                            Parent parent = FXMLLoader.load(getClass().getResource("/View/teacherViewQuiz.fxml"));
                            parent.getStylesheets().add("View/application.css");
                            // Use casting to point to specific window
                            Scene scence = new Scene(parent);
                            main.pStage.setScene(scence);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    if (loginType == 2) { // Admin
                        System.out.println("Account flag Admin!");  
                        try {
                            Parent parent = FXMLLoader.load(getClass().getResource("/View/register.fxml"));
                            parent.getStylesheets().add("View/application.css");
                            // Use casting to point to specific window
                            Scene scence = new Scene(parent);
                            main.pStage.setScene(scence);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        
        fx_pwd_login_password.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int loginType = 0; // 0 Student 1 Teacher 2 Admin
                boolean loginSuccess = false;
                String uniID = fx_txtbox_login_username.getText();
                String pw = fx_pwd_login_password.getText();
                System.out.println("USER: "+fx_txtbox_login_username.getText());
                System.out.println("PASS: "+fx_pwd_login_password.getText());

                main.login = new Account();
                main.login.setUniqID(uniID);
                main.login.setPassword(pw);

                loginSuccess = main.login.getLogin(uniID, pw);
                if(loginSuccess) {
                    loginType = main.login.getType();
                    System.out.println("Account Name: " + main.login.getName());
                    System.out.println("Account Email: " + main.login.getEmail());
                    System.out.println("Account UID: " + main.login.getUniqID());
                    System.out.println("Account AID: " + main.login.getaID());

                    if (loginType == 0) { // Student
                        System.out.println("Account Flag: student!");
                        try {
                            Parent parent = FXMLLoader.load(getClass().getResource("/View/studentQuiz.fxml"));
                            parent.getStylesheets().add("View/application.css");
                            // Use casting to point to specific window
                            Scene scence = new Scene(parent);
                            main.pStage.setScene(scence);

                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    if (loginType == 1) { // Teacher
                        System.out.println("Account flag Teacher!");
                        try {
                            Parent parent = FXMLLoader.load(getClass().getResource("/View/teacherViewQuiz.fxml"));
                            parent.getStylesheets().add("View/application.css");
                            // Use casting to point to specific window
                            Scene scence = new Scene(parent);
                            main.pStage.setScene(scence);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    if (loginType == 2) { // Admin
                        System.out.println("Account flag Admin!");  
                        try {
                            Parent parent = FXMLLoader.load(getClass().getResource("/View/register.fxml"));
                            parent.getStylesheets().add("View/application.css");
                            // Use casting to point to specific window
                            Scene scence = new Scene(parent);
                            main.pStage.setScene(scence);
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

    }

}

