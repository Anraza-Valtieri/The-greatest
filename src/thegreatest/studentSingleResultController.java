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
import java.util.regex.Pattern;
import javax.print.DocFlavor.URL;
import Model.tableData;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * Created by munpa on 24/2/2017.
 */
public class studentSingleResultController implements Initializable {
    @FXML private MenuButton ssr_profile_menu_btn;
    @FXML private Button ssResult_homeBtn;
    @FXML private Button ssResult_quizBtn;
    @FXML private Button ssResult_resultBtn;
    @FXML private MenuItem ssResult_logout;
    @FXML private VBox ssr_dataVBox;

    @Override
    public void initialize(java.net.URL location, ResourceBundle resources) {
        ssr_profile_menu_btn.setText(main.login.getName());

        ssResult_logout.setOnAction(new EventHandler<ActionEvent>() {
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

        ssResult_homeBtn.setOnAction(new EventHandler<ActionEvent>() {
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

        ssResult_quizBtn.setOnAction(new EventHandler<ActionEvent>() {
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

        ssResult_resultBtn.setOnAction(new EventHandler<ActionEvent>() {
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

        if (main.results.getResultID() != -1) {
            main.results = main.results.getSingleResult();
            String[] questionSet = main.results.getQuestion().split(Pattern.quote("/*/*/"));
            String[] stuAnswerSet = main.results.getInputAnswer().split(Pattern.quote("/*/*/"));
            String[] actAnswerSet = main.results.getActualAnswer().split(Pattern.quote("/*/*/"));
            String[] indvMarks = main.results.getIndvmark().split(Pattern.quote("/*/*/"));

            ssr_dataVBox.setSpacing(10);

            ssr_dataVBox.getChildren().add(studentSingleResultController.createMarks(main.results.getMarkObtained(), main.results.getTotalMarks()));

            for (int i = 0; i < questionSet.length; i++){
                ssr_dataVBox.getChildren().add(studentSingleResultController.createResultPart(questionSet[i], stuAnswerSet[i], actAnswerSet[i], indvMarks[i]));
            }



        }

    }

    private static Label createLabel(String textString){
        Label text = new Label();
        text.setText(textString);

        return text;
    }

    private static VBox createResultPart(String questionText, String inputText, String answerText, String indvMark){
        VBox result = new VBox(5);
        result.getChildren().addAll(studentSingleResultController.createLabel(questionText), studentSingleResultController.createLabel(""), studentSingleResultController.createLabel("Answer: " + answerText), studentSingleResultController.createLabel("Your input: " + inputText),
                studentSingleResultController.createLabel("Marks obtained: " + indvMark), studentSingleResultController.createLabel("_ _ _ _ _ _ _ _ _ _ _ _ _ _ _"));
        return result;
    }

    private static VBox createMarks(String obtainedMark, String totalMark){
        VBox result = new VBox(5);
        result.getChildren().addAll(studentSingleResultController.createLabel("Marks: " + obtainedMark + " / " + totalMark), studentSingleResultController.createLabel("_____________________________"));
        return result;
    }

}
