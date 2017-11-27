package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import model.MainModel;

public class GamePlayScreen extends GodView {

	// Swing Components
	private JLayeredPane layeredPane;
	private JButton quit;
	// private JButton titleScreen;
	private static Window window;
	static Timer timer;
	private static MiniGameScreen mgs;
	private static PlayScreen gamePanel;

	// Images
	private BufferedImage fishImage;
	private BufferedImage trashImage;
	private BufferedImage foodImage;
	private BufferedImage diverImage;
	private BufferedImage minibgImage;
	private BufferedImage bgImage1;
	private int bgHeight = 592;
	private static int bgLength = 5728;
	// windowHeght is based on the desired height of the tutorial screen window
	// based on background image size
	private static int playHeight = 592;
	private final static int playLength = 2000;
	private final int controlpanelHeight = 100;

	// controller
	private static MainController c;

	private ArrayList<JLabel> stuff;
	private int cursorx;
	private int cursory;

	// settings
	private static int bg1xpos;
	private static int bg2xpos;
	private static int autoscrolldpt = 20;
	private static int dx;
	private static int dy;
	// private static int[] shift = {0,0}; //difference between map origin and
	// window origin

	public GamePlayScreen() {
		// create buffered images:
		// Create and load the fish icon.
		fishImage = createBufferedImage(c.getFishURL());

		// Create and load food icon
		foodImage = createBufferedImage(c.getFoodURL());

		// Create and load trash icon
		trashImage = createBufferedImage(c.getTrashURL());

		// Create and load the diver image
		diverImage = createBufferedImage(c.getDiverURL());

		// Create and load the background image
		minibgImage = createBufferedImage(c.getMinibgURL());

		// Create and load the background image
		bgImage1 = createBufferedImage(c.getBgURL());

		// resize
		// set size of background
		bgLength = playLength;
		bgHeight = playHeight;

		// layout
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// add game panel
		gamePanel = new PlayScreen();
		gamePanel.setSize(new Dimension(playLength, playHeight));
		// gamePanel.addMouseMotionListener(this);
		// add(gamePanel);

		// add layered pane for minigame
		JLayeredPane layeredPane = new JLayeredPane(); // create
		// mgs = new
		// MiniGameScreen(playLength*(1/4),playHeight*(1/4),playLength*7/8,
		// playHeight*7/8);
		mgs = new MiniGameScreen(500, 100, playLength * 7 / 8, playHeight * 7 / 8);
		layeredPane.add(mgs);
		layeredPane.setPreferredSize(new Dimension(playLength, playHeight + controlpanelHeight)); // resize
		gamePanel.add(layeredPane, new Integer(300));
		add(gamePanel);

		add(createControlPanel());

		// create map locations
		bg1xpos = 0;
		bg2xpos = bgLength;
		
		int mainCharRad = (int) Math.sqrt(Math.pow(fishImage.getHeight(),2) + Math.pow(fishImage.getWidth(), 2));
		int foodSize = (int) Math.sqrt(Math.pow(foodImage.getHeight(), 2) + Math.pow(foodImage.getWidth(), 2));
		int trashSize = (int) Math.sqrt(Math.pow(trashImage.getHeight(), 2) + Math.pow(trashImage.getWidth(), 2));
		int mapHeight = bgImage1.getHeight();
		int mapUnique = bgImage1.getWidth();
		int mapLength = mapUnique * 3;
		
		
		MainModel.setup(c.getModel(), mainCharRad, foodSize, trashSize, mapHeight, mapLength, mapUnique);

	}
	
	public BufferedImage createBufferedImage(String fileLocation) {
		BufferedImage img;
		try {
			img = ImageIO.read(new File(fileLocation));
			return img;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Creates the Buttons in the panel
	 * 
	 * @param label
	 *            text for button Label
	 * @param actionCommand
	 *            actionCommand associated with button click
	 * @return JButton - created button
	 */
	private JButton createButton(String label, String actionCommand) {
		JButton b = new JButton(label);
		b.setActionCommand(actionCommand);
		return b;
	}
	
	private JPanel createControlPanel() {
		quit = createButton("Quit", "quitGame");
		quit.addActionListener(this);
		JPanel controls = new JPanel();
		controls.add(quit);
		return controls;
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
	private static JComponent createAndShowGUI(Window frame) {
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
				//update();
				gamePanel.update();
				boolean b = false;
				if (b) {
					mgs.update();
				} else {
					mgs.setVisible(false);
				}		
				
				newContentPane.repaint();
				newContentPane.revalidate();
				System.out.println("game paint");
				// c.getGamePlayScreen().updateFishPosition();
			}
		});

		window.addTimer(timer);
		timer.start();
		
		return newContentPane;
	}

