package People;

import java.util.ArrayList;

import Hardware.Dock;
import Hardware.Harbor;
import Hardware.ShippingBoat;
import Hardware.TugBoat;

public class HarborMaster extends Person{	//the harbor master deals with dock assignment requests and dock departure requests, and makes the
											//appropriate assignments to shipping boats. Also assigns tug boats to shipping boats
	//Fields
	
	//Constructors
	public HarborMaster(String name, String username, String password) {
		
		super(name, username, password, "Harbor Master");
		
	}
	//Getters and Setters
	
	//Methods
	public String assignBoatToDock(ShippingBoat b, Dock d) {	//this method assigns the ship to the dock that are passed in as arguments
		String result = "";
		if((d.isClear() == true) && (d.isManned() == true)) {
			if((b.getLength() < d.getMaxBoatSize())){
				if(b.getTugBoats().size() >= b.requiredTugBoats()) {
					b.setAssignedDock(d);
					d.setBoat(b);
					d.setClear(false);
					this.harbor.dockBoat(d);
					this.harbor.getDockAssignments().remove(b);
					b.getShippingCaptain().setDockAssignmentRequest(false);
					result = "Success! " + b.getName() + " assigned to dock " + d.getName();
					while(b.getTugBoats().size() > 0) {
						result = result + "\n" + b.getTugBoats().get(0).getName() + " has been removed from " + b.getName();
						b.getTugBoats().get(0).setTugAssignment(null);
						b.getTugBoats().get(0).getTugCaptain().setAssignedShip(null);
						b.getTugBoats().remove(0);
					}
				}
				else 
					result = "Could not make dock assignment, " + b.getName() + " does not have the proper amount of tugboats";
			}
			else
				result = "Could not make dock assignment, " + b.getName() + " is too large for dock " + d.getName();
		}
		else if(d.isClear() == false) {
			result = "Could not make dock assignment, " + d.getName() + " already has a boat assigned to it";
		}
		else if(d.isManned() == false) {
			result = "Could not make dock assignment, " + d.getName() + " does not have a dock operator";
		}
		
		return result;
	}
	
	public String removeBoatFromDock(ShippingBoat b) {	//removes a shipping boat from the dock that the ship was docked at
		String result = "";
		if(b.getTugBoats().size() >= b.requiredTugBoats()) {
			result = result + "Success! " + b.getName() + " has successfully departed from " + b.getAssignedDock().getName();
			b.setUnloadingStatus(false);
			b.getAssignedDock().setBoat(null);
			b.getAssignedDock().setClear(true);
			this.harbor.undockBoat(b.getAssignedDock());
			this.harbor.getDockDepartures().remove(b);
			b.setAssignedDock(null);
			while(b.getTugBoats().size() > 0) {
				result = result + "\n" + b.getTugBoats().get(0).getName() + " has been removed from " + b.getName();
				b.getTugBoats().get(0).setTugAssignment(null);
				b.getTugBoats().get(0).getTugCaptain().setAssignedShip(null);
				b.getTugBoats().remove(0);
			}
		}
		else {
			result = result + "Error! Cannot approve departure, " + b.getName() + " does not have the proper amount of tugboats";
		}
		return result;
	}
	
	public String assignTugBoats(ShippingBoat b, TugBoat t) {		//assigns tug boat to the shipping boat that are passed in as arguments
		String result = "";
		
		if (t.getTugAssignment() != null) {
			result = "Error! Cannot assign tug boat " + t.getName() + " to shipping boat " + b.getName() + " because " + t.getName() + " is already assigned to " + t.getTugAssignment().getName();
		}
		else if (t.getCaptain() == null){
			result = "Error! Cannot assign tug boat " + t.getName() + " to shipping boat " + b.getName() + " because " + t.getName() + " does not have a captain!";
		}
		else {
			t.setTugAssignment(b);
			t.getTugCaptain().setAssignedShip(b);
			b.getTugBoats().add(t);
			result = "Success! Tug boat " + t.getName() + " is now assigned to " + b.getName();
		}
		return result;
	}
	

}
