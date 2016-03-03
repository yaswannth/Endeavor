package core;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

public class Canvas extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6762936064655965702L;
	
	public Canvas(JPanel coordsArea){
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				((CoordinatesSection)coordsArea).displayCoords(x,y);				
			}
		});
	}
}
