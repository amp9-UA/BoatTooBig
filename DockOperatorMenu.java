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

import Hardware.Dock;
import Hardware.Harbor;
import Hardware.TugBoat;
import People.DockOperator;

public class DockOperatorMenu extends JFrame{
	private Harbor harb;
	private DockOperator op;
	
	private JButton manButton; //man Dock button
	private JButton unmanButton; //remove boat button
	private JButton unloadButton; //unload ship button
	private JButton viewDocksButton; //view dock button
	private JButton newDockButton; //new dock button
	
	
	private JButton logout; //logout button
	
	private JPanel panel1; //object for buttons in the east
	private JPanel panel2; //object for buttons in the center
	private JPanel panel3; //object for buttons in the west
	private JPanel panel4; //panel for button in the sout
	private JPanel panel5;
	
	public DockOperatorMenu(Harbor h, DockOperator o) {
		super("Welcome, Dock Operator " + o.getName());
		
		this.harb = h;
		this.op = o;
		
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
			image = ImageIO.read(new File("images/operator.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JLabel opImage = new JLabel(new ImageIcon(image));
		
		manButton = new JButton("Man a Dock");
		unmanButton = new JButton("Unman Dock"); //instantiate buttons
		
		manButton.addActionListener(new manButtonListener());
		unmanButton.addActionListener(new unmanButtonListener()); //add appropriate action listeners
		
		c.gridx = 0;
		c.gridy = 0;
		
		panel1.add(opImage, c);
		
		c.gridy = 1;
		
		panel1.add(manButton, c);
		
		c.gridy = 2;
		
		panel1.add(unmanButton, c); //add buttons to panel
		
		panel1.setBackground(Color.WHITE);
	
	}
	public void buildPanel2() {
		panel2 = new JPanel();
		
		panel2.setLayout(new GridBagLayout()); 
		GridBagConstraints c = new GridBagConstraints(); //gridbag constraints object
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("images/dock.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JLabel dockImage = new JLabel(new ImageIcon(image));
		
		newDockButton = new JButton("Add a New Dock to System");
		
		newDockButton.addActionListener(new newDockButtonListener());
		
		c.gridx = 0;
		c.gridy = 0;
		
		panel2.add(dockImage, c);
		
		c.gridy = 1;
		
		panel2.add(newDockButton, c);
		
		panel2.setBackground(Color.WHITE);
	}
	
	public void buildPanel3() {
		panel3 = new JPanel(); //instantiate a new panel
		
		panel3.setLayout(new GridBagLayout()); //set layout of panel
		GridBagConstraints c = new GridBagConstraints(); //gridbag constraints object
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("images/depart.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JLabel departImage = new JLabel(new ImageIcon(image));
		
		unloadButton = new JButton("Unload Ship at Dock");
		viewDocksButton = new JButton("View Docks in System"); //instantiate buttons
		
		unloadButton.addActionListener(new unloadButtonListener());
		viewDocksButton.addActionListener(new viewDocksListener()); //add appropriate action listeners
		
		c.gridx = 0;
		c.gridy = 0;
		
		panel3.add(departImage, c);
		
		c.gridy = 1;
		
		panel3.add(unloadButton, c);
		
		c.gridy = 2;
		
		panel3.add(viewDocksButton, c); //add buttons to panel
		
		panel3.setBackground(Color.WHITE);
	
	}
	
	public void buildPanel4() {
		panel4 = new JPanel(); //instantiate a new panel
		
		logout = new JButton("Log Out");
		
		logout.addActionListener(new logoutListener());
		
		panel4.add(logout, BorderLayout.CENTER); //add buttons to panel
		
		panel4.setBackground(Color.BLUE);
	}
	
	public void buildPanel5() {
		panel5 = new JPanel();
		
		panel5.add(new JLabel("Welcome, " + op.getName() + ", what would you like to do?"));
		
		panel5.setBackground(Color.WHITE);
	}
	
	private class manButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String dockName; //string to hold the shipping boat
			
			boolean validDock = false; //checking if there is a valid shipping boat
			
			
			Dock dock = null; //reference variable for the shipping boat
			
			
			JTextField doc = new JTextField(); //field to hold the boat input
			
			
			Object[] input = {
				"Dock Name", doc,	
			};
			
			int result = JOptionPane.showConfirmDialog(null, input, "Enter Dock to Man", JOptionPane.OK_CANCEL_OPTION); //create JOption Pane
			if(result == JOptionPane.OK_OPTION) {	//if OK is selected
				dockName = doc.getText(); //get text
				
				
				//Flow: check if proper boat name, then attempt to captain boat
				
				for(Dock d : harb.getAvailableDocks()) { //iterate through docks
					if(dockName.equals(d.getName())){ //if a match was found
						validDock = true; //dock found
						JOptionPane.showMessageDialog(null, op.manDock(d), "Result", JOptionPane.PLAIN_MESSAGE);
						}
					}
				if(validDock == false) { //shipping boat was not found
					JOptionPane.showMessageDialog(null, "Error! Dock " + dockName + " is not available", "Error Manning Dock", JOptionPane.ERROR_MESSAGE);
					return; //error message displayed exit
				}
	
			}					
		}
		
	}
	
	private class unmanButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, op.unManDock(), "Result", JOptionPane.PLAIN_MESSAGE); //prints string result of the disembark ship function
			
		}
		
	}
	
	private class unloadButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null, op.unloadShip(), "Result", JOptionPane.PLAIN_MESSAGE);
			
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
	
	private class newDockButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			JTextField n = new JTextField();
			JTextField max = new JTextField();
			
			int result = JOptionPane.showConfirmDialog(null, new Object[] {"Dock Name:" , n, "Max Boat Size:", max}, 
					"Create a Dock", JOptionPane.OK_CANCEL_OPTION);
			
			if(result == JOptionPane.OK_OPTION) {
				String dockName = n.getText().trim();
				String maxString = max.getText().trim();
				int maxLength = Integer.parseInt(maxString);
				String takenName = null;
				
				for(Dock d : harb.getAvailableDocks()) {
					if (dockName.equals(d.getName())) {
						JOptionPane.showMessageDialog(null, "The name " + dockName + " is already taken please try again.", 
								"Error Creating Dock", JOptionPane.ERROR_MESSAGE);
						takenName = d.getName();
						return;
					}

				}
				for(Dock d : harb.getUnavailableDocks()) {
					if (dockName.equals(d.getName())) {
						JOptionPane.showMessageDialog(null, "The name " + dockName + " is already taken please try again.", 
								"Error Creating Dock", JOptionPane.ERROR_MESSAGE);
						takenName = d.getName();
						return;
					}

				}
				if(harb.getAvailableDocks().size() + harb.getUnavailableDocks().size() == 5) {
					JOptionPane.showMessageDialog(null, "Error! Cannot add more than 5 docks to this system", "Error Creating Dock", JOptionPane.ERROR_MESSAGE);
				}
				else if(takenName == null) {
					Dock newDock = new Dock(dockName, maxLength);
					harb.addDock(newDock);
					JOptionPane.showMessageDialog(null, "Dock " + dockName + " Added!", "Success!", JOptionPane.PLAIN_MESSAGE);
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
