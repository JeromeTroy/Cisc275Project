package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import controller.MainController;

//import fishieredux.MovementDemo;

//import fishieredux.MovementDemo;

public class GamePlayScreen extends JPanel implements ActionListener, MouseMotionListener {

	private JLayeredPane layeredPane;
	private JLabel fishLabel;
	private JLabel foodLabel;
	private JLabel trashLabel;
	private static MainController c;
	private JLabel bgLabel;
	private int bgPos;
	final ImageIcon foodIcon;
	final ImageIcon trashIcon;
	// private Image bgImage;
	// private JCheckBox onTop;
	// private JComboBox layerList;

	public GamePlayScreen() {
		c.setGamePlayScreen(this);
		bgPos = 0;
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(700, 500));
		layeredPane.setBorder(BorderFactory.createTitledBorder("Move the Mouse to Move Fishie"));
		layeredPane.addMouseMotionListener(this);
		layeredPane.setLayout(new FlowLayout());

		// Create and load the duke icon.
		final ImageIcon fishIcon = createImageIcon("images/fishie.png");

		// Create and load food icon
		foodIcon = createImageIcon("images/foodsmall.png");

		// Create and load trash icon
		trashIcon = createImageIcon("images/trashsmall.png");

		// Create and load the background image.
		final ImageIcon bg = createImageIcon("images/bg.png");

		// Create and add the Duke label to the layered pane.
		bgLabel = new JLabel(bg);
		bgLabel.setBounds(0, 0, 2000, 500);
		if (bg == null) {
			System.err.println("Background not found; using blue rectangle instead.");
			fishLabel.setOpaque(true);
			fishLabel.setBackground(Color.BLUE);
		}
		layeredPane.add(bgLabel, new Integer(0), 0);

		// Create and add the trash label to the layered pane.
		trashLabel = new JLabel(trashIcon);
		//bgLabel.setBounds(0, 0, 20, 20);
		if (trashIcon == null) {
			System.err.println("Background not found; using blue rectangle instead.");
			fishLabel.setOpaque(true);
			fishLabel.setBackground(Color.BLUE);
		}
		layeredPane.add(trashLabel, new Integer(1), 0);

		// Create and add the food label to the layered pane.
		foodLabel = new JLabel(foodIcon);
		foodLabel.setBounds(0, 0, 20, 20);
		if (bg == null) {
			System.err.println("Background not found; using blue rectangle instead.");
			fishLabel.setOpaque(true);
			fishLabel.setBackground(Color.BLUE);
		}
		layeredPane.add(foodLabel, new Integer(3), 0);

		// Create and add the Duke label to the layered pane.
		fishLabel = new JLabel(fishIcon);
		if (fishIcon == null) {
			System.err.println("Fishie icon not found; using blue rectangle instead.");
			fishLabel.setOpaque(true);
			fishLabel.setBackground(Color.BLUE);
		}
		layeredPane.add(fishLabel, new Integer(2), 0);

		// Add control pane and layered pane to this JPanel.
		add(Box.createRigidArea(new Dimension(0, 10)));
		// add(createControlPanel());
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(layeredPane);
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = GamePlayScreen.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	// Create and set up the layered pane.
	// layeredPane = new JLayeredPane();
	// layeredPane.setPreferredSize(new Dimension(700, 500));
	// layeredPane.setBorder(BorderFactory.createTitledBorder("Move the Mouse to
	// Move Fishie"));
	// layeredPane.addMouseMotionListener(this);

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Eel Quest");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		JComponent newContentPane = new GamePlayScreen();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void activateGamePlayScreen(MainController co) {
		c = co;
		createAndShowGUI();
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO: Make so fish character doesn't go out of bounds at all. Head
		// should stop within frame. so should the tail.
		if (!c.inMiniGame()) {
			fishLabel.setLocation(e.getX() - fishLabel.getWidth() / 2, e.getY() - fishLabel.getHeight() / 2);
			bgPos--;
			bgLabel.setLocation(bgPos, 0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
	// public static void main(String[] args) {
	// // Schedule a job for the event-dispatching thread:
	// // creating and showing this application's GUI.
	// javax.swing.SwingUtilities.invokeLater(new Runnable() {
	// public void run() {
	// createAndShowGUI();
	// }
	// });
	// }
	
	public static void paint(){
//		//map move
//		bgPos--;
//		bgLabel.setLocation(bgPos, 0);
//		JLabel tmp;
//		
//		//stuff.clear();
//		
//		for (int i=0; i<c.getModel().getStuff().size(); i++){
//			if (c.getModel().getStuff().get(i).isTrash()){
//				tmp = new JLabel(trashIcon);
//			} else{
//				tmp = new JLabel(foodIcon);
//			}
//			tmp.setLocation(c.getModel().getStuff().get(i).getPosition().getX(),c.getModel().getStuff().get(i).getPosition().getX());
//			layeredPane.add(tmp, new Integer(3), 0);
//		}
		
		System.out.println("paint");
	}
}
