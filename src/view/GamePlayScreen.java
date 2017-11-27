package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
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
import javax.swing.Timer;

import controller.GameTimer;
import controller.MainController;

public class GamePlayScreen extends JPanel implements ActionListener, MouseMotionListener {

	// Swing Components
	private JLayeredPane layeredPane;
	private JLabel fishLabel;
	private JLabel foodLabel;
	private JLabel trashLabel;
	private static Window window;
	private static JLabel bgLabel;
	private static int bgPos;
	private static Timer timer;

	// Images
	private BufferedImage fishImage;
	private BufferedImage foodImage;
	private BufferedImage trashImage;
	private BufferedImage bgImage1;
	private int bgHeight = 592;
	private static int bgLength = 5728;
	// windowHeght is based on the desired height of the tutorial screen window
	// based on background image size
	private final static int windowHeight = 592;
	private final static int windowLength = 2000;

	// controller
	private static MainController c;

	private ArrayList<JLabel> stuff;
	private int cursorx;
	private int cursory;

	// settings
	private static int bg1xpos;
	private static int bg2xpos;
	private static int dx;
	private static int dy;
	private static int[] shift = {0,0}; //difference between map origin and window origin

	public GamePlayScreen() {

		// layout
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(2000, 500));
		// layeredPane.setBorder(BorderFactory.createTitledBorder("Move the
		// Mouse to Move Fishie"));
		// add mouse listener
		layeredPane.addMouseMotionListener(this);
		layeredPane.setLayout(new FlowLayout());
		// Add control pane and layered pane to this JPanel.
		add(Box.createRigidArea(new Dimension(0, 10)));
		// add(createControlPanel());
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(layeredPane);

