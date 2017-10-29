package view;

import java.awt.Color;
import java.awt.Dimension;
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

import eviecontroller.GamePlayController;



public class GamePlayScreen extends JPanel  implements ActionListener, MouseMotionListener {
	
	public GamePlayController control;
	public JLayeredPane layeredPane;
	private JLabel fishLabel;
	private JLabel bgLabel;
	private int gameScore = -1;
	//private Image bgImage;
	//private JCheckBox onTop;
	//private JComboBox layerList;
	
	public GamePlayScreen(){
		//control = new GamePlayController();
		setLayeredPane(new JLayeredPane());
		getLayeredPane().setPreferredSize(new Dimension(700, 500));
		getLayeredPane().setBorder(BorderFactory.createTitledBorder("Move the Mouse to Move Fishie. Current Score: "+ gameScore));
		//layeredPane.addMouseMotionListener(this);
		getLayeredPane().setLayout(new GridLayout(1, 1));
		
		// Create and load the duke icon.
				final ImageIcon icon = createImageIcon("images/fishie.png");
				final ImageIcon bg = createImageIcon("images/bg.png");
		
		// Create and add the Duke label to the layered pane.
		fishLabel = new JLabel(icon);
		if (icon == null) {
			System.err.println("Fishie icon not found; using blue rectangle instead.");
			fishLabel.setOpaque(true);
			fishLabel.setBackground(Color.BLUE);
		}
		layeredPane.add(fishLabel, new Integer(2), 0);
		fishMovement(10,10);
		
		// Create and add the background label to the layered pane.
				bgLabel = new JLabel(bg);
				if (icon == null) {
					System.err.println("Background icon not found; using blue rectangle instead.");
					bgLabel.setOpaque(true);
					bgLabel.setBackground(Color.BLUE);
				}
				layeredPane.add(bgLabel, new Integer(0), 0);
				bgLabel.setLocation(3, 3);
		
		// Add control pane and layered pane to this JPanel.
		add(Box.createRigidArea(new Dimension(0, 10)));
		// add(createControlPanel());
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(getLayeredPane());
	}
	
	public GamePlayScreen(GamePlayController g){
		GamePlayScreen gs = new GamePlayScreen();
		gs.control = g;
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
	//layeredPane = new JLayeredPane();
			//layeredPane.setPreferredSize(new Dimension(700, 500));
			//layeredPane.setBorder(BorderFactory.createTitledBorder("Move the Mouse to Move Fishie"));
			//layeredPane.addMouseMotionListener(this);

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
	public static void activateGamePlayScreen() {
		createAndShowGUI();
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		control.thisModel.moveFishy(e.getX(), e.getY());
		control.thisModel.printFishy();
		System.out.println("bababooey");
		control.thisGameScreen.getFishLabel().setLocation(10, 10);
		//thisGameScreen.getFishLabel().setLocation(f.getPosition().getX() - thisGameScreen.getFishLabel().getWidth() / 2, f.getPosition().getX() - thisGameScreen.getFishLabel().getHeight() / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		
	}
	

	public JLayeredPane getLayeredPane() {
		return layeredPane;
	}

	public void setLayeredPane(JLayeredPane layeredPane) {
		this.layeredPane = layeredPane;
	}
	
	public JLabel getFishLabel() {
		return fishLabel;
	}

	public void setFishLabel(JLabel fishLabel) {
		this.fishLabel = fishLabel;
	}
	
	public void fishMovement(int a, int b){
		fishLabel.setLocation(a, b);
	}
	
	public void setGameScore(int a){
		gameScore = a;
	}
}
