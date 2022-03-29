package HarborSystem;

import javax.imageio.ImageIO;
import javax.swing.*;


import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Hardware.Harbor;
import People.DockOperator;
import People.HarborMaster;
import People.ShippingCaptain;
import People.TugCaptain;


public class MainMenu extends JFrame{
	
	private Harbor harb; //object for holding the harbor and all of its info
	private JPanel panel1; //object to hold the users input field
	private JPanel panel2; // object for holding the the login button
	private JPanel panel3; //object for holding the radio button group
	private JButton loginButton; //login button
	private JButton createAccount; //create account button
	
	private JLabel usernameLabel; //login label
	private JLabel passwordLabel; //password Label
	private JTextField usernameInput; //login input
	private JPasswordField passwordInput; //password input
	
	private JLabel typeOfUser; //user label for radio button group
	private JRadioButton harbMast; //port Controller button option
	private JRadioButton dockOp; //dock operator option
	private JRadioButton shipCap; //shipping captain option
	private JRadioButton tugCap; //tug captain option
	private ButtonGroup group; //group to hold all the radio buttons
	
	private HarborMasterMenu mastMenu; //harbor master menu
	private ShippingCaptainMenu shipMenu; //shipping captain menu
	private TugCaptainMenu tugMenu; //tug captain menu
	private DockOperatorMenu opMenu; //dock operator menu
	
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem fileLoad;
	private JMenuItem fileSave;
	
	public MainMenu(Harbor h) { //constructor takes harbor object as input
		super("Welcome to the Harbor!"); //Dispay window message
		this.harb = h;	//get the harbor object to work wif
		
		final int WINDOW_HEIGHT = 600; //number of height pixels
		final int WINDOW_WIDTH = 1000; //number of width pixels
		
		//set Size of window
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		
		//Set what happens when the close button is clicked
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Set the layout of the window
		setLayout(new BorderLayout());
		
		buildPanel1(); //build panel 1
		buildPanel2(); //build panel 2
		buildPanel3(); //build panel 3
		buildMenuBar();
		
		add(panel1, BorderLayout.CENTER); //put login  fields on center
		add(panel2, BorderLayout.SOUTH);	//put login button panel on south side
		add(panel3, BorderLayout.EAST);
		add(new JLabel("Welcome to " + harb.getName() + "!"), BorderLayout.WEST);
		add(new JLabel("Make Sure to Save/Load Before Continuing or Exiting!"), BorderLayout.NORTH);
		
		//pack(); //pack the window
		
		setVisible(true); //display the window
		
	}
	
