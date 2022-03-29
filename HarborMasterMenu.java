package HarborSystem;

import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import Hardware.Dock;
import Hardware.Harbor;
import Hardware.ShippingBoat;
import Hardware.TugBoat;
import People.HarborMaster;
import People.ShippingCaptain;

public class HarborMasterMenu extends JFrame {
	private Harbor harb;
	private HarborMaster mast;
	
	private JButton dockButton; //dock assignment button
	private JButton removeButton; //remove boat button
	private JButton tugButton; //assign tug boat button
	private JButton dockRequests; //view dock requests
	private JButton departureRequests; //view departure requests
	private JButton tugCaptains; 	//View available tug captains and tug boats
	private JButton viewDockButton; //button to view docks
	
	private JButton logout; //logout button
	
	private JPanel panel1; //object for buttons in the east
	private JPanel panel2; // object for buttons in the center
	private JPanel panel3; //object for buttons in the east
	private JPanel panel4; //panel for button in the sout
	private JPanel panel5;
	
	public HarborMasterMenu(Harbor h, HarborMaster m) {
		super("Welcome, Harbor Master " + m.getName() + "!");
		this.harb = h;
		this.mast = m;
		
		final int WINDOW_HEIGHT = 300; //number of height pixels
		final int WINDOW_WIDTH = 800; //number of width pixels
		
		//set Size of window
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		//Set what happens when the close button is clicked
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Set the layout of the window
		setLayout(new BorderLayout());
		
		buildPanel1(); //build panel 1
		buildPanel2(); //build panel 2
		buildPanel3(); //build panel 3
		buildPanel4(); //build panel 4
		buildPanel5();
		
		add(panel1, BorderLayout.WEST); //put put dock assignment buttons  west
		add(panel2, BorderLayout.CENTER);	//put dock departure buttons center
		add(panel3, BorderLayout.EAST);		//put tug boat buttons east
		add(panel4, BorderLayout.SOUTH); //put logout button in the south
		add(panel5, BorderLayout.NORTH);
		
		//pack(); //pack the window
		
		setVisible(true); //display the window
	}
	
