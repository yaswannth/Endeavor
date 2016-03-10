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
	
	public Vertex(Point p, Color c) {
		vertexPoints.add(p);
		this.x = p.x;
		this.y = p.y;
		this.c = c;
		id = nextId.incrementAndGet();
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
		return id;
	}	
	
}