	public void buildPanel1() {
		panel1 = new JPanel(); //instantiate a new panel
		
		panel1.setLayout(new GridBagLayout()); //set layout of the panel
		GridBagConstraints c = new GridBagConstraints(); //gridbag constraints object
		
		usernameLabel = new JLabel("Enter Username: "); //define username input label
		passwordLabel = new JLabel("Enter Password: "); //define password input label
		
		usernameInput = new JTextField(20); //define username text field
		passwordInput = new JPasswordField(20); //define password text field
		
		c.gridx = 1;
		c.gridy = 0;
		
		panel1.add(new JLabel("Welcome to the Harbor!"), c);
		
		c.gridx = 0;
		c.gridy = 1;
		panel1.add(usernameLabel, c); //add all components to the panel
		
		c.gridx = 1;
		panel1.add(usernameInput, c);
		
		c.gridx = 0;
		c.gridy = 2;
		panel1.add(passwordLabel, c);
		
		c.gridx = 1;
		panel1.add(passwordInput, c);
		
		c.gridx = 1;
		c.gridy = 4;
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("images/port.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JLabel portImage = new JLabel(new ImageIcon(image));
	    
	    panel1.add(portImage, c);
		
		panel1.setBackground(Color.WHITE); //change the color of the window 

	}
	
	public void buildPanel2() {
		panel2 = new JPanel(); //instantiate a new panel
		
		loginButton = new JButton("Login"); //create the button
		createAccount = new JButton("Create Account"); //create the button
		
		loginButton.addActionListener(new LoginButtonListener()); //add the event listener object
		createAccount.addActionListener(new AccountButtonListener()); 
		
		panel2.add(loginButton); //add the button to the panel
		panel2.add(createAccount); //add the button to the panel
		
		panel2.setBackground(Color.BLUE);
	}
	
	public void buildPanel3() {
		panel3 = new JPanel(); //instantiate a new panel
		
		panel3.setLayout(new GridLayout(5, 2));
		
		typeOfUser = new JLabel("Identify User Type:\t");
		
		harbMast = new JRadioButton("Harbor Master", true); //add all of the buttons to represent different users
		dockOp = new JRadioButton("Dock Operator");
		shipCap = new JRadioButton("Shipping Captain");
		tugCap = new JRadioButton("Tug Captain");
		
		harbMast.setBackground(Color.WHITE);
		dockOp.setBackground(Color.WHITE);
		shipCap.setBackground(Color.WHITE);
		tugCap.setBackground(Color.WHITE);
		
		group = new ButtonGroup(); //create a new radio button group
		
		group.add(harbMast); //add the radio buttons to the button group
		group.add(dockOp);
		group.add(shipCap);
		group.add(tugCap);
		
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("images/tugboat.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JLabel tugboatImage = new JLabel(new ImageIcon(image));
	    
	    
	    image = null;
		try {
			image = ImageIO.read(new File("images/captainhat.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JLabel captainImage = new JLabel(new ImageIcon(image));
	
	    
	    image = null;
		try {
			image = ImageIO.read(new File("images/operator.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JLabel operatorImage = new JLabel(new ImageIcon(image));
	    
	    
	    image = null;
		try {
			image = ImageIO.read(new File("images/anchor.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    JLabel anchorImage = new JLabel(new ImageIcon(image));
	    
	    JPanel blank = new JPanel();
	    
	    blank.setBackground(Color.WHITE);
		
	    panel3.setBackground(Color.WHITE);
	    
		panel3.add(typeOfUser); //add the radio button label
		panel3.add(blank);
		panel3.add(harbMast); //add the radio buttons to the panel
		panel3.add(anchorImage);
		panel3.add(dockOp);
		panel3.add(operatorImage);
		panel3.add(shipCap);
		panel3.add(captainImage);
		panel3.add(tugCap);
		panel3.add(tugboatImage);
		
	}
	
	public void buildMenuBar() {
		menuBar = new JMenuBar();
		
		fileMenu = new JMenu("Save/Load");
		
		fileLoad = new JMenuItem("File Load");
		fileSave = new JMenuItem("File Save");
		
		fileSave.addActionListener(new MenuListener());
		fileLoad.addActionListener(new MenuListener());
		
		fileMenu.add(fileSave);
		fileMenu.add(fileLoad);
		
		menuBar.add(fileMenu);
		
		setJMenuBar(menuBar);
	}
	
	private class MenuListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			JMenuItem source = (JMenuItem)(e.getSource());
			
			if(source.equals(fileSave)) {
				handleFileSave();
			}
			else if(source.equals(fileLoad)) {
				handleFileLoad();
			}
		}
		
		private void handleFileSave() {	//what happens when file save is clicked
			Harbor.saveData(harb);
		}
		
		private void handleFileLoad() {	//what happens when file load is clicked
			harb = Harbor.loadData();
		}
	}
	
	private class LoginButtonListener implements ActionListener { //private class that handles the event where login button is pressed
		
		public void actionPerformed(ActionEvent e) { //method that handles the action event 
			String username; //string to hold login field
			String password; //string to hold password field
			
			boolean validUsername = false; //checking if there is a valid username
			boolean validPassword = false; //checking if there is a valid password
			
			HarborMaster mast = null; //reference object for the harbor master
			DockOperator doc = null; //reference object for the dock operator
			ShippingCaptain shi = null; //reference variable for the shipping captain
			TugCaptain tug = null; //reference variable for the tug operator
			
			username = usernameInput.getText(); //get the text from the input
			password = passwordInput.getText(); //get the text from input
			
			if(harbMast.isSelected()) { //if harbor master is attempting to login
				
				//Flow: first check valid username, then check valid password
				
				for(HarborMaster m : harb.getHarborMasters()) { //iterate through the list of harbor masters
					if(username.equals(m.getUsername())) { //if there is a username match
						validUsername = true; //set this field to true
						mast = m; //remember this reference
					}
				}
				
				if(validUsername == false) { //if the username did not find a match
					JOptionPane.showMessageDialog(null, "Username " + username + " Not Found", "Error Logging In!", JOptionPane.ERROR_MESSAGE);
					return; //Error message displayed, exit
				}
				else if(password.equals(mast.getPassword())) { //if for the found username, a password match has been found
					validPassword = true; //set this field to true
				}
				
				if(validPassword == false) { //if the password did not match the username
					JOptionPane.showMessageDialog(null, "Password Did Not Match Given Username", "Error Logging In!", JOptionPane.ERROR_MESSAGE);
					return; //Error message displayed exit,
				}
				else { //at this point, a valid user has been found!
					mastMenu = new HarborMasterMenu(harb, mast); //pull harbor master menu
					usernameInput.setText("");
					passwordInput.setText("");
				}
			}
			
			if(dockOp.isSelected()) { //if harbor master is attempting to login
				
				//Flow: first check valid username, then check valid password
				
				for(DockOperator d : harb.getDockOps()) { //iterate through the list of harbor masters
					if(username.equals(d.getUsername())) { //if there is a username match
						validUsername = true; //set this field to true
						doc = d; //remember this reference
					}
				}
				
				if(validUsername == false) { //if the username did not find a match
					JOptionPane.showMessageDialog(null, "Username " + username + " Not Found", "Error Logging In!", JOptionPane.ERROR_MESSAGE);
					return; //Error message displayed, exit
				}
				else if(password.equals(doc.getPassword())) { //if for the found username, a password match has been found
					validPassword = true; //set this field to true
				}
				
				if(validPassword == false) { //if the password did not match the username
					JOptionPane.showMessageDialog(null, "Password Did Not Match Given Username", "Error Logging In!", JOptionPane.ERROR_MESSAGE);
					return; //Error message displayed exit,
				}
				else { //at this point, a valid user has been found!
					opMenu = new DockOperatorMenu(harb, doc); //dock operator menu
					usernameInput.setText("");
					passwordInput.setText("");
				}
			}
			
			if(shipCap.isSelected()) { //if harbor master is attempting to login
				
				//Flow: first check valid username, then check valid password
				
				for(ShippingCaptain s : harb.getShippingCaps()) { //iterate through the list of harbor masters
					if(username.equals(s.getUsername())) { //if there is a username match
						validUsername = true; //set this field to true
						shi = s; //remember this reference
					}
				}
				
				if(validUsername == false) { //if the username did not find a match
					JOptionPane.showMessageDialog(null, "Username " + username + " Not Found", "Error Logging In!", JOptionPane.ERROR_MESSAGE);
					return; //Error message displayed, exit
				}
				else if(password.equals(shi.getPassword())) { //if for the found username, a password match has been found
					validPassword = true; //set this field to true
				}
				
				if(validPassword == false) { //if the password did not match the username
					JOptionPane.showMessageDialog(null, "Password Did Not Match Given Username", "Error Logging In!", JOptionPane.ERROR_MESSAGE);
					return; //Error message displayed exit,
				}
				else { //at this point, a valid user has been found!
					shipMenu = new ShippingCaptainMenu(harb, shi); //bring shipping captain menu
					usernameInput.setText("");
					passwordInput.setText("");
				}
			}
			
			if(tugCap.isSelected()) { //if harbor master is attempting to login
				
				//Flow: first check valid username, then check valid password
				
				for(TugCaptain t : harb.getTugCaps()) { //iterate through the list of harbor masters
					if(username.equals(t.getUsername())) { //if there is a username match
						validUsername = true; //set this field to true
						tug = t; //remember this reference
					}
				}
				
				if(validUsername == false) { //if the username did not find a match
					JOptionPane.showMessageDialog(null, "Username " + username + " Not Found", "Error Logging In!", JOptionPane.ERROR_MESSAGE);
					return; //Error message displayed, exit
				}
				else if(password.equals(tug.getPassword())) { //if for the found username, a password match has been found
					validPassword = true; //set this field to true
				}
				
				if(validPassword == false) { //if the password did not match the username
					JOptionPane.showMessageDialog(null, "Password Did Not Match Given Username", "Error Logging In!", JOptionPane.ERROR_MESSAGE);
					return; //Error message displayed exit,
				}
				else { //at this point, a valid user has been found!
					tugMenu = new TugCaptainMenu(harb, tug); //bring tug captain menu
					usernameInput.setText("");
					passwordInput.setText("");
				}
			}
		}
	}
	
	private class AccountButtonListener implements ActionListener { //private class that handles the event where create account is pressed
		
		public void actionPerformed(ActionEvent e) { //method that handles the action event
			String name; //string to hold the name of user
			String username; //string to hold the username
			String password; //string to hold the password
			
			
			if(harbMast.isSelected()) {//if attempting to make new account for harbor master
				
				HarborMaster h = null; //this will reference a new harbor master object later
				
				JTextField nam = new JTextField(); //field to hold the name input
				JTextField use = new JTextField(); //field to hold the username input
				JTextField pass = new JTextField(); //field to hold the password input
				
				Object[] input = {	//to hold the fields of input for the j option pane
					"Name: ", nam,
					"Username: ", use,
					"Password: ", pass,
				};
				
				int result = JOptionPane.showConfirmDialog(null, input, "Create New Harbor Master Account", JOptionPane.OK_CANCEL_OPTION); //display the option Pane
				if(result == JOptionPane.OK_OPTION) {//if OK option is selected)
					name = nam.getText();//get the user input
					username = use.getText(); 
					password = pass.getText();
					
					//Flow: Check if username or password combo has been used already, then make account!
					
					for(HarborMaster m : harb.getHarborMasters()) { //go through the systems harbor masters
						if(username.equals(m.getUsername())) {
							if(password.equals(m.getPassword())) {
								JOptionPane.showMessageDialog(null, "This username and password combo is already being used!", "Error Creating Account", JOptionPane.ERROR_MESSAGE); 
								return; //Error message displayed, exit
							}
						}
					}
					
					h = new HarborMaster(name, username, password); //if no errors, create new harbor master object
					harb.addHarborMaster(h); //add harbor master to the system
					JOptionPane.showMessageDialog(null, "Welcome, Harbor Master " + name + "!", "Success!", JOptionPane.PLAIN_MESSAGE);
				}
			}
			
			
			if(dockOp.isSelected()) {//if attempting to make new account for dock operator
				
				DockOperator d = null; //this will reference a new dock operator object later
				
				JTextField nam = new JTextField(); //field to hold the name input
				JTextField use = new JTextField(); //field to hold the username input
				JTextField pass = new JTextField(); //field to hold the password input
				
				Object[] input = {	//to hold the fields of input for the j option pane
					"Name: ", nam,
					"Username: ", use,
					"Password: ", pass,
				};
				
				int result = JOptionPane.showConfirmDialog(null, input, "Create New Dock Operator Account", JOptionPane.OK_CANCEL_OPTION); //display the option Pane
				if(result == JOptionPane.OK_OPTION) {//if OK option is selected)
					name = nam.getText();//get the user input
					username = use.getText(); 
					password = pass.getText();
					
					//Flow: Check if username or password combo has been used already, then make account!
					
					for(DockOperator o : harb.getDockOps()) { //go through the systems dock operators
						if(username.equals(o.getUsername())) {
							if(password.equals(o.getPassword())) {
								JOptionPane.showMessageDialog(null, "This username and password combo is already being used!", "Error Creating Account", JOptionPane.ERROR_MESSAGE); 
								return; //Error message displayed, exit
							}
						}
					}
					
					d = new DockOperator(name, username, password); //if no errors, create new dock operator object
					harb.addDockOp(d); //add harbor master to the system
					JOptionPane.showMessageDialog(null, "Welcome, Dock Operator " + name + "!", "Success!", JOptionPane.PLAIN_MESSAGE);
				}
			}
			
			if(shipCap.isSelected()) {//if attempting to make new account for shipping captain
				
				String license; //need this field to hold the license string
				
				ShippingCaptain s = null; //this will reference a new shipping object later
				
				JTextField nam = new JTextField(); //field to hold the name input
				JTextField use = new JTextField(); //field to hold the username input
				JTextField pass = new JTextField(); //field to hold the password input
				JTextField lic = new JTextField(); //field to hold the license type input
				
				Object[] input = {	//to hold the fields of input for the j option pane
					"Name: ", nam,
					"Username: ", use,
					"Password: ", pass,
					"License Type (A, B, or C): ", lic
				};
				
				int result = JOptionPane.showConfirmDialog(null, input, "Create New Shipping Captain Account", JOptionPane.OK_CANCEL_OPTION); //display the option Pane
				if(result == JOptionPane.OK_OPTION) {//if OK option is selected)
					name = nam.getText();//get the user input
					username = use.getText(); 
					password = pass.getText();
					license = lic.getText();
					
					//Flow: Check if username or password combo has been used already, check license type validation, then make account!
					
					for(ShippingCaptain c : harb.getShippingCaps()) { //go through the systems dock operators
						if(username.equals(c.getUsername())) {
							if(password.equals(c.getPassword())) {
								JOptionPane.showMessageDialog(null, "This username and password combo is already being used!", "Error Creating Account", JOptionPane.ERROR_MESSAGE); 
								return; //Error message displayed, exit
							}
						}
					}
					
					if((license.equals("A") == false) && (license.equals("B") == false ) && (license.equals("C") == false)) { //if the shipping license is not equal to either of these inputs
						JOptionPane.showMessageDialog(null, "Invalid License Type Entered!", "Error Creating Account", JOptionPane.ERROR_MESSAGE);
						return; //Error message displayed, Exit
					}
					
					s = new ShippingCaptain(name, username, password, license.charAt(0)); //if no errors, create new shipping captain object
					harb.addShipCap(s); //add harbor master to the system
					JOptionPane.showMessageDialog(null, "Welcome, Shipping Captain " + name + "!", "Success!", JOptionPane.PLAIN_MESSAGE);
				}
			}
			
			if(tugCap.isSelected()) {//if attempting to make new account for tug captain
				
				TugCaptain t = null; //this will reference a new tug captain object later
				
				JTextField nam = new JTextField(); //field to hold the name input
				JTextField use = new JTextField(); //field to hold the username input
				JTextField pass = new JTextField(); //field to hold the password input
				
				Object[] input = {	//to hold the fields of input for the j option pane
					"Name: ", nam,
					"Username: ", use,
					"Password: ", pass,
				};
				
				int result = JOptionPane.showConfirmDialog(null, input, "Create New Tug Captain Account", JOptionPane.OK_CANCEL_OPTION); //display the option Pane
				if(result == JOptionPane.OK_OPTION) {//if OK option is selected)
					name = nam.getText();//get the user input
					username = use.getText(); 
					password = pass.getText();
					
					//Flow: Check if username or password combo has been used already, then make account!
					
					for(TugCaptain c : harb.getTugCaps()) { //go through the systems dock operators
						if(username.equals(c.getUsername())) {
							if(password.equals(c.getPassword())) {
								JOptionPane.showMessageDialog(null, "This username and password combo is already being used!", "Error Creating Account", JOptionPane.ERROR_MESSAGE); 
								return; //Error message displayed, exit
							}
						}
					}
					
					t = new TugCaptain(name, username, password); //if no errors, create new tug captain object
					harb.addTugCap(t); //add harbor master to the system
					JOptionPane.showMessageDialog(null, "Welcome, Tug Captain " + name + "!", "Success!", JOptionPane.PLAIN_MESSAGE);
				}
			}
		}
	}
}


