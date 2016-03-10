package core;

import java.awt.Color;
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
	
	public Vertex(Point p, Color c) {
		vertexPoints.add(p);
		this.x = p.x;
		this.y = p.y;
		this.c = c;
		id = nextId.incrementAndGet();
		type = DrawnObjects.DOT;
	}
	
	@Override
	public void setColor(Color c) {
		this.c = c;
	}
	
	@Override
	public void setVertices(HashSet<Point> vertices) {
		vertexPoints.clear();
		vertexPoints.addAll(vertices);
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
