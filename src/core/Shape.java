package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;

public interface Shape {
	
	public void setColor(Color c);
	public int getId();
	public int getType();
	public Color getColor();
	public void draw(Graphics g);
}
