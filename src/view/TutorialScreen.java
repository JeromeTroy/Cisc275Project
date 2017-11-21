package view;

//imports
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

import controller.MainController;

import javax.swing.JButton;

public class TutorialScreen extends JPanel implements ActionListener, MouseMotionListener {
	private JLabel instructions;
	private static MainController c; // the controller operating the game
	private JButton gameStart;
	private JButton titleScreen;
	private static Window window;
	private BufferedImage fishImage;
	private BufferedImage foodImage;
	private BufferedImage bgImage;
	private BufferedImage trashImage;
	private final int mapHeight = 500;
	private final int mapLength = 2000;
	private int[] foodxLocation;
	private int[] foodyLocation;
	private int[] trashxLocation;
	private int[] trashyLocation;
	private int numFood = 10;
	private int numTrash = 25;
	private boolean dispFood = true;
	private boolean dispTrash = false;

	// constructor
	public TutorialScreen() {
		// layout
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// layered pane
		JLayeredPane layeredPane = new JLayeredPane(); // create
		layeredPane.setPreferredSize(new Dimension(mapLength, mapHeight)); // resize
		layeredPane.setBorder(BorderFactory.createTitledBorder("Tutorial")); // TODO:
																				// Remove
		layeredPane.addMouseMotionListener(this); // add mouse listener

		// add Mouse Motion
		// addMouseMotionListener(this);
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(layeredPane);
		add(createControlPanel());
		instructions = new JLabel("Use mouse to move the fish. Eat food, don't eat trash");
		add(instructions);

		// create buffered images
		// Create and load the duke icon.
		try {
			fishImage = ImageIO.read(new File(c.getFishURL()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Create and load food icon
		try {
			foodImage = ImageIO.read(new File(c.getFoodURL()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Create and load trash icon
		try {
			trashImage = ImageIO.read(new File(c.getTrashURL()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Create and load the background image.
		try {
			bgImage = ImageIO.read(new File(c.getBgURL()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// generate food locations
		foodxLocation = new int[numFood];
		foodyLocation = new int[numFood];
		for (int i = 0; i < numFood; i++) {
			foodxLocation[i] = (int) (Math.random() * mapLength);
			foodyLocation[i] = (int) (Math.random() * mapHeight);
		}

		// generate trash locations
		trashxLocation = new int[numTrash];
		trashyLocation = new int[numTrash];
		for (int i = 0; i < numTrash; i++) {
			trashxLocation[i] = (int) (Math.random() * mapLength);
			trashyLocation[i] = (int) (Math.random() * mapHeight);
		}
		

	}

	private JButton createButton(String label, String actionCommand) {
		JButton b = new JButton(label);
		b.setActionCommand(actionCommand);
		return b;
	}

	private JPanel createControlPanel() {
		gameStart = createButton("Start Game", "goToGame");
		gameStart.addActionListener(this);
		titleScreen = createButton("Return to Title Screen", "goToTitle");
		titleScreen.addActionListener(this);

		JPanel controls = new JPanel();
		controls.add(gameStart);
		controls.add(titleScreen);
		return controls;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		MainController c = new MainController(true);
		if (cmd == "goToGame") {
			c.startGame();
			// TODO: need action to open game
		} else if (cmd == "goToTitle") {
			c.showTitleScreen();

		}
	}

	private static TutorialScreen createAndShowGUI(Window frame) {
		// content
		JComponent newContentPane = new TutorialScreen();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// display
		frame.pack();
		frame.setVisible(true);
		
		return (TutorialScreen) newContentPane;
	}

	public static TutorialScreen activateTutorial(MainController c, Window w) {
		TutorialScreen.c = c;
		TutorialScreen.window = w;
		return createAndShowGUI(w);
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//disp map
		g.drawImage(bgImage, 0, 0, this);
		
		if (dispFood){
			for (int i=0; i<numFood; i++){
				g.drawImage(foodImage, foodxLocation[i], foodyLocation[i], this);
				System.out.println(foodxLocation[i]+" "+ foodyLocation[i]);
				//g.drawImage(foodImage, 1000, 250, this);
			}
		}
		if (dispTrash){
			for (int i=0; i<numTrash; i++){
				g.drawImage(trashImage, trashxLocation[i], trashyLocation[i], this);
			}
		}
		
		//paintComponent(g);
		
		
	}

	public void update() {
		this.repaint();
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void runTutorial() {
		System.out.println("the");
	}

}
