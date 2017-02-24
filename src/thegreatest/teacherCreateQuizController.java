package thegreatest;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.ResourceBundle;
import javax.print.DocFlavor.URL;

import Model.tableData;
//import com.sun.tools.javac.comp.Check;
import javafx.collections.FXCollections;
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
import javafx.stage.Stage;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class teacherCreateQuizController implements Initializable {
	@FXML private MenuButton tcq_profile_menu_btn;
	@FXML private MenuItem tc_logout;
	@FXML private Button tc_homeBtn;
	@FXML private VBox vbox_teacher_qnplain;
	@FXML private VBox vbox_teacher_qnwrap;
	@FXML private VBox vbox_teacher_qnwrap_mcq;
	@FXML private VBox vbox_teacher_qnwrap_tf;
	@FXML private VBox vbox_teacher_qnwrap_sa;
	@FXML private Button btn_addmcq;
	@FXML private Button btn_addtf;
	@FXML private Button btn_addsa;
	@FXML private Label label_teacher_createquiz_mcq_qnno;
	@FXML private Label label_teacher_createquiz_tf_qnno;
	@FXML private Label label_teacher_createquiz_sa_qnno;

	@FXML private TextField txtfield_createquiz_mcq;
	@FXML private CheckBox cb1_createquiz;
	@FXML private TextField txtfield_createquiz_mcq1;
	@FXML private CheckBox cb2_createquiz;
	@FXML private TextField txtfield_createquiz_mcq2;
	@FXML private CheckBox cb3_createquiz;
	@FXML private TextField txtfield_createquiz_mcq3;
	@FXML private CheckBox cb4_createquiz;
	@FXML private TextField txtfield_createquiz_mcq4;
	@FXML private TextField txtbx_createquiz_mcq_marks;
	@FXML private Button btn_createquiz_mcq;

	@FXML private TextField txtfield_createquiz_tf;
	@FXML private RadioButton txtfield_createquiz_tf_1;
	@FXML private RadioButton txtfield_createquiz_tf_2;
	@FXML private TextField txtbx_createquiz_tf_marks;
	@FXML private Button btn_createquiz_tf;

	@FXML private TextField txtfield_createquiz_sa;
	@FXML private TextField txtfield_createquiz_saAns;
	@FXML private TextField txtbx_createquiz_sa_marks;
	@FXML private Button btn_createquiz_sa;

	@FXML private Button teacher_createquiz_createbtn;
	@FXML private TextField txtbx_teacher_createquiz_quiztitle;
	private DbConnection dc;

	@FXML
	public void changetoMCQ(ActionEvent event){
		vbox_teacher_qnwrap_mcq.setVisible(true);
		vbox_teacher_qnwrap_tf.setVisible(false);
		vbox_teacher_qnwrap_sa.setVisible(false);
		label_teacher_createquiz_mcq_qnno.setText("Question " + Integer.toString(main.qnno));
		label_teacher_createquiz_tf_qnno.setText("Question " + Integer.toString(main.qnno));
		label_teacher_createquiz_sa_qnno.setText("Question " + Integer.toString(main.qnno));
//		main.qnno += 1;
//		label_teacher_createquiz_mcq_qnno.setText("Question " + Integer.toString(main.qnno));
	}
	@FXML
	public void changetoTF(ActionEvent event){
		vbox_teacher_qnwrap_mcq.setVisible(false);
		vbox_teacher_qnwrap_tf.setVisible(true);
		vbox_teacher_qnwrap_sa.setVisible(false);
		label_teacher_createquiz_mcq_qnno.setText("Question " + Integer.toString(main.qnno));
		label_teacher_createquiz_tf_qnno.setText("Question " + Integer.toString(main.qnno));
		label_teacher_createquiz_sa_qnno.setText("Question " + Integer.toString(main.qnno));
//		main.qnno += 1;
//		label_teacher_createquiz_tf_qnno.setText("Question " + Integer.toString(main.qnno));
	}
	@FXML
	public void changetoSA(ActionEvent event){
		vbox_teacher_qnwrap_mcq.setVisible(false);
		vbox_teacher_qnwrap_tf.setVisible(false);
		vbox_teacher_qnwrap_sa.setVisible(true);
		label_teacher_createquiz_mcq_qnno.setText("Question " + Integer.toString(main.qnno));
		label_teacher_createquiz_tf_qnno.setText("Question " + Integer.toString(main.qnno));
		label_teacher_createquiz_sa_qnno.setText("Question " + Integer.toString(main.qnno));
//		main.qnno += 1;
//		label_teacher_createquiz_sa_qnno.setText("Question " + Integer.toString(main.qnno));
	}

	@FXML
	public void AddQuizName(ActionEvent event){

		if (txtbx_teacher_createquiz_quiztitle.getText() == ""){
			return;
		}

		Quiz q = new Quiz();
		if(q.checkExist(txtbx_teacher_createquiz_quiztitle.getText()) == false){
			q.setQuizname(txtbx_teacher_createquiz_quiztitle.getText());
			q.setSubject(txtbx_teacher_createquiz_quiztitle.getText());
			q.setQuestionids("0");
			q.createquiz();
			txtbx_teacher_createquiz_quiztitle.setDisable(true);
			teacher_createquiz_createbtn.setDisable(true);
			main.qnno = 1;

			tc_homeBtn.setDisable(true);
			tc_logout.setDisable(true);
			btn_addmcq.setDisable(false);
			btn_addtf.setDisable(false);
			btn_addsa.setDisable(false);
		}

	}

	@FXML
	public void createQuestion(ActionEvent event){
		if(vbox_teacher_qnwrap_mcq.isVisible()){
			Questions q = new Questions();
			q.setQuestion_id(main.qnno);
			q.setTeacher_id(main.login.getUniqID());
			q.setSubject(main.quizName);
			if(txtfield_createquiz_mcq.getText().trim().isEmpty())
				return;
			q.setQuestion_text(txtfield_createquiz_mcq.getText());
			q.setQuestion_type(0);
			if(txtfield_createquiz_mcq1.getText().trim().isEmpty() || txtfield_createquiz_mcq2.getText().trim().isEmpty()
					|| txtfield_createquiz_mcq3.getText().trim().isEmpty() || txtfield_createquiz_mcq4.getText().trim().isEmpty())
				return;
			q.setData1(txtfield_createquiz_mcq1.getText());
			q.setData2(txtfield_createquiz_mcq2.getText());
			q.setData3(txtfield_createquiz_mcq3.getText());
			q.setData4(txtfield_createquiz_mcq4.getText());
			String CBoption = "";
			if(cb1_createquiz.isSelected() && !txtfield_createquiz_mcq1.getText().trim().isEmpty())
				CBoption += "1";
			if(cb2_createquiz.isSelected() && !txtfield_createquiz_mcq2.getText().trim().isEmpty())
				CBoption += "2";
			if(cb3_createquiz.isSelected() && !txtfield_createquiz_mcq3.getText().trim().isEmpty())
				CBoption += "3";
			if(cb4_createquiz.isSelected() && !txtfield_createquiz_mcq4.getText().trim().isEmpty())
				CBoption += "4";
			if(CBoption.isEmpty())
				return;
			q.setData5(CBoption);
			int marks = Integer.parseInt(txtbx_createquiz_mcq_marks.getText());
			if(marks > 0)
				q.setMarks(marks);
			else
				q.setMarks(1);

			q.createQuestion();
		}

		if(vbox_teacher_qnwrap_tf.isVisible()){
			Questions q = new Questions();
			q.setQuestion_id(main.qnno);
			q.setTeacher_id(main.login.getUniqID());
			q.setSubject(main.quizName);
			if(txtfield_createquiz_tf.getText().trim().isEmpty())
				return;
			q.setQuestion_text(txtfield_createquiz_tf.getText());
			q.setQuestion_type(1);
			q.setData1(txtfield_createquiz_tf_1.getText());
			q.setData2(txtfield_createquiz_tf_2.getText());
			q.setData3("");
			q.setData4("");
			String CBoption = "";
			//CBoption += txtfield_createquiz_tf_1.isSelected() ? "1" : "";
			//CBoption += txtfield_createquiz_tf_2.isSelected() ? "2" : "";

			if(txtfield_createquiz_tf_1.isSelected() && !txtfield_createquiz_tf_2.isSelected())
				CBoption = "1";

			if(txtfield_createquiz_tf_2.isSelected() && !txtfield_createquiz_tf_1.isSelected())
				CBoption = "2";

			if(CBoption.isEmpty())
				return;
			q.setData5(CBoption);
			int marks = Integer.parseInt(txtbx_createquiz_tf_marks.getText());
			if(marks > 0)
				q.setMarks(marks);
			else
				q.setMarks(1);

			q.createQuestion();

		}
		if(vbox_teacher_qnwrap_sa.isVisible()){
			Questions q = new Questions();
			q.setQuestion_id(main.qnno);
			q.setTeacher_id(main.login.getUniqID());
			q.setSubject(main.quizName);
			if(txtfield_createquiz_sa.getText().trim().isEmpty())
				return;
			q.setQuestion_text(txtfield_createquiz_sa.getText());
			q.setQuestion_type(2);
			q.setData1("");
			q.setData2("");
			q.setData3("");
			q.setData4("");
			if(txtfield_createquiz_saAns.getText().trim().isEmpty())
				return;
			q.setData5(txtfield_createquiz_saAns.getText());
			int marks = Integer.parseInt(txtbx_createquiz_sa_marks.getText());
			if(marks > 0)
				q.setMarks(marks);
			else
				q.setMarks(1);

			q.createQuestion();
		}
		main.qnno += 1;
		label_teacher_createquiz_mcq_qnno.setText("Question " + Integer.toString(main.qnno));
		label_teacher_createquiz_tf_qnno.setText("Question " + Integer.toString(main.qnno));
		label_teacher_createquiz_sa_qnno.setText("Question " + Integer.toString(main.qnno));
		tc_homeBtn.setDisable(false);
		tc_logout.setDisable(false);
	}

	public int getMaxQuestionID(String subject)  {
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement statement = null;
		String query = "SELECT * FROM quiz_questions WHERE subject='" + subject+"'";
		dc = new DbConnection();
		try {
			connection = dc.Connect();
			statement = connection.prepareStatement(query);
			rs = statement.executeQuery(query);
			int x = 0;
			while (rs.next()) {
				//main.quizName = subject;
				x = rs.getInt(1);
			}
			return x;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		tcq_profile_menu_btn.setText(main.login.getName());
		if(main.quizName != "") {
			txtbx_teacher_createquiz_quiztitle.setText(main.quizName);
			txtbx_teacher_createquiz_quiztitle.setDisable(true);
			teacher_createquiz_createbtn.setDisable(true);
			main.qnno = getMaxQuestionID(main.quizName)+1;

			tc_homeBtn.setDisable(true);
			tc_logout.setDisable(true);
			btn_addmcq.setDisable(false);
			btn_addtf.setDisable(false);
			btn_addsa.setDisable(false);
		}
		tc_logout.setOnAction(new EventHandler<ActionEvent>() {
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

		tc_homeBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try{
					Parent parent = FXMLLoader.load(getClass().getResource("/View/teacherViewQuiz.fxml"));
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
