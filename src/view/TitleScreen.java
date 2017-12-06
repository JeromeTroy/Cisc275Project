package view;


// imports
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
import javax.swing.JPanel;

import controller.MainController;

import javax.swing.JButton;


public class TitleScreen extends GodView implements MouseMotionListener {
	
	
	// attributes
	private JPanel layeredPane;
	private JButton gameStart;
	private JButton tutorial;
	private JButton FishCaught;
	private JButton MiniGameOver;
	private JButton MainGameOver;
	
	private static String GO_TO_GAME = "goToGame";
	private static String GO_TO_TUTORIAL = "goToTutorial";
	
	private MainController game;
	
	// methods
	
	// constructor
	public TitleScreen() {
		layeredPane = new JPanel();
		layeredPane.setPreferredSize(new Dimension(300,300));
		layeredPane.setBorder(BorderFactory.createTitledBorder("Game Over"));
		layeredPane.addMouseMotionListener(this);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(layeredPane);
		
	}
	public TitleScreen(MainController c){
		//invoke constructor
		
		game = c;
		// layout
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		// layered pane
		layeredPane = new JPanel();
		layeredPane.setPreferredSize(new Dimension(300,300));
		layeredPane.setBorder(BorderFactory.createTitledBorder("Title Screen")); //TODO: remove
		
		layeredPane.addMouseMotionListener(this);
		BufferedImage myPicture = createBufferedImage(c.getTitleURL());
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		picLabel.setPreferredSize(new Dimension(700,700));
		layeredPane.add(picLabel);
		
		add(Box.createRigidArea(new Dimension(0,10)));
		add(layeredPane);
		add(createControlPanel());		

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
	
	private JButton createButton(String label, String actionCommand) {
		JButton b = new JButton(label);
		b.setActionCommand(actionCommand);
		b.setFont(new Font("Arial", Font.PLAIN, 40));
		return b;
	}
	
	private JPanel createControlPanel(){
		gameStart = createButton("Start Game", GO_TO_GAME);
		gameStart.addActionListener(this);
		tutorial = createButton("Tutorial", GO_TO_TUTORIAL);
		tutorial.addActionListener(this);
		//TODO: remove
		FishCaught = createButton("fishCaught", "fishCaught");
		FishCaught.addActionListener(this);
		MiniGameOver = createButton("miniGameOver", "miniGameOver");
		MiniGameOver.addActionListener(this);
		MainGameOver = createButton("mainGameOver", "mainGameOver");
		MainGameOver.addActionListener(this);
		
		JPanel controls = new JPanel();
		controls.add(gameStart);
		controls.add(tutorial);
		//TODO:remove
//		controls.add(FishCaught);
//		controls.add(MiniGameOver);
//		controls.add(MainGameOver);
//		controls.setBorder(BorderFactory.createTitledBorder("Choose"));
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
		if (cmd == "goToGame"){
			System.out.println("game");
			game.startGame();
			//c.showTitleScreen(true);
			//TODO: need action to open game
		} else if (cmd == "goToTutorial"){
			System.out.println("Tutorial Pressed");
			game.startTutorial();
			//TODO: get rid of below
		} else if (cmd== "fishCaught"){
			//c.getModel().getFishy().setCaught(!c.getModel().getFishy().getIsCaught());
		} else if (cmd == "miniGameOver"){
			//c.getMiniGame().setGameOver(true);
		} else if (cmd == "mainGameOver"){
			//c.getModel().setGameOver(true);
		}
		
	}
	
	
	private static TitleScreen createAndShowGUI(Window frame, MainController c) {
		// window
		//JFrame frame = new JFrame("TitleScreen");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// content
        JComponent newContentPane = new TitleScreen(c);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		// display
        frame.pack();
        frame.setVisible(true);
        
        return (TitleScreen) newContentPane;
	}
	
	
	public static TitleScreen activateTitle(Window w, MainController c) {
		return createAndShowGUI(w, c);
	}
	
	

}
