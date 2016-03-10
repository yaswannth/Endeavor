package core;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import java.net.URL;
import java.util.Vector;
public class ToolsMenu extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4100870954459822323L;
	private JToolBar toolBox;
	private JButton grab;
	private JButton dot;
	private JButton line;
	private JButton rectangle;
	private JButton circle;
	private JButton polygon;
	private JButton colorFill;
	private JButton colorSelect;
	private JLabel strokeSizeLabel;
	private JButton delete;
	
	public static final String GRAB = "grab";
	public static final String DOT = "dot";
	public static final String LINE = "line"; 
	public static final String RECTANGLE = "rectangle";
	public static final String CIRCLE = "circle";
	public static final String POLYGON = "polygon";
	public static final String COLORFILL = "colorFill";
	public static final String COLORSELECT = "colorSelect";
	public static final String DELETE = "delete";
	
	private static String selectedChoice = "none";
	private static Vector<Integer> STROKESIZES;
	
	private static JComboBox<Integer> brushStrokeSize;
	
	public ToolsMenu () {
		STROKESIZES = new Vector<Integer>();
		for(int i = 10; i <= 36; i++)
			STROKESIZES.addElement(i);
		
		toolBox = new JToolBar("Tools",JToolBar.VERTICAL);
			
		grab = getToolBoxButton(ToolsMenu.GRAB);
		dot = getToolBoxButton(ToolsMenu.DOT);
		line = getToolBoxButton(ToolsMenu.LINE);
		rectangle = getToolBoxButton(ToolsMenu.RECTANGLE);
		circle = getToolBoxButton(ToolsMenu.CIRCLE);
		polygon = getToolBoxButton(ToolsMenu.POLYGON);
		colorFill = getToolBoxButton(ToolsMenu.COLORFILL);
		colorSelect = getToolBoxButton(ToolsMenu.COLORSELECT);
		delete = getToolBoxButton(ToolsMenu.DELETE);
		
		strokeSizeLabel = new JLabel("Size");
		strokeSizeLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		brushStrokeSize = new JComboBox<Integer>(STROKESIZES);
		brushStrokeSize.setSelectedIndex(0);
		brushStrokeSize.addActionListener(this);
		brushStrokeSize.setMaximumSize(new Dimension(52, 52));
		brushStrokeSize.setAlignmentX(CENTER_ALIGNMENT);
			
		toolBox.add(grab);
		toolBox.add(dot);
		toolBox.add(line);
		toolBox.add(rectangle);
		toolBox.add(circle);
		toolBox.add(polygon);
		toolBox.add(colorFill);
		toolBox.add(colorSelect);
		toolBox.add(delete);
		toolBox.add(strokeSizeLabel);
		toolBox.add(brushStrokeSize);
		
		toolBox.setFloatable(false);
		toolBox.setBorderPainted(false);
		
		this.add(toolBox);
		this.setBorder(BorderFactory.createEtchedBorder());	
	}
	
	private JButton getToolBoxButton(String name) {		
		String iconPath = "/res/" + name + ".png";
		String selectedIconPath = "/res/" + name + "Selected.png";
		ImageIcon buttonIcon = new ImageIcon((URL)ToolsMenu.class.getResource(iconPath));
		ImageIcon selectedButtonIcon = new ImageIcon((URL)ToolsMenu.class.getResource(selectedIconPath));
		
		JButton button = new JButton(buttonIcon);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setBackground(Color.WHITE);
		button.setToolTipText(name);
		button.addActionListener(this);
		button.setActionCommand(name);
		button.setPressedIcon(selectedButtonIcon);
		button.setAlignmentX(CENTER_ALIGNMENT);
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