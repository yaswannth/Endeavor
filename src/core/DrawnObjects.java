package core;

import java.util.HashSet;

public class DrawnObjects {
	
	public static final int DOT = 1;
	public static final int LINE = 2;
	public static final int CIRCLE = 3;
	public static final int RECTANGLE = 4;
	public static final int POLYGON = 5;
	
	public HashSet<Shape> shapes;
	
	public DrawnObjects(){
		shapes = new HashSet<Shape>();
	}
}
