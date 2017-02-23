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
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;



public class studentQuizController implements Initializable {

	@FXML private MenuButton sq_profile_menu_btn;
	@FXML private Button sQuiz_homeBtn;
	@FXML private Button sQuiz_quizBtn;
	@FXML private Button sQuiz_viewBtn;
	@FXML private MenuItem sQuiz_logout;

	@FXML
	private TableView<tableData> student_table_quizlist;
	@FXML
	private TableColumn<tableData, String> tablestatus;
	@FXML
	private TableColumn<tableData, String> tableqname;
	//Initialize observable list to hold out database data
	private ObservableList<tableData> tableinfo;
	private DbConnection dc;
	
	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		sq_profile_menu_btn.setText(main.login.getName());
		dc = new DbConnection();

		try {
			Connection conn = dc.Connect();
			tableinfo = FXCollections.observableArrayList();
			// Execute query and store result in a resultset
			ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM quiz");
			tableinfo.add(new tableData(null, "-Practice random questions-"));
			while (rs.next()) {
				//get string from db,whichever way
				//rs.getString(1) = database first column
				tableinfo.add(new tableData(rs.getString(1), rs.getString(2)));
			}

		} catch (SQLException ex) {
			System.err.println("Error"+ex);
		}

		//Set cell value factory to tableview.
		//NB.PropertyValue Factory must be the same with the one set in model class.
		//Can use and get ID of quiz but visible to false
		tablestatus.setCellValueFactory(new PropertyValueFactory<>("qstatus"));
		tableqname.setCellValueFactory(new PropertyValueFactory<>("qname"));

		student_table_quizlist.setItems(null);
		student_table_quizlist.setItems(tableinfo);

		// For selection of view quiz
		student_table_quizlist.setRowFactory( tv -> {
			TableRow<tableData> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
//                	tableData rowData = row.getItem();
//                    System.out.println(rowData);

					student_table_quizlist.getSelectionModel().setCellSelectionEnabled(true);
					ObservableList selectedCells = student_table_quizlist.getSelectionModel().getSelectedCells();
					TablePosition tablePosition = (TablePosition) selectedCells.get(0);
					Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
					// Get id of quiz and go to next fxml file that show full quiz
					System.out.println(val);
					if (val == null){
						main.quiz = new Quiz();
						main.quiz.setQuiz_id(-12345);

						try {
							Parent parent = FXMLLoader.load(getClass().getResource("/View/studentTakeQuiz.fxml"));
							parent.getStylesheets().add("View/application.css");

							Scene scence = new Scene(parent);
							//Stage stage = (Stage) createQ.getScene().getWindow();
							main.pStage.setScene(scence);

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						boolean check = false;
						try {
							Integer.parseInt(val.toString());
							check = true;
						} catch (Exception e) {
							check = false;
						}

						if (check) {
							main.quiz = new Quiz();
							main.quiz.setQuiz_id(Integer.parseInt(val.toString()));

							try {
								Parent parent = FXMLLoader.load(getClass().getResource("/View/studentTakeQuiz.fxml"));
								parent.getStylesheets().add("View/application.css");

								Scene scence = new Scene(parent);
								//Stage stage = (Stage) createQ.getScene().getWindow();
								main.pStage.setScene(scence);

							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}
				}

			});
			return row;
		});
		
		sQuiz_logout.setOnAction(new EventHandler<ActionEvent>() {
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
		
		sQuiz_homeBtn.setOnAction(new EventHandler<ActionEvent>() {
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
		
		sQuiz_quizBtn.setOnAction(new EventHandler<ActionEvent>() {
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
		
		sQuiz_viewBtn.setOnAction(new EventHandler<ActionEvent>() {
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
	}
}
