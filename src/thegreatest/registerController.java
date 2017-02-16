package thegreatest;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.w3c.dom.Node;

public class registerController implements Initializable {

    @FXML
    private Button fx_register_back_btn;
    @FXML
    private Button fx_register_register_btn;

    @Override
    public void initialize(java.net.URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        assert fx_register_back_btn != null : "fx:id=\"myButton\" was not injected: check your FXML file 'simple.fxml'.";

        // initialize your logic here: all @FXML variables will have been injected
        fx_register_back_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    Parent parent = FXMLLoader.load(getClass().getResource("View/login.fxml"));
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

        fx_register_register_btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Input Register Code Here");

            }
        });
    }

}

