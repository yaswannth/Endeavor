package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public interface Shape {
	
	public float MAX_COLOR = 255f;
	
	public void setColor(Color c);
	public int getId();
	public int getType();
	public Color getColor();
	public void draw(Graphics g);
	public double getDist(Point p);
	public void moveTo(Point p1, Point p2);
	public String getShapeScript(int ury);
	public float[] getRGB();
	public int getMinX();
	public int getMinY(int ury);
	public int getMaxX();
	public int getMaxY();
}
