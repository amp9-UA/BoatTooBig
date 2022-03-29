package Hardware;

import People.Captain;
import People.TugCaptain;

public class TugBoat extends Boat{		//this boat can only be captained by tug captains, and assist in bringing ships into the harbor
	//Fields
	private ShippingBoat tugAssignment;
	//Constructors
	public TugBoat(String name, int length) {
		super(name, 500, length);
		
	}
	//Getters and Setters
	public ShippingBoat getTugAssignment() {
		return tugAssignment;
	}
	public void setTugAssignment(ShippingBoat tugAssignment) {
		this.tugAssignment = tugAssignment;
	}
	//Methods
	
	public TugCaptain getTugCaptain() {
		return (TugCaptain)this.getCaptain();
	}
	
	public String printShipInfo() {
		String result = "Name: " + this.name + "\nWieght: " + this.weight + "\nLength: " + this.length;
		if(this.captain == null) {
			result = result + "\nCurrent Captain: none";
		}
		else {
			result = result + "\nCurrent Captain: " + this.captain.getName();
		}
		if(this.tugAssignment == null) {
			result = result + "\nCurrent Tug Assignment: none";
		}
		else {
			result = result + "\nCurrent Tug Assignment: " + this.tugAssignment.getName();
		}
		return result;
	}
}
