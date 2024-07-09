package Models;

import java.sql.Timestamp;

public class Task {

	private String name;
	private String description;
	private Timestamp dateCreated;
	private int userid;
	private int taskid;
	
	public Task() {
		super();
	}

	public Task(String name, String description, Timestamp dateCreated, int userid) {
		super();
		this.name = name;
		this.description = description;
		this.dateCreated = dateCreated;
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getTaskid() {
		return taskid;
	}

	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}
	
	
}
