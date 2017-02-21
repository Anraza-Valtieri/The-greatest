package thegreatest;

//import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.Initializable;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.geometry.Rectangle2D;

public class main extends Application {
	// Global variable
	public static int qnno = 1;
	public static String quizName = "";
	public static Stage pStage;
	public static Account login;
	public static Quiz quiz;
	public static ArrayList<Questions> questions;

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/View/login.fxml"));
		root.getStylesheets().add("View/application.css");
		pStage = primaryStage;
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("Learn Java Program");
		primaryStage.show();
		Rectangle2D screenbounds = Screen.getPrimary().getVisualBounds();
		primaryStage.setX(screenbounds.getWidth()/8);
		primaryStage.setY(screenbounds.getHeight()/8);
		primaryStage.setMinHeight(800);
		primaryStage.setMinWidth(1280);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
