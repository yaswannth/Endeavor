package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

public class Rectangle implements Shape{
	static AtomicInteger nextId = new AtomicInteger();
	HashSet<Point> vertexPoints;
	public int x;
	public int y;
	public int x1;
	public int y1;
	public int x2;
	public int y2;
	public int xc;
	public int yc;
	public int height;
	public int width;
	Color c;
	int id;
	int type;
	
	public Rectangle(Point s, Point e, Color c) {
		this.x1 = s.x;
		this.y1 = s.y;
		
		this.x2 = e.x;
		this.y2 = e.y;
		
		this.x = Math.min(x1, x2);
		this.y = Math.min(y1, y2);
		
		this.height = Math.abs(y2 - y1);
		this.width = Math.abs(x2 - x1);
		
		this.xc = (x2 + x1) /2;
		this.yc = (y2 + y1) /2;
		this.c = c;
		id = nextId.incrementAndGet();
		type = DrawnObjects.RECTANGLE;
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(this.c);
		g.drawRect(this.x, this.y, this.width, this.height);
	}
	
	@Override
	public void moveTo(Point p1, Point p2) {
		int xdiff = p2.x - p1.x;
		int ydiff = p2.y - p1.y;
		
		this.x1 = this.x1 + xdiff;
		this.x2 = this.x2 + xdiff;
		this.y1 = this.y1 + ydiff;
		this.y2 = this.y2 + ydiff;

		this.x = Math.min(this.x1, this.x2);
		this.y = Math.min(this.y1, this.y2);
		
		this.xc = (this.x2 + this.x1) /2;
		this.yc = (this.y2 + this.y1) /2; 
	}
	
	@Override
	public double getDist(Point p) {
		int x = p.x;
		int y = p.y;
		double dist1 = Integer.MAX_VALUE;
		double dist2 = Integer.MAX_VALUE;
		if((x > this.x1 && x < this.x2) || (x < this.x1 && x > this.x2) || x == this.x1 || x == this.x2)
			dist1 = Math.abs(height/2 - Math.abs(this.yc - y));
		if((y > this.y1 && y < this.y2) || (y < this.y1 && y > this.y2) || y == this.y1 || y == this.y2)
			dist2 = Math.abs(width/2 - Math.abs(this.xc - x));
		return Math.min(dist1,dist2);
	}
	
	@Override
	public void setColor(Color c) {
		this.c = c;
	}
	
	@Override
	public int getId(){
		return this.id;
	}	
	
	@Override
	public int getType(){
		return this.type;
	}
	
	@Override
	public Color getColor(){
		return this.c;
	}
	
	public static void drawLine(Point p1, Point p2, Color c2, Graphics g){
		g.setColor(c2);
		g.drawLine(p1.x, p1.y, p2.x, p2.y);
	}

	public static void drawRectangle(Point p1, Point p2, Color c2, Graphics g) {
		int x1 = p1.x;
		int y1 = p1.y;
		
		int x2 = p2.x;
		int y2 = p2.y;
		
		int height = Math.abs(y2 - y1);
		int width = Math.abs(x2 - x1);
		
		int x = Math.min(x1, x2);
		int y = Math.min(y1, y2);
		g.setColor(c2);
		g.drawRect(x, y, width, height);
	}

}