		// create buffered images: TODO: use create buffered image
		// Create and load the fish icon.
		try {
			fishImage = ImageIO.read(new File(c.getFishURL()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Create and load food icon
		try {
			foodImage = ImageIO.read(new File(c.getFoodURL()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Create and load trash icon
		try {
			trashImage = ImageIO.read(new File(c.getTrashURL()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Create and load the background image
		try {
			bgImage1 = ImageIO.read(new File(c.getBgURL()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//set size of background
		bgLength = bgImage1.getWidth();

		// create map locations
		bg1xpos = c.getModel().getMap().getOrigin().getX();
		bg2xpos = bg1xpos+bgLength;

		// // Create and load the duke icon.
		// final ImageIcon fishIcon = createImageIcon("images/fishie.png");
		//
		// // Create and load food icon
		// foodIcon = createImageIcon("images/foodsmall.png");
		//
		// // Create and load trash icon
		// trashIcon = createImageIcon("images/trashsmall.png");
		//
		// // Create and load the background image.
		// final ImageIcon bg = createImageIcon("images/bg.png");
		//
		// // Create and add the Duke label to the layered pane.
		// bgLabel = new JLabel(bg);
		// bgLabel.setBounds(0, 0, 2000, 500);
		// if (bg == null) {
		//
		// System.err.println("Background not found; using blue rectangle
		// instead.");
		// fishLabel.setOpaque(true);
		// fishLabel.setBackground(Color.BLUE);
		// }
		// layeredPane.add(bgLabel, new Integer(0), 0);
		//
		// // Create and add the trash label to the layered pane.
		// trashLabel = new JLabel(trashIcon);
		// // bgLabel.setBounds(0, 0, 20, 20);
		// if (trashIcon == null) {
		// System.err.println("Background not found; using blue rectangle
		// instead.");
		// fishLabel.setOpaque(true);
		// fishLabel.setBackground(Color.BLUE);
		// }
		// layeredPane.add(trashLabel, new Integer(1), 0);
		//
		// // Create and add the food label to the layered pane.
		// foodLabel = new JLabel(foodIcon);
		// foodLabel.setBounds(0, 0, 20, 20);
		// if (bg == null) {
		// System.err.println("Background not found; using blue rectangle
		// instead.");
		// fishLabel.setOpaque(true);
		// fishLabel.setBackground(Color.BLUE);
		// }
		// layeredPane.add(foodLabel, new Integer(3), 0);
		//
		// // Create and add the Duke label to the layered pane.
		// fishLabel = new JLabel(fishIcon);
		// if (fishIcon == null) {
		// System.err.println("Fishie icon not found; using blue rectangle
		// instead.");
		// fishLabel.setOpaque(true);
		// fishLabel.setBackground(Color.BLUE);
		// }
		// layeredPane.add(fishLabel, new Integer(2), 0);
		//
		// // initialize stuff array
		// stuff = new ArrayList<>();
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

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI(Window frame) {
		// Create and set up the content pane.
		JComponent newContentPane = new GamePlayScreen();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// set game play screen
		c.setGamePlayScreen((GamePlayScreen) newContentPane);

		// Display the window
		frame.pack();
		frame.setVisible(true);

		// create Timer
		// create Swing timer with actionListener
		timer = new Timer(40, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
				newContentPane.repaint();
				newContentPane.revalidate();
				System.out.println("game paint");
				// c.getGamePlayScreen().updateFishPosition();
			}
		});

		window.addTimer(timer);
		timer.start();

	}

	protected static void update() {
		// update background position
		//bg1xpos -= 20;
		//bg2xpos -= 20;
		
		
		// relocate maps
		if (bg1xpos < bg2xpos && bg1xpos < (-bgLength)) {
			bg1xpos = bg2xpos + (bgLength);
		} else if (bg1xpos > bg2xpos && bg2xpos < (-bgLength)) {
			bg2xpos = bg1xpos + (bgLength);
		}
		
		//set fish location
		if(!c.inMiniGame()){ //main game
			int dx = c.getModel().getMainCharacter().getPosition().getX();
			int dy = c.getModel().getMainCharacter().getPosition().getY();
			
		}
		
		//set scuba location

	}

	public static void activateGamePlayScreen(MainController co, Window w) {
		c = co;
		GamePlayScreen.window = w;
		createAndShowGUI(w);
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
			//calcuate change in dx & dy
			dx = e.getX() - cursorx;
			dy = e.getY() - cursory;
			
			//move map
			int xinc = ((cursorx<e.getX())? e.getX()-cursorx : 0);
			shift[0]+= xinc;
			
			//set location of cursor
			cursorx = e.getX();
			cursory = e.getY();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

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

	// public void paintScreen() {
	// // //map move
	// bgPos--;
	// bgLabel.setLocation(bgPos, 0);
	// JLabel tmp;
	//
	// /*
	// * System.out.println(c.getModel().newStuff.size()); for (int i=0;
	// * i<c.getModel().newStuff.size(); i++){
	// * System.out.println(c.getModel().newStuff.get(i).isTrash()); if
	// * (c.getModel().newStuff.get(i).isTrash()){ tmp = new
	// * JLabel(trashIcon); } else{ tmp = new JLabel(foodIcon); }
	// * tmp.setBounds(0, 0, 20, 20); tmp.setLocation(500+bgPos,250);
	// * //tmp.setLocation(c.getModel().getStuff().get(i).getPosition().getX()
	// * ,c.getModel().getStuff().get(i).getPosition().getX());
	// * stuff.add(tmp);
	// * System.out.println(c.getModel().getStuff().get(i).getPosition());
	// * layeredPane.add(tmp, new Integer(20), 0); }
	// */
	// // add(layeredPane);
	// System.out.println(stuff.size());
	// System.out.println("Paint fish (test):");
	// System.out.println(c.getModel().getMainCharacter());
	// // updatePositions();
	// // frame.revalidate();
	// // frame.setVisible(true);
	//
	// }

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// disp map
		g.drawImage(bgImage1, bg1xpos, 0, this);
		g.drawImage(bgImage1, bg2xpos, 0, this);

		// disp objects
		for (int[] loc :c.getModel().getStuffSet().getFood()){
			g.drawImage(foodImage, loc[0]-shift[0], loc[1], this);
		}

		// disp fish
		int fishx = c.getModel().getMainCharacter().getPosition().getX();
		int fishy = c.getModel().getMainCharacter().getPosition().getY();
		g.drawImage(fishImage, cursorx, cursory, this); //where cursor is
		g.drawImage(fishImage, fishx, fishy, this); //where the fish is on the map

	}

	public void updatePositions() {
		for (JLabel j : stuff) {
			j.setLocation(bgPos + 500, 250);
		}
	}

	private BufferedImage createImage(String dir) {
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("images/food.png"));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getdx(){
		return dx;
	}
	
	public int getdy(){
		return dy;
	}
}
