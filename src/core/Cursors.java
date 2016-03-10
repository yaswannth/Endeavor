package core;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.net.URL;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class Cursors {
	
	private Cursor grabCursor;
	private Cursor grabPressedCursor;
	private Cursor dotCursor;
	private Cursor lineCursor;
	private Cursor rectangleCursor;
	private Cursor circleCursor;
	private Cursor polygonCursor;
	private Cursor colorFillCursor;
	private Cursor colorSelectCursor;
	private Cursor deleteCursor;
	
	public static Cursor currentCursor;
	public static HashMap<String, Cursor> cursorMap = new HashMap<String, Cursor>();
	
	public Cursors() {

		grabCursor = createCustomCursor("grab");
		grabPressedCursor = createCustomCursor("grabPressed");
		dotCursor = new Cursor(Cursor.DEFAULT_CURSOR);
		lineCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
		rectangleCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);		
		circleCursor = createCustomCursor("circle");
		polygonCursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
		colorFillCursor = createCustomCursor("colorFill");
		colorSelectCursor = createCustomCursor("colorSelect");
		deleteCursor = createCustomCursor("delete");
		
		cursorMap.put(ToolsMenu.GRAB, grabCursor);
		cursorMap.put("grabPressed", grabPressedCursor);
		cursorMap.put(ToolsMenu.DOT, dotCursor);
		cursorMap.put(ToolsMenu.LINE, lineCursor);
		cursorMap.put(ToolsMenu.RECTANGLE, rectangleCursor);
		cursorMap.put(ToolsMenu.CIRCLE, circleCursor);
		cursorMap.put(ToolsMenu.POLYGON, polygonCursor);
		cursorMap.put(ToolsMenu.COLORFILL, colorFillCursor);
		cursorMap.put(ToolsMenu.COLORSELECT, colorSelectCursor);
		cursorMap.put(ToolsMenu.DELETE, deleteCursor);
		
	}
	
	public static Cursor getCurrentCursor(String name) {
		return cursorMap.get(name);
	}

	private Cursor createCustomCursor(String name){
		String cursorPath = "/res/" + name + "Cursor.png";
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		ImageIcon cursorIcon = new ImageIcon((URL)Canvas.class.getResource(cursorPath));
		Image cursorImage = cursorIcon.getImage();
		Point cursorHotSpot = new Point(0,0);
		Cursor customCursor = toolkit.createCustomCursor(cursorImage, cursorHotSpot, name + "Cursor");
		return customCursor;
	}
}
