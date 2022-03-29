package HarborSystem;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import Hardware.Dock;
import Hardware.ShippingBoat;

public class DockSelectScreen extends JFrame {
	 
	private int numDocks;
	private ShippingBoat ship;
	private ArrayList<Dock> docks;
	private DragPanel dragPanel;
	 
	
	public static void main(String[] args) {
		DockSelectScreen window = new DockSelectScreen(new ShippingBoat("Ship1", 100, 100));
	}
	
	
	 public DockSelectScreen(ShippingBoat s/*ArrayList<Dock> d */){
		 
		 ship = s;
		 //docks = d;
		 
		 dragPanel = new DragPanel(ship);
		 
		 
		 
		 
		 this.add(dragPanel);
		 this.setTitle("Select Which Dock The Boat Should Go To!");
		 this.setSize(12000, 600);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 this.setVisible(true);
	 }
	 
	 private class DragPanel extends JPanel{
		 
		 private ShippingBoat ship;
		 private boolean dragValid = false;
		 ImageIcon shipImage = new ImageIcon("images/boat.png");
		 final int WIDTH = shipImage.getIconWidth();
		 final int HEIGHT = shipImage.getIconHeight();
		 Point imageCorner;
		 Point prevPt;
		 private JLabel shipName;
		 
		 public DragPanel(ShippingBoat s) {
			 shipName = new JLabel(s.getName());
			 
			 imageCorner = new Point(0,0);
			 ClickListener clickListener = new ClickListener();
			 DragListener dragListener = new DragListener();
			 
			 
			 this.addMouseListener(clickListener);
			 this.addMouseMotionListener(dragListener);
			 
			 this.add(shipName);
			 
		 }
		 
		 public void paintComponent(Graphics g) {
			 super.paintComponent(g);
			 shipImage.paintIcon(this, g, (int)imageCorner.getX(), (int)imageCorner.getY());
		 }
		 
		 
		 private class ClickListener extends MouseAdapter{
			 
			  public void mousePressed(MouseEvent e) {
			   prevPt = e.getPoint();
			   dragValid = (e.getPoint().getX() > imageCorner.getX()) && 
	                    (e.getPoint().getX() < (imageCorner.getX() + WIDTH)) &&
	                    (e.getPoint().getY() > imageCorner.getY()) &&
	                    (e.getPoint().getY() < (imageCorner.getY() + HEIGHT));
			  }
		 }
		 
		 private class DragListener extends MouseMotionAdapter{
			  
			  public void mouseDragged(MouseEvent e) {
			   
			   Point currentPt = e.getPoint();
			   if(dragValid) {
				   imageCorner.translate(
			     
						   (int)(currentPt.getX() - prevPt.getX()),
						   (int)(currentPt.getY() - prevPt.getY())
						   );
			   }
			   prevPt = currentPt;
			   repaint();   
			  }  
		}
	}
	 
}

