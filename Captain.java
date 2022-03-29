package People;

import java.io.Serializable;

public abstract class Captain extends Person  {	//extension of person class, has the necessary fields for tug captains and shipping captains
	//Fields
	protected char licenseType;	//captains have a license type that they must be aware of when captaining ships, helpful for identification
	
	
	//Constructors
	public Captain () {}
	
	public Captain (String name, String username, String password, String occupation, char licenseType) {
		
		super(name, username, password, occupation);
		this.licenseType = licenseType;
		
	}
	
	//Getters and Setters
	
	//Methods
}
