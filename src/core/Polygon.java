package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;

public class Polygon implements Shape{
	static AtomicInteger nextId = new AtomicInteger();
	HashSet<Point> vertexPoints;
	public int [] xArray;
	public int [] yArray;
	public int n;//Number of vertices
	Color c;
	int id;
	int type;
	String bodyType;

	public Polygon(ArrayList<Integer> xArray, ArrayList<Integer> yArray, Color c, String bodyType) {

		this.xArray = getIntArray(xArray);
		this.yArray = getIntArray(yArray);
		this.n = xArray.size();
		this.c = c;
		id = nextId.incrementAndGet();
		type = DrawnObjects.POLYGON;
		this.bodyType = bodyType;
	}

	public static int [] getIntArray(ArrayList<Integer> array){
		int [] requiredArray = new int[array.size()];
		for( int i = 0; i < array.size(); i++){
			requiredArray[i] = array.get(i);			
		}
		return requiredArray;
	}

	@Override
	public void draw(Graphics g){
		g.setColor(this.c);
		if(bodyType.equals(ToolsMenu.OUTLINE))
			g.drawPolygon(xArray, yArray, n);
		else
			g.fillPolygon(xArray, yArray, n);
	}

	@Override
	public void moveTo(Point p1, Point p2) {
		int xdiff = p2.x - p1.x;
		int ydiff = p2.y - p1.y;

		for(int i = 0; i < n; i++){
			xArray[i] = xArray[i] + xdiff;
			yArray[i] = yArray[i] + ydiff;
		}
	}

	@Override
	public double getDist(Point p) {
		double dist = Integer.MAX_VALUE;
		for(int i = 0; i < n - 1; i++){
			int x1 = this.xArray[i];
			int x2 = this.xArray[i + 1];
			int y1 = this.yArray[i];
			int y2 = this.yArray[i + 1];
			double numer = Math.abs((x2 - x1)*(y1 - p.y) - (y2 - y1)*(x1 - p.x));
			double denom = Math.sqrt((x1-x2)* (x1-x2)+ (y1-y2)*(y1-y2));
			double tmpDist = Integer.MAX_VALUE;
			if(denom != 0)
				tmpDist = numer/denom;
			
			if(tmpDist < dist)
				dist = tmpDist;
		}
		
		int x1 = this.xArray[n-1];
		int x2 = this.xArray[0];
		int y1 = this.yArray[n-1];
		int y2 = this.yArray[0];
		double numer = Math.abs((x2 - x1)*(y1 - p.y) - (y2 - y1)*(x1 - p.x));
		double denom = Math.sqrt((x1-x2)* (x1-x2)+ (y1-y2)*(y1-y2));
		double tmpDist = Integer.MAX_VALUE;
		if(denom != 0)
			tmpDist = numer/denom;
		
		if(tmpDist < dist)
			dist = tmpDist;
		
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

	public static void drawPolygon(ArrayList<Integer> array1, ArrayList<Integer> array2, Color c2, Graphics g, String drawingType) {
		int[] xArray = getIntArray(array1);
		int[] yArray = getIntArray(array2);
		int n = array1.size();

		g.setColor(c2);
		
		if(drawingType.equals(ToolsMenu.OUTLINE) || n < 3)
			g.drawPolygon(xArray, yArray, n);
		else
			g.fillPolygon(xArray, yArray, n);
		
	}
}
