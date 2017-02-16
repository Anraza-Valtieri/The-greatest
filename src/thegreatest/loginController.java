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

import org.w3c.dom.Node;

public class loginController implements Initializable {

    @FXML
    private Button fx_gotoregister_register_btn;
    @FXML
    private Button fx_login_btn;
    @FXML
    private TextField txtbox_login_username;
    @FXML
    private  TextField pwd_login_password;

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
                    Stage stage = new Stage();
                    stage.setScene(scence);
                    stage.show();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
	        	/* System.out.println("?!?!?!");*/
            }
        });

        /*fx_login_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String[] nextButtonArray = new String[100];
                System.out.println(txtbox_login_username.textProperty().get());
                /*try{

                    Parent parent = FXMLLoader.load(getClass().getResource("/View/teacherCreateQuiz.fxml"));
                    parent.getStylesheets().add("View/application.css");
                    // Use casting to point to specific window
                    ((javafx.scene.Node)(event.getSource())).getScene().getWindow().hide();


                    Scene scence = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scence);
                    stage.show();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("Input Login Code Here");

            }
        });*/
    }

    @FXML
    protected void doSomething() {
        System.out.println("The button was clicked!");
        int loginType = 0; // 0 Student 1 Teacher 2 Admin
        boolean loginSuccess = false;
        String uniID = txtbox_login_username.getText();
        String pw = pwd_login_password.getText();
        System.out.println("USER: "+txtbox_login_username.getText());
        System.out.println("PASS: "+pwd_login_password.getText());

        Account login = new Account();
        login.setUniqID(uniID);
        login.setPassword(pw);

        loginSuccess = login.getLogin(uniID, pw);
        if(loginSuccess) {
            loginType = login.getType();
            System.out.println("Account Name: " + login.getName());
            System.out.println("Account Email: " + login.getEmail());
            System.out.println("Account UID: " + login.getUniqID());
            System.out.println("Account AID: " + login.getaID());

            if (loginType == 0) { // Student
                System.out.println("Account Flag: student!");
                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("/View/studentTakeQuiz.fxml"));
                    parent.getStylesheets().add("View/application.css");
                    // Use casting to point to specific window
                    txtbox_login_username.getScene().getWindow().hide();
                    Scene scence = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scence);
                    stage.show();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (loginType == 1) { // Teacher
                System.out.println("Account flag Teacher!");
                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("/View/teacherCreateQuiz.fxml"));
                    parent.getStylesheets().add("View/application.css");
                    // Use casting to point to specific window
                    txtbox_login_username.getScene().getWindow().hide();
                    Scene scence = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scence);
                    stage.show();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            if (loginType == 2) { // Admin
                System.out.println("Account flag Admin!");
                System.out.println("Account flag Teacher!");
                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("/View/register.fxml"));
                    parent.getStylesheets().add("View/application.css");
                    // Use casting to point to specific window
                    txtbox_login_username.getScene().getWindow().hide();
                    Scene scence = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setScene(scence);
                    stage.show();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public String getText() {
        return textProperty().get();
    }

    public void setText(String value) {
        textProperty().set(value);
    }

    public StringProperty textProperty() {
        return txtbox_login_username.textProperty();
    }
}

