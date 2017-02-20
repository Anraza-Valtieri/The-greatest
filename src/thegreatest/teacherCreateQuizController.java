package thegreatest;

import java.io.IOException;
import java.util.Collection;
import java.util.ResourceBundle;
import javax.print.DocFlavor.URL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Node;

public class teacherCreateQuizController implements Initializable {
	
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
	
	@FXML
	public void changetoMCQ(ActionEvent event) throws IOException{
		vbox_teacher_qnwrap_mcq.setVisible(true);
		vbox_teacher_qnwrap_tf.setVisible(false);
		vbox_teacher_qnwrap_sa.setVisible(false);
		main.qnno += 1;
		label_teacher_createquiz_mcq_qnno.setText("Question " + Integer.toString(main.qnno));
		
//		vbox_teacher_qnplain.getChildren().clear();
//		vbox_teacher_qnplain.getChildren().add(FXMLLoader.load(getClass().getResource("createMCQ.fxml")));
//		vbox_teacher_qnplain.getChildren().add((javafx.scene.Node) FXMLLoader.load(getClass().getResource("createMCQ.fxml")));
//		vbox_teacher_qnplain.getChildren().setAll(FXMLLoader.load("createMCQ.fxml"));
//		vbox_teacher_qnplain.getChildren().add((javafx.scene.Node) FXMLLoader.load(getClass().getResource("createMCQ.fxml")));
//		createMCQPane.getChildren().addAll((Collection<? extends javafx.scene.Node>) FXMLLoader.load(getClass().getResource("Content2.fxml")));
	}
	@FXML
	public void changetoTF(ActionEvent event) throws IOException{
		vbox_teacher_qnwrap_mcq.setVisible(false);
		vbox_teacher_qnwrap_tf.setVisible(true);
		vbox_teacher_qnwrap_sa.setVisible(false);
		main.qnno += 1;
		label_teacher_createquiz_tf_qnno.setText("Question " + Integer.toString(main.qnno));
	}
	@FXML
	public void changetoSA(ActionEvent event){
		vbox_teacher_qnwrap_mcq.setVisible(false);
		vbox_teacher_qnwrap_tf.setVisible(false);
		vbox_teacher_qnwrap_sa.setVisible(true);
		main.qnno += 1;
		label_teacher_createquiz_sa_qnno.setText("Question " + Integer.toString(main.qnno));
	}

	@Override
	public void initialize(java.net.URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
