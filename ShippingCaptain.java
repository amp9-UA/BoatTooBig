package People;

import Hardware.ShippingBoat;

public class ShippingCaptain extends Captain{ //this class are the ones who can captain shipping boats, as well as make dock assignment/departure requests
	
	//Fields
	private ShippingBoat ship;		//ship that the shipping captain drives, null if none
	private boolean dockAssignmentRequest = false; //indicates whether a dock assignment request is active or not
	
	//Constructors
	public ShippingCaptain () {}
	
	public ShippingCaptain(String name, String username, String password, char licenseType) {
		
		super(name, username, password, "Cargo Ship Captain", licenseType);
		
	}
	//Getters and Setters
	public boolean isDockAssignmentRequest() {
		return dockAssignmentRequest;
	}

	public boolean setDockAssignmentRequest(boolean dockAssignmentRequest) {
		this.dockAssignmentRequest = dockAssignmentRequest;
		return dockAssignmentRequest;
	}

	public ShippingBoat getShip() {
		return ship;
	}

	public void setShip(ShippingBoat ship) {
		this.ship = ship;
	}
	
	
	//Methods
	public String captainShip(ShippingBoat b1) {  //this method will set the shipping captain as the captain of the ship passed as an argument
		
		String result = "";
		
		if(b1.getCaptain() != null)
			result = "Error, you cannot captain this ship, it is already being captained by " + b1.getCaptain().getName();
		else if(this.ship != null && this.dockAssignmentRequest == true){
			result = "Error, you cannot captain another ship with an active dock assignment request";
		}
		else if(this.ship != null && this.ship.getAssignedDock() != null) {
			result = "Error, you cannot captain another ship if you are currently docked";
		}
		else {
			if(this.ship != null) 
				result = this.disembarkShip();
			
			result = result + "\nSuccess! " + this.getName() + " is now captaining " + b1.getName();
			b1.setCaptain(this);
			this.ship = b1;
		}
		return result;
	}
	
	public String disembarkShip() {		//this method will allow the captain to stop captaining a certain ship
		String result = "";
		if(this.ship == null) 
			result = "Error, you are not captaining a ship, cannot disembark anything";	
		else if(this.ship.getAssignedDock() != null) 
			result = "Error, you cannot disembark a ship while it is assigned to a dock";
		else if(dockAssignmentRequest == true) {
			result = "Error, you cannot disembark a ship while having an active dock assignment request";
		}
		else {
			result = "Success! You are no longer operating " + this.ship.getName();
			this.ship.setCaptain(null);
			this.ship = null;
		}
		return result;
	}
	
	//public void addShipToSystem(String name, int weight, int length) {
		//this.captainShip(new ShippingBoat(name, weight, length));
	//}
	
	public String requestDockAssignment() {		//this method allows the shipping captain to request a dock assignment from the harbor master
		String result = "";
		String name1 = null;
		String name2 = null;
		
		if(this.ship == null) {
			result = "Error, cannot request a dock assignment when you are not captaining a ship";
		}
		else if(this.ship.getAssignedDock() != null) {
			result = "Error, cannot request a dock assignment when you are already docked";
		}
		else {
			name1 = this.ship.getName();
			for(int i = 0; i < this.getHarbor().getDockAssignments().size(); i++) {
				name2 = this.getHarbor().getDockAssignments().get(i).getName();
				if(name1 == name2) {
					result = "Error! You have already made a dock assignment request";
					return result;
				}
			}
			this.harbor.getDockAssignments().add(this.ship);
			this.setDockAssignmentRequest(true);
			result = "Success! " + this.ship.getName() + " has now requested to dock at " + this.harbor.getName();
			return result;
		}
		return result;
	}
	
	public String requestDockDeparture() {	//this method allows the shipping captain to a request a dock departure from the harbor master
		String result = "";
		String name1 = null;
		String name2 = null;
		if(this.ship == null) {
			result = "Error, cannot request a dock departure when you are not captaining a ship";
		}
		else if(this.ship.getAssignedDock() == null) {
			result = "Error, cannot request a dock departure when not docked";
		}
		else if(this.ship.isUnloadingStatus() == false) {
			result = "Error, cannot request a dock departure while still in the unloading process";
		}
		else {
			for(int i = 0; i < this.getHarbor().getDockDepartures().size(); i++) {
				name1 = this.ship.getName();
				name2 = this.getHarbor().getDockDepartures().get(i).getName();
				if(name1 == name2) {
					result = "Error! You have already made a dock departure request";
					return result;
				}
			}
			result = "Success! " + this.ship.getName() + " has successfully requested departure from " + this.ship.getAssignedDock().getName();
			this.harbor.getDockDepartures().add(this.ship);
			return result;
		}
		return result;
	}

	
}
