package Hardware;

import java.io.Serializable;

import People.DockOperator;

public class Dock implements Serializable{	//this class represents all of the information necessary at each dock within the harbor
	//Fields
	private String name;	//name of dock
	private int maxBoatSize;	//the maximum boat size this dock can take
	private boolean clear;   //false = boat, true = empty
	private boolean manned; //false = empty, true = manned
	private ShippingBoat boat;	//field to recognize what boat is in the dock, null if none
	private DockOperator operator;	//field to recognize who is operating the dock, null if none
	private Harbor harbor;			//field to establish the harbor it belongs to
	
	//Constructors
	public Dock(String name, int boatSize) {
		this.name = name;
		this.maxBoatSize = boatSize;
		this.clear = true;
	}
	
	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxBoatSize() {
		return maxBoatSize;
	}

	public void setMaxBoatSize(int maxBoatSize) {
		this.maxBoatSize = maxBoatSize;
	}

	public boolean isClear() {
		return clear;
	}

	public void setClear(boolean clear) {
		this.clear = clear;
	}

	public boolean isManned() {
		return manned;
	}

	public void setManned(boolean manned) {
		this.manned = manned;
	}

	public ShippingBoat getBoat() {
		return boat;
	}

	public void setBoat(ShippingBoat boat) {
		this.boat = boat;
	}

	public DockOperator getOperator() {
		return operator;
	}

	public void setOperator(DockOperator operator) {
		this.operator = operator;
	}

	public Harbor getHarbor() {
		return harbor;
	}

	public void setHarbor(Harbor harbor) {
		this.harbor = harbor;
	}
	
	//Methods
}
