package core;

import java.awt.Color;
import java.awt.Point;
import java.util.HashSet;

public interface Shape {
	
	public void setColor(Color c);
	public void setVertices(HashSet<Point> vertices);
	public int getId();
}