	public void buildPanel1() {
		panel1 = new JPanel(); //instantiate a new panel
		
		panel1.setLayout(new GridBagLayout()); //set layout of panel
		GridBagConstraints c = new GridBagConstraints(); //gridbag constraints object
		
		dockButton = new JButton("Dock Ships");
		dockRequests = new JButton("View Dock Assignment Requests"); //instantiate buttons
		
		dockButton.addActionListener(new dockButtonListener());
		dockRequests.addActionListener(new dockRequestsListener()); //add appropriate action listeners
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("images/sailboat.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JLabel boatImage = new JLabel(new ImageIcon(image));
	    
	    c.gridx = 0;
	    c.gridy = 0;
	    
	    panel1.add(boatImage, c);
		
	    c.gridy = 1;
		panel1.add(dockButton, c);
		
		c.gridy = 2;
		
		panel1.add(dockRequests, c); //add buttons to panel
		
		panel1.setBackground(Color.WHITE);
	
	}
	
	public void buildPanel2() {
		panel2 = new JPanel(); //instantiate a new panel
		
		panel2.setLayout(new GridBagLayout()); //set layout of panel
		GridBagConstraints c = new GridBagConstraints(); //gridbag constraints object
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("images/depart.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JLabel departImage = new JLabel(new ImageIcon(image));
		
		removeButton = new JButton("Depart Ships");
		departureRequests = new JButton("View Dock Departure Requests"); //instantiate buttons
		
		removeButton.addActionListener(new removeButtonListener());
		departureRequests.addActionListener(new departureRequestsListener()); //add appropriate action listeners
		
		c.gridx = 0;
	    c.gridy = 0;
	    
	    panel2.add(departImage, c);
	    
	    c.gridy = 1;
		
		panel2.add(removeButton, c);
		
		c.gridy = 2;
		panel2.add(departureRequests, c); //add buttons to panel
		
		panel2.setBackground(Color.WHITE);
	}
	
	public void buildPanel3() {
		panel3 = new JPanel(); //instantiate a new panel
		
		panel3.setLayout(new GridBagLayout()); //set layout of panel
		GridBagConstraints c = new GridBagConstraints(); //gridbag constraints object
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("images/operator.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JLabel dockImage = new JLabel(new ImageIcon(image));
	    
	    image = null;
		try {
			image = ImageIO.read(new File("images/tugboat.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JLabel tugImage = new JLabel(new ImageIcon(image));
		
		tugButton = new JButton("Assign Tug Boats");
		tugCaptains = new JButton("View Available Tug Boats"); //instantiate buttons
		viewDockButton = new JButton("View Docks in System"); 
		
		tugButton.addActionListener(new tugButtonListener());
		tugCaptains.addActionListener(new tugCaptainsListener()); //add appropriate action listeners
		viewDockButton.addActionListener(new viewDocksListener());
		
		c.gridx = 0;
		c.gridy = 0;
		
		panel3.add(tugImage, c);
		
		c.gridy = 1;
		
		panel3.add(tugButton, c);
		
		c.gridy = 2;
		
		panel3.add(tugCaptains, c); //add buttons to panel
		
		c.gridx = 1;
		c.gridy = 0;
		
		panel3.add(dockImage, c);
		
		c.gridy = 1;
		
		panel3.add(viewDockButton, c);
		
		panel3.setBackground(Color.WHITE);
	}
	
	public void buildPanel4() {
		panel4 = new JPanel(); //instantiate a new panel
		
		
		logout = new JButton("Log Out");
		
		logout.addActionListener(new logoutListener());
		
		panel4.add(logout); //add buttons to panel
		
		panel4.setBackground(Color.BLUE);
	}
	
	public void buildPanel5() {
		panel5 = new JPanel();
		
		panel5.add(new JLabel("Welcome, " + mast.getName() + ", what would you like to do?"));
		
		panel5.setBackground(Color.WHITE);
	}
	
	private class dockButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String boatName; //string to hold the shipping boat
			String dockName; //string to hold the dock name
			
			boolean validShip = false; //checking if there is a valid shipping boat
			boolean validDock = false; //checking if there is a valid dock
			
			ShippingBoat boat = null; //reference variable for the shipping boat
			Dock dock = null; //reference variable for the dock
			
			JTextField bo = new JTextField(); //field to hold the boat input
			JTextField dk = new JTextField(); //field to hold the dock input
			
			Object[] input = {
				"Boat Name", bo,
				"Dock Name", dk,
			};
			
			int result = JOptionPane.showConfirmDialog(null, input, "Assign Boat to Dock", JOptionPane.OK_CANCEL_OPTION); //create JOption Pane
			if(result == JOptionPane.OK_OPTION) {	//if OK is selected
				boatName = bo.getText(); //get text
				dockName = dk.getText();
				
				//Flow: check if proper boat name or dock name, then attempt to assign boat
				
				for(int i = 0; i < harb.getDockAssignments().size(); i++) { //iterate through dock assignment requests
					ShippingBoat b = harb.getDockAssignments().get(i);
					if(boatName.equals(b.getName())){ //if a match was found
						validShip = true; //shipping boat found
						for(int j = 0; j < harb.getAvailableDocks().size(); j++) { //iterate through available docks
							Dock d = harb.getAvailableDocks().get(i);
							if(dockName.equals(d.getName())) { //if a valud dock was found
								validDock = true; //dock was found
								JOptionPane.showMessageDialog(null, mast.assignBoatToDock(b, d), "Result", JOptionPane.PLAIN_MESSAGE);
								return; //result displayed, exit
							}
						}
					}
				}
				
				if(validShip == false) { //shipping boat was not found
					JOptionPane.showMessageDialog(null, "Error! " + boatName + " did not make a dock request", "Error Assigning Boat", JOptionPane.ERROR_MESSAGE);
					return; //error message displayed exit
				}
				
				if(validDock == false) { //dock was not found
					JOptionPane.showMessageDialog(null, "Error! " + dockName + " is not available", "Error Assigning Boat", JOptionPane.ERROR_MESSAGE);
					return; //eroor message displayed exit
				}
				
			}
			
		}
		
	}
	
	private class dockRequestsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame window = new JFrame("Dock Assignment Requests");
			JTextArea text = new JTextArea(20, 10);
			
			String info = harb.printDockAssignmentRequests();
			
			text.setText(info);
			text.setEditable(false);
			 
			JScrollPane scroll = new JScrollPane(text);
			
			window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //what happens when window is closed
			window.setSize(400, 400);		
			window.add(scroll);
			window.setVisible(true);
		}
		
	}
	
	private class removeButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String boatName; //string to hold the shipping boat
			
			boolean validShip = false; //checking if there is a valid shipping boat
			
			ShippingBoat boat = null; //reference variable for the shipping boat
			
			JTextField bo = new JTextField(); //field to hold the boat input
			
			Object[] input = {
				"Boat Name", bo,
			};
			
			int result = JOptionPane.showConfirmDialog(null, input, "Assign Boat to Depart", JOptionPane.OK_CANCEL_OPTION); //create JOption Pane
			if(result == JOptionPane.OK_OPTION) {	//if OK is selected
				boatName = bo.getText(); //get text
				
				//Flow: check if proper boat name or dock name, then attempt to assign boat
				
				for(int i = 0; i < harb.getDockDepartures().size(); i++) { //iterate through dock departure requests
					ShippingBoat b = harb.getDockDepartures().get(i);
					if(boatName.equals(b.getName())){ //if a match was found
						validShip = true; //shipping boat found
						JOptionPane.showMessageDialog(null, mast.removeBoatFromDock(b), "Result", JOptionPane.PLAIN_MESSAGE);
						return; //result displayed exit
					}
				}
				
				if(validShip == false) { //shipping boat was not found
					JOptionPane.showMessageDialog(null, "Error! " + boatName + " did not make a depart request", "Error Departing Boat", JOptionPane.ERROR_MESSAGE);
					return; //error message displayed exit
				}
				
			}
			
		}
		
	}
	
