package core;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import java.net.URL;
public class ToolsMenu extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4100870954459822323L;
	private JToolBar toolBox;
	private JButton dot;
	private JButton line;
	private JButton rectangle;
	private JButton circle;
	private JButton colorFill;
	private JButton colorSelect;
	
	public static final String DOT = "dot";
	public static final String LINE = "line"; 
	public static final String RECTANGLE = "rectangle";
	public static final String CIRCLE = "circle";
	public static final String COLORFILL = "colorFill";
	public static final String COLORSELECT = "colorSelect";
	
	private static String selectedChoice = null;
	
	public ToolsMenu () {
		
		toolBox = new JToolBar("Tools",JToolBar.VERTICAL);
				
		dot = getToolBoxButton(ToolsMenu.DOT);
		line = getToolBoxButton(ToolsMenu.LINE);
		rectangle = getToolBoxButton(ToolsMenu.RECTANGLE);
		circle = getToolBoxButton(ToolsMenu.CIRCLE);
		colorFill = getToolBoxButton(ToolsMenu.COLORFILL);
		colorSelect = getToolBoxButton(ToolsMenu.COLORSELECT);	
				
		toolBox.add(dot);
		toolBox.add(line);
		toolBox.add(rectangle);
		toolBox.add(circle);
		toolBox.add(colorFill);
		toolBox.add(colorSelect);
		
		toolBox.setFloatable(false);
		toolBox.setBorderPainted(false);
		
		this.add(toolBox);
		this.setBorder(BorderFactory.createEtchedBorder());
		this.setSize(192, 32);		
	}
	
	private JButton getToolBoxButton(String name) {		
		String iconPath = "/res/" + name + ".png";
		String selectedIconPath = "/res/" + name + "Selected.png";
		ImageIcon buttonIcon = new ImageIcon((URL)ToolsMenu.class.getResource(iconPath));
		ImageIcon selectedButtonIcon = new ImageIcon((URL)ToolsMenu.class.getResource(selectedIconPath));
		
		JButton button = new JButton(buttonIcon);
		button.setBackground(Color.WHITE);
		button.setToolTipText(name);
		button.addActionListener(this);
		button.setActionCommand(name);
		button.setPressedIcon(selectedButtonIcon);
		//button.setSelectedIcon(selectedButtonIcon);
		return button;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String command = event.getActionCommand();
	    setSelectedChoice(command);
	}
	
	private static void setSelectedChoice(String choice){
		selectedChoice = choice;
	}
	
	public static String getSelectedChoice() {
		return selectedChoice;
	}
}