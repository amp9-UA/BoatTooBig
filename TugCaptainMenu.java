package HarborSystem;

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

import Hardware.Harbor;
import Hardware.ShippingBoat;
import Hardware.TugBoat;
import People.ShippingCaptain;
import People.TugCaptain;

public class TugCaptainMenu extends JFrame {
	
	private Harbor harb;
	private TugCaptain cap;
	
	private JButton capButton; //captain ship button
	private JButton disButton; //disembark boat button
	private JButton viewTugs; //view all tugs
	private JButton addTug; //add tug to system
	
	private JButton logout; //logout button
	
	private JPanel panel1; //object for buttons in the east
	private JPanel panel2; // object for buttons in the center
	private JPanel panel3; //object for buttons in the east
	private JPanel panel4; //panel for button in the sout
	private JPanel panel5;
	
	public TugCaptainMenu(Harbor h, TugCaptain c) {
		super("Welcome, Tug Captain " + c.getName() + "!");
		
		this.harb = h;
		this.cap = c;
		
		final int WINDOW_HEIGHT = 250; //number of height pixels
		final int WINDOW_WIDTH = 550; //number of width pixels
		
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
		
		capButton = new JButton("Captain a Tug");
		disButton = new JButton("Diesmbark Current Tug"); //instantiate buttons
		
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
		panel2 = new JPanel();
		
		panel2.setBackground(Color.WHITE);
	}
	
	public void buildPanel3() {
		panel3 = new JPanel(); //instantiate a new panel
		
		panel3.setLayout(new GridBagLayout()); //set layout of panel
		GridBagConstraints c = new GridBagConstraints(); //gridbag constraints object
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("images/tugboat.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JLabel tugImage = new JLabel(new ImageIcon(image));
		
		viewTugs = new JButton("View Tug Boats in System");
		addTug = new JButton("Add Tug Boat to System"); //instantiate buttons
		
		viewTugs.addActionListener(new viewTugListener());
		addTug.addActionListener(new addTugListener()); //add appropriate action listeners
		
		c.gridx = 0;
		c.gridy = 0;
		
		panel3.add(tugImage, c);
		
		c.gridy = 1;
		
		panel3.add(viewTugs, c);
		
		c.gridy = 2;
		
		panel3.add(addTug, c); //add buttons to panel
		
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
		
		panel5.add(new JLabel("Welcome " + cap.getName() + ", what would you like to do?"));
		
		panel5.setBackground(Color.WHITE);
	}
	
	private class capButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String boatName; //string to hold the shipping boat
			
			boolean validShip = false; //checking if there is a valid shipping boat
			
			
			TugBoat boat = null; //reference variable for the shipping boat
			
			
			JTextField bo = new JTextField(); //field to hold the boat input
			
			
			Object[] input = {
				"Tug Name", bo,
				
			};
			
			int result = JOptionPane.showConfirmDialog(null, input, "Enter Tug to Captain", JOptionPane.OK_CANCEL_OPTION); //create JOption Pane
			if(result == JOptionPane.OK_OPTION) {	//if OK is selected
				boatName = bo.getText(); //get text
				
				
				//Flow: check if proper boat name, then attempt to captain boat
				
				for(TugBoat t : harb.getTugs()) { //iterate through shipping boats
					if(boatName.equals(t.getName())){ //if a match was found
						validShip = true; //shipping boat found
						JOptionPane.showMessageDialog(null, cap.captainTug(t), "Result", JOptionPane.PLAIN_MESSAGE);
						}
					}
				if(validShip == false) { //shipping boat was not found
					JOptionPane.showMessageDialog(null, "Error! Tug " + boatName + " does not exist in System", "Error Captaining Tug", JOptionPane.ERROR_MESSAGE);
					return; //error message displayed exit
				}
	
			}					
		}
		
	}
	
	private class disButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, cap.disembarkTug(), "Result", JOptionPane.PLAIN_MESSAGE); //prints string result of the disembark ship function
			
		}
		
	}
	
	private class viewTugListener implements ActionListener{

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
	
	private class addTugListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JTextField n = new JTextField();
			JTextField l = new JTextField();
			
			int result = JOptionPane.showConfirmDialog(null, new Object[] {"Tug Name:" , n, "Tug Length:", l}, 
					"Create a Tug", JOptionPane.OK_CANCEL_OPTION);
			
			if(result == JOptionPane.OK_OPTION) {
				String tugName = n.getText().trim();
				String lengthString = l.getText().trim();
				int tugLength = Integer.parseInt(lengthString);
				String takenName = null;
				
				for(TugBoat t : harb.getTugs()) {
					if (tugName.equals(t.getName())) {
						JOptionPane.showMessageDialog(null, "The name " + tugName + " is already taken please try again.", 
								"Error Creating TugBoat", JOptionPane.ERROR_MESSAGE);
						takenName = t.getName();
						return;
					}

				}
				
				if(takenName == null) {
					TugBoat newTug = new TugBoat(tugName, tugLength);
					harb.addTug(newTug);
					JOptionPane.showMessageDialog(null, "Welcome, to the harbor tug boat " + tugName + "!", "Success!", JOptionPane.PLAIN_MESSAGE);
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
