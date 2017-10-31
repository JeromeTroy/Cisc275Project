package view;

//imports
import java.awt.*;
import java.awt.event.*;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import controller.MainController;

import javax.swing.JButton;

public class TutorialScreen extends JPanel{
	private JLabel instructions;

	// constructor
		public TutorialScreen(){
			
			// layout
			setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
			
			// layered pane
			JLayeredPane layeredPane = new JLayeredPane();
			layeredPane.setPreferredSize(new Dimension(300,300));
			layeredPane.setBorder(BorderFactory.createTitledBorder("Tutorial"));
			
			//layeredPane.addMouseMotionListener(this);
			
			add(Box.createRigidArea(new Dimension(0,10)));
			add(layeredPane);
			//add(createControlPanel());

			instructions = new JLabel("Use mouse to move the fish. Eat food, don't eat trash");		
			add(instructions);

		}

		private static void createAndShowGUI() {
			// window
			JFrame frame = new JFrame("Tutorial");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			// content
	        JComponent newContentPane = new TutorialScreen();
	        newContentPane.setOpaque(true); //content panes must be opaque
	        frame.setContentPane(newContentPane);
			
			// display
	        frame.pack();
	        frame.setVisible(true);
		}
		
		
		public static void activateTutorial() {
			createAndShowGUI();
		}

}
