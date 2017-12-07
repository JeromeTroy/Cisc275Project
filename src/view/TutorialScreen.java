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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import controller.MainController;
import model.MainModel;
import view.GamePlayScreen.MiniGameScreen;

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
	private JLayeredPane layeredPane;
	private static JLabel instructions;
	private JButton gameStart;
	private JButton titleScreen;
	private static Window window;
	static Timer timer;
	private static MiniGameScreen mgs;

	// Images
	private BufferedImage fishImage;
	private BufferedImage foodImage;
	private static BufferedImage diverImage;
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
	private static int playHeight = windowHeight;
	private String dir;

	// controller
	private static MainController c; // the controller operating the game

	// Tutorial Screen Settings
	private static int[] foodxLocation;
	private static int[] foodyLocation;
	private static int[] trashxLocation;
	private static int[] trashyLocation;
	private int cursorx;
	private int cursory;
	private static int numFood = 10;
	private static int numTrash = 25;
	private static boolean dispFood = true;
	private static boolean dispTrash = true;

	private static int autoscrolldpt = 0; // autoscroll x distance per tick
											// (positive objects travel to left)
	private static int bg1xpos;
	private static int bg2xpos;

	private static String mode;
	private static String text;
	private static boolean pauseMovement;
	private int tick;
	private JPanel instructionsPanel;
	private static PlayScreen gamePanel;

	private static boolean useMSG;
	String directions;
	JLabel directionsLabel;

	
	private void setupInstructions(String s) {
		instructionsPanel.remove(getInstructions());
		setInstructions(new JLabel(s));
		getInstructions().setFont(new Font("Arial", Font.PLAIN, 50));
		getInstructions().setSize(50, instructionsHeight);
		instructionsPanel.add(getInstructions());
	}
	
	// constructor
	public TutorialScreen() {
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
		// setSize(new Dimension(windowWidth,windowHeight));

		// add title panel
		instructionsPanel = new JPanel();
		instructionsPanel.setBorder(BorderFactory.createLineBorder(Color.green));
		instructionsPanel.setMaximumSize(new Dimension(playLength, instructionsHeight));
		dir = "Estuary Adventure Tutorial Mode - eat the food but avoid the trash!";
		
		setInstructions(new JLabel(dir));
		
		getInstructions().setFont(new Font("Arial", Font.PLAIN, 50));
		getInstructions().setSize(50, instructionsHeight);
		instructionsPanel.add(getInstructions());
		instructionsPanel.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
		add(instructionsPanel);
		

		// add game panel
		gamePanel = new PlayScreen();
		gamePanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gamePanel.setPreferredSize(new Dimension(playLength, playHeight));
		gamePanel.setBorder(BorderFactory.createLineBorder(Color.red));

		// add layered pane for minigame
		layeredPane = new JLayeredPane(); // create

		mgs = new MiniGameScreen(playLength, playHeight);
		layeredPane.add(mgs, gbc);
		layeredPane.setPreferredSize(new Dimension(playLength / 2, playHeight / 2)); // resize
		gamePanel.add(layeredPane, gbc);
		// gamePanel.revalidate();
		add(gamePanel);

		// mgs.setVisible(true);

		add(createControlPanel());

		// create map locations
		bg1xpos = 0;
		bg2xpos = bgLength;

		int mainCharRad = (int) Math.sqrt(Math.pow(fishImage.getHeight(), 2) + Math.pow(fishImage.getWidth(), 2));
		int foodSize = (int) Math.sqrt(Math.pow(foodImage.getHeight(), 2) + Math.pow(foodImage.getWidth(), 2));
		int trashSize = (int) Math.sqrt(Math.pow(trashImage.getHeight(), 2) + Math.pow(trashImage.getWidth(), 2));
		int mapHeight = playHeight;
		int mapUnique = playLength;
		int mapLength = mapUnique * 1000;

		MainModel.setup(c.getTutorial(), mainCharRad, foodSize, trashSize, mapHeight, mapLength, mapUnique);// ,
																											// playLength,
																											// playHeight);//,
																											// gamePanel.getWidth(),gamePanel.getHeight());
		c.getTutorial().setMiniHeight(playHeight / 2);
		c.getTutorial().setMiniWidth(playLength / 2);

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
		b.setFont(new Font("Arial", Font.PLAIN, 40));
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
		controls.setMaximumSize(new Dimension(windowWidth, controlpanelHeight));
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
		// MainController c = new MainController(true);
		if (cmd == "goToGame") {
			window.stopAndRemoveTimer(timer);
			c.startGame();
			// setUseMGS(!useMSG);
		} else if (cmd == "goToTitle") {
			window.stopAndRemoveTimer(timer);
			this.c.showTitleScreen();
			c.newGame();

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
		frame.pack();
		// frame.setSize(windowWidth,windowHeight);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);

		// System.out.println("disp"); // TODO: remove
		// create Timer - updates and paints
		// create Swing timer with actionListener
		timer = new Timer(40, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// update();
				mgs.setVisible(useMSG);
				if (useMSG) {
					TutorialScreen content = (TutorialScreen) newContentPane;
					content.setupInstructions("Collect all the trash!");
					c.getTutorial().getMiniGame().getMainCharacter().setRadius((int) Math.sqrt(Math.pow(diverImage.getHeight(),2) + Math.pow(diverImage.getWidth(), 2))-50);
					mgs.update();
					mgs.repaint();
				} else {
					TutorialScreen content = (TutorialScreen) newContentPane;
					content.setupInstructions("Estuary Adventure Tutorial Mode - eat the food but avoid the trash!");
					gamePanel.update();
					gamePanel.repaint();
				}
				newContentPane.repaint();
				newContentPane.revalidate();
				// System.out.println("paint tut"); //TODO: remove
			}
		});
		frame.addTimer(timer);

		timer.start();

	}

	public static void activateTutorial(MainController c, Window w) {
		TutorialScreen.c = c;
		TutorialScreen.window = w;
		createAndShowGUI(w);
	}

	// public void paintComponent(Graphics g) {
	//
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
			//this.setLayout(new FlowLayout(FlowLayout.CENTER));
			// add instructions panel
			
			//add directions
			JPanel p = new JPanel();
			p.setSize(200, 20);
			setLayout(new GridLayout(2,2));
			/*
			directions = "Estuary Adventure Tutorial Mode";
			instructions = new JLabel(directions);
			instructions.setFont(new Font("Arial", Font.PLAIN, 80));
			//panel.add(directionsLabel);

			//directionsLabel.setVerticalTextPosition(-500);
			instructions.setBounds(-250,0,10,10);
			instructions.setSize(500, 500);
			//add(panel);
			add(instructions);
			*/
			
			
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			// disp map
			g.drawImage(bgImage1, bg1xpos, 0, playLength, playHeight, this);
			g.drawImage(bgImage1, bg2xpos, 0, playLength, playHeight, this);

			// disp objects
			for (int[] loc : c.getTutorial().getStuffSet().getFood()) {
				g.drawImage(foodImage, loc[0], loc[1], this);
			}
			for (int[] loc : c.getTutorial().getStuffSet().getTrash()) {
				g.drawImage(trashImage, loc[0], loc[1], this);
			}

			// disp fish
			int mouseX = cursorx;
			int mouseY = cursory;

			System.out.println("mouse at <" + mouseX + ", " + mouseY + ">");

			double newSpeed = c.getTutorial().getMainCharacter().getPosition().distFrom(mouseX, mouseY);
			int deltaTheta = c.getTutorial().getMainCharacter().getPosition().angleBetween(mouseX, mouseY);
			System.out.println(deltaTheta);
			c.getTutorial().update((int) newSpeed, deltaTheta);

			double tmpx = c.getTutorial().getMainCharacter().getPosition().getX();
			double tmpy = c.getTutorial().getMainCharacter().getPosition().getY();
			int fishx = (int) tmpx;
			int fishy = (int) tmpy;
			// g.drawImage(fishImage, cursorx, cursory, this); // where cursor
			// is
			g.drawImage(fishImage, fishx - 80, fishy - 20, this); // where the
																	// fish is
																	// on the

			// g2d.drawImage(fishImage, fishxLocation, fishyLocation, this);
			// display instructions
			// TODO: add game intruction tutorial mode
			

			System.out.println("PAINT " + fishx + " " + fishy);
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

			// update use MSG
			setUseMGS(c.getTutorial().getInMiniGame());
			if (useMSG) {
				setUseMGS(!c.getTutorial().getMiniGame().getMiniGameOver());
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
			if (!useMSG) {
				// System.out.println("PLAY SCREEN "+e.getX() + " " + e.getY());
				cursorx = e.getX();
				cursory = e.getY();
			} else {
				// System.out.println("MGS SCREEN "+e.getX() + " " + e.getY());
				Point p = SwingUtilities.convertPoint(gamePanel, e.getPoint(), layeredPane);
				if (layeredPane.contains(p)) {
					System.out.println(p);
					cursorx = (int) p.getX();
					cursory = (int) p.getY();
				}

			}
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			if (!useMSG) {
				// System.out.println("PLAY SCREEN "+e.getX() + " " + e.getY());
				cursorx = e.getX();
				cursory = e.getY();
			} else {
				// System.out.println("MGS SCREEN "+e.getX() + " " + e.getY());
				
				Point p = SwingUtilities.convertPoint(gamePanel, e.getPoint(), layeredPane);
				if (layeredPane.contains(p)) {
					System.out.println(p);
					cursorx = (int) p.getX();
					cursory = (int) p.getY();
				}

			}

		}
	}

	private class MiniGameScreen extends GodView {

		public MiniGameScreen(int width, int height) {
			this.setBounds(0, 0, width, height);
			this.setBackground(Color.BLACK);
			this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(minibgImage, 0, 0, layeredPane.getWidth(),layeredPane.getHeight(), MiniGameScreen.this);
			
			//draw trash and food
			for (int[] loc : c.getTutorial().getMiniGame().getStuffSet().getTrash()) {
				g.drawImage(trashImage, loc[0], loc[1], this);
				//System.out.println(loc[0]+" "+ loc[1]);
			}
			for (int[] loc : c.getTutorial().getMiniGame().getStuffSet().getTrash()) {
				g.drawImage(trashImage, loc[0], loc[1], this);
			}
			
			
			int mouseX = cursorx;
			int mouseY = cursory;
			
			
			System.out.println("mouse at <" + mouseX + ", " + mouseY + ">");

						
			
			double newSpeed = c.getTutorial().getMiniGame().getMainCharacter().getPosition().distFrom(mouseX, mouseY);
			int deltaTheta = c.getTutorial().getMiniGame().getMainCharacter().getPosition().angleBetween(mouseX, mouseY);
			System.out.println(deltaTheta);
			c.getTutorial().update(0,deltaTheta);
			
			c.getTutorial().getMiniGame().getMainCharacter().getPosition().setX(mouseX);
			c.getTutorial().getMiniGame().getMainCharacter().getPosition().setY(mouseY);;										
			
			//draw the diver
			g.drawImage(diverImage, mouseX-50, mouseY-80, this);

		}

		public void update() {
			// color = new Color(r.nextInt());
			setUseMGS(c.getTutorial().getInMiniGame());
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
	 * @return cursorx - cursor x position relative to the top left corner of
	 *         the play screen
	 */
	public int getCursorX() {
		return cursorx;
	}

	/**
	 * @return cursory - cursor y position relative to the top left corner of
	 *         the play screen
	 */
	public int getCursorY() {
		return cursory;
	}

	public void setUseMGS(Boolean b) {
		boolean tmp = useMSG;
		useMSG = b;
		if ((tmp!=useMSG) && useMSG){
			cursorx = 0;
			cursory = 0;
		}
	}

	public static JLabel getInstructions() {
		return instructions;
	}

	public static void setInstructions(JLabel instructions) {
		TutorialScreen.instructions = instructions;
	}

}
