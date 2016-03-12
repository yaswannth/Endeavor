package core;

import java.awt.Point;
import java.util.ArrayList;

public class DrawnObjects {
	
	public static final int DOT = 1;
	public static final int LINE = 2;
	public static final int CIRCLE = 3;
	public static final int RECTANGLE = 4;
	public static final int POLYGON = 5;
	
	public static final int MINDIST = 12;
	
	public ArrayList<Shape> shapes;
	
	public DrawnObjects(){
		shapes = new ArrayList<Shape>();
	}
	
	public Shape getNearestObjects(Point p){
		int idx = 0;
		int requiredIdx = 0;
		double minDist = Integer.MAX_VALUE;
		for(Shape s : shapes){
			double tmpDist = s.getDist(p);
			if(tmpDist < minDist) {
				minDist = tmpDist;
				requiredIdx = idx;
			}
			idx++;
		}
		if(minDist <= 30)
			return shapes.get(requiredIdx);
		else
			return null;
	}
}
