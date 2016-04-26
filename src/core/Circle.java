package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;
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
	String bodyType;
	
	public Circle(Point s, double radius, Color c, String bodyType) {
		this.x1 = s.x;
		this.y1 = s.y;
				
		this.r = radius;
		
		this.x = (int) (this.x1 - r);
		this.y = (int) (this.y1 - r);		
		
		this.height = (int) (2 * this.r);
		this.width = (int) (2 * this.r);
		
		this.c = c;
		id = nextId.incrementAndGet();
		type = DrawnObjects.CIRCLE;
		this.bodyType = bodyType;
	}
	
	@Override
	public void draw(Graphics g){
		g.setColor(this.c);
		if(bodyType.equals(ToolsMenu.OUTLINE))
			g.drawOval(x, y, width, height);
		else
			g.fillOval(x, y, width, height);	
	}
	
	@Override
	public void moveTo(Point p1, Point p2) {
		int xdiff = p2.x - p1.x;
		int ydiff = p2.y - p1.y;
		
		this.x1 = this.x1 + xdiff;
		this.y1 = this.y1 + ydiff;
		
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

	public static void drawCircle(Point p1, double radius, Color c2, Graphics g, String drawingType) {
		int x1 = p1.x;
		int y1 = p1.y;
		
		
		double r = radius;
		
		int x = (int) (x1 - r);
		int y = (int) (y1 - r);		
		
		int height = (int) (2 * r);
		int width = (int) (2 * r);
		g.setColor(c2);
		
		if(drawingType.equals(ToolsMenu.OUTLINE))
			g.drawOval(x, y, width, height);
		else
			g.fillOval(x, y, width, height);		
	}

	@Override
	public String getShapeScript(int ury) {
		StringBuffer sb = new StringBuffer();

		float[] rgb = getRGB();
		float rIntensity = rgb[0];
		float gIntensity = rgb[1];
		float bIntensity = rgb[2];

		sb.append("%circle" + "\n");
		String type;
		
		if(this.bodyType.equals(ToolsMenu.SOLID))
			type = "solidcircle";
		else
			type = "circle";
		
		sb.append(this.x1 + " " + (ury - this.y1) + " " + this.r + " " + rIntensity + " " + gIntensity + " " + bIntensity + " " + type + "\n");
		return sb.toString();
	}

	public static Circle getCircle(String script, int ury) {
		Scanner scanner = new Scanner(script);
		String input = scanner.nextLine();
		StringTokenizer inputTokens = new StringTokenizer(input);
		String type;

		int x1 = Integer.parseInt(inputTokens.nextToken());
		int y1 = ury - Integer.parseInt(inputTokens.nextToken());
		double radius = Double.parseDouble(inputTokens.nextToken());
		
		float r = Float.parseFloat(inputTokens.nextToken());
		float g = Float.parseFloat(inputTokens.nextToken());
		float b = Float.parseFloat(inputTokens.nextToken());
		
		if(inputTokens.nextToken().equals("solidcircle"))			
			type = ToolsMenu.SOLID;
		else
			type = ToolsMenu.OUTLINE;
		scanner.close();
		
		return new Circle(new Point(x1, y1), radius, new Color(r,g,b), type);
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
		return (int) (x1 - this.r);
	}

	@Override
	public int getMinY(int ury) {
		return (int) (ury - (y1 + this.r));
	}

	@Override
	public int getMaxX() {
		return (int) (x1 + this.r);
	}

	@Override
	public int getMaxY() {
		return (int) (y1 + this.r);
	}
}
