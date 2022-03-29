package HarborSystem;

import Hardware.Dock;
import Hardware.Harbor;
import Hardware.ShippingBoat;
import Hardware.TugBoat;
import People.DockOperator;
import People.HarborMaster;
import People.ShippingCaptain;
import People.TugCaptain;

public class Driver1 {
	
	/*This driver class performs system level testing of the software engine*/
	
	
	public static void main(String[] args){
		//Initialize Harbor
		Harbor harb = new Harbor("Main");
		
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
		
		/* Testing Dock Operator Functionality */
		
		//Error 1: message when trying to unman dock when not operating one
		dockOp1.unManDock();
		
		//Dock operator manning dock
		dockOp1.manDock(d1);
		
		//Error 2: if dock is already being operated
		dockOp2.manDock(d1);
		
		//Remove from a dock, man a new dock!
		dockOp2.manDock(d3);
		dockOp2.manDock(d2);
		
		System.out.println();
		System.out.println();
		
		/* Testing Tug Boat Captain Functionality */
		
		//Error 1:  when trying to disembark tug when not operating one
		tugCap1.disembarkTug();
		
		//Tug Cap driving tugboat
		tugCap2.captainTug(tug1);
		
		//Error 2: when trying to captain a boat already being operated on
		tugCap1.captainTug(tug1);
		
		//Change from one tug to another
		tugCap2.captainTug(tug2);
		tugCap1.captainTug(tug1);
		
		System.out.println();
		System.out.println();
		
		/* Testing Ship Captain Functionality */
		
		//Error 1: message when trying to disembark a ship when not operating one
		shipCap1.disembarkShip();
		
		//Ship Cap driving shipping boat
		shipCap2.captainShip(ship1);
		
		//Error 2: when trying to captain a boat already being operated on
		shipCap1.captainShip(ship1);
		
		//Change from one ship to another
		shipCap2.captainShip(ship2);
		shipCap1.captainShip(ship1);
		
		System.out.println();
		System.out.println();
		
		
		
		/* Ship Captain making a dock request! */
		
		ShippingCaptain shipCap3 = new ShippingCaptain("ShipCap3", "lalaman", "bruh", 'B');
		
		//Error 1: Trying to make a dock request without captaining a ship
		shipCap3.requestDockAssignment();
		
		//Request dock assignment
		shipCap1.requestDockAssignment();
		
		//Error 2: when requesting a dock assignment more than once
		shipCap1.requestDockAssignment();
		
		
		System.out.println();
		System.out.println();
		
		/* Harbor Master Assigning Tug boats to ships */
		
		//Successful assignment
		master1.assignTugBoats(ship1, tug1);
		
		//Error 1: when trying to reassign tug boat
		master1.assignTugBoats(ship2, tug1);
		
		//Successful assignment
		master1.assignTugBoats(ship1, tug2);
		
		System.out.println();
		System.out.println();
		
		/* Harbor Master Making Dock assignments */
		
		//Error 1: Dock does not have a dock operator
		master1.assignBoatToDock(ship1, d3);
		
		//Error 2: Dock is not big enough
		master1.assignBoatToDock(ship1, d2);
		
		//Error 3: Ship does not have enough tug boats
		master1.assignBoatToDock(ship2, d1);
		
		//Successful assignment
		master1.assignBoatToDock(ship1, d1);
		
		//Error 4: dock already has a boat within it
		master1.assignBoatToDock(ship2, d1);
		
		//ERROR 3 FROM DOCK ASSIGNMENTS: trying to request a dock assignment while docked
		shipCap1.requestDockAssignment();
		
		System.out.println();
		System.out.println();
		
		/* Dock Operators Confirming unload status of boat */
		
		DockOperator dockOp3 = new DockOperator("DockOp3", "feefee", "foofoo");
		
		//Error 1: Trying to unload a boat when not manning a dock
		dockOp3.unloadShip();
		
		//Error 2: Trying to unload a boat when no ship in dock
		dockOp2.unloadShip();
		
		//Successful ship unload
		dockOp1.unloadShip();
		
		System.out.println();
		System.out.println();
		
		/* Shipping Captains Requesting Departure */
		
		master1.assignTugBoats(ship2, tug1);
		master1.assignTugBoats(ship2, tug2);
		master1.assignBoatToDock(ship2, d2);
		
		//Error 1: Trying to make a departure request without captaining a ship
		shipCap3.requestDockDeparture();
				
		//Error 2: Trying to make a departure request without being cleared of unload status
		shipCap2.requestDockDeparture();
		
		//Request a departure assignment
		shipCap1.requestDockDeparture();
		
		//Error 3: trying to make multiple departure requests
		shipCap1.requestDockDeparture();
		
		//Error 4: Trying to request a departure when not docked
		ShippingBoat ship3 = new ShippingBoat("Ship3", 500, 500);
		shipCap3.captainShip(ship3);
		shipCap3.requestDockDeparture();
		
		System.out.println();
		System.out.println();
		
		/* Harbor Master approving departure requests */
		master1.assignTugBoats(ship1, tug1);
		master1.assignTugBoats(ship1, tug2);
		
		//Error 1: Ship does not have enough tug boats
		master1.removeBoatFromDock(ship2);
				
		//Successful assignment
		master1.removeBoatFromDock(ship1);
		
		
	}
}
