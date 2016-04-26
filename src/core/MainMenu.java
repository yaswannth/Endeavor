package core;

import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
		exit.setActionCommand("Exit");

		openFile.addActionListener(this);
		saveFile.addActionListener(this);
		clearAll.addActionListener(this);
		about.addActionListener(this);
		exit.addActionListener(this);		

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
		if (event.getActionCommand().equals("Exit")) {
			Object[] options = {"Yes","No"};
			String warning = "Do you want to save your work?";
			if(Canvas.drawnObjects.shapes.size() > 0) {
				int option = JOptionPane.showOptionDialog(window, warning, "!", JOptionPane.YES_NO_OPTION, 
						JOptionPane.QUESTION_MESSAGE, null,options,options[1]);
				if(option == 1)
					System.exit(0);
		
			}
		}
		if (event.getActionCommand().equals("Clear All")) {
			Canvas.xArray.clear();
			Canvas.yArray.clear();
			Canvas.drawnObjects.shapes.clear();
			window.refresh();
		}
		if (event.getActionCommand().equals("Save File")) {
			int returnVal = fileWindow.showDialog(window, "Save");
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				String filePath = fileWindow.getSelectedFile().getAbsolutePath();
				if (!filePath.endsWith(".eps"))
					filePath = filePath + ".eps";				
				try
				{
					FileWriter fileWriter = new FileWriter(new File(filePath));
					fileWriter.write(Canvas.drawnObjects.getData());					
					fileWriter.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		if(event.getActionCommand().equals("Open File")) {
			int returnVal = fileWindow.showOpenDialog(window);
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				String filePath = fileWindow.getSelectedFile().getAbsolutePath();
				if (filePath == "" || !filePath.endsWith(".eps"))
				{
					JOptionPane.showMessageDialog(window, "Format not supported!");
					//return;
				}else{
					try {
						BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filePath)));
						Canvas.drawnObjects.loadData(bufferedReader);
						window.refresh();
						bufferedReader.close();
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(window, "File not found!");
					} catch (IOException e) {
						JOptionPane.showMessageDialog(window, "File cannot be read!");
					}
				}
			}
		}
	}
}
