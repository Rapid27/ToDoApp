package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Task;
import Models.User;
import application.ListViewController;

public class DatabaseHandler extends Configs{

	Connection dbConnection;
	
	public Connection getDBConnection() throws ClassNotFoundException, SQLException {
		String connectionString = "jdbc:mysql://"+ DBHOST + ":"+ DBPORT + "/"+ DBNAME;
		
		Class.forName("com.mysql.jdbc.Driver");
		
		dbConnection = DriverManager.getConnection(connectionString , DBUSER , DBPWD);
		
				
		return dbConnection;
		
		
	}
	public void saveUser(User user){
		
		String insert = "INSERT INTO " + Constants.USER_TABLE + "(" + Constants.USER_FIRSTNAME +"," + Constants.USER_LASTNAME
																+"," + Constants.USER_PASSWORD + "," + Constants.USER_LOCATION
																+ "," + Constants.USER_GENDER + ","+ Constants.USER_USERNAME + ")" 
																+ "VALUE(?,?,?,?,?,?)";
		
		try {
			PreparedStatement preparedstatement = getDBConnection().prepareStatement(insert);
			
			preparedstatement.setString(1, user.getFirstname());
			preparedstatement.setString(2, user.getLastname());
			preparedstatement.setString(3, user.getPassword());
			preparedstatement.setString(4, user.getLocation());
			preparedstatement.setString(5, user.getGender());
			preparedstatement.setString(6, user.getUsername());
			
			
			preparedstatement.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public ResultSet loginUser(User user) {
		ResultSet resultset = null;
		
		if (user.getUsername() != "") {
			
			String insert = "SELECT * FROM " + Constants.USER_TABLE + " WHERE " + Constants.USER_USERNAME + "=?"
							+ " AND "+ Constants.USER_PASSWORD + "=?";
			
			try {
				PreparedStatement myStatement = getDBConnection().prepareStatement(insert);
				
				myStatement.setString(1, user.getUsername());
				myStatement.setString(2, user.getPassword());
				
				resultset = myStatement.executeQuery();
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		return resultset;
	}
	
	public void saveTasks(Task task) {
		
		String insert = "INSERT INTO " + Constants.TASK_TABLE + "(" + Constants.USER_id +"," + Constants.TASK_NAME
				+"," + Constants.TASK_DESCRIPTION + "," + Constants.Task_Date
				+  ")" 
				+ "VALUE(?,?,?,?)";

				try {
				PreparedStatement preparedstatement = getDBConnection().prepareStatement(insert);
				
				preparedstatement.setInt(1, task.getUserid());
				preparedstatement.setString(2, task.getName());
				preparedstatement.setString(3, task.getDescription());
				preparedstatement.setTimestamp(4, task.getDateCreated());
				
				
				
				preparedstatement.executeUpdate();
				
				} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}

		
	}
	public ResultSet getTaskCount(int user_id) {
		
		ResultSet taskCount = null;
		
		String insert = "SELECT count(*) FROM " + Constants.TASK_TABLE + " WHERE " + Constants.USER_id + "=?";

		try {
			PreparedStatement myStatement = getDBConnection().prepareStatement(insert);
			
			myStatement.setInt(1, user_id);
			
			
			taskCount = myStatement.executeQuery();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		
		
		return taskCount;
		
		
	}
	
	public ResultSet getUserTasks(int user_id) {
		
		ResultSet resultset = null;

		String insert = "SELECT * FROM " + Constants.TASK_TABLE + " WHERE " + Constants.USER_id + "=?";

		try {
			PreparedStatement myStatement = getDBConnection().prepareStatement(insert);
			
			myStatement.setInt(1, user_id);
			
			
			resultset = myStatement.executeQuery();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return resultset;
	
	
	}
	
	public void deleteTask(int task_id) {
		
		String insert = "DELETE  FROM " + Constants.TASK_TABLE + " WHERE " + Constants.TASK_ID+ "=?";

		try {
			PreparedStatement myStatement = getDBConnection().prepareStatement(insert);
			
			myStatement.setInt(1, task_id);
			
			
			myStatement.execute();
			
			ListViewController listController = new ListViewController();
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		
		
		
		
		
	
}
