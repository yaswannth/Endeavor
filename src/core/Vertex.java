package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

public class Vertex implements Shape{
	static AtomicInteger nextId = new AtomicInteger();
	HashSet<Point> vertexPoints;
	public int x;
	public int y;
	Color c;
	int id;
	int type;
	private int r = 6;
	
	public Vertex(Point p, Color c) {
		this.x = p.x;
		this.y = p.y;
		this.c = c;
		id = nextId.incrementAndGet();
		type = DrawnObjects.DOT;
	}
	
	@Override
	public void draw(Graphics g){
		g.fillOval(this.x - r, this.y - r, 2*r, 2*r);		
	}
	
	@Override
	public void moveTo(Point p) {
		this.x = p.x;
		this.y = p.y;
	}
	
	@Override
	public double getDist(Point p) {
		int x1 = this.x;
		int x2 = p.x;
		int y1 = this.y;
		int y2 = p.y;
		double dist = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
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
	
}
