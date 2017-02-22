package thegreatest;

import Model.tableData2;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

//import com.sun.tools.javac.comp.Check;

public class teacherEditQnsController implements Initializable {
	@FXML private MenuButton tequ_profile_menu_btn;
	@FXML private MenuItem tequ_logout;
	@FXML private Button tequ_homeBtn;
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
		btn_addmcq.setDisable(false);
		btn_addtf.setDisable(false);
		btn_addsa.setDisable(false);
		Quiz q = new Quiz();
		q.setQuizname(txtbx_teacher_createquiz_quiztitle.getText());
		q.setSubject(txtbx_teacher_createquiz_quiztitle.getText());
		q.setQuestionids("0");
		q.createquiz();
		txtbx_teacher_createquiz_quiztitle.setDisable(true);
		teacher_createquiz_createbtn.setDisable(true);
		main.qnno = 1;
	}

	@FXML
	public void createQuestion(ActionEvent event){
		if(vbox_teacher_qnwrap_mcq.isVisible()){
			Questions q = new Questions();
			int qid = Integer.parseInt(main.quizId.toString());
			q.setQuestion_id(qid);
			q.setTeacher_id(main.login.getUniqID());
			q.setSubject(main.quizName);
			q.setQuestion_text(txtfield_createquiz_mcq.getText());
			q.setQuestion_type(0);
			q.setData1(txtfield_createquiz_mcq1.getText());
			q.setData2(txtfield_createquiz_mcq2.getText());
			q.setData3(txtfield_createquiz_mcq3.getText());
			q.setData4(txtfield_createquiz_mcq4.getText());
			String CBoption = "";
			CBoption += cb1_createquiz.isSelected() ? "1" : "";
			CBoption += cb2_createquiz.isSelected() ? "2" : "";
			CBoption += cb3_createquiz.isSelected() ? "3" : "";
			CBoption += cb4_createquiz.isSelected() ? "4" : "";
			q.setData5(CBoption);
			int marks = Integer.parseInt(txtbx_createquiz_mcq_marks.getText());
			if(marks > 0)
				q.setMarks(marks);
			else
				q.setMarks(1);

			q.updateQuestion();
		}

		if(vbox_teacher_qnwrap_tf.isVisible()){
			Questions q = new Questions();
			int qid = Integer.parseInt(main.quizId.toString());
			q.setQuestion_id(qid);
			q.setTeacher_id(main.login.getUniqID());
			q.setSubject(main.quizName);
			q.setQuestion_text(txtfield_createquiz_tf.getText());
			q.setQuestion_type(1);
			q.setData1(txtfield_createquiz_tf_1.getText());
			q.setData2(txtfield_createquiz_tf_2.getText());
			q.setData3("");
			q.setData4("");
			String CBoption = "";
			CBoption += txtfield_createquiz_tf_1.isSelected() ? "1" : "";
			CBoption += txtfield_createquiz_tf_2.isSelected() ? "2" : "";
			q.setData5(CBoption);
			int marks = Integer.parseInt(txtbx_createquiz_tf_marks.getText());
			if(marks > 0)
				q.setMarks(marks);
			else
				q.setMarks(1);

			q.updateQuestion();

		}
		if(vbox_teacher_qnwrap_sa.isVisible()) {
			Questions q = new Questions();
			int qid = Integer.parseInt(main.quizId.toString());
			q.setQuestion_id(qid);
			q.setTeacher_id(main.login.getUniqID());
			q.setSubject(main.quizName);
			q.setQuestion_text(txtfield_createquiz_sa.getText());
			q.setQuestion_type(2);
			q.setData1("");
			q.setData2("");
			q.setData3("");
			q.setData4("");
			q.setData5(txtfield_createquiz_saAns.getText());
			int marks = Integer.parseInt(txtbx_createquiz_sa_marks.getText());
			if (marks > 0)
				q.setMarks(marks);
			else
				q.setMarks(1);

			q.updateQuestion();

		}

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/View/teacherEditQuiz.fxml"));
			parent.getStylesheets().add("View/application.css");

			Scene scence = new Scene(parent);
			main.pStage.setScene(scence);

		} catch (IOException e) {
			// TODO Auto-generated catch block

		}
	}

	public int getMaxQuestionID(String subject)  {
		ResultSet rs = null;
		Connection connection = null;
		PreparedStatement statement = null;

		Account acc = null;
		String query = "SELECT * FROM quiz_question WHERE subject=" + subject;
		dc = new DbConnection();
		try {
			connection = dc.Connect();
			statement = connection.prepareStatement(query);
			rs = statement.executeQuery(query);

			if (rs.next()) {
				main.quizName = subject;
				main.qnno = rs.getInt(1);
				return main.qnno;
			}
			else{
				main.quizName = "";
				main.qnno = 0;
				return 0;
			}
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
		tequ_profile_menu_btn.setText(main.userName);
		dc = new DbConnection();
		try {
			Connection conn = dc.Connect();
			// Execute query and store result in a resultset
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM quiz_questions where subject ='"+main.quizName+"' and question_id='"+main.quizId+"'");
			while (rs.next()) {
				teacher_createquiz_createbtn.setDisable(true);
				txtbx_teacher_createquiz_quiztitle.setText(main.quizName);
				txtbx_teacher_createquiz_quiztitle.setDisable(true);
				String type = rs.getString(5);
				if(type.equals("0")){
					vbox_teacher_qnwrap_mcq.setVisible(true);
					txtfield_createquiz_mcq.setText(rs.getString(4));
					txtfield_createquiz_mcq1.setText(rs.getString(6));;
					txtfield_createquiz_mcq2.setText(rs.getString(7));;
					txtfield_createquiz_mcq3.setText(rs.getString(8));;
					txtfield_createquiz_mcq4.setText(rs.getString(9));;
					txtbx_createquiz_mcq_marks.setText(rs.getString(11));;
					String ans = rs.getString(10);
					if(ans.contains("1"))
						cb1_createquiz.setSelected(true);
					if(ans.contains("2"))
						cb2_createquiz.setSelected(true);
					if(ans.contains("3"))
						cb3_createquiz.setSelected(true);
					if(ans.contains("4"))
						cb4_createquiz.setSelected(true);
				}
				if(type.equals("1")){
					vbox_teacher_qnwrap_tf.setVisible(true);
					txtfield_createquiz_tf.setText(rs.getString(4));
					txtbx_createquiz_tf_marks.setText(rs.getString(11));;
					String ans = rs.getString(10);
					if(ans.contains("1"))
						txtfield_createquiz_tf_1.setSelected(true);
					if(ans.contains("2"))
						txtfield_createquiz_tf_2.setSelected(true);
				}
				if(type.equals("2")){
					vbox_teacher_qnwrap_sa.setVisible(true);
					txtfield_createquiz_sa.setText(rs.getString(4));
					txtfield_createquiz_saAns.setText(rs.getString(10));
					txtbx_createquiz_sa_marks.setText(rs.getString(11));;
				}
					return;
			}

		} catch (SQLException ex) {
			System.err.println("Error"+ex);
		}

		tequ_logout.setOnAction(new EventHandler<ActionEvent>() {
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

		tequ_homeBtn.setOnAction(new EventHandler<ActionEvent>() {
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
