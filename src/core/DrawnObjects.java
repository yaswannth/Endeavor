package core;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class DrawnObjects {

	public static final int DOT = 1;
	public static final int LINE = 2;
	public static final int CIRCLE = 3;
	public static final int RECTANGLE = 4;
	public static final int POLYGON = 5;

	public static final int MINDIST = 12;

	public ArrayList<Shape> shapes;
	
	private int llx = Integer.MAX_VALUE;
	private int lly = Integer.MAX_VALUE;
	private int urx = Integer.MIN_VALUE;
	private int ury = Integer.MIN_VALUE;
	private static final int PADDING = 50;

	public DrawnObjects(){
		this.shapes = new ArrayList<Shape>();
	}

	public Shape getNearestObjects(Point p){
		int idx = 0;
		int requiredIdx = 0;
		double minDist = Integer.MAX_VALUE;
		for(Shape s : this.shapes){
			double tmpDist = s.getDist(p);
			if(tmpDist < minDist) {
				minDist = tmpDist;
				requiredIdx = idx;
			}
			idx++;
		}
		if(minDist <= 30)
			return this.shapes.get(requiredIdx);
		else
			return null;
	}

	void setMinMax(int x, int y)
	{
		if (this.llx > x)
			this.llx = x;
		if (this.urx < x)
			this.urx = x;
		if (this.lly > y)
			this.lly = y;
		if (this.ury < y)
			this.ury = y;
	}
	
	public void setBoundingBox(){
		for (Shape s : this.shapes){
			int x2 = s.getMaxX();
			int y2 = s.getMaxY();
			this.setMinMax(x2, y2);
		}
		//this.ury = (this.ury + PADDING);			
		//this.urx = (this.urx + PADDING);
		
		for (Shape s : this.shapes){
			int x1 = s.getMinX();
			int y1 = s.getMinY(this.ury);
			this.setMinMax(x1,y1);
		}
		//this.llx = (this.llx - PADDING);	
		//this.lly = (this.lly - PADDING);
		
		
	}
	
	
	public String getData(){
		StringBuilder data = new StringBuilder();
		this.setBoundingBox();
		data.append("%!PS-Adobe-3.0 EPSF-3.0\n");
		data.append("%%BoundingBox: " + (this.llx - PADDING)+ " " + (this.lly - 2 * PADDING) + " " + (this.urx + PADDING) + " " + (this.ury + PADDING) + "\n");
		
		data.append("/vertex { setrgbcolor newpath " + Vertex.getRadius() + " 0 360 arc fill } def\n");
		data.append("/line { setrgbcolor newpath moveto lineto stroke } def\n");
		data.append("/circle { setrgbcolor newpath 0 360 arc stroke } def\n");
		data.append("/solidcircle { setrgbcolor newpath 0 360 arc fill } def\n");

		
		for (Shape s : this.shapes)
			data.append(s.getShapeScript(this.ury));			
		return data.toString();
	}

	public void loadData(BufferedReader br) throws IOException{
		this.shapes.clear();
		String input = br.readLine();
		input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		st.nextToken();
		st.nextToken();
		st.nextToken();
		st.nextToken();
		int ury = Integer.parseInt(st.nextToken());
		System.out.println(ury);
		
		while ( (input = br.readLine()) != null)
		{	
			if (input.startsWith("%rectangle"))
			{
				StringBuffer rectangleScript = new StringBuffer();
				while(!input.endsWith("stroke")){
					input = br.readLine();
					rectangleScript.append(input);
					rectangleScript.append("\n");					
				}
				Rectangle newRect = Rectangle.getRectangle(rectangleScript.toString(),ury - PADDING);
				this.shapes.add(newRect);
			}
			
			if (input.startsWith("%vertex"))
			{
				StringBuffer vertexScript = new StringBuffer();
				input = br.readLine();
				vertexScript.append(input);				
				
				Vertex newV = Vertex.getVertex(vertexScript.toString(),ury - PADDING);
				this.shapes.add(newV);
			}
			
			if (input.startsWith("%line"))
			{
				StringBuffer lineScript = new StringBuffer();
				input = br.readLine();
				lineScript.append(input);				
				
				Line newL = Line.getLine(lineScript.toString(),ury - PADDING);
				this.shapes.add(newL);
			}
			
			if (input.startsWith("%circle"))
			{
				StringBuffer circleScript = new StringBuffer();
				input = br.readLine();
				circleScript.append(input);				
				
				Circle newC = Circle.getCircle(circleScript.toString(),ury - PADDING);
				this.shapes.add(newC);
			}
			
			if (input.startsWith("%polygon"))
			{
				StringBuffer rectangleScript = new StringBuffer();
				while(!input.endsWith("stroke")){
					input = br.readLine();
					rectangleScript.append(input);
					rectangleScript.append("\n");					
				}
				Polygon poly = Polygon.getPolygon(rectangleScript.toString(),ury - PADDING);
				this.shapes.add(poly);
			}
			
			
		}

	}
}
