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

public class studentTakeQuizController implements Initializable {


	@FXML MenuItem stQuiz_logout;
	@FXML Button stQuiz_homeBtn;
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
		
	}

}
