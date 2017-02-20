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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;



public class teacherViewQuizController implements Initializable {
	
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
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM quiz_info");
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
//                	tableData rowData = row.getItem();
//                    System.out.println(rowData);
                	
                    teacher_table_quizlist.getSelectionModel().setCellSelectionEnabled(true);
                    ObservableList selectedCells = teacher_table_quizlist.getSelectionModel().getSelectedCells();         
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
                    // Get id of quiz and go to next fxml file that show full quiz
                    System.out.println(val);
                }        

            });
            return row;
        });
        
        
	}


	
}
