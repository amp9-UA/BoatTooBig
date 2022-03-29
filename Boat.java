package Hardware;

import java.io.Serializable;

import People.Captain;

public abstract class Boat implements Serializable { //this is the parent class of tug boat and shipping boat
	//Fields
	protected String name; //name of boat
	protected int weight; //weight of boat
	protected Captain captain; //every boat has a captain
	protected int length;	//length 
	protected Harbor harbor; //Each boat must be recognized by the harbor system
	//Constructors
	public Boat(String name, int weight, int length) {
		this.name = name;
		this.weight = weight;
		this.length = length;
	}
	
	//Getters and Setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Captain getCaptain() {
		return captain;
	}
	public void setCaptain(Captain captain) {
		this.captain = captain;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}

	public Harbor getHarbor() {
		return harbor;
	}

	public void setHarbor(Harbor harbor) {
		this.harbor = harbor;
	}
	
	//Methods
	
	public abstract String printShipInfo();
		
	
}
