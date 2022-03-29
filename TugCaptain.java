package People;

import Hardware.ShippingBoat;
import Hardware.TugBoat;

public class TugCaptain extends Captain{	//this class is the only one that can captain tug boats in the harbor system
	
	//Fields
	private TugBoat tug;		//this is the tug that the tug captain is piloting, null if none
	private ShippingBoat assignedShip;	//tug captains will receive shipping boat assignments that they need to bring into the harbor
	
	//Constructors
	public TugCaptain () {}
	
	public TugCaptain (String name, String username, String password) {
		
		super(name, username, password, "Tug Captain", 'C');
		
	}

	//Getters and Setters

	public TugBoat getTug() {
		return tug;
	}

	public void setTug(TugBoat tug) {
		this.tug = tug;
	}

	public ShippingBoat getAssignedShip() {
		return assignedShip;
	}

	public void setAssignedShip(ShippingBoat assignedShip) {
		this.assignedShip = assignedShip;
	}
	
	//Methods
	public String captainTug(TugBoat t1) {		//this allows the the tug captain to start piloting a tug in the harbor system
		String result = "";
		if(t1.getCaptain() != null)
			result = "Error, you cannot captain this tug, it is already being captained by " + t1.getCaptain().getName();
		else if(this.assignedShip != null){
			result = "Error! You cannot captain a new tug while are you are assigned to " + this.assignedShip.getName();
		}
		else {
			if(this.tug != null) 
				result = this.disembarkTug();
			
			result = result + "\nSuccess! " + this.getName() + " is now captaining " + t1.getName();
			t1.setCaptain(this);
			this.tug = t1;
		}
		return result;
	}
	
	public String disembarkTug() {		//this allows the tug captain to stop piloting a their current tug
		String result = "";
		if(this.tug == null) 
			result = "Error, you are not captaining a tug, cannot disembark anything";	
		else if(this.assignedShip != null) 
			result = "Error, you cannot disembark a tug while assigned a ship to dock!";
		else {
			result = "Success! You are no longer operating " + this.tug.getName();
			this.tug.setCaptain(null);
			this.tug = null;
		}
		return result;
	}
	
	//public void addTugToSystem(String name, int length) {
		//this.captainTug(new TugBoat(name, length));
	//}
}