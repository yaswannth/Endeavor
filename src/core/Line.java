package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;
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
		g.drawLine(this.x1, this.y1, this.x2, this.y2);	
	}
	
	@Override
	public void moveTo(Point p1, Point p2) {
		int xdiff = p2.x - p1.x;
		int ydiff = p2.y - p1.y;
		
		this.x1 = this.x1 + xdiff;
		this.x2 = this.x2 + xdiff;
		this.y1 = this.y1 + ydiff;
		this.y2 = this.y2 + ydiff;
	}
	
	@Override
	public double getDist(Point p) {
		double numer = Math.abs((this.x2 - this.x1)*(this.y1 - p.y) - (this.y2 - this.y1)*(this.x1 - p.x));
		double denom = Math.sqrt((this.x1-this.x2)* (this.x1-this.x2)+ (this.y1-this.y2)*(this.y1-this.y2));
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

	@Override
	public String getShapeScript(int ury) {
		StringBuffer sb = new StringBuffer();

		float[] rgb = getRGB();
		float rIntensity = rgb[0];
		float gIntensity = rgb[1];
		float bIntensity = rgb[2];

		sb.append("%line" + "\n");
		sb.append(this.x1 + " " + (ury - this.y1) + " " + this.x2 + " " + (ury - this.y2) + " " + 
		rIntensity + " " + gIntensity + " " + bIntensity + " " + "line" + "\n");
		
		return sb.toString();
	}
	
	public static Line getLine(String script, int ury){
		Scanner scanner = new Scanner(script);
		String input = scanner.nextLine();
		StringTokenizer inputTokens = new StringTokenizer(input);


		int x1 = Integer.parseInt(inputTokens.nextToken());
		int y1 = ury - Integer.parseInt(inputTokens.nextToken());
		int x2 = Integer.parseInt(inputTokens.nextToken());
		int y2 = ury - Integer.parseInt(inputTokens.nextToken());
		
		float r = Float.parseFloat(inputTokens.nextToken());
		float g = Float.parseFloat(inputTokens.nextToken());
		float b = Float.parseFloat(inputTokens.nextToken());
		scanner.close();
		
		return new Line(new Point(x1,y1), new Point(x2,y2), new Color(r,g,b));
	}

	@Override
	public float[] getRGB()
	{
		float[] rgb = new float[3];		
		rgb[0] = ((float)c.getRed()) / MAX_COLOR;
		rgb[1] = ((float)c.getGreen()) / MAX_COLOR;
		rgb[2] = ((float)c.getBlue()) / MAX_COLOR;
		return rgb;
	}

	@Override
	public int getMinX() {
		return Math.min(x1, x2);
	}

	@Override
	public int getMinY(int ury) {
		return ury - Math.max(y1, y2);
	}

	@Override
	public int getMaxX() {
		return Math.max(x1, x2);
	}

	@Override
	public int getMaxY() {
		return Math.max(y1, y2);
	}

}
