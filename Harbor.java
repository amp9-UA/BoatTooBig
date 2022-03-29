package Hardware;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


import People.DockOperator;
import People.HarborMaster;
import People.ShippingCaptain;
import People.TugCaptain;



public class Harbor implements Serializable{		//this class is kind of an overhead that has a list of all important classes within the harbor system
	//Fields
	private String name;									//name of harbor
	private ArrayList<Dock> availableDocks;					//list of available docks
	private ArrayList<Dock> unavailableDocks;				//list of unavailable docks
	private ArrayList<HarborMaster> harborMasters;			//list of harbor masters
	private ArrayList<DockOperator> dockOps;				//list of dock operators
	private ArrayList<ShippingCaptain> shippingCaps;		//list of shipping captains
	private ArrayList<TugCaptain> tugCaps;					//list of tug captains
	private ArrayList<ShippingBoat> ships;					//list of shipping boats
	private ArrayList<TugBoat> tugs;						//list of tug boats
	private ArrayList<ShippingBoat> dockAssignments;		//list of dock assignment requests
	private ArrayList<ShippingBoat> dockDepartures;			//list of dock departure requests
	
	//Constructors
	public Harbor(String name) {
		this.name = name;
		availableDocks = new ArrayList<Dock>();
		unavailableDocks = new ArrayList<Dock>();
		harborMasters = new ArrayList<HarborMaster>();
		dockOps = new ArrayList<DockOperator>();
		shippingCaps = new ArrayList<ShippingCaptain>();
		tugCaps = new ArrayList<TugCaptain>();
		ships = new ArrayList<ShippingBoat>();
		tugs = new ArrayList<TugBoat>();
		dockAssignments = new ArrayList<ShippingBoat>();
		dockDepartures = new ArrayList<ShippingBoat>();
	}
	
	
	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Dock> getAvailableDocks() {
		return availableDocks;
	}

	public void setAvailableDocks(ArrayList<Dock> availableDocks) {
		this.availableDocks = availableDocks;
	}

	public ArrayList<Dock> getUnavailableDocks() {
		return unavailableDocks;
	}

	public void setUnavailableDocks(ArrayList<Dock> unavailableDocks) {
		this.unavailableDocks = unavailableDocks;
	}
	
	public ArrayList<HarborMaster> getHarborMasters() {
		return harborMasters;
	}


	public void setHarborMasters(ArrayList<HarborMaster> harborMasters) {
		this.harborMasters = harborMasters;
	}


	public ArrayList<DockOperator> getDockOps() {
		return dockOps;
	}


	public void setDockOps(ArrayList<DockOperator> dockOps) {
		this.dockOps = dockOps;
	}


	public ArrayList<ShippingCaptain> getShippingCaps() {
		return shippingCaps;
	}


	public void setShippingCaps(ArrayList<ShippingCaptain> shippingCaps) {
		this.shippingCaps = shippingCaps;
	}


	public ArrayList<TugCaptain> getTugCaps() {
		return tugCaps;
	}


	public void setTugCaps(ArrayList<TugCaptain> tugCaps) {
		this.tugCaps = tugCaps;
	}


	public ArrayList<ShippingBoat> getShips() {
		return ships;
	}


	public void setShips(ArrayList<ShippingBoat> ships) {
		this.ships = ships;
	}


	public ArrayList<TugBoat> getTugs() {
		return tugs;
	}


	public void setTugs(ArrayList<TugBoat> tugs) {
		this.tugs = tugs;
	}
	

	public ArrayList<ShippingBoat> getDockAssignments() {
		return dockAssignments;
	}


	public void setDockAssignments(ArrayList<ShippingBoat> dockAssignments) {
		this.dockAssignments = dockAssignments;
	}


	public ArrayList<ShippingBoat> getDockDepartures() {
		return dockDepartures;
	}


	public void setDockDepartures(ArrayList<ShippingBoat> dockDepartures) {
		this.dockDepartures = dockDepartures;
	}


	//Methods
	
	//NOTE all these methods are add to arraylist methods that also sets the harbor to the object
	public void addDock(Dock d) {
		this.availableDocks.add(d);
		d.setHarbor(this);
	}
	
