package People;

import Hardware.Dock;

public class DockOperator extends Person {	//this class interacts with docks and unloads shipping boats docked at them
	
	//Fields
	private Dock dock;	//dock operators only operate at a specific dock, null if none
	
	//Construction
	public DockOperator() {}
	
	public DockOperator(String name, String username, String password) {
		
		super(name, username, password, "Dock Operator");
		
		
	}

	//Getters and Setters
	
	public Dock getDock() {
		return dock;
	}

	public void setDock(Dock dock) {
		this.dock = dock;
	}
	
	//Methods
	public String manDock(Dock d1) {		//this method will assign a dock operator to a specific dock
		String result = "";
		if(d1.isManned() == true) {
			result = "Error, cannot man this dock, it is already being operated by " + d1.getOperator().getName();
		}
		else {
			if(this.dock != null) {
				if(this.dock.getBoat() != null) {
					result = "Error, cannot man a different dock when there is an active boat in this one";
					return result;
				}
				else
				result = this.unManDock();
			}
			result = result + "\nSuccess! " + this.getName() + " is now operating " + d1.getName();
			this.dock = d1;
			d1.setOperator(this);
			d1.setManned(true);
		}
		
		return result;
	}
	
	public String unManDock() {		//this method will dissociate a dock operator from the dock they were at
		String result = "";
		if(this.dock == null) {
			result = "Error, you are not operating a dock, cannot unman anything";
		}
		else if(this.dock.getBoat() != null){
			result = "Error! Cannot unman dock while there is a boat in it";
		}
		else {
			result = "Success! You are no longer operating " + this.dock.getName();
			this.dock.setOperator(null);
			this.dock.setManned(false);
			this.dock = null;
		}
		return result;
	}
	
	public String unloadShip() {	//if a shipping boat is docked at the dock operator's dock, he can verify when it is unloaded
		String result = "";
		if(this.dock == null) {
			result = "Error, cannot unload a ship if you are not manning a dock";
		}
		else if(this.dock.getBoat() == null) {
			result = "Error, cannot unload a ship if your dock is empty";
		}
		else {
			this.dock.getBoat().setUnloadingStatus(true);
			result = "Success! " + this.dock.getBoat().getName() + " has now been unloaded at dock " + this.dock.getName();
		}
		return result;
	}

}