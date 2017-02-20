package Controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;

public class studentTakeQuizController implements Initializable {
	
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
	@FXML TextArea textarea_student_ans;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
