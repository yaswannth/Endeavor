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
	
	private JPanel mainMenuPane;
	private JPanel toolsMenuPane;
	private JPanel coordinatesPane;
	private JPanel canvasPane;
	
	private static Font font;
	
	public ArtGeo(){
		
		font = new Font("Ariel", Font.PLAIN, 16);
		setUIFont(new FontUIResource(font));		
		appIcon = new ImageIcon((URL)ArtGeo.class.getResource("/res/EndeavorIcon.png"));
		
		mainMenuPane = new MainMenu(new BorderLayout(), this);
		toolsMenuPane = new ToolsMenu();
		toolsMenuPane.setLayout(new BoxLayout(toolsMenuPane, BoxLayout.Y_AXIS));
		coordinatesPane = new CoordinatesSection();
		canvasPane = new Canvas();
		
		setIconImage(appIcon.getImage());
		setTitle("Endeavor");
			
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setMinimumSize(new Dimension(350,350));
		getContentPane().add(mainMenuPane,BorderLayout.NORTH);
		getContentPane().add(toolsMenuPane,BorderLayout.WEST);
		getContentPane().add(coordinatesPane, BorderLayout.SOUTH);
		getContentPane().add(canvasPane, BorderLayout.CENTER);
		
		setVisible(true);	
		setResizable(true);
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
	
	public void refresh(){
		canvasPane.repaint();
	}
	
	public void showWarning(String warning){
		JOptionPane.showMessageDialog(this,warning);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ArtGeo();				
			}
		});
		
		
	}
}
