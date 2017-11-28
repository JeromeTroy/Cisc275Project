package view;

//imports
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import controller.MainController;

import javax.swing.JButton;

/**
 * TutorialScreen is a JPanel for the game tutorial intended to fill a window
 * mapImage must be cyclic
 * 
 * @author Team4
 *
 */
public class TutorialScreen extends JPanel implements ActionListener {

	// Swing components
	private static JLabel instructions;
	private JButton gameStart;
	private JButton titleScreen;
	private static Window window;
	static Timer timer;
	private static MiniGameScreen mgs;

	// Images
	private BufferedImage fishImage;
	private BufferedImage foodImage;
	private BufferedImage diverImage;
	private BufferedImage minibgImage;
	private BufferedImage trashImage;
	private BufferedImage bgImage1;
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static int windowWidth = (int) screenSize.getWidth();
	private static int windowHeight = (int) screenSize.getHeight();
	private int bgHeight = windowHeight - controlpanelHeight - instructionsHeight;
	private static int bgLength = windowWidth;
	// windowHeght is based on the desired height of the tutorial screen window
	// based on background image size
	private final static int playLength = windowWidth;
	private final static int controlpanelHeight = 100;
	private final static int instructionsHeight = 150;
	private  static int playHeight = windowHeight;
	private String dir;

	// controller
	private static MainController c; // the controller operating the game

	// Tutorial Screen Settings
	private static int[] foodxLocation;
	private static int[] foodyLocation;
	private static int[] trashxLocation;
	private static int[] trashyLocation;
	private static int cursorx;
	private static int cursory;
	private static int numFood = 10;
	private static int numTrash = 25;
	private static boolean dispFood = true;
	private static boolean dispTrash = false;

	private static int autoscrolldpt = 20; // autoscroll x distance per tick
											// (positive objects travel to left)
	private static int bg1xpos;
	private static int bg2xpos;

	private static String mode;
	private static String text;
	private static boolean pauseMovement;
	private int tick;
	private JPanel instructionsPanel;
	private static PlayScreen gamePanel;

