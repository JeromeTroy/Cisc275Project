package view;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Window extends JFrame {
	
	private static JFrame createAndShowGUI() {
		// window
		JFrame frame = new JFrame("Estuary Adventure - DNERR");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// display
        frame.pack();
        frame.setVisible(true);
        
        return frame;
	}
	
	
	public static JFrame activateTitle() {
		return createAndShowGUI();
	}
}
