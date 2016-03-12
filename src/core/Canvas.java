package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6762936064655965702L;

	private Point startPoint;
	private Point currentPoint = null;
	//private Point endPoint;
	private DrawnObjects drawnObjects;
	private String selectedTool;
	public static Color c;
	private Shape selectedShape;
	private static boolean movingObject = false;
	private static boolean drawingObject = false;
	private ArrayList<Integer> xArray = new ArrayList<Integer>();
	private ArrayList<Integer> yArray = new ArrayList<Integer>();
	private ArrayList<Integer> tmpXArray = new ArrayList<Integer>();
	private ArrayList<Integer> tmpYArray = new ArrayList<Integer>();

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
		
		if(!selectedTool.equals(ToolsMenu.POLYGON)) {
			if(!xArray.isEmpty()){
				Polygon polygon = new Polygon(xArray, yArray, c);
				drawnObjects.shapes.add(polygon);
				xArray.clear();
				yArray.clear();
				drawingObject = false;
			}				
		}
		
	}	

	@Override
	public void mouseMoved(MouseEvent event) {
		int x = event.getX();
		int y = event.getY();
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
		
		if(!selectedTool.equals(ToolsMenu.POLYGON)){
			xArray.clear();
			yArray.clear();
		}
		
		if(selectedTool.equals(ToolsMenu.GRAB)) {
			this.setCursor(Cursors.getCurrentCursor("grabPressed"));
			selectedShape = drawnObjects.getNearestObjects(startPoint);			
			currentPoint = null;
			if(selectedShape != null){
				drawnObjects.shapes.remove(selectedShape);			
				movingObject = true;
			}else{
				this.setCursor(Cursors.getCurrentCursor(ToolsMenu.GRAB));
			}
		}else if(selectedTool.equals(ToolsMenu.LINE)) {
			drawingObject = true;
		}else if(selectedTool.equals(ToolsMenu.RECTANGLE)) {
			drawingObject = true;
		}else if(selectedTool.equals(ToolsMenu.CIRCLE)) {
			drawingObject = true;
		}else if(selectedTool.equals(ToolsMenu.POLYGON)) {
			if(xArray.isEmpty()){
				xArray.add(new Integer(startPoint.x));
				yArray.add(new Integer(startPoint.y));
			}
			drawingObject = true;
		}else if(selectedTool.equals(ToolsMenu.COLORFILL)) {

		}else if(selectedTool.equals(ToolsMenu.COLORSELECT)) {

		}
	}

	@Override
	public void mouseDragged(MouseEvent event) {
		int x = event.getX();
		int y = event.getY();
		CoordinatesSection.displayCoords(x,y);	

		Point previousPoint = startPoint;

		if(currentPoint != null)
			previousPoint = currentPoint;

		currentPoint = new Point(x,y);
		if(movingObject && selectedShape != null){				
			selectedShape.moveTo(previousPoint, currentPoint);			
		}
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent event) {		
		currentPoint = new Point(event.getX(), event.getY());
		selectedTool = ToolsMenu.getSelectedChoice();
		if(movingObject) {
			drawnObjects.shapes.add(selectedShape);
			this.setCursor(Cursors.getCurrentCursor(ToolsMenu.GRAB));
			selectedShape = null;
			movingObject = false;
		}else if(selectedTool.equals(ToolsMenu.LINE)) {
			drawingObject = false;
			Line line = new Line(startPoint, currentPoint, c);
			drawnObjects.shapes.add(line);
		}else if(selectedTool.equals(ToolsMenu.RECTANGLE)) {
			drawingObject = false;
			Rectangle rectangle = new Rectangle(startPoint, currentPoint, c);
			drawnObjects.shapes.add(rectangle);
		}else if(selectedTool.equals(ToolsMenu.CIRCLE)) {
			drawingObject = false;
			Circle circle = new Circle(startPoint, currentPoint, c);
			drawnObjects.shapes.add(circle);
		}else if(selectedTool.equals(ToolsMenu.POLYGON)) {
			xArray.add(new Integer(currentPoint.x));
			yArray.add(new Integer(currentPoint.y));	
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

		if(Canvas.drawingObject && selectedTool.equals(ToolsMenu.CIRCLE)) {
			Circle.drawCircle(startPoint, currentPoint, Canvas.c, g);
		}
		
		if(Canvas.drawingObject && selectedTool.equals(ToolsMenu.POLYGON)) {
			if(xArray.size() == 1){
				Line.drawLine(startPoint, currentPoint, Canvas.c, g);
			}else {
				tmpXArray.addAll(xArray);
				tmpYArray.addAll(yArray);
				tmpXArray.add(new Integer(currentPoint.x));
				tmpYArray.add(new Integer(currentPoint.y));
				Polygon.drawPolygon(tmpXArray, tmpYArray, Canvas.c, g);
				tmpXArray.clear();
				tmpYArray.clear();
			}
		}
	}
}
