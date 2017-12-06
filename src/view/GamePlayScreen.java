package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Toolkit;
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
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import controller.GameTimer;
import controller.MainController;
import model.MainModel;
//import view.GameOverScreen;

public class GamePlayScreen extends GodView {

	// Swing Components
	private static JLayeredPane layeredPane;
	private JButton quit;
	// private JButton titleScreen;
	private static Window window;
	static Timer timer;
	private static MiniGameScreen mgs;
	private static PlayScreen gamePanel;
	//private static GameOverScreen gameOver;
	private static JPanel controlPanel;
	private static JLabel clock;
	private JLabel miniclock;

	// Images
	private BufferedImage fishImage;
	private BufferedImage trashImage;
	private BufferedImage foodImage;
	private static BufferedImage diverImage;
	private BufferedImage minibgImage;
	private BufferedImage bgImage1;
	private BufferedImage endImageGood;
	private BufferedImage endImageBad;
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static int windowWidth = (int) screenSize.getWidth();
	private static int windowHeight = (int) screenSize.getHeight();
	private int bgHeight = windowHeight;
	private static int bgLength = windowWidth;
	// windowHeght is based on the desired height of the tutorial screen window
	// based on background image size
	private static int playHeight = windowHeight;
	private final static int playLength = windowWidth;
	private final static int controlpanelHeight = 600;

	// controller
	private static MainController c;

	private ArrayList<JLabel> stuff;
	private int cursorx;
	private int cursory;
	protected static boolean useMSG;

	// settings
	private static int bg1xpos;
	private static int bg2xpos;
	private static int autoscrolldpt = 0;
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
		
		// Create and load the background image
		endImageGood = createBufferedImage(c.getEndbg_goodURL());

		// Create and load the background image
		endImageBad = createBufferedImage(c.getEndbg_badURL());
		// resize
		// set size of background
		bgLength = playLength;
		bgHeight = playHeight;

		// layout
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// add game panel
		gamePanel = new PlayScreen();
		gamePanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gamePanel.setPreferredSize(new Dimension(playLength, playHeight));
		gamePanel.setBorder(BorderFactory.createLineBorder(Color.red));
		
		// add clock
		
		GridBagConstraints gc = new GridBagConstraints();
	
        gc.anchor = GridBagConstraints.NORTHWEST;
    

		// add layered pane for minigame
		layeredPane = new JLayeredPane(); // create

		mgs = new MiniGameScreen(playLength, playHeight);
		layeredPane.add(mgs, gbc);
		layeredPane.setPreferredSize(new Dimension(playLength/2, playHeight/2)); // resize
		//layeredPane.setMinimumSize(minimumSize);
		gamePanel.add(layeredPane, gbc);
		gamePanel.revalidate();
		add(gamePanel);

		controlPanel = createControlPanel();
		add(controlPanel);

		// create map locations
		bg1xpos = 0;
		bg2xpos = bgLength;
		
		int mainCharRad = (int) Math.sqrt(Math.pow(fishImage.getHeight(),2) + Math.pow(fishImage.getWidth(), 2));
		int foodSize = (int) Math.sqrt(Math.pow(foodImage.getHeight(), 2) + Math.pow(foodImage.getWidth(), 2));
		int trashSize = (int) Math.sqrt(Math.pow(trashImage.getHeight(), 2) + Math.pow(trashImage.getWidth(), 2));
		int mapHeight = playHeight;
		int mapUnique = playLength;
		int mapLength = mapUnique * 1;
		
