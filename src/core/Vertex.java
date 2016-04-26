package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

public class Vertex implements Shape{
	static AtomicInteger nextId = new AtomicInteger();
	HashSet<Point> vertexPoints;
	public int x;
	public int y;
	Color c;
	int id;
	int type;
	private static final int RADIUS = 6;
	
	public Vertex(Point p, Color c) {
		this.x = p.x;
		this.y = p.y;
		this.c = c;
		id = nextId.incrementAndGet();
		type = DrawnObjects.DOT;
	}
	
	public static int getRadius(){
		return RADIUS;
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(this.c);
		g.fillOval(this.x - RADIUS, this.y - RADIUS, 2*RADIUS, 2*RADIUS);		
	}
	
	@Override
	public void moveTo(Point p1, Point p2) {
		int xdiff = p2.x - p1.x;
		int ydiff = p2.y - p1.y;
		
		this.x = this.x + xdiff;
		this.y = this.y + ydiff;
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
	public String getShapeScript(int ury) {
		StringBuffer sb = new StringBuffer();

		float[] rgb = getRGB();
		float rIntensity = rgb[0];
		float gIntensity = rgb[1];
		float bIntensity = rgb[2];

		sb.append("%vertex" + "\n");
		sb.append(this.x + " " + (ury - this.y) + " " + rIntensity + " " + gIntensity + " " + bIntensity + " " + "vertex" + "\n");
		
		return sb.toString();
	}

	public static Vertex getVertex(String script, int ury) {
		Scanner scanner = new Scanner(script);
		String input = scanner.nextLine();
		StringTokenizer inputTokens = new StringTokenizer(input);


		int x = Integer.parseInt(inputTokens.nextToken());
		int y = ury - Integer.parseInt(inputTokens.nextToken());
		
		float r = Float.parseFloat(inputTokens.nextToken());
		float g = Float.parseFloat(inputTokens.nextToken());
		float b = Float.parseFloat(inputTokens.nextToken());
		scanner.close();
		
		return new Vertex(new Point(x,y), new Color(r,g,b));
	}
	
	@Override
	public int getMinX() {
		return x - RADIUS;
	}

	@Override
	public int getMinY(int ury) {
		return ury - (y + RADIUS);
	}

	@Override
	public int getMaxX() {
		return x + RADIUS;
	}

	@Override
	public int getMaxY() {
		return y + RADIUS;
	}
}
