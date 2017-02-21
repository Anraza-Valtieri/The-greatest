package thegreatest;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class studentTakeQuizController implements Initializable {


	@FXML private MenuItem stQuiz_logout;
	@FXML private Button stQuiz_homeBtn;
	@FXML private Label stq_quizName;
	@FXML private Button btn_student_submitquiz;
	@FXML private Label label_student_qnno_mcq;
	@FXML private Label label_student_qnno_tf;
	@FXML private Label label_student_qnno_sa;
	@FXML private RadioButton radio_student_mcq1;
	@FXML private RadioButton radio_student_mcq2;
	@FXML private RadioButton radio_student_mcq3;
	@FXML private RadioButton radio_student_mcq4;
	@FXML private Label label_student_qnno;
	@FXML private RadioButton radio_student_true;
	@FXML private RadioButton radio_student_false;
	@FXML private VBox stq_dataVBox;
	@FXML TextField textarea_student_ans;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		stQuiz_logout.setOnAction(new EventHandler<ActionEvent>() {
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

		stQuiz_homeBtn.setOnAction(new EventHandler<ActionEvent>() {
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

		if (main.quiz.getQuiz_id() != -1){
			main.quiz.getQuizDBName();
			stq_quizName.setText(main.quiz.getQuizname());

			stq_dataVBox.setSpacing(10);

			VBox test;

			test = new VBox(5);
			test.getChildren().addAll(studentTakeQuizController.createLabel(), studentTakeQuizController.createRBtn(), studentTakeQuizController.createTField());

			stq_dataVBox.getChildren().add(test);

			test = new VBox(5);
			test.getChildren().addAll(studentTakeQuizController.createLabel(), studentTakeQuizController.createRBtn(), studentTakeQuizController.createTField());
			stq_dataVBox.getChildren().add(test);
		}

		
	}


	private static Label createLabel(){
		Label text = new Label();
		text.setText("Test ");

		return text;
	}

	private static RadioButton createRBtn(){
		RadioButton rbtn = new RadioButton();
		rbtn.setText("RBTN TEST");

		return rbtn;
	}

	private static TextField createTField(){
		TextField txtfield = new TextField();
		txtfield.setPromptText("LOL TEST");

		return txtfield;
	}

}
