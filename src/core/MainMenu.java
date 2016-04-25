package core;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainMenu extends JPanel implements ActionListener{

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
	private JFileChooser fileWindow;
	private ArtGeo window;

	private JMenuItem about;

	public MainMenu(LayoutManager layout, ArtGeo paintWindow) {
		this.window = paintWindow;
		fileWindow = new JFileChooser();
		fileWindow.setFileFilter(new FileNameExtensionFilter("Encapsulated PostScript File(*.eps)", "eps"));


		menuBar = new JMenuBar();		
		fileMenu = new JMenu("File");
		helpMenu = new JMenu("Help");

		openFile = new JMenuItem("Open File");		
		saveFile = new JMenuItem("Save File");		
		clearAll = new JMenuItem("Clear All");
		exit = new JMenuItem("Exit");
		about = new JMenuItem("About");

		openFile.setActionCommand("Open File");
		saveFile.setActionCommand("Save File");
		clearAll.setActionCommand("Clear All");
		about.setActionCommand("About");

		openFile.addActionListener(this);
		saveFile.addActionListener(this);
		clearAll.addActionListener(this);
		about.addActionListener(this);


		exit.setActionCommand("Exit");

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

	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("Save File")) {
			int returnVal = fileWindow.showOpenDialog(window);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String filePath = fileWindow.getSelectedFile().getAbsolutePath();
				if (!filePath.endsWith(".eps"))
					filePath = filePath + ".eps";
			}
		}

		if(event.getActionCommand().equals("Open File")) {
			int returnVal = fileWindow.showOpenDialog(window);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				String filePath = fileWindow.getSelectedFile().getAbsolutePath();
			}
		}
	}
}
