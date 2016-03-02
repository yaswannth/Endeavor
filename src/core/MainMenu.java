package core;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainMenu extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenu helpMenu;
	
	private JMenuItem openFile;
	private JMenuItem saveFile;
	private JMenuItem clearAll;
	private JMenuItem exit;
	
	private JMenuItem about;
	
	public MainMenu(LayoutManager layout) {
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
		
		this.setLayout(layout);
		this.setBackground(Color.WHITE);
		this.add(menuBar);
	}
}
