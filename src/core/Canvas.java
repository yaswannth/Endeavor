package core;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.EventListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6762936064655965702L;
	
	private Cursor dotCursor;
	private Cursor lineCursor;
	private Cursor rectangleCursor;
	private Cursor circleCursor;
	private Cursor polygonCursor;
	private Cursor colorFillCursor;
	private Cursor colorSelectCursor;
	private CoordinatesSection coordsRegion;

	public Canvas(JPanel coordsArea){
	    
		coordsRegion = (CoordinatesSection)coordsArea;
		dotCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		lineCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
		rectangleCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);		
		circleCursor = getCursor("circle");
		polygonCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
		colorFillCursor = getCursor("colorFill");
		colorSelectCursor = getCursor("colorSelect");
		
		addMouseMotionListener(this);
		addMouseListener(this);	
	}

	private Cursor getCursor(String name){
		String cursorPath = "/res/" + name + "Cursor.png";
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		ImageIcon cursorIcon = new ImageIcon((URL)Canvas.class.getResource(cursorPath));
	    Image cursorImage = cursorIcon.getImage();
	    Point cursorHotSpot = new Point(0,0);
	    Cursor customCursor = toolkit.createCustomCursor(cursorImage, cursorHotSpot, name + "Cursor");
	    return customCursor;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		coordsRegion.displayCoords(x,y);		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		String selectedTool = ToolsMenu.getSelectedChoice();
		if(selectedTool.equals(ToolsMenu.DOT)) {					
			setCursor(dotCursor);
		}else if(selectedTool.equals(ToolsMenu.LINE)) {
			setCursor(lineCursor);					 	
		}else if(selectedTool.equals(ToolsMenu.RECTANGLE)) {
			setCursor(rectangleCursor);					
		}else if(selectedTool.equals(ToolsMenu.CIRCLE)) {
			setCursor(circleCursor);					
		}else if(selectedTool.equals(ToolsMenu.POLYGON)) {
			setCursor(polygonCursor);					
		}else if(selectedTool.equals(ToolsMenu.COLORFILL)) {
			setCursor(colorFillCursor);					
		}else if(selectedTool.equals(ToolsMenu.COLORSELECT)) {
			setCursor(colorSelectCursor);
		}		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
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
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
