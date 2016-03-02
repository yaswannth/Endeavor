package core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CoordinatesSection extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2446278368202616149L;
	
	private JLabel coords;
	
	public CoordinatesSection (){
		super(new BorderLayout());
		coords = new JLabel("X : 0, Y : 0");
		this.add(coords, BorderLayout.WEST);
		
		this.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(MouseEvent e) {
				coords.setText("X : " + e.getX() + ", Y : " + e.getY());
			}
		});
		
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLoweredSoftBevelBorder());
	}

}
