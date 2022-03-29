package HarborSystem;

import Hardware.Dock;
import Hardware.Harbor;
import Hardware.ShippingBoat;
import Hardware.TugBoat;
import People.DockOperator;
import People.HarborMaster;
import People.ShippingCaptain;
import People.TugCaptain;

public class GUIDriver {
	
	//using this for testing GUI functionality
	
	public static void main(String[] args) {
		
		MainMenu harborGUI; //object for main menu gui
		Harbor harb; //object to hold the harbor object
		
		// INITIALIZATIONS 
		
		//Initialize Harbor
		harb = new Harbor("Test Harbor");

		//Initialize Docks 
		Dock d1 = new Dock("Dock A", 1000);
		Dock d2 = new Dock("Dock B", 750);
		Dock d3 = new Dock("Dock C", 500);
		
		//Initialize TugBoat Captains
		TugCaptain tugCap1 = new TugCaptain("TugCap1", "hey", "1234");
		TugCaptain tugCap2 = new TugCaptain("TugCap2", "whatsgood", "ahaha");
				
		//Initialize Shipping Captains
		ShippingCaptain shipCap1 = new ShippingCaptain("ShipCap1", "username", "password", 'B');
		ShippingCaptain shipCap2 = new ShippingCaptain("ShipCap2", "gaga", "googoo", 'C');
				
				
		//Initialize TugBoats
		TugBoat tug1 = new TugBoat("Tug1", 100);
		TugBoat tug2 = new TugBoat("Tug2", 100);
				
		//Initialize ShippingBoats
		ShippingBoat ship1 = new ShippingBoat("Ship1", 1750, 760);
		ShippingBoat ship2 = new ShippingBoat("Ship2", 2000, 500);
				
		//Initialize Dock Operators
		DockOperator dockOp1 = new DockOperator("DockOp1", "ajaj", "jaja");
		DockOperator dockOp2 = new DockOperator("DockOp2", "lemur", "panda");
				
		//Initialize HarborMaster
		HarborMaster master1 = new HarborMaster("Master1", "blah", "glah");
				
		//Adding Docks to Harbor
		harb.addDock(d1);
		harb.addDock(d2);
		harb.addDock(d3);
				
		//Adding Ships to Harbor
		harb.addShip(ship1);
		harb.addShip(ship2);
				
		//Adding Tugs to Harbor
		harb.addTug(tug1);
		harb.addTug(tug2);
				
		//Adding Ship Captains
		harb.addShipCap(shipCap1);
		harb.addShipCap(shipCap2);
				
		//Adding tug captains
		harb.addTugCap(tugCap1);
		harb.addTugCap(tugCap2);
				
		//Adding dock operators
		harb.addDockOp(dockOp1);
		harb.addDockOp(dockOp2);
				
		//Adding Harbor Master
		harb.addHarborMaster(master1);
		
		
		//NOW, Creating the GUI!
		
		harborGUI = new MainMenu(harb);
	}
}
