package Hardware;

import java.util.ArrayList;

import People.Captain;
import People.ShippingCaptain;

public class ShippingBoat extends Boat{	//this a shipping boat, what actually gets docked and unloaded at the harbor
	//Fields
	private Dock assignedDock;		//dock that the boat is currently docked at, null if none
	private boolean unloadingStatus; //false = not unloaded, true = unloaded
	private ArrayList<TugBoat> tugBoats;	//containts all of the tugboats currently assigned to the shipping boat
	//Constructors
	public ShippingBoat(String name, int weight, int length) {
		super(name, weight, length);
		tugBoats = new ArrayList<TugBoat>();
	}
	
	//Getters and Setters
	public Dock getAssignedDock() {
		return assignedDock;
	}

	public void setAssignedDock(Dock assignedDock) {
		this.assignedDock = assignedDock;
	}


	public boolean isUnloadingStatus() {
		return unloadingStatus;
	}

	public void setUnloadingStatus(boolean unloadingStatus) {
		this.unloadingStatus = unloadingStatus;
	}

	public ArrayList<TugBoat> getTugBoats() {
		return tugBoats;
	}

	public void setTugBoats(ArrayList<TugBoat> tugBoats) {
		this.tugBoats = tugBoats;
	}
	
	public ShippingCaptain getShippingCaptain() {
		return (ShippingCaptain) this.getCaptain();
	}
	
	

	//Methods	
	public int requiredTugBoats() {	 //this method takes the boats size and weight, and calculates the required tugboats it will need to enter/exit the harbor
		if((this.weight > 1000) && (this.weight <= 2000)){
			if(this.length < 1000) {
				return 2;
			}
			else
				return 3;
		}
		else if ((this.weight > 2000) && (this.weight <= 4000)) {
			if(this.length < 1000) {
				return 4;
			}
			else
				return 5;
		}
		else if ((this.weight > 4000) && (this.weight <= 6000)) {
			if(this.length < 10000) {
				return 6;
			}
			else
				return 7;
		}
		else
			return 1;
		
	}
	
	public String printShipInfo() {
		String result = "Name: " + this.name + "\nWieght: " + this.weight + "\nLength: " + this.length;
		if(this.captain == null) {
			result = result + "\nCurrent Captain: none";
		}
		else {
			result = result + "\nCurrent Captain: " + this.captain.getName();
		}
		result = result + "\nRequired Tug Boats: " + this.requiredTugBoats();
		result = result + "\nNumber of Tug Boats Assigned: " + this.tugBoats.size();
		if(this.assignedDock == null) {
			result = result + "\nCurrent Dock: none";
		}
		else {
			result = result + "\nCurrent Dock: " + this.assignedDock.getName();
		}
		if(this.unloadingStatus == false) {
			result = result + "\nHas not been unloaded";
		}
		else {
			result = result + "\nHas been unloaded";
		}
		
		return result;
	}

}
