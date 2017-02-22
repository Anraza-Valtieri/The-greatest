package thegreatest;

import Model.tableData;
import Model.tableData2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class teacherEditQuizController implements Initializable {
    @FXML MenuButton teq_profile_menu_btn;
    @FXML MenuItem teq_logout;
    @FXML TextField quizName;
    @FXML Button teq_homeBtn;
    @FXML Button teq_createBtn;
    @FXML Button teq_viewBtn;
    @FXML Button teq_resultBtn;
    @FXML
    private Label label;
    @FXML
    private TableView<tableData2> teacher_table_quizlist;
    @FXML
    private TableColumn<tableData2, String> table_editquiz_id;
    @FXML
    private TableColumn<tableData2, String> table_editquiz_type;
    @FXML
    private TableColumn<tableData2, String> table_editquiz_qns;
    //Initialize observable list to hold out database data
    private ObservableList<tableData2> tableinfo;
    private DbConnection dc;
    
    @FXML
    private void loadDataFromDatabase(ActionEvent event) {
    }

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
        teq_profile_menu_btn.setText(main.userName);
        dc = new DbConnection();
        
        try {
            Connection conn = dc.Connect();
            tableinfo = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM quiz_questions where subject ='"+main.quizName+"'");
            while (rs.next()) {
                tableData2 td2 = new tableData2();
                td2.setQid(rs.getString(1));
                td2.setQquestion(rs.getString(4));
                String type = rs.getString(5);
                td2.setQtype("MCQ");
                if(type.equals("0"))
                    td2.setQtype("MCQ");
                if(type.equals("1"))
                    td2.setQtype("T/F");
                if(type.equals("2"))
                    td2.setQtype("SA");
            	tableinfo.add(td2);
                quizName.setText(main.quizName);
            }

        } catch (SQLException ex) {
            System.err.println("Error"+ex);
        }
        
        //Set cell value factory to tableview.
        //NB.PropertyValue Factory must be the same with the one set in model class.
        //Can use and get ID of quiz but visible to false
        table_editquiz_id.setCellValueFactory(new PropertyValueFactory<>("qid"));
        table_editquiz_type.setCellValueFactory(new PropertyValueFactory<>("qtype"));
        table_editquiz_qns.setCellValueFactory(new PropertyValueFactory<>("qquestion"));
        
        teacher_table_quizlist.setItems(null);
        teacher_table_quizlist.setItems(tableinfo);
        
        // For selection of view quiz
        teacher_table_quizlist.setRowFactory( tv -> {
            TableRow<tableData2> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    tableData2 clickedRow = row.getItem();
                    String quizsub = clickedRow.getQid().toString();
                    // Get id of quiz and go to next fxml file that show full quiz
                    main.quizId = quizsub;
                    try{
                        Parent parent = FXMLLoader.load(getClass().getResource("/View/teacherEditQns.fxml"));
                        parent.getStylesheets().add("View/application.css");

                        Scene scence = new Scene(parent);
                        main.pStage.setScene(scence);

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }        

            });
            return row;
        });

        teq_logout.setOnAction(new EventHandler<ActionEvent>() {
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

        teq_homeBtn.setOnAction(new EventHandler<ActionEvent>() {
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

        teq_createBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    Parent parent = FXMLLoader.load(getClass().getResource("/View/teacherCreateQuiz.fxml"));
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

        teq_viewBtn.setOnAction(new EventHandler<ActionEvent>() {
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

        teq_resultBtn.setOnAction(new EventHandler<ActionEvent>() {
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
