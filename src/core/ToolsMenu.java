package core;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import java.net.URL;
public class ToolsMenu extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4100870954459822323L;
	private JToolBar toolsMenu;
	private JButton dots;
	private JButton lines;
	private JButton rectangles;
	private JButton circles;
	private JButton colorFill;
	private JButton colorSelect;
	
	private ImageIcon dotsIcon;
	private ImageIcon linesIcon;
	private ImageIcon rectIcon;
	private ImageIcon circleIcon;
	private ImageIcon fillIcon;
	private ImageIcon selectIcon;
	
	public ToolsMenu () {
		
		toolsMenu = new JToolBar("Tools",JToolBar.VERTICAL);
		
		dotsIcon = new ImageIcon((URL)ToolsMenu.class.getResource("/res/dot.jpg"));
		linesIcon = new ImageIcon((URL)ToolsMenu.class.getResource("/res/line.png"));
		rectIcon = new ImageIcon((URL)ToolsMenu.class.getResource("/res/rectangle.jpg"));
		circleIcon = new ImageIcon((URL)ToolsMenu.class.getResource("/res/circle.jpg"));
		fillIcon = new ImageIcon((URL)ToolsMenu.class.getResource("/res/colorFill.png"));
		selectIcon = new ImageIcon((URL)ToolsMenu.class.getResource("/res/colorSelect.png"));
		
		dots = new JButton(dotsIcon);
		lines = new JButton(linesIcon);
		rectangles = new JButton(rectIcon);
		circles = new JButton(circleIcon);
		colorFill = new JButton(fillIcon);
		colorSelect = new JButton(selectIcon);	
		
		dots.setBackground(Color.WHITE);
		lines.setBackground(Color.WHITE);
		rectangles.setBackground(Color.WHITE);
		circles.setBackground(Color.WHITE);
		colorFill.setBackground(Color.WHITE);
		colorSelect.setBackground(Color.WHITE);
		
		toolsMenu.add(dots);
		toolsMenu.add(lines);
		toolsMenu.add(rectangles);
		toolsMenu.add(circles);
		toolsMenu.add(colorFill);
		toolsMenu.add(colorSelect);
		
		toolsMenu.setFloatable(false);
		toolsMenu.setBorderPainted(false);
		
		this.add(toolsMenu);
		this.setSize(192, 32);
		
	}
}