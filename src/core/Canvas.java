package core;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6762936064655965702L;
	private static final int NODE_RADIUS = 7;

	private Point startPoint;
	private Point currentPoint;
	private Point endPoint;
	private DrawnObjects drawnObjects;
	private String selectedTool;
	public static Color c;
	private Shape selectedShape;
	private static boolean movingObject = false;
	private static boolean drawingObject = false;
	
	public Canvas(){

		super();
		new Cursors();
		Canvas.c = Color.BLACK;
		drawnObjects = new DrawnObjects();
		System.out.println("Created");
		
		addMouseMotionListener(this);
		addMouseListener(this);	
	}

	
	@Override
	public void mouseEntered(MouseEvent arg0) {
		selectedTool = ToolsMenu.getSelectedChoice();
		setCursor(Cursors.getCurrentCursor(selectedTool));			
	}	

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		CoordinatesSection.displayCoords(x,y);		
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		startPoint = new Point(event.getX(), event.getY());
		selectedTool = ToolsMenu.getSelectedChoice();
		if(selectedTool.equals(ToolsMenu.DOT)) {
			Vertex vertex = new Vertex(startPoint,c);
			drawnObjects.shapes.add(vertex);
		}else if(selectedTool.equals(ToolsMenu.LINE)) {
			
		}else if(selectedTool.equals(ToolsMenu.RECTANGLE)) {

		}else if(selectedTool.equals(ToolsMenu.CIRCLE)) {

		}else if(selectedTool.equals(ToolsMenu.POLYGON)) {

		}else if(selectedTool.equals(ToolsMenu.COLORFILL)) {

		}else if(selectedTool.equals(ToolsMenu.COLORSELECT)) {

		}
		repaint();
	}
	
	@Override
	public void mousePressed(MouseEvent event) {
		startPoint = new Point(event.getX(), event.getY());
		selectedTool = ToolsMenu.getSelectedChoice();
		
		if(selectedTool.equals(ToolsMenu.GRAB)) {
			this.setCursor(Cursors.getCurrentCursor("grabPressed"));
			selectedShape = drawnObjects.getNearestObjects(startPoint);
			drawnObjects.shapes.remove(selectedShape);			
			movingObject = true;
		}else if(selectedTool.equals(ToolsMenu.LINE)) {
			drawingObject = true;
		}else if(selectedTool.equals(ToolsMenu.RECTANGLE)) {
			drawingObject = true;
		}else if(selectedTool.equals(ToolsMenu.CIRCLE)) {

		}else if(selectedTool.equals(ToolsMenu.POLYGON)) {

		}else if(selectedTool.equals(ToolsMenu.COLORFILL)) {

		}else if(selectedTool.equals(ToolsMenu.COLORSELECT)) {
			
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent event) {
		currentPoint = new Point(event.getX(), event.getY());
		if(movingObject && selectedShape != null){	
			selectedShape.moveTo(currentPoint);
			
		}
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent event) {		
		endPoint = new Point(event.getX(), event.getY());
		selectedTool = ToolsMenu.getSelectedChoice();
		if(movingObject) {
			drawnObjects.shapes.add(selectedShape);
			this.setCursor(Cursors.getCurrentCursor(ToolsMenu.GRAB));
			selectedShape = null;
			movingObject = false;
		}else if(selectedTool.equals(ToolsMenu.LINE)) {
			drawingObject = false;
			Line line = new Line(startPoint, endPoint, c);
			drawnObjects.shapes.add(line);
		}else if(selectedTool.equals(ToolsMenu.RECTANGLE)) {
			drawingObject = false;
			Rectangle rectangle = new Rectangle(startPoint, endPoint, c);
			drawnObjects.shapes.add(rectangle);
		}else if(selectedTool.equals(ToolsMenu.CIRCLE)) {

		}else if(selectedTool.equals(ToolsMenu.POLYGON)) {

		}else if(selectedTool.equals(ToolsMenu.COLORFILL)) {

		}else if(selectedTool.equals(ToolsMenu.COLORSELECT)) {

		}
		this.repaint();
	}
	
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		selectedTool = ToolsMenu.getSelectedChoice();
		if(selectedShape != null)
			selectedShape.draw(g);
		for(Shape s : drawnObjects.shapes){
			s.draw(g);
		}
		
		if(Canvas.drawingObject && selectedTool.equals(ToolsMenu.LINE)) {
			Line.drawLine(startPoint, currentPoint, Canvas.c, g);
		}
		
		if(Canvas.drawingObject && selectedTool.equals(ToolsMenu.RECTANGLE)) {
			Rectangle.drawRectangle(startPoint, currentPoint, Canvas.c, g);
		}
	}
}
