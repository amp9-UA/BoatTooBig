package HarborSystem;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;

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

import Hardware.Dock;
import Hardware.Harbor;
import Hardware.ShippingBoat;
import Hardware.TugBoat;
import People.ShippingCaptain;

public class ShippingCaptainMenu extends JFrame {
	private Harbor harb;
	private ShippingCaptain cap;
	
	private JButton capButton; //captain ship button
	private JButton disButton; //disembark boat button
	private JButton requestDockButton; //request dock assignment boat button
	private JButton requestDepartButton; //request dock departure button
	private JButton viewShip; //view all ships
	private JButton addShip; //add ship to system
	
	private JButton logout; //logout button
	
	private JPanel panel1; //object for buttons in the east
	private JPanel panel2; // object for buttons in the center
	private JPanel panel3; //object for buttons in the east
	private JPanel panel4; //panel for button in the sout
	private JPanel panel5;
	
	public ShippingCaptainMenu(Harbor h, ShippingCaptain c) {
		super("Welcome, Shipping Captain " + c.getName() + "!");
		
		this.harb = h;
		this.cap = c;
		
		final int WINDOW_HEIGHT = 250; //number of height pixels
		final int WINDOW_WIDTH = 750; //number of width pixels
		
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
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("images/captainhat.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JLabel hatImage = new JLabel(new ImageIcon(image));
		
		capButton = new JButton("Captain a Ship");
		disButton = new JButton("Diesmbark Current Ship"); //instantiate buttons
		
		capButton.addActionListener(new capButtonListener());
		disButton.addActionListener(new disButtonListener()); //add appropriate action listeners
		
		c.gridx = 0;
		c.gridy = 0;
		
		panel1.add(hatImage, c);
		
		c.gridy = 1;
		
		panel1.add(capButton, c);
		
		c.gridy = 2;
		
		panel1.add(disButton, c); //add buttons to panel
		
		panel1.setBackground(Color.WHITE);
	
	}
	
	public void buildPanel2() {
		panel2 = new JPanel(); //instantiate a new panel
		
		panel2.setLayout(new GridBagLayout()); //set layout of panel
		GridBagConstraints c = new GridBagConstraints(); //gridbag constraints object
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("images/dock.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JLabel dockImage = new JLabel(new ImageIcon(image));
		
		requestDockButton = new JButton("Request Dock Assignment");
		requestDepartButton = new JButton("Request Dock Departure"); //instantiate buttons
		
		requestDockButton.addActionListener(new requestDockButtonListener());
		requestDepartButton.addActionListener(new requestDepartButtonListener()); //add appropriate action listeners
		
		c.gridx = 0;
		c.gridy = 0;
		
		panel2.add(dockImage, c);
		
		c.gridy = 1;
		
		panel2.add(requestDockButton, c);
		
		c.gridy = 2;
		
		panel2.add(requestDepartButton, c); //add buttons to panel
		
		panel2.setBackground(Color.WHITE);
	
	}
	
	public void buildPanel3() {
		panel3 = new JPanel(); //instantiate a new panel
		
		panel3.setLayout(new GridBagLayout()); //set layout of panel
		GridBagConstraints c = new GridBagConstraints(); //gridbag constraints object
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("images/sailboat.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JLabel boatImage = new JLabel(new ImageIcon(image));
		
		viewShip = new JButton("View Shipping Boats in System");
		addShip = new JButton("Adding Shipping Boat to System"); //instantiate buttons
		
		viewShip.addActionListener(new viewShipListener());
		addShip.addActionListener(new addShipListener()); //add appropriate action listeners
		
		c.gridx = 0;
		c.gridy = 0;
		
		panel3.add(boatImage, c);
		
		c.gridy = 1;
		
		panel3.add(viewShip, c);
		
		c.gridy = 2;
		
		panel3.add(addShip, c); //add buttons to panel
		
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
		
		panel5.add(new JLabel("Welcome, " + cap.getName() + ", what would you like to do?"));
		
		panel5.setBackground(Color.WHITE);
	}
	
	private class capButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String boatName; //string to hold the shipping boat
			
			boolean validShip = false; //checking if there is a valid shipping boat
			
			
			ShippingBoat boat = null; //reference variable for the shipping boat
			
			
			JTextField bo = new JTextField(); //field to hold the boat input
			
			
			Object[] input = {
				"Boat Name", bo,
				
			};
			
			int result = JOptionPane.showConfirmDialog(null, input, "Enter Ship to Captain", JOptionPane.OK_CANCEL_OPTION); //create JOption Pane
			if(result == JOptionPane.OK_OPTION) {	//if OK is selected
				boatName = bo.getText(); //get text
				
				
				//Flow: check if proper boat name, then attempt to captain boat
				
				for(ShippingBoat b : harb.getShips()) { //iterate through shipping boats
					if(boatName.equals(b.getName())){ //if a match was found
						validShip = true; //shipping boat found
						JOptionPane.showMessageDialog(null, cap.captainShip(b), "Result", JOptionPane.PLAIN_MESSAGE);
						}
					}
				if(validShip == false) { //shipping boat was not found
					JOptionPane.showMessageDialog(null, "Error! Ship " + boatName + " does not exist in System", "Error Captaining Boat", JOptionPane.ERROR_MESSAGE);
					return; //error message displayed exit
				}
	
			}					
		}
		
	}
	
	private class disButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, cap.disembarkShip(), "Result", JOptionPane.PLAIN_MESSAGE); //prints string result of the disembark ship function
			
		}
		
	}
	
	private class requestDockButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, cap.requestDockAssignment(), "Result", JOptionPane.PLAIN_MESSAGE);
			
		}
		
	}
	
	private class requestDepartButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, cap.requestDockDeparture(), "Result", JOptionPane.PLAIN_MESSAGE);
			
		}
		
	}
	
	private class viewShipListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame window = new JFrame("Ships in System");
			JTextArea text = new JTextArea(20, 10);
			
			String info = harb.printShippingBoats();
			
			text.setText(info);
			text.setEditable(false);
			 
			JScrollPane scroll = new JScrollPane(text);
			
			window.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //what happens when window is closed
			window.setSize(400, 400);		
			window.add(scroll);
			window.setVisible(true);
			
		}
		
	}
	
	private class addShipListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField n = new JTextField();
			JTextField l = new JTextField();
			JTextField w = new JTextField();
			
			int result = JOptionPane.showConfirmDialog(null, new Object[] {"Ship Name:" , n, "Ship Length:", l, "Ship Weight", w}, 
					"Create a Shiping Boat", JOptionPane.OK_CANCEL_OPTION);
			
			if(result == JOptionPane.OK_OPTION) {
				String shipName = n.getText().trim();
				String lengthString = l.getText().trim();
				String weightString = w.getText().trim();
				int shipLength = Integer.parseInt(lengthString);
				int shipWeight = Integer.parseInt(weightString);
				String takenName = null;
				
				for(ShippingBoat s : harb.getShips()) {
					if (shipName.equals(s.getName())) {
						JOptionPane.showMessageDialog(null, "The name " + shipName + " is already taken please try again.", 
								"Error Creating ShippingBoat", JOptionPane.ERROR_MESSAGE);
						takenName = s.getName();
						return;
					}

				}
				
				if(takenName == null) {
					ShippingBoat newShip = new ShippingBoat(shipName, shipWeight, shipLength);
					harb.addShip(newShip);
					JOptionPane.showMessageDialog(null, "Welcome, to the harbor shipping boat " + shipName + "!", "Success!", JOptionPane.PLAIN_MESSAGE);
				}	
			}	
			
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
