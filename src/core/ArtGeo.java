package core;

import java.awt.*; //Using AWT containers and components
import java.awt.event.*; //Using AWT events and listener interfaces
import java.awt.geom.Line2D;

import javax.swing.*; //Using Swing components and containers

//A Swing GUI app inherits from top-level container
//javax.swing.JFrame
public class ArtGeo extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ArtGeo(){
		setTitle("Geo Art");
		setSize(500, 500);	
		setVisible(true);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ArtGeo();
			}
		});
	}
}
