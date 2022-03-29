package People;

import java.io.Serializable;

import Hardware.Harbor;

public abstract class Person implements Serializable{		//parent of dock operator, and both captains. Contains necessary fields for all users of the harbor system

	//Fields
	protected String name;			//name of person
	protected String username;		//username associated with a person
	protected String password;		//password associated with a person
	protected String occupation;	//occupation of a person
	protected Harbor harbor;		//Harbor associated with a person
	
	//Constructors
	public Person () {};
	
	public Person (String name, String username, String password, String occupation) {
		
		this.name = name;
		this.username = username;
		this.occupation = occupation;
		this.password = password;
		
	}

	//Getters and Setters
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public Harbor getHarbor() {
		return harbor;
	}

	public void setHarbor(Harbor harbor) {
		this.harbor = harbor;
	}
	
	//Methods
	
	
	
}