		MainModel.setup(c.getModel(), mainCharRad, foodSize, trashSize, mapHeight, mapLength, mapUnique);//, playLength, playHeight);//, gamePanel.getWidth(),gamePanel.getHeight());
		c.getModel().setMiniHeight(playHeight/2);
		c.getModel().setMiniWidth(playLength/2);
		

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
		b.setFont(new Font("Arial", Font.PLAIN, 40));
		b.setActionCommand(actionCommand);
		return b;
	}
	
	private JPanel createControlPanel() {
		quit = createButton("Quit", "quitGame");
		quit.addActionListener(this);
		JPanel controls = new JPanel();
		controls.setMinimumSize(new Dimension(playLength, controlpanelHeight));
		clock = new JLabel(c.getTimeString());
		//clock.setBackground(Color.BLUE);
		clock.setLocation(0, 0);
		//clock.setPreferredSize(new Dimension(400,400));
		clock.setFont(new Font("Arial", Font.PLAIN, 40));
		clock.setOpaque(true);
		controls.add(quit);
		controls.add(clock);
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
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);


		// create Timer
		// create Swing timer with actionListener
		timer = new Timer(40, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// update();
				layeredPane.setVisible(useMSG);
				if (useMSG) {
					c.getModel().getMiniGame().getMainCharacter().setRadius((int) Math.sqrt(Math.pow(diverImage.getHeight(),2) + Math.pow(diverImage.getWidth(), 2))-50);
					mgs.update();
					mgs.repaint();
					//System.out.println("MGS PANEL");
				} else {
					gamePanel.update();
					gamePanel.repaint();

					//System.out.println("GAME PANEL");

					if(c.getRemainingTime() > 0) {
						clock.setText(c.getTimeString());
						controlPanel.repaint();
					}
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

	}

	public static JComponent activateGamePlayScreen(MainController co, Window w) {
		c = co;
		GamePlayScreen.window = w;
		return createAndShowGUI(w);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		performAction(cmd);
	}
	
	public void performAction(String s) {
		if (s == "quitGame") {
			window.stopAndRemoveTimer(timer);
			c.showGameOver();
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
			
//			int mainCharRad = (int) Math.sqrt(Math.pow(fishImage.getHeight(),2) + Math.pow(fishImage.getWidth(), 2));
//			int foodSize = (int) Math.sqrt(Math.pow(foodImage.getHeight(), 2) + Math.pow(foodImage.getWidth(), 2));
//			int trashSize = (int) Math.sqrt(Math.pow(trashImage.getHeight(), 2) + Math.pow(trashImage.getWidth(), 2));
//			int mapHeight = playHeight;
//			int mapUnique = playLength;
//			int mapLength = mapUnique * 3;
//			
//			MainModel.setup(c.getModel(), mainCharRad, foodSize, trashSize, mapHeight, mapLength, mapUnique);//, gamePanel.getWidth(),gamePanel.getHeight());
//			
			
			
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			// disp map
			if(c.getGameOver()) {
				if(!c.getHasWon()) {
					g.drawImage(endImageBad, bg1xpos, 0, playLength, playHeight, this);
					g.drawImage(endImageBad, bg2xpos, 0, playLength, playHeight, this);
				}
				else {
					g.drawImage(endImageGood, bg1xpos, 0, playLength, playHeight, this);
					g.drawImage(endImageGood, bg2xpos, 0, playLength, playHeight, this);
				}
			}
			else {
				g.drawImage(bgImage1, bg1xpos, 0, playLength, playHeight, this);
				g.drawImage(bgImage1, bg2xpos, 0, playLength, playHeight, this);
			}
			
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
			c.getModel().update((int)newSpeed,deltaTheta);
			
			double tmpx = c.getModel().getMainCharacter().getPosition().getX();
			double tmpy = c.getModel().getMainCharacter().getPosition().getY();
			int fishx = (int) tmpx;
			int fishy = (int) tmpy;
			//g.drawImage(fishImage, cursorx, cursory, this); // where cursor is
			g.drawImage(fishImage, fishx-80, fishy-20, this); // where the fish is on the
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
			
			//update use MSG
			setUseMGS(c.getModel().getInMiniGame());
			if (useMSG){
				setUseMGS(!c.getModel().getMiniGame().getMiniGameOver());
			}

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (!useMSG){
				//System.out.println("PLAY SCREEN "+e.getX() + " " + e.getY());
				cursorx = e.getX();
				cursory = e.getY();
				} else {
					//System.out.println("MGS SCREEN "+e.getX() + " " + e.getY());
					Point p = SwingUtilities.convertPoint(gamePanel, e.getPoint(), layeredPane);
					if (layeredPane.contains(p)){
						System.out.println(p);
						cursorx = (int) p.getX();
						cursory = (int) p.getY();
					}
					
				}
		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			int timeRemaining = c.getModel().getRemainingTime();
			if (timeRemaining <= 0) {
				performAction("quitGame");
			}

		}
	}
	public class MiniGameScreen extends GodView implements MouseMotionListener {

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
			for (int[] loc : c.getModel().getMiniGame().getStuffSet().getTrash()) {
				g.drawImage(trashImage, loc[0], loc[1], this);
				//System.out.println(loc[0]+" "+ loc[1]);
			}
			for (int[] loc : c.getModel().getMiniGame().getStuffSet().getTrash()) {
				g.drawImage(trashImage, loc[0], loc[1], this);
			}
			
			
			int mouseX = cursorx;
			int mouseY = cursory;
			
			
			System.out.println("mouse at <" + mouseX + ", " + mouseY + ">");

						
			
			double newSpeed = c.getModel().getMiniGame().getMainCharacter().getPosition().distFrom(mouseX, mouseY);
			int deltaTheta = c.getModel().getMiniGame().getMainCharacter().getPosition().angleBetween(mouseX, mouseY);
			System.out.println(deltaTheta);
			c.getModel().update(0,deltaTheta);
			
			c.getModel().getMiniGame().getMainCharacter().getPosition().setX(mouseX);
			c.getModel().getMiniGame().getMainCharacter().getPosition().setY(mouseY);;										
			
			//draw the diver
			g.drawImage(diverImage, mouseX-50, mouseY-80, this);
		}

		public void update() {
			// color = new Color(r.nextInt());
			setUseMGS(c.getModel().getInMiniGame());
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

		@Override
		public void mouseDragged(MouseEvent e) {
//			if (!useMSG){
//				//System.out.println("PLAY SCREEN "+e.getX() + " " + e.getY());
//				cursorx = e.getX();
//				cursory = e.getY();
//				} else {
//					//System.out.println("MGS SCREEN "+e.getX() + " " + e.getY());
//					Point p = SwingUtilities.convertPoint(gamePanel, e.getPoint(), layeredPane);
//					if (layeredPane.contains(p)){
//						System.out.println(p);
//						cursorx = (int) p.getX();
//						cursory = (int) p.getY();
//					}
//					
//				}
		}
	}
	
	
	public int getCursorX() {
		return cursorx;
	}
	
	public int getCursorY() {
		return cursory;
	}
	
	public void setUseMGS(Boolean b){
		boolean tmp = useMSG;
		useMSG = b;
		if ((tmp!=useMSG) && useMSG){
			cursorx = 0;
			cursory = 0;
		}
	}
	public int getMiniGameWidth(){
		return layeredPane.getWidth();
	}
	
	public int gerMiniGameHeight(){
		return layeredPane.getHeight();
	}
	
	

	
}