	protected static void update() {
		// update background position
		// bg1xpos -= 20;
		// bg2xpos -= 20;

		// relocate maps
		if (bg1xpos < bg2xpos && bg1xpos < (-bgLength)) {
			bg1xpos = bg2xpos + (bgLength);
		} else if (bg1xpos > bg2xpos && bg2xpos < (-bgLength)) {
			bg2xpos = bg1xpos + (bgLength);
		}

		// set fish location
		if (!c.inMiniGame()) { // main game
			int dx = (int) c.getModel().getMainCharacter().getPosition().getX();
			int dy = (int) c.getModel().getMainCharacter().getPosition().getY();

		}

		// set scuba location

	}

	public static JComponent activateGamePlayScreen(MainController co, Window w) {
		c = co;
		GamePlayScreen.window = w;
		return createAndShowGUI(w);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd == "quitGame") {
			window.stopAndRemoveTimer(timer);
			c.showTitleScreen();

		}
	}


	/*public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// disp map
		g.drawImage(bgImage1, bg1xpos, 0, this);
		g.drawImage(bgImage1, bg2xpos, 0, this);

		// disp objects
		for (int[] loc : c.getModel().getStuffSet().getFood()) {
			g.drawImage(foodImage, loc[0], loc[1], this);
		}

		// disp fish
		int fishx = c.getModel().getMainCharacter().getPosition().getX();
		int fishy = c.getModel().getMainCharacter().getPosition().getY();
		//System.out.println("\n " +fishx + ", " + fishy + "\n");
		g.drawImage(fishImage, cursorx, cursory, this); // where cursor is
		g.drawImage(fishImage, fishx, fishy, this); // where the fish is on the
													// map

	}*/


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

	public int getdx() {
		return dx;
	}

	public int getdy() {
		return dy;
	}
	
	
	
	//classes
	
	private class PlayScreen extends GodView implements MouseMotionListener {

		PlayScreen() {
			addMouseMotionListener(PlayScreen.this);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			// disp map
			g.drawImage(bgImage1, bg1xpos, 0, playLength, playHeight, this);
			g.drawImage(bgImage1, bg2xpos, 0, playLength, playHeight, this);

			// disp objects
			for (int[] loc : c.getModel().getStuffSet().getFood()) {
				g.drawImage(foodImage, loc[0], loc[1], this);
			}
			for (int[] loc : c.getModel().getStuffSet().getTrash()) {
				g.drawImage(trashImage, loc[0], loc[1], this);
			}

			// disp fish
			int mouseX = cursorx;
			int mouseY = cursory;
			
			
			System.out.println("mouse at <" + mouseX + ", " + mouseY + ">");
			
			
			
			double newSpeed = c.getModel().getMainCharacter().getPosition().distFrom(mouseX, mouseY);
			int deltaTheta = c.getModel().getMainCharacter().getPosition().angleBetween(mouseX, mouseY);
			System.out.println(deltaTheta);
			c.getModel().update(0,deltaTheta);
			
			double tmpx = c.getModel().getMainCharacter().getPosition().getX();
			double tmpy = c.getModel().getMainCharacter().getPosition().getY();
			int fishx = (int) tmpx;
			int fishy = (int) tmpy;
			//g.drawImage(fishImage, cursorx, cursory, this); // where cursor is
			g.drawImage(fishImage, fishx, fishy, this); // where the fish is on the
														// map
		}

		/**
		 * updates positions
		 */
		public void update() {
			// update background position
			bg1xpos -= autoscrolldpt;
			bg2xpos -= autoscrolldpt;

			// relocateMap
			if (bg1xpos < bg2xpos && bg1xpos < (-bgLength)) {
				bg1xpos = bg2xpos + (bgLength);
			} else if (bg1xpos > bg2xpos && bg2xpos < (-bgLength)) {
				bg2xpos = bg1xpos + (bgLength);
			}

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO: Make so fish character doesn't go out of bounds at all. Head
			// should stop within frame. so should the tail.
			if (!c.inMiniGame()) {
				// calcuate change in dx & dy
				dx = e.getX() - cursorx;
				dy = e.getY() - cursory;

				// move map
				int xinc = ((cursorx < e.getX()) ? e.getX() - cursorx : 0);

				// set location of cursor
				cursorx = e.getX();
				cursory = e.getY();
			}
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	}
	
	private class MiniGameScreen extends JPanel {

		public MiniGameScreen(int x, int y, int width, int height) {
			this.setBounds(x, y, width, height);
			this.setBackground(Color.RED);
			this.setBorder(BorderFactory.createRaisedBevelBorder());
			// this.addMouseListener(new MouseHandler(this));
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			// g.drawImage(minibgImage, 0, 0, observer)
		}

		public void update() {
			// color = new Color(r.nextInt());
			repaint();
		}

		public BufferedImage createBufferedImage(String fileLocation) {
			BufferedImage img;
			try {
				img = ImageIO.read(new File(fileLocation));
				return img;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	public int getCursorX() {
		return cursorx;
	}
	
	public int getCursorY() {
		return cursory;
	}
	

	
}
