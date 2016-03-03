package core;

import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6762936064655965702L;
	private static final int NODE_RADIUS = 7;

	private CoordinatesSection coordsRegion;
	private Point startPoint;
	private Point endPoint;

	public Canvas(){

		super();
		new Cursors();
		System.out.println("Created");
		
		addMouseMotionListener(this);
		addMouseListener(this);	
	}

	

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		CoordinatesSection.displayCoords(x,y);		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		String selectedTool = ToolsMenu.getSelectedChoice();
		setCursor(Cursors.getCurrentCursor(selectedTool));			
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent event) {
		startPoint = new Point(event.getX(), event.getY());
		String selectedTool = ToolsMenu.getSelectedChoice();
		if(selectedTool.equals(ToolsMenu.DOT)) {
			
		}else if(selectedTool.equals(ToolsMenu.LINE)) {

		}else if(selectedTool.equals(ToolsMenu.RECTANGLE)) {

		}else if(selectedTool.equals(ToolsMenu.CIRCLE)) {

		}else if(selectedTool.equals(ToolsMenu.POLYGON)) {

		}else if(selectedTool.equals(ToolsMenu.COLORFILL)) {

		}else if(selectedTool.equals(ToolsMenu.COLORSELECT)) {

		}
	}

	@Override
	public void mouseReleased(MouseEvent event) {
		endPoint = new Point(event.getX(), event.getY());
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		String selectedTool = ToolsMenu.getSelectedChoice();
		if(selectedTool.equals(ToolsMenu.DOT)) {
			int r = Canvas.NODE_RADIUS;
			g.fillOval(startPoint.x - r, startPoint.y - r, r*2,r*2);
		}else if(selectedTool.equals(ToolsMenu.LINE)) {

		}else if(selectedTool.equals(ToolsMenu.RECTANGLE)) {

		}else if(selectedTool.equals(ToolsMenu.CIRCLE)) {

		}else if(selectedTool.equals(ToolsMenu.POLYGON)) {

		}else if(selectedTool.equals(ToolsMenu.COLORFILL)) {

		}else if(selectedTool.equals(ToolsMenu.COLORSELECT)) {

		}
	}

}
