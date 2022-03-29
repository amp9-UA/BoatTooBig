package Software;

import java.util.ArrayList;

import People.DockOperator;
import People.HarborMaster;
import People.ShippingCaptain;
import People.TugCaptain;

public class HarborSystem {
	
	//Fields
	private ArrayList<HarborMaster> HarborMasters;
	private ArrayList<ShippingCaptain> ShipCaptains;
	private ArrayList<DockOperator> DockOperators;
	private ArrayList<TugCaptain> TugCaptains;
	
	//Constructors
	public HarborSystem () {
		
		HarborMasters = new ArrayList<HarborMaster>();
		ShipCaptains = new ArrayList<ShippingCaptain>();
		DockOperators = new ArrayList<DockOperator>();
		TugCaptains = new ArrayList <TugCaptain>();
		
	}
	
	//Getters and Setters
	public ArrayList<HarborMaster> getHarborMasters() {
		return HarborMasters;
	}

	public void setHarborMasters(ArrayList<HarborMaster> harborMasters) {
		HarborMasters = harborMasters;
	}

	public ArrayList<ShippingCaptain> getShipCaptains() {
		return ShipCaptains;
	}

	public void setShipCaptains(ArrayList<ShippingCaptain> shipCaptains) {
		ShipCaptains = shipCaptains;
	}

	public ArrayList<DockOperator> getDockOperators() {
		return DockOperators;
	}

	public void setDockOperators(ArrayList<DockOperator> dockOperators) {
		DockOperators = dockOperators;
	}

	public ArrayList<TugCaptain> getTugCaptains() {
		return TugCaptains;
	}

	public void setTugCaptains(ArrayList<TugCaptain> tugCaptains) {
		TugCaptains = tugCaptains;
	}
	
	//Methods
	public void addHarborMaster(HarborMaster h) {
		
		this.HarborMasters.add(h);
		
	}
	
	public void subHarborMaster(HarborMaster h) {
		
		for (HarborMaster subH : this.HarborMasters) {
			
			if (subH.equals(h)) {
				
				this.HarborMasters.remove(h);
				
				System.out.println(h.getName() + " was successfully removed from the list of Harbor Masters.");
				
			}

		}
		
		System.out.println(h.getName() + " was not found in the list of Harbor Masters and could not be removed.");
		
	}

}