	public void addTug(TugBoat t) {
		this.tugs.add(t);
		t.setHarbor(this);
	}
	public void addShip(ShippingBoat s) {
		this.ships.add(s);
		s.setHarbor(this);
	}
	public void addShipCap(ShippingCaptain s) {
		this.shippingCaps.add(s);
		s.setHarbor(this);
	}
	public void addTugCap(TugCaptain t) {
		this.tugCaps.add(t);
		t.setHarbor(this);
	}
	public void addDockOp(DockOperator d) {
		this.dockOps.add(d);
		d.setHarbor(this);
	}
	public void addHarborMaster(HarborMaster h) {
		this.harborMasters.add(h);
		h.setHarbor(this);
	}
	public void dockBoat(Dock d) {
		this.availableDocks.remove(d);
		this.unavailableDocks.add(d);
	}
	
	public void undockBoat(Dock d) {
		this.unavailableDocks.remove(d);
		this.availableDocks.add(d);
	}
	
	//Print Methods
	public String printAvailableDocks() {
		String result = "Available Docks:";
		for (Dock d : this.availableDocks) {
			result = result + "\nDock Name: " + d.getName() + "\nMax Boat Size: " + d.getMaxBoatSize();
			if(d.getOperator() == null) {
				result = result + "\nCurrent Operator: none";
			}
			else {
				result = result + "\nCurrent Operator: " + d.getOperator().getName();
			}
			if(d.getBoat() == null) {
				result = result + "\n Current Boat Docked: none";
			}
			else {
				result = result + "\n Current Boat Docked: " + d.getBoat().getName();
			}
			result = result + "\n";
		}
		return result;
	}
	
	public String printUnavailableDocks(){
		String result = "Unavailable Docks:";
		for(Dock d : this.unavailableDocks) {
			result = result + "\nDock Name: " + d.getName() + "\nMax Boat Size: " + d.getMaxBoatSize();
			if(d.getOperator() == null) {
				result = result + "\nCurrent Operator: none";
			}
			else {
				result = result + "\nCurrent Operator: " + d.getOperator().getName();
			}
			if(d.getBoat() == null) {
				result = result + "\n Current Boat Docked: none";
			}
			else {
				result = result + "\n Current Boat Docked: " + d.getBoat().getName();
			}
			result = result + "\n";
		}
		return result;
	}
	
	public String printDockAssignmentRequests() {
		String result = "Dock Assignment Requests:\n";
		for(ShippingBoat s : this.dockAssignments) {
			result = result + s.printShipInfo() + "\n";
		}
		
		return result;
	}
	
	public String printDockDepartureRequests() {
		String result = "Dock Departure Requests:\n";
		for(ShippingBoat s : this.dockDepartures) {
			result = result + s.printShipInfo() + "\n";
		}

		return result;
	}
	
	public String printShippingBoats() {
		String result = "Current Shipping Boats in System:\n";
		for(ShippingBoat s : this.ships) {
			result = result + s.printShipInfo() + "\n\n";
		}
		
		return result;
	}
	
	public String printTugBoats(){
		String result = "Current Tug Boats in System:\n";
		for(TugBoat t : this.tugs) {
			result = result + t.printShipInfo() + "\n\n";
		}
		return result;
	}
	
	public String printDocks() {
		String result = "Current Docks in System:\n";
		result = result + this.printAvailableDocks() + this.printUnavailableDocks(); 
		return result;
	}
	
	public static Harbor loadData() {
		FileInputStream fileIn = null;
		ObjectInputStream objIn = null;
		Harbor harb = null;
		
		try {
			fileIn = new FileInputStream("root/harbor.ser");
			objIn = new ObjectInputStream(fileIn);
			harb = (Harbor) objIn.readObject();
			objIn.close();
			fileIn.close();
		}
		catch(IOException i) {
			i.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return harb;
	}
	
	public static void saveData(Harbor harb) {
		FileOutputStream fileOut = null;
		ObjectOutputStream objOut= null;
		
		try {
			fileOut = new FileOutputStream("root/harbor.ser");
			objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(harb);
			objOut.close();
			fileOut.close();
		}
		catch(IOException i){
			i.printStackTrace();
		}
	}
}
