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
	public static DrawnObjects drawnObjects;
	private String selectedTool;
	private String previousSelectedTool;
	public static Color c;
	public static boolean toolChange = false;
	private Shape selectedShape;
	private Shape nearestShape = null;
	private Color previousColor = c;
	private String previousDrawingType;
	private static boolean movingObject = false;
	private static boolean drawingObject = false;
	public static ArrayList<Integer> xArray = new ArrayList<Integer>();
	public static ArrayList<Integer> yArray = new ArrayList<Integer>();
	private ArrayList<Integer> tmpXArray = new ArrayList<Integer>();
	private ArrayList<Integer> tmpYArray = new ArrayList<Integer>();

	public Canvas(){

		super();
		new Cursors();
		Canvas.c = Color.BLACK;
		drawnObjects = new DrawnObjects();
		System.out.println("Created");

		previousDrawingType = ToolsMenu.DRAWING_TYPE;
		addMouseMotionListener(this);
		addMouseListener(this);	
	}



	@Override
	public void mouseEntered(MouseEvent arg0) {
		if(selectedTool != null)
			previousSelectedTool = new String(selectedTool);
		selectedTool = ToolsMenu.getSelectedChoice();
		setCursor(Cursors.getCurrentCursor(selectedTool));

		/*if(previousSelectedTool.equals(ToolsMenu.POLYGON) && toolChange){
			toolChange = false;
			if(!xArray.isEmpty()){
				drawingObject = false;
				Polygon poly = new Polygon(xArray,yArray,previousColor, previousDrawingType);
				drawnObjects.shapes.add(poly);
				xArray.clear();
				yArray.clear();
			}
		}*/
		
		previousDrawingType = ToolsMenu.DRAWING_TYPE;
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
			nearestShape = drawnObjects.getNearestObjects(startPoint);
			if(nearestShape != null){
				if(nearestShape.getType() == DrawnObjects.DOT){
					int x = ((Vertex)nearestShape).x;
					int y = ((Vertex)nearestShape).y;
					startPoint = new Point(x,y);
				}
			}
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
		Point previousPoint = currentPoint;
		currentPoint = new Point(event.getX(), event.getY());
		selectedTool = ToolsMenu.getSelectedChoice();

		if(movingObject) {
			drawnObjects.shapes.add(selectedShape);
			this.setCursor(Cursors.getCurrentCursor(ToolsMenu.GRAB));
			selectedShape = null;
			movingObject = false;
		}else if(selectedTool.equals(ToolsMenu.LINE)) {
			drawingObject = false;
			nearestShape = drawnObjects.getNearestObjects(currentPoint);
			System.out.println(currentPoint.x + " " + currentPoint.y);
			if(nearestShape != null){
				if(nearestShape.getType() == DrawnObjects.DOT){
					System.out.println("dot");
					int x = ((Vertex)nearestShape).x;
					int y = ((Vertex)nearestShape).y;

					currentPoint = new Point(x,y);
				}
			}
			System.out.println(currentPoint.x + " " + currentPoint.y);
			Line line = new Line(startPoint, currentPoint, c);
			drawnObjects.shapes.add(line);
		}else if(selectedTool.equals(ToolsMenu.RECTANGLE)) {
			drawingObject = false;
			Rectangle rectangle = new Rectangle(startPoint, currentPoint, c, ToolsMenu.DRAWING_TYPE);
			drawnObjects.shapes.add(rectangle);
		}else if(selectedTool.equals(ToolsMenu.CIRCLE)) {
			drawingObject = false;
			double radius = Math.sqrt((startPoint.x - currentPoint.x)* (startPoint.x - currentPoint.x)+ 
					(startPoint.y - currentPoint.y)*(startPoint.y - currentPoint.y));
			Circle circle = new Circle(startPoint, radius, c, ToolsMenu.DRAWING_TYPE);
			drawnObjects.shapes.add(circle);
		}else if(selectedTool.equals(ToolsMenu.POLYGON)) {
			drawingObject = false;
			xArray.add(new Integer(currentPoint.x));
			yArray.add(new Integer(currentPoint.y));
		}
		this.repaint();
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
		
		if(selectedTool.equals(ToolsMenu.POLYGON)){
			toolChange = false;
			if(!xArray.isEmpty()){
				drawingObject = false;
				Polygon poly = new Polygon(xArray,yArray,previousColor, previousDrawingType);
				drawnObjects.shapes.add(poly);
				xArray.clear();
				yArray.clear();
			}
		}
		previousColor = Canvas.c;
		previousDrawingType = ToolsMenu.DRAWING_TYPE;
		previousSelectedTool = selectedTool;

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
			Rectangle.drawRectangle(startPoint, currentPoint, Canvas.c, g, ToolsMenu.DRAWING_TYPE);
		}

		if(Canvas.drawingObject && selectedTool.equals(ToolsMenu.CIRCLE)) {
			double radius = Math.sqrt((startPoint.x - currentPoint.x)* (startPoint.x - currentPoint.x)+ 
					(startPoint.y - currentPoint.y)*(startPoint.y - currentPoint.y));
			Circle.drawCircle(startPoint, radius, Canvas.c, g, ToolsMenu.DRAWING_TYPE);
		}

		if(Canvas.drawingObject && selectedTool.equals(ToolsMenu.POLYGON)) {
			tmpXArray.addAll(xArray);
			tmpYArray.addAll(yArray);
			tmpXArray.add(new Integer(currentPoint.x));
			tmpYArray.add(new Integer(currentPoint.y));
			Polygon.drawPolygon(tmpXArray, tmpYArray, Canvas.c, g, ToolsMenu.DRAWING_TYPE);
			tmpXArray.clear();
			tmpYArray.clear();

		}

		if(!Canvas.drawingObject && !xArray.isEmpty() && selectedTool.equals(ToolsMenu.POLYGON)){
			Polygon.drawPolygon(xArray, yArray, Canvas.c, g, ToolsMenu.DRAWING_TYPE);
		}
	}
}
