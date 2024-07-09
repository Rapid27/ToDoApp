package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import Database.Constants;
import Database.DatabaseHandler;
import Models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginViewController {

	@FXML
    private Button loginButton;

    @FXML
    private PasswordField loginPwd;

    @FXML
    private Button loginSignUpButton;

    @FXML
    private TextField loginUsername;
    
    public static int global_user;
    
    @FXML
    void initialize() {
    	
    	
    	loginButton.setOnAction(event -> {
    		
    		String uname = loginUsername.getText().trim();  		
    		String upwd = loginPwd.getText().trim();
    		ResultSet result = null;
    		
    		
    		if (!(uname == "")) {
    			
    			DatabaseHandler dbHandler = new DatabaseHandler();
    			User user = new User();
    			
    			user.setUsername(uname);
    			user.setPassword(upwd);
    			
    			result = dbHandler.loginUser(user);
    			
    			int i = 0;
    			
    			try {
					while (result.next()) {
						i++;
						System.out.println(result.getString(Constants.USER_LASTNAME));
						System.out.println(result.getInt(Constants.USER_id));
						global_user = result.getInt(Constants.USER_id);
						
					}if (i==1) {
						System.out.println("Login Successful");
						//global_user = result.getInt(Constants.USER_id);
						
						loginSignUpButton.getScene().getWindow().hide();
			    		
			    		Stage DetailsStage = new Stage();
			    		FXMLLoader loader = new FXMLLoader();
			    		
			    		loader.setLocation(getClass().getResource("/Views/DetailsMainView.fxml"));
			    		
			    		try {
							loader.load();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			    		Parent signUpRoot = loader.getRoot();
			    		DetailsStage.setScene(new Scene(signUpRoot));
			    		DetailsStage.showAndWait();   
			    		
					}else System.out.println("Please correct your login details");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		
    		}
    		
    		
    	
    	});
    	
    	loginSignUpButton.setOnAction( event -> {
    		
    		loginSignUpButton.getScene().getWindow().hide();
    		
    		Stage signUpStage = new Stage();
    		FXMLLoader loader = new FXMLLoader();
    		
    		loader.setLocation(getClass().getResource("/Views/signUp.fxml"));
    		
    		try {
				loader.load();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Parent signUpRoot = loader.getRoot();
    		signUpStage.setScene(new Scene(signUpRoot));
    		signUpStage.showAndWait();    		
    		
    	});
    }
    
}
