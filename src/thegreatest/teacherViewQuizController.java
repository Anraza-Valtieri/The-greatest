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

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;



public class teacherViewQuizController implements Initializable {

    @FXML MenuItem tv_logout;
    @FXML Button tv_homeBtn;
    @FXML Button tv_createBtn;
    @FXML Button tv_viewBtn;
    @FXML Button tv_resultBtn;
    @FXML
    private Label label;
    @FXML
    private TableView<tableData> teacher_table_quizlist;
    @FXML
    private TableColumn<tableData, String> tablestatus;
    @FXML
    private TableColumn<tableData, String> tableqname;
    //Initialize observable list to hold out database data
    private ObservableList<tableData> tableinfo;
    private DbConnection dc;
    
    @FXML
    private void loadDataFromDatabase(ActionEvent event) {
    }

	@Override
	public void initialize(java.net.URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
        dc = new DbConnection();
        
        try {
            Connection conn = dc.Connect();
            tableinfo = FXCollections.observableArrayList();
            // Execute query and store result in a resultset
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM quiz");
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
        
        teacher_table_quizlist.setItems(null);
        teacher_table_quizlist.setItems(tableinfo);
        
        // For selection of view quiz
        teacher_table_quizlist.setRowFactory( tv -> {
            TableRow<tableData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                	
                    teacher_table_quizlist.getSelectionModel().setCellSelectionEnabled(true);
                    ObservableList selectedCells = teacher_table_quizlist.getSelectionModel().getSelectedCells();         
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
                    // Get id of quiz and go to next fxml file that show full quiz
                    System.out.println(val);
                    boolean check = false;
                    try{
                        Integer.parseInt(val.toString());
                        check = true;
                    } catch(Exception e){
                        check = false;
                    }

                    if (check){
                        main.quiz = new Quiz();
                        main.quiz.setQuiz_id(Integer.parseInt(val.toString()));

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
                }        

            });
            return row;
        });

        tv_logout.setOnAction(new EventHandler<ActionEvent>() {
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

        tv_homeBtn.setOnAction(new EventHandler<ActionEvent>() {
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

        tv_createBtn.setOnAction(new EventHandler<ActionEvent>() {
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

        tv_viewBtn.setOnAction(new EventHandler<ActionEvent>() {
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

        tv_resultBtn.setOnAction(new EventHandler<ActionEvent>() {
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
