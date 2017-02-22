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

	@FXML private MenuButton stq_profile_menu_btn;
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
		stq_profile_menu_btn.setText(main.login.getName());
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
			Questions DUMMY = new Questions();
			main.questions = DUMMY.getQuestionsDataBySubject(main.quiz.getQuizname());

			stq_dataVBox.setSpacing(10);

			Button button = new Button("Submit");

			VBox test;
			VBox questionVBox;

			for(int i = 0; i < main.questions.size(); i++){
				if (main.questions.get(i).getQuestion_type()== 0){
					test = studentTakeQuizController.createQuestionMCQ(main.questions.get(i).getQuestion_text(), main.questions.get(i).getData1(),
							main.questions.get(i).getData2(), main.questions.get(i).getData3(), main.questions.get(i).getData4());
					stq_dataVBox.getChildren().add(test);
				}
				else if (main.questions.get(i).getQuestion_type()== 1){
					test = studentTakeQuizController.createQuestionTF(main.questions.get(i).getQuestion_text());
					stq_dataVBox.getChildren().add(test);
				}
				else if (main.questions.get(i).getQuestion_type()== 2){
					test = studentTakeQuizController.createQuestionSA(main.questions.get(i).getQuestion_text());
					stq_dataVBox.getChildren().add(test);
				}
			}

			stq_dataVBox.getChildren().add(button);

			button.setOnAction(new EventHandler<ActionEvent>() {
				@Override public void handle(ActionEvent e) {
					int marks = 0;

					for(int i = 0; i < main.questions.size(); i++){
						if (main.questions.get(i).getQuestion_type() == 0){
							if (studentTakeQuizController.getAnswerMCQ(i, stq_dataVBox, main.questions.get(i).getData5())) {
								marks += main.questions.get(i).getMarks();
							}
						} else if (main.questions.get(i).getQuestion_type() == 1){
							if (studentTakeQuizController.getAnswerTF(i, stq_dataVBox, main.questions.get(i).getData5())) {
								marks += main.questions.get(i).getMarks();
							}
						} else if (main.questions.get(i).getQuestion_type() == 2){
							if (studentTakeQuizController.getAnswerSA(i, stq_dataVBox, main.questions.get(i).getData5())) {
								marks += main.questions.get(i).getMarks();
							}
						}
					}

					System.out.println("Marks: " + marks);
				}
			});



			/*test = new VBox(5);
			test.getChildren().addAll(studentTakeQuizController.createLabel("Test1"), studentTakeQuizController.createRBtn("test1"), studentTakeQuizController.createTField());
			stq_dataVBox.getChildren().add(test);

			test = new VBox(5);
			test.getChildren().addAll(studentTakeQuizController.createLabel("Test2"), studentTakeQuizController.createRBtn("test2"), studentTakeQuizController.createTField());
			stq_dataVBox.getChildren().add(test);

			questionVBox = (VBox) stq_dataVBox.getChildren().get(0);

			if (questionVBox.getChildren().get(0) instanceof Label) {
				System.out.println("Found a Label of a question 1 at pos 0");
				Label text = (Label)questionVBox.getChildren().get(0);
				System.out.println(text.getText());
			}
			if (questionVBox.getChildren().get(1) instanceof Label) {
				System.out.println("Found a Label of a question 1 at pos 1");
				Label text = (Label)questionVBox.getChildren().get(1);
				System.out.println(text.getText());
			}
			if (questionVBox.getChildren().get(2) instanceof Label) {
				System.out.println("Found a Label of a question 1 at pos 2");
				Label text = (Label)questionVBox.getChildren().get(2);
				System.out.println(text.getText());
			}

			questionVBox = (VBox) stq_dataVBox.getChildren().get(1);

			if (questionVBox.getChildren().get(0) instanceof Label) {
				System.out.println("Found a Label of a question 2 at pos 0");
				Label text = (Label)questionVBox.getChildren().get(0);
				System.out.println(text.getText());
			}
			if (questionVBox.getChildren().get(1) instanceof Label) {
				System.out.println("Found a Label of a question 2 at pos 1");
				Label text = (Label)questionVBox.getChildren().get(1);
				System.out.println(text.getText());
			}
			if (questionVBox.getChildren().get(2) instanceof Label) {
				System.out.println("Found a Label of a question 2 at pos 2");
				Label text = (Label)questionVBox.getChildren().get(2);
				System.out.println(text.getText());
			}
*/

		}
	}


	private static Label createLabel(String textString){
		Label text = new Label();
		text.setText(textString);

		return text;
	}

	private static RadioButton createRBtn(String textString){
		RadioButton rbtn = new RadioButton();
		rbtn.setText(textString);

		return rbtn;
	}

	private static TextField createTField(){
		TextField txtfield = new TextField();
		txtfield.setPromptText("Enter your answer here");

		return txtfield;
	}

	private static VBox createQuestionMCQ(String questionText, String mcq1, String mcq2, String mcq3, String mcq4){
		VBox question = new VBox(5);
		question.getChildren().addAll(studentTakeQuizController.createLabel(questionText), studentTakeQuizController.createRBtn(mcq1), studentTakeQuizController.createRBtn(mcq2),
				studentTakeQuizController.createRBtn(mcq3), studentTakeQuizController.createRBtn(mcq4), studentTakeQuizController.createLabel(""));
		return question;
	}

	private static VBox createQuestionTF(String questionText){
		VBox question = new VBox(5);
		question.getChildren().addAll(studentTakeQuizController.createLabel(questionText), studentTakeQuizController.createRBtn("True"), studentTakeQuizController.createRBtn("False"),
				studentTakeQuizController.createLabel(""));
		return question;
	}

	private static VBox createQuestionSA(String questionText) {
		VBox question = new VBox(5);
		question.getChildren().addAll(studentTakeQuizController.createLabel(questionText), studentTakeQuizController.createTField(), studentTakeQuizController.createLabel(""));
		return question;
	}

	private static boolean getAnswerMCQ(int questionNo, VBox stq_dataVBox, String answers) {
		boolean check = false;
		String answer = "";
		RadioButton rbtn;
		VBox questionVBox = (VBox) stq_dataVBox.getChildren().get(questionNo);

		rbtn = (RadioButton) questionVBox.getChildren().get(1);
		if (rbtn.isSelected()) {
			answer += "1";
		}
			rbtn = (RadioButton) questionVBox.getChildren().get(2);
		if (rbtn.isSelected()) {
			answer += "2";
		}

		rbtn = (RadioButton) questionVBox.getChildren().get(3);
		if (rbtn.isSelected()) {
			answer += "3";
		}

		rbtn = (RadioButton) questionVBox.getChildren().get(4);
		if (rbtn.isSelected()) {
			answer += "4";
		}

		System.out.println("ANSWER: " + answers + " SELECTED: " + answer);

		if (answer.equals(answers)){
			check = true;
			System.out.println("MCQ GAME GOOD");
		} else {

			System.out.println("MCQ GAME NOT GOOD");
		}


		System.out.println("Marks awarded: " + check);

		return check;
	}

	private static boolean getAnswerTF(int questionNo, VBox stq_dataVBox, String answers) {
		boolean check = false;
		String answer = "";
		RadioButton rbtn;
		VBox questionVBox = (VBox) stq_dataVBox.getChildren().get(questionNo);

		rbtn = (RadioButton) questionVBox.getChildren().get(1);
		if (rbtn.isSelected()) {
			answer += "1";
		}
		rbtn = (RadioButton) questionVBox.getChildren().get(2);
		if (rbtn.isSelected()) {
			answer += "2";
		}

		if (answer.equals(answers)){
			check = true;
			System.out.println("TF GAME GOOD");
		} else {
			System.out.println("TF GAME NOT GOOD");
		}

		System.out.println("ANSWER: " + answers + " SELECTED: " + answer);
		System.out.println("Marks awarded: " + check);

		return check;
	}

	private static boolean getAnswerSA(int questionNo, VBox stq_dataVBox, String answers){
		boolean check = false;
		VBox questionVBox = (VBox) stq_dataVBox.getChildren().get(questionNo);
		TextField studentAnswer = (TextField) questionVBox.getChildren().get(1);

		String answerToCheck = studentAnswer.getText();

		String[] answerSet = answers.split(",");

		for (int i = 0; i < answerSet.length; i++){
			if (answerToCheck.toLowerCase().contains(answerSet[i].toLowerCase())) {
				check = true;
				System.out.println("SA GAME GOOD: " + i);
			} else{
				check = false;
				System.out.println("SA GAME NOT GOOD: " + i );
			}
		}

		System.out.println("ANSWER: " + answers + " SELECTED: " + answerToCheck);
		System.out.println("Marks awarded: " + check);

		return check;
	}





}
