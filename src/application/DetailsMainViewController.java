package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DetailsMainViewController {

	@FXML
    private AnchorPane detailsMainPane;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView newImage;

    @FXML
    private Label noTasksLabel;

    @FXML
    void initialize() {
    	
    	newImage.addEventHandler( MouseEvent.MOUSE_CLICKED , event -> {
    		
    		
    		TranslateObs newBtTrans = new TranslateObs(newImage);
    		newBtTrans.shake();    	
    		
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(getClass().getResource("/Views/addTaskForm.fxml"));
    		
    		try {
				AnchorPane addFormPane = loader.load();
				detailsMainPane.getChildren().setAll(addFormPane);				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	});

    
    }
    

}
