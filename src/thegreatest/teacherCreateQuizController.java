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
import com.sun.tools.javac.comp.Check;
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

	@FXML MenuItem tc_logout;
	@FXML Button tc_homeBtn;
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
	@FXML private Button btn_createquiz_mcq;

	@FXML private TextField txtfield_createquiz_tf;
	@FXML private RadioButton txtfield_createquiz_tf_1;
	@FXML private RadioButton txtfield_createquiz_tf_2;
	@FXML private Button btn_createquiz_tf;

	@FXML private TextField txtfield_createquiz_sa;
	@FXML private TextField txtfield_createquiz_saAns;
	@FXML private Button btn_createquiz_sa;

	private DbConnection dc;

	@FXML
	public void changetoMCQ(ActionEvent event){
		vbox_teacher_qnwrap_mcq.setVisible(true);
		vbox_teacher_qnwrap_tf.setVisible(false);
		vbox_teacher_qnwrap_sa.setVisible(false);
//		main.qnno += 1;
//		label_teacher_createquiz_mcq_qnno.setText("Question " + Integer.toString(main.qnno));
	}
	@FXML
	public void changetoTF(ActionEvent event){
		vbox_teacher_qnwrap_mcq.setVisible(false);
		vbox_teacher_qnwrap_tf.setVisible(true);
		vbox_teacher_qnwrap_sa.setVisible(false);
//		main.qnno += 1;
//		label_teacher_createquiz_tf_qnno.setText("Question " + Integer.toString(main.qnno));
	}
	@FXML
	public void changetoSA(ActionEvent event){
		vbox_teacher_qnwrap_mcq.setVisible(false);
		vbox_teacher_qnwrap_tf.setVisible(false);
		vbox_teacher_qnwrap_sa.setVisible(true);
//		main.qnno += 1;
//		label_teacher_createquiz_sa_qnno.setText("Question " + Integer.toString(main.qnno));
	}

	@FXML
	public void createQuestion(ActionEvent event){
		if(vbox_teacher_qnwrap_mcq.isVisible()){
			// INSERT INTO `quiz_questions` (`question_id`, `teacher_id`, `subject`, `question_text`,
			// `question_type`, `data1`, `data2`, `data3`, `data4`, `data5`, `marks`) VALUES ('2', '1', 'Test', 'Q1 question', '0',
			// '1\n', '2', '3', '4', '1', '1');
			//INSERT INTO `quiz_questions` (`question_id`, `teacher_id`, `subject`,
			//`question_text`, `question_type`, `data1`, `data2`, `data3`, `data4`, `data5`, `marks`) VALUES (NULL, '1',
			// 'TEST2', 'Q2 QUESTION', '0', '1', '2', '3', NULL, NULL, NULL);

			String sql = "INSERT INTO `quiz_questions` (`teacher_id`, `subject`,`question_text`," +
					"`question_type`, `data1`, `data2`, `data3`, `data4`, `data5`, `marks`) VALUES (?,?,?,?,?,?,?,?,?,?)";
			Connection connection = null;
			PreparedStatement statement = null;
			dc = new DbConnection();
			try {
				connection = dc.Connect();
				statement = connection.prepareStatement(sql);
				statement.setString(1, main.login.getUniqID());
				statement.setString(2, "BLANK");
				statement.setString(3, txtfield_createquiz_mcq.getText());
				statement.setString(4, "0");
				statement.setString(5, txtfield_createquiz_mcq1.getText());
				statement.setString(6, txtfield_createquiz_mcq2.getText());
				statement.setString(7, txtfield_createquiz_mcq3.getText());
				statement.setString(8, txtfield_createquiz_mcq4.getText());
				String CBoption = "";
				CBoption += cb1_createquiz.isSelected() ? "1" : "";
				CBoption += cb2_createquiz.isSelected() ? "2" : "";
				CBoption += cb3_createquiz.isSelected() ? "3" : "";
				CBoption += cb4_createquiz.isSelected() ? "4" : "";
				statement.setString(9, CBoption);
				statement.setString(10, "1");
				System.out.println(statement.toString());
				int rowsInserted = statement.executeUpdate();
				if (rowsInserted > 0) {
					System.out.println("Question Inserted!");
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

		}
		if(vbox_teacher_qnwrap_tf.isVisible()){

		}
		if(vbox_teacher_qnwrap_sa.isVisible()){

		}
		main.qnno += 1;
		label_teacher_createquiz_sa_qnno.setText("Question " + Integer.toString(main.qnno));
	}

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

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
