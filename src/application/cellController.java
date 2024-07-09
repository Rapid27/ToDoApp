package application;

import java.io.IOException;

import Database.DatabaseHandler;
import Models.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

public class cellController extends ListCell<Task> {

	@FXML
    private Label taskDateLabel;

	private FXMLLoader fxmlloader;
    

	@FXML
    private Label taskDescriptionLabel;

    @FXML
    private Label taskNameLabel;
    
    @FXML
    private AnchorPane rootPane;
    
    @FXML
    private Label deleteLabel;

    @FXML
    void initialize() {
        
    	
    }
    @Override
	protected void updateItem(Task task, boolean empty) {
		// TODO Auto-generated method stub
		super.updateItem(task, empty);
		
		if ( task == null) {
			setText(null);
			setGraphic(null);
		
		}else {
			
			if (fxmlloader == null) {
				
				fxmlloader = new FXMLLoader(getClass().getResource("/views/cell.fxml"));
				fxmlloader.setController(this);
				
				try {
					fxmlloader.load();
					
					taskDateLabel.setText(task.getDateCreated().toString());
					taskNameLabel.setText(task.getName());
					taskDescriptionLabel.setText(task.getDescription());
					
					
					setText(null);
					setGraphic(rootPane);
					
					deleteLabel.setOnMouseClicked(event -> {
			    		
			    		DatabaseHandler dbHandler = new DatabaseHandler();
			    		dbHandler.deleteTask(task.getTaskid());
			    		ListViewController myListView = new ListViewController();
			    		//myListView.refreshlist();			    	
					});
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			
			
			
			
			
		}
		
	}
}
