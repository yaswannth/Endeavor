package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

public class Circle implements Shape {
	static AtomicInteger nextId = new AtomicInteger();
	HashSet<Point> vertexPoints;
	public int x;
	public int y;
	public int x1;
	public int y1;
	public int x2;
	public int y2;
	public int height;
	public int width;
	Color c;
	int id;
	int type;
	double r;
	
	public Circle(Point s, Point e, Color c) {
		this.x1 = s.x;
		this.y1 = s.y;
		
		this.x2 = e.x;
		this.y2 = e.y;
		
		this.r = Math.sqrt((this.x1-this.x2)* (this.x1-this.x2)+ (this.y1-this.y2)*(this.y1-this.y2));
		
		this.x = (int) (this.x1 - r);
		this.y = (int) (this.y1 - r);		
		
		this.height = (int) (2 * this.r);
		this.width = (int) (2 * this.r);
		
		this.c = c;
		id = nextId.incrementAndGet();
		type = DrawnObjects.CIRCLE;
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(this.c);
		g.drawOval(this.x, this.y, this.width, this.height);
	}
	
	@Override
	public void moveTo(Point p1, Point p2) {
		int xdiff = p2.x - p1.x;
		int ydiff = p2.y - p1.y;
		
		this.x1 = this.x1 + xdiff;
		this.x2 = this.x2 + xdiff;
		this.y1 = this.y1 + ydiff;
		this.y2 = this.y2 + ydiff;
		
		this.x = this.x + xdiff;
		this.y = this.y + ydiff;
	}
	
	@Override
	public double getDist(Point p) {
		int x = p.x;
		int y = p.y;
		double dist = Math.sqrt((this.x1-x)*(this.x1-x) + (this.y1-y)*(this.y1-y));
		return Math.abs(r - dist);
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

	public static void drawCircle(Point p1, Point p2, Color c2, Graphics g) {
		int x1 = p1.x;
		int y1 = p1.y;
		
		int x2 = p2.x;
		int y2 = p2.y;
		
		double r = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
		
		int x = (int) (x1 - r);
		int y = (int) (y1 - r);		
		
		int height = (int) (2 * r);
		int width = (int) (2 * r);
		g.setColor(c2);
		g.drawOval(x, y, width, height);
	}
}
