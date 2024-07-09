package application;

import Database.DatabaseHandler;
import Models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class signUpController {

	@FXML
    private TextField SignUpLocation;

    @FXML
    private TextField signUpFirstName;

    @FXML
    private TextField signUpLastname;
    
    @FXML
    private Button signUpButton;

    @FXML
    private PasswordField signUpPwd;
    
    @FXML
    private TextField signUpUsername;
    
    @FXML
    private CheckBox maleCheckBox;

    
    @FXML
    void initialize() {
    	
    	signUpButton.setOnAction(event -> {
    		if ((signUpUsername.getText().trim() != "") && (signUpPwd.getText().trim()!="")) {
    			
    			DatabaseHandler dbHandler = new DatabaseHandler();
    			
    			User user = new User();
    			user.setFirstname(signUpFirstName.getText().trim());
    			user.setLastname(signUpLastname.getText().trim());
    			
    			user.setPassword(signUpPwd.getText().trim());
    			user.setLocation(SignUpLocation.getText().trim());
    			user.setUsername(signUpUsername.getText().trim());
    			
    			if (maleCheckBox.isSelected()) {
    				
    				user.setGender("Male");
    				
    			}else user.setGender("Female");
    			
    			dbHandler.saveUser(user);
    			System.out.println("sign up successful");
    			
    			
    			
    		}
    		
    		
    	});
    	
    }
    
    
}