	private class departureRequestsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame window = new JFrame("Dock Departure Requests");
			JTextArea text = new JTextArea(20, 10);
			
			String info = harb.printDockDepartureRequests();
			
			text.setText(info);
			text.setEditable(false);
			 
			JScrollPane scroll = new JScrollPane(text);
			
			window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //what happens when window is closed
			window.setSize(400, 400);		
			window.add(scroll);
			window.setVisible(true);
		}
		
	}
	
	private class tugButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String boatName; //string to hold the shipping boat
			String tugName; //string to hold the dock name
			
			boolean validShip = false; //checking if there is a valid shipping boat
			boolean validTug = false; //checking if there is a valid dock
			
			ShippingBoat boat = null; //reference variable for the shipping boat
			TugBoat tug = null; //reference variable for the dock
			
			JTextField bo = new JTextField(); //field to hold the boat input
			JTextField tu = new JTextField(); //field to hold the dock input
			
			Object[] input = {
				"Boat Name", bo,
				"Tug Name", tu,
			};
			
			int result = JOptionPane.showConfirmDialog(null, input, "Assign Tug to Boat", JOptionPane.OK_CANCEL_OPTION); //create JOption Pane
			if(result == JOptionPane.OK_OPTION) {	//if OK is selected
				boatName = bo.getText(); //get text
				tugName = tu.getText();
				
				//Flow: check if proper boat name or tug name, then attempt to assign tug
				
				for(ShippingBoat b : harb.getDockAssignments()) { //iterate through  assignment requests
					if(boatName.equals(b.getName())){ //if a match was found
						validShip = true; //shipping boat found
						for(TugBoat t : harb.getTugs()) { //iterate through available tug
							if(tugName.equals(t.getName())) { //if a valud tug was found
								validTug = true; //tug was found
								JOptionPane.showMessageDialog(null, mast.assignTugBoats(b, t), "Result", JOptionPane.PLAIN_MESSAGE);
							}
						}
					}
				}
				
				for(ShippingBoat b : harb.getDockDepartures()) { //iterate through  departure requests
					if(boatName.equals(b.getName())){ //if a match was found
						validShip = true; //shipping boat found
						for(TugBoat t : harb.getTugs()) { //iterate through available tug
							if(tugName.equals(t.getName())) { //if a valud tug was found
								validTug = true; //tug was found
								JOptionPane.showMessageDialog(null, mast.assignTugBoats(b, t), "Result", JOptionPane.PLAIN_MESSAGE);
							}
						}
					}
				}
				
				if(validShip == false) { //shipping boat was not found
					JOptionPane.showMessageDialog(null, "Error! " + boatName + " did not make a dock or depature request", "Error Assigning Tug", JOptionPane.ERROR_MESSAGE);
					return; //error message displayed exit
				}
				
				if(validTug == false) { //dock was not found
					JOptionPane.showMessageDialog(null, "Error! " + tugName + " is not a valid tug name", "Error Assigning Tug", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	
	private class tugCaptainsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame window = new JFrame("Tugs in System");
			JTextArea text = new JTextArea(20, 10);
			
			String info = harb.printTugBoats();
			
			text.setText(info);
			text.setEditable(false);
			 
			JScrollPane scroll = new JScrollPane(text);
			
			window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //what happens when window is closed
			window.setSize(400, 400);		
			window.add(scroll);
			window.setVisible(true);
		}
		
	}
	
	private class viewDocksListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame window = new JFrame("Docks in System");
			JTextArea text = new JTextArea(20, 10);
			
			String info = harb.printAvailableDocks() + "\n\n" + harb.printUnavailableDocks();
			
			text.setText(info);
			text.setEditable(false);
			 
			JScrollPane scroll = new JScrollPane(text);
			
			window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //what happens when window is closed
			window.setSize(400, 400);		
			window.add(scroll);
			window.setVisible(true);
			
		}
		
	}
	
	private class logoutListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			int result = JOptionPane.showConfirmDialog(null, "Are you sure?", "Logout?", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.OK_OPTION) {
				dispose();
			}
			
		}
	}
}
