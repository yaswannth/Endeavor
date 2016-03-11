package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

public class Line implements Shape{
	
	static AtomicInteger nextId = new AtomicInteger();
	HashSet<Point> vertexPoints;
	public int x1;
	public int y1;
	public int x2;
	public int y2;
	Color c;
	int id;
	int type;
	
	public Line(Point s, Point e, Color c) {
		this.x1 = s.x;
		this.y1 = s.y;
		
		this.x2 = e.x;
		this.y2 = e.y;
		
		this.c = c;
		id = nextId.incrementAndGet();
		type = DrawnObjects.LINE;
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(this.c);
		g.drawLine(x1, y1, x2, y2);	
	}
	
	@Override
	public void moveTo(Point p) {
		
	}
	
	@Override
	public double getDist(Point p) {
		double numer = Math.abs((this.x2 - this.x1)*(this.y1 - p.y) - (this.y2 - this.y1)*(this.x1 - p.x));
		double denom = Math.sqrt((x2-x1)^2 + (y2-y1)^2);
		double dist;
		if(denom != 0)
			dist = numer/denom;
		else
			dist = Integer.MAX_VALUE;
		return dist;
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
	
	public static void drawLine(Point p1, Point p2, Color c, Graphics g){
		g.setColor(c);
		g.drawLine(p1.x, p1.y, p2.x, p2.y);
	}

}
