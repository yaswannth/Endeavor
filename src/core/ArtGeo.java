package core;

import java.awt.*; //Using AWT containers and components
import java.net.URL;
import java.util.Enumeration;

import javax.swing.*; //Using Swing components and containers
import javax.swing.plaf.FontUIResource;

//A Swing GUI app inherits from top-level container
//javax.swing.JFrame
public class ArtGeo extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon appIcon;
	
	private JPanel menuPane;
	
	private static Font font;
	
	public ArtGeo(){
		
		font = new Font("Ariel", Font.PLAIN, 16);
		appIcon = new ImageIcon((URL)ArtGeo.class.getResource("/res/EndeavorIcon.png"));
		menuPane = new MainMenu(new BorderLayout());
		
		setUIFont(new FontUIResource(font));
		setIconImage(appIcon.getImage());
		setTitle("Endeavor");
		
		setSize(500, 500);	
		getContentPane().add(menuPane,BorderLayout.NORTH);
		setVisible(true);	
		setDefaultCloseOperation(EXIT_ON_CLOSE);		
	}
	
	public static void setUIFont(FontUIResource f) {
		Enumeration<Object> keys = UIManager.getDefaults().keys();
		while(keys.hasMoreElements()) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if(value instanceof FontUIResource) {
				UIManager.put(key, f);
			}
		}
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ArtGeo();				
			}
		});
		
		
	}
}
