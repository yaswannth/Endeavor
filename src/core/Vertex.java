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
