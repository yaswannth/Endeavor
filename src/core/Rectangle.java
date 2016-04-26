package core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

public class Rectangle implements Shape{

	static AtomicInteger nextId = new AtomicInteger();
	ArrayList<Point> vertexPoints;
	public int x;
	public int y;
	public int xStart;
	public int yStart;
	public int xEnd;
	public int yEnd;
	public int xc;
	public int yc;
	public int height;
	public int width;
	Color c;
	int id;
	int type;
	String bodyType;

	public Rectangle(Point s, Point e, Color c, String bodyType) {
		this.xStart = s.x;
		this.yStart = s.y;

		this.xEnd = e.x;
		this.yEnd = e.y;

		this.x = Math.min(xStart, xEnd);
		this.y = Math.min(yStart, yEnd);

		this.height = Math.abs(yEnd - yStart);
		this.width = Math.abs(xEnd - xStart);

		this.vertexPoints = new ArrayList<Point>();
		this.vertexPoints.add(new Point(x,y));
		this.vertexPoints.add(new Point(x + width,y));
		this.vertexPoints.add(new Point(x + width,y + height));
		this.vertexPoints.add(new Point(x ,y + height));

		this.xc = (xEnd + xStart) /2;
		this.yc = (yEnd + yStart) /2;
		this.c = c;
		id = nextId.incrementAndGet();
		type = DrawnObjects.RECTANGLE;
		this.bodyType = bodyType;
	}

	@Override
	public void draw(Graphics g){
		g.setColor(c);

		if(bodyType.equals(ToolsMenu.OUTLINE))
			g.drawRect(x, y, width, height);
		else
			g.fillRect(x, y, width, height);		
	}

	@Override
	public void moveTo(Point p1, Point p2) {
		int xdiff = p2.x - p1.x;
		int ydiff = p2.y - p1.y;

		this.xStart = this.xStart + xdiff;
		this.xEnd = this.xEnd + xdiff;
		this.yStart = this.yStart + ydiff;
		this.yEnd = this.yEnd + ydiff;

		this.x = Math.min(this.xStart, this.xEnd);
		this.y = Math.min(this.yStart, this.yEnd);

		this.xc = (this.xEnd + this.xStart) /2;
		this.yc = (this.yEnd + this.yStart) /2; 

		this.vertexPoints.clear();
		this.vertexPoints.add(new Point(x,y));
		this.vertexPoints.add(new Point(x + width,y));
		this.vertexPoints.add(new Point(x + width,y + height));
		this.vertexPoints.add(new Point(x ,y + height));
	}

	@Override
	public double getDist(Point p) {
		int x = p.x;
		int y = p.y;
		double dist1 = Integer.MAX_VALUE;
		double dist2 = Integer.MAX_VALUE;
		if((x > this.xStart && x < this.xEnd) || (x < this.xStart && x > this.xEnd) || x == this.xStart || x == this.xEnd)
			dist1 = Math.abs(height/2 - Math.abs(this.yc - y));
		if((y > this.yStart && y < this.yEnd) || (y < this.yStart && y > this.yEnd) || y == this.yStart || y == this.yEnd)
			dist2 = Math.abs(width/2 - Math.abs(this.xc - x));
		return Math.min(dist1,dist2);
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

	public static void drawRectangle(Point p1, Point p2, Color c2, Graphics g, String drawingType) {
		int xStart = p1.x;
		int yStart = p1.y;

		int xEnd = p2.x;
		int yEnd = p2.y;

		int height = Math.abs(yEnd - yStart);
		int width = Math.abs(xEnd - xStart);

		int x = Math.min(xStart, xEnd);
		int y = Math.min(yStart, yEnd);
		g.setColor(c2);

		if(drawingType.equals(ToolsMenu.OUTLINE))
			g.drawRect(x, y, width, height);
		else
			g.fillRect(x, y, width, height);
	}

	public float[] getRGB()
	{
		float[] rgb = new float[3];		
		rgb[0] = ((float)c.getRed()) / MAX_COLOR;
		rgb[1] = ((float)c.getGreen()) / MAX_COLOR;
		rgb[2] = ((float)c.getBlue()) / MAX_COLOR;
		return rgb;
	}

	public String getShapeScript(int ury){
		StringBuffer sb = new StringBuffer();

		float[] rgb = getRGB();
		float rIntensity = rgb[0];
		float gIntensity = rgb[1];
		float bIntensity = rgb[2];

		sb.append("%rectangle" + "\n");
		sb.append("newpath\n");
		sb.append(this.x + " " + (ury - this.y) + " " + "moveto" + "\n");
		System.out.println(ury);
		for (int i = 1; i < 4; i++)
		{
			int x1 = vertexPoints.get(i).x;
			System.out.println(vertexPoints.get(i).y);
			
			int y1 = ury - vertexPoints.get(i).y;
			sb.append(x1 + " " + y1 + " " + "lineto" + "\n");
		}

		if(bodyType.equals(ToolsMenu.SOLID)) {
			sb.append("closepath\n");
			sb.append("gsave\n");
			sb.append(rIntensity + " " + gIntensity + " " + bIntensity + " " + "setrgbcolor" + " " + "fill\n");
			sb.append("grestore\n");
			sb.append("stroke\n");
			
		}else{
			sb.append("closepath\n");
			sb.append(rIntensity + " " + gIntensity + " " + bIntensity + " " + "setrgbcolor" + " " + "stroke\n");
		}
		
		return sb.toString();
	}

	public static Rectangle getRectangle(String script, int ury) {

		Point startPoint;
		Point endPoint;
		Color rectColor;
		String rectBodyType;
		
		Scanner scanner = new Scanner(script);
		String input = scanner.nextLine();
		input = scanner.nextLine();
		StringTokenizer inputTokens = new StringTokenizer(input);


		int xStart = Integer.parseInt(inputTokens.nextToken());
		int yStart = ury - Integer.parseInt(inputTokens.nextToken());
		startPoint = new Point(xStart,yStart);

		scanner.nextLine();
		input = scanner.nextLine();			
		inputTokens = new StringTokenizer(input);

		int x = Integer.parseInt(inputTokens.nextToken());
		int y = ury - Integer.parseInt(inputTokens.nextToken());			
		endPoint = new Point(x,y);

		input = scanner.nextLine();
		scanner.nextLine();		
		input = scanner.nextLine();	
		
		if(input.endsWith("gsave")){
			rectBodyType = ToolsMenu.SOLID;	
			input = scanner.nextLine();
		}
		else{
			rectBodyType = ToolsMenu.OUTLINE;			
		}
		
		
		inputTokens = new StringTokenizer(input);
		float r = Float.parseFloat(inputTokens.nextToken());
		float g = Float.parseFloat(inputTokens.nextToken());
		float b = Float.parseFloat(inputTokens.nextToken());
		rectColor = new Color(r, g, b);

		System.out.println("reached end");
		scanner.close();

		return new Rectangle(startPoint, endPoint, rectColor, rectBodyType);
	}

	@Override
	public int getMinX() {
		return x;
	}

	@Override
	public int getMinY(int ury) {
		// TODO Auto-generated method stub
		return ury - (y + height);
	}

	@Override
	public int getMaxX() {
		// TODO Auto-generated method stub
		return x + width;
	}

	@Override
	public int getMaxY() {
		// TODO Auto-generated method stub
		return y + height;
	}

}
