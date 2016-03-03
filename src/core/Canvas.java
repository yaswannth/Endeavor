package core;

import java.awt.Graphics;
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
	private Point endPoint;
	private Shapes shapes;

	public Canvas(){

		super();
		new Cursors();
		shapes = new Shapes();
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
	public void mouseClicked(MouseEvent event) {
		startPoint = new Point(event.getX(), event.getY());
		String selectedTool = ToolsMenu.getSelectedChoice();
		if(selectedTool.equals(ToolsMenu.DOT)) {
			shapes.vertices.add(new Vertex(startPoint));
		}else if(selectedTool.equals(ToolsMenu.DELETE)) {
			Vertex selectedPoint = getSelectedPoint(event.getX(),event.getY());
			System.out.println(shapes.vertices.size());
			if(selectedPoint != null) {
				shapes.vertices.remove(selectedPoint);
			}
		}
		repaint();
	}
	
	private Vertex getSelectedPoint(int x, int y) {
		int min = Integer.MAX_VALUE;
		Vertex q = null;
		for (Vertex p : shapes.vertices) {
			int d = dist2(p.x, p.y, x, y);
			if (min > d) {
				min = d;
				q = p;
			}
		}
		return q;
	}
	
	private int dist2(int x1, int y1, int x2, int y2) {
		int x = x1 - x2;
		int y = y1 - y2;
		return x * x + y * y;
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
		if(selectedTool.equals(ToolsMenu.LINE)) {
			
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
		String selectedTool = ToolsMenu.getSelectedChoice();
		if(selectedTool.equals(ToolsMenu.LINE)) {
			
		}else if(selectedTool.equals(ToolsMenu.RECTANGLE)) {

		}else if(selectedTool.equals(ToolsMenu.CIRCLE)) {

		}else if(selectedTool.equals(ToolsMenu.POLYGON)) {

		}else if(selectedTool.equals(ToolsMenu.COLORFILL)) {

		}else if(selectedTool.equals(ToolsMenu.COLORSELECT)) {

		}
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		String selectedTool = ToolsMenu.getSelectedChoice();
		if(selectedTool.equals(ToolsMenu.DOT)) {
			int r = Canvas.NODE_RADIUS;			
			for(Vertex vertex : shapes.vertices){
				g.fillOval(vertex.x - r, vertex.y - r, r*2,r*2);
			}
		}else if(selectedTool.equals(ToolsMenu.LINE)) {
			
		}else if(selectedTool.equals(ToolsMenu.RECTANGLE)) {

		}else if(selectedTool.equals(ToolsMenu.CIRCLE)) {

		}else if(selectedTool.equals(ToolsMenu.POLYGON)) {

		}else if(selectedTool.equals(ToolsMenu.COLORFILL)) {

		}else if(selectedTool.equals(ToolsMenu.COLORSELECT)) {

		}else if(selectedTool.equals(ToolsMenu.DELETE)) {
			int r = Canvas.NODE_RADIUS;			
			for(Vertex vertex : shapes.vertices){
				g.fillOval(vertex.x - r, vertex.y - r, r*2,r*2);
			}
		}
	}

}
