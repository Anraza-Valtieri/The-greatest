package thegreatest;

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
	 private Button fx_register_register_btn;

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	
		 fx_register_register_btn.setOnAction(new EventHandler<ActionEvent>() {		
	         @Override
	         public void handle(ActionEvent event) {
	        	 System.out.println("Input Register Code Here");
	     		
	         }
	 });
}

}

