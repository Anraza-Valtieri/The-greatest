package thegreatest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import javax.print.DocFlavor.URL;
import Model.tableData;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;



public class studentQuizController implements Initializable {
	
	
	@FXML Button sQuiz_homeBtn;
	@FXML Button sQuiz_quizBtn;
	@FXML Button sQuiz_viewBtn;
	@FXML MenuItem sQuiz_logout;
	
	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		
		sQuiz_logout.setOnAction(new EventHandler<ActionEvent>() {
	         @Override
	         public void handle(ActionEvent event) {
	        	 try{
	        		 Parent parent = FXMLLoader.load(getClass().getResource("/View/login.fxml"));	        	 
	        		 parent.getStylesheets().add("View/application.css");
	     		
	        		 Scene scence = new Scene(parent);
	        		 //Stage stage = (Stage) createQ.getScene().getWindow();
	        		 main.pStage.setScene(scence);
	        		 
	        	 } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 	 
	        	/* System.out.println("?!?!?!");*/	
	         }
       });
		
		sQuiz_homeBtn.setOnAction(new EventHandler<ActionEvent>() {
	         @Override
	         public void handle(ActionEvent event) {
	        	 try{
	        		 Parent parent = FXMLLoader.load(getClass().getResource("/View/studentQuiz.fxml"));	        	 
	        		 parent.getStylesheets().add("View/application.css");
	     		
	        		 Scene scence = new Scene(parent);
	        		 //Stage stage = (Stage) createQ.getScene().getWindow();
	        		 main.pStage.setScene(scence);
	        		 
	        	 } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 	 
	        	/* System.out.println("?!?!?!");*/	
	         }
        });
		
		sQuiz_quizBtn.setOnAction(new EventHandler<ActionEvent>() {
	         @Override
	         public void handle(ActionEvent event) {
	        	 try{
	        		 Parent parent = FXMLLoader.load(getClass().getResource("/View/studentTakeQuiz.fxml"));	        	 
	        		 parent.getStylesheets().add("View/application.css");
	     		
	        		 Scene scence = new Scene(parent);
	        		 //Stage stage = (Stage) createQ.getScene().getWindow();
	        		 main.pStage.setScene(scence);
	        		 
	        	 } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 	 
	        	/* System.out.println("?!?!?!");*/	
	         }
       });
		
		sQuiz_viewBtn.setOnAction(new EventHandler<ActionEvent>() {
	         @Override
	         public void handle(ActionEvent event) {
	        	 try{
	        		 Parent parent = FXMLLoader.load(getClass().getResource("/View/studentResult.fxml"));	        	 
	        		 parent.getStylesheets().add("View/application.css");
	     		
	        		 Scene scence = new Scene(parent);
	        		 //Stage stage = (Stage) createQ.getScene().getWindow();
	        		 main.pStage.setScene(scence);
	        		 
	        	 } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 	 
	        	/* System.out.println("?!?!?!");*/	
	         }
       });
	}
}
