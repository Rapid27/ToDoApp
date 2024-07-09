package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.Constants;
import Database.DatabaseHandler;
import Models.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class ListViewController {
	@FXML
	private ListView<Task> taskListView;
	@FXML
	private ImageView addMoreButton;
	
	private ObservableList<Task> taskslist;
	
	@FXML
    private AnchorPane mainListPane;
	
	@FXML
    private Button refreshButton;

	
	@FXML
	void initialize() {
		
		taskslist = FXCollections.observableArrayList();
		
		DatabaseHandler dbHandler = new DatabaseHandler();
		
		ResultSet taskResults = dbHandler.getUserTasks(loginViewController.global_user);
		//System.out.println("list size " + taskslist.size());
		
		try {
			while (taskResults.next()) {
				
				Task eachTask = new Task();
				eachTask.setDescription(taskResults.getString(Constants.TASK_DESCRIPTION));
				eachTask.setName(taskResults.getString(Constants.TASK_NAME));
				eachTask.setDateCreated(taskResults.getTimestamp(Constants.Task_Date));
				eachTask.setTaskid(taskResults.getInt(Constants.TASK_ID));
				
				taskslist.add(eachTask);
				
				
				
			}
			System.out.println("list size " + taskslist.size());
			taskListView.setItems(taskslist);
			taskListView.setCellFactory(cellController -> new cellController());
			
			refreshButton.setOnAction(event -> {
				taskListView.getItems().clear();
				refreshlist();
				taskListView.setItems(taskslist);
				taskListView.setCellFactory(cellController -> new cellController());
				
				
				
			});
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addMoreButton.setOnMouseClicked(event -> {
			
			try {
				AnchorPane addTaskView = FXMLLoader.load(getClass().getResource("/Views/addTaskForm.fxml"));
				mainListPane.getChildren().setAll(addTaskView);				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		});
		
		
	}


	public void refreshlist() {
		
		initialize();
		
		
		
	}
}