	// constructor
	public TutorialScreen() { // TODO: update mouselistener to limit fish
								// movement boundaries
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
		// bgImage1 = Scalr.
		// set size of background
		bgLength = playLength;
		bgHeight = playHeight;

		// layout
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		//setSize(new Dimension(windowWidth,windowHeight));

		// add instructions panel
		instructionsPanel = new JPanel();
		instructionsPanel.setBorder(BorderFactory.createLineBorder(Color.green));
		instructionsPanel.setMaximumSize(new Dimension(playLength, instructionsHeight));
		dir = "Estuary Adventure Tutorial Mode";
		instructions = new JLabel(dir);
		instructions.setFont(new Font("Arial", Font.PLAIN, 40));
		instructions.setSize(50, instructionsHeight);
		instructionsPanel.add(instructions);
		add(instructionsPanel);
		

		// add game panel
		gamePanel = new PlayScreen();
		gamePanel.setSize(new Dimension(playLength, playHeight));
		gamePanel.setBorder(BorderFactory.createLineBorder(Color.red));
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
		//gamePanel.add(layeredPane, new Integer(300));
		add(gamePanel);
		
		mgs.setVisible(false);

		add(createControlPanel());

		// create map locations
		bg1xpos = 0;
		bg2xpos = bgLength;

		// generate food locations
		foodxLocation = new int[numFood];
		foodyLocation = new int[numFood];
		for (int i = 0; i < numFood; i++) {
			foodxLocation[i] = (int) (Math.random() * playLength);
			foodyLocation[i] = (int) (Math.random() * playHeight);
		}

		// generate trash locations
		trashxLocation = new int[numTrash];
		trashyLocation = new int[numTrash];
		for (int i = 0; i < numTrash; i++) {
			trashxLocation[i] = (int) (Math.random() * playLength);
			trashyLocation[i] = (int) (Math.random() * playHeight);
		}

		// window.add(layeredPane);

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

	/**
	 * Develops a control panel for game buttons
	 * 
	 * @return JPanel panel to be on screen
	 */
	private JPanel createControlPanel() {
		gameStart = createButton("Start Game", "goToGame");
		gameStart.addActionListener(this);
		titleScreen = createButton("Return to Title Screen", "goToTitle");
		titleScreen.addActionListener(this);

		JPanel controls = new JPanel();
		controls.setBorder(BorderFactory.createLineBorder(Color.blue));
		controls.add(gameStart);
		controls.add(titleScreen);
		controls.setMaximumSize(new Dimension(windowWidth,controlpanelHeight));
		return controls;
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

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		MainController c = new MainController(true);
		if (cmd == "goToGame") {
			window.stopAndRemoveTimer(timer);
			c.startGame();
			// TODO: need action to open game
		} else if (cmd == "goToTitle") {
			window.stopAndRemoveTimer(timer);
			c.showTitleScreen();

		}
	}

	private static void createAndShowGUI(Window frame) {
		// content
		JComponent newContentPane = new TutorialScreen();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// set tutorial screen
		c.setTutorialScreen((TutorialScreen) newContentPane);

		// display
		frame.setSize(windowWidth,windowHeight);
		frame.setVisible(true);

		// System.out.println("disp"); // TODO: remove
		// create Timer - updates and paints
		// create Swing timer with actionListener
		timer = new Timer(40, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// update();
				gamePanel.update();
				boolean b = true;
				if (b) {
					mgs.update();
				} else {
					mgs.setVisible(false);
				}
				newContentPane.repaint();
				newContentPane.revalidate();
				// System.out.println("paint tut"); //TODO: remove
			}
		});
		frame.addTimer(timer);
		;
		timer.start();

	}

	public static void activateTutorial(MainController c, Window w) {
		TutorialScreen.c = c;
		TutorialScreen.window = w;
		createAndShowGUI(w);
	}

	public void paintComponent(Graphics g) {
		// super.paintComponent(g);

		// // disp map
		// g.drawImage(bgImage1, bg1xpos, 0, playLength, playHeight, gamePanel);
		// g.drawImage(bgImage1, bg2xpos, 0, playLength, playHeight, gamePanel);
		//
		// // disp objects
		// if (dispFood) {
		// for (int i = 0; i < numFood; i++) {
		// foodxLocation[i] -= autoscrolldpt;
		// g.drawImage(foodImage, foodxLocation[i], foodyLocation[i] + 50,
		// this);
		// // System.out.println((foodxLocation[i]-j)+" "+
		// // foodyLocation[i]);
		// // g.drawImage(foodImage, 1000, 250, this);
		// }
		// }
		// if (dispTrash) {
		// for (int i = 0; i < numTrash; i++) {
		// trashxLocation[i] -= autoscrolldpt;
		// g.drawImage(trashImage, trashxLocation[i], trashyLocation[i] + 50,
		// this);
		// }
		// }
		//
		// // disp fish
		// g.drawImage(fishImage, 0, 0, gamePanel);

		// display instructions
		// TODO: add game intruction tutorial mode
	}

	/**
	 * updates positions
	 */
	// public static void update() {
	// //update background position
	// bg1xpos -= autoscrolldpt;
	// bg2xpos -= autoscrolldpt;
	//
	// // push items to back end of screen
	// System.out.println("update");
	// for (int i = 0; i < numFood; i++) {
	// if (foodxLocation[i] <= 0) {
	// foodxLocation[i] = playLength + 100;
	// foodyLocation[i] = (int) (Math.random() * playHeight);
	// }
	// }
	//
	// for (int i = 0; i < numTrash; i++) {
	// if (trashxLocation[i] <= 0) {
	// trashxLocation[i] = playLength + 100;
	// trashyLocation[i] = (int) (Math.random() * playHeight);
	// }
	// }
	//
	// // relocateMap
	// if (bg1xpos < bg2xpos && bg1xpos < (-bgLength)) {
	// bg1xpos = bg2xpos + (bgLength);
	// } else if (bg1xpos > bg2xpos && bg2xpos < (-bgLength)) {
	// bg2xpos = bg1xpos + (bgLength);
	// }
	//
	// // update mode
	// if (mode == "instructions") {
	// pauseMovement = true;
	// } else if (mode == "moveFish") {
	// text = "Use mouse to move fish!";
	// dispFood = false;
	// dispTrash = false;
	// pauseMovement = false;
	// } else if (mode == "getFood") {
	// text = "Move fish to food to eat fish";
	// dispFood = true;
	// dispTrash = false;
	// pauseMovement = false;
	// } else if (mode == "avoidTrash") {
	// text = "Avoid the trash as you are swimming";
	// dispFood = true;
	// dispTrash = true;
	// pauseMovement = false;
	// } else if (mode == "accumulateTrash") {
	// text = "Trash accumulates in the game, keep avoiding trash!";
	// dispFood = false;
	// dispTrash = false;
	// pauseMovement = false;
	// } else if (mode == "collectTrash") {
	// text = "You hit trash";
	// dispFood = false;
	// dispTrash = false;
	// pauseMovement = false;
	// }
	//
	// // runTutorial();
	// }

	public static void runTutorial() {
		mode = "moveFish";
		mode = "getFood";
		mode = "avoidTrash";
		mode = "accumlateTrash";
		mode = "collectTrash";
		mode = "dispInstuctions";

	}

	private class PlayScreen extends JPanel implements MouseMotionListener {

		PlayScreen() {
			addMouseMotionListener(PlayScreen.this);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			// disp map
			g.drawImage(bgImage1, bg1xpos, 0, playLength, playHeight, this);
			g.drawImage(bgImage1, bg2xpos, 0, playLength, playHeight, this);
			Graphics2D g2d = (Graphics2D) g;

			// disp objects
			if (dispFood) {
				for (int i = 0; i < numFood; i++) {
					foodxLocation[i] -= autoscrolldpt;
					g.drawImage(foodImage, foodxLocation[i], foodyLocation[i] + 50, this);
					// System.out.println((foodxLocation[i]-j)+" "+
					// foodyLocation[i]);
					// g.drawImage(foodImage, 1000, 250, this);
				}
			}
			if (dispTrash) {
				for (int i = 0; i < numTrash; i++) {
					trashxLocation[i] -= autoscrolldpt;
					g.drawImage(trashImage, trashxLocation[i], trashyLocation[i] + 50, this);
				}
			}

			// disp fish
			g.drawImage(fishImage, cursorx, cursory, this);
			//g2d.drawImage(fishImage, fishxLocation, fishyLocation, this);
			// display instructions
			// TODO: add game intruction tutorial mode
		}

		/**
		 * updates positions
		 */
		public void update() {
			// update background position
			bg1xpos -= autoscrolldpt;
			bg2xpos -= autoscrolldpt;

			// push items to back end of screen
			// System.out.println("update");
			for (int i = 0; i < numFood; i++) {
				if (foodxLocation[i] <= 0) {
					foodxLocation[i] = playLength + 100;
					foodyLocation[i] = (int) (Math.random() * playHeight);
				}
			}

			for (int i = 0; i < numTrash; i++) {
				if (trashxLocation[i] <= 0) {
					trashxLocation[i] = playLength + 100;
					trashyLocation[i] = (int) (Math.random() * playHeight);
				}
			}

			// relocateMap
			if (bg1xpos < bg2xpos && bg1xpos < (-bgLength)) {
				bg1xpos = bg2xpos + (bgLength);
			} else if (bg1xpos > bg2xpos && bg2xpos < (-bgLength)) {
				bg2xpos = bg1xpos + (bgLength);
			}

			// update mode
			if (mode == "instructions") {
				pauseMovement = true;
			} else if (mode == "moveFish") {
				text = "Use mouse to move fish!";
				dispFood = false;
				dispTrash = false;
				pauseMovement = false;
			} else if (mode == "getFood") {
				text = "Move fish to food to eat fish";
				dispFood = true;
				dispTrash = false;
				pauseMovement = false;
			} else if (mode == "avoidTrash") {
				text = "Avoid the trash as you are swimming";
				dispFood = true;
				dispTrash = true;
				pauseMovement = false;
			} else if (mode == "accumulateTrash") {
				text = "Trash accumulates in the game, keep avoiding trash!";
				dispFood = false;
				dispTrash = false;
				pauseMovement = false;
			} else if (mode == "collectTrash") {
				text = "You hit trash";
				dispFood = false;
				dispTrash = false;
				pauseMovement = false;
			}

			// runTutorial();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			System.out.println(e.getX() + " " + e.getY());
			cursorx = e.getX();
			cursory = e.getY();
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
	
	/**
	 * @return	cursorx - cursor x position relative to the top left corner of the play screen
	 */
	public int getCursorx(){
		return cursorx;
	}
	
	/**
	 * @return	cursory - cursor y position relative to the top left corner of the play screen
	 */
	public int getCursory(){
		return cursory;
	}

}
