package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.ResourceBundle;

import Database.DatabaseHandler;
import Models.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class addTaskFormController {
	
	 
	    
		@FXML
	    private AnchorPane formMainPane;

	    @FXML
	    private Button saveTaskButton;

	    @FXML
	    private TextField taskDescriptionText;

	    @FXML
	    private TextField taskNameText;
	    
	    @FXML
	    private Button viewUserTasksButton;
	    
	    @FXML
	    private Label addSuccessLabel;
	    
	    int NumOfTasks = 0;


	    @FXML
	    void initialize() {
	    	
	    	viewUserTasksButton.setVisible(false);
	    	saveTaskButton.setOnAction(event ->{
	    		
	    		
	    		String description = taskDescriptionText.getText().trim();
	    		String taskName = taskNameText.getText().trim();
	    		
	    		if(!(taskName == "")) {
	    			DatabaseHandler dbHandler = new DatabaseHandler();
	    			Task task = new Task();
		    		
		    		Timestamp creationTime = new Timestamp(Calendar.getInstance().getTimeInMillis());
		    		task.setDateCreated(creationTime);
		    		task.setName(taskName);
		    		task.setDescription(description);
		    		task.setUserid(loginViewController.global_user);
		    		
		    		dbHandler.saveTasks(task);
		    		
		    		addSuccessLabel.setVisible(true);
		    		
		    		ResultSet taskcount = dbHandler.getTaskCount(loginViewController.global_user);
		    		
		    		try {
						while (taskcount.next()) NumOfTasks = taskcount.getInt("count(*)");
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		
		    		viewUserTasksButton.setText("View Tasks" + "(" + NumOfTasks + ")");
		    		viewUserTasksButton.setVisible(true);
		    		
		    		
		    		System.out.println("Create task Success for user id: " + loginViewController.global_user);
	    		}else System.out.println("enter full task information");
	    		
	    		viewUserTasksButton.setOnAction(event2 -> {
		    		
	    			FXMLLoader loader = new FXMLLoader();
	        		loader.setLocation(getClass().getResource("/Views/listView.fxml"));
	        		
	        		try {
	    				AnchorPane addFormPane = loader.load();
	    				
	    				
	    				
	    				formMainPane.getChildren().setAll(addFormPane);				
	    			} catch (IOException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
					
		    		
		    		
		    	});
	    		
	    	});
	        
	    	
	    }

}
