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
	private JPanel menuPane;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu helpMenu;
	
	private JMenuItem openFile;
	private JMenuItem saveFile;
	private JMenuItem clearAll;
	private JMenuItem exit;
	
	private JMenuItem about;
	
	public ArtGeo(){
		
		menuPane = new JPanel(new BorderLayout());
		menuBar = new JMenuBar();		
		fileMenu = new JMenu("File");
		helpMenu = new JMenu("Help");
		
		openFile = new JMenuItem("Open File");
		saveFile = new JMenuItem("Save File");
		clearAll = new JMenuItem("Clear All");
		exit = new JMenuItem("Exit");
		about = new JMenuItem("About");
		
		fileMenu.add(openFile);
		fileMenu.add(saveFile);
		fileMenu.addSeparator();
		fileMenu.add(clearAll);
		fileMenu.addSeparator();
		fileMenu.add(exit);
		
		helpMenu.add(about);
		
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		menuPane.setBackground(Color.WHITE);
		menuPane.add(menuBar);
		
		
		setTitle("Geo Art");
		setSize(500, 500);	
		getContentPane().add(menuPane,BorderLayout.NORTH);
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
