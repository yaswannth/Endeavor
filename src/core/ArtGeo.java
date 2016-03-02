package core;

import java.awt.*; //Using AWT containers and components
import java.awt.event.*; //Using AWT events and listener interfaces
import javax.swing.*; //Using Swing components and containers

//A Swing GUI app inherits from top-level container
//javax.swing.JFrame
public class ArtGeo extends JFrame{
	private static final long serialVersionUID = 2506635311264141215L;
	/**
	 * 
	 */
	
	private JTextField tfCount; //Using Swing's JTextField
	private int count = 0;
	
	/** Constructor to setup the GUI */
	public ArtGeo () {
		//Retrieve the content-pane of the top-level container JFrame
		//All operations are one on the content pane
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		
		cp.add(new JLabel("Counter"));
		tfCount = new JTextField("0", 10);
		tfCount.setEditable(false);
		cp.add(tfCount);
		
		JButton btnCount = new JButton("Count");
		cp.add(btnCount);
		
		//Allocate an anonymous instance of an anonymous inner class
		//that implements ActionListener as ActionEvent listener
		
		btnCount.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				++count;
				tfCount.setText(count +"");
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Program is terminated when the window is closed
		setTitle("Swing Counter"); //"this" JFrame sets title
		setSize(300, 100); //"this" JFrame sets initial size
		setVisible(true); //"this" JFrame shows		
	}
	
	public static void main(String[] args){
		//Runs the GUI construct in the event-dispatching thread for thread-safety
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ArtGeo(); //Constructor does the job!				
			}
		});
	}	
}
