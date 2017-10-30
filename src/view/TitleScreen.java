package view;


// imports
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


public class TitleScreen extends JPanel implements ActionListener, MouseMotionListener {
	
	private MainController c;
	
	// attributes
	private JLayeredPane layeredPane;
	private JButton gameStart;
	private JButton tutorial;
	private JButton FishCaught;
	private JButton MiniGameOver;
	private JButton MainGameOver;
	
	private static String GO_TO_GAME = "goToGame";
	private static String GO_TO_TUTORIAL = "goToTutorial";
	
	// methods
	
	// constructor
	public TitleScreen(){
		//invoke constructor
		c = new MainController();
		
		// layout
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		// layered pane
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(300,300));
		layeredPane.setBorder(BorderFactory.createTitledBorder("Title Screen"));
		
		layeredPane.addMouseMotionListener(this);
		
		add(Box.createRigidArea(new Dimension(0,10)));
		add(layeredPane);
		add(createControlPanel());		

	}
	
	private JButton createButton(String label, String actionCommand) {
		JButton b = new JButton(label);
		b.setActionCommand(actionCommand);
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
		controls.add(FishCaught);
		controls.add(MiniGameOver);
		controls.add(MainGameOver);
		controls.setBorder(BorderFactory.createTitledBorder("Choose"));
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
			c.startGame();
			GamePlayScreen.activateGamePlayScreen(c);
			//TODO: need action to open game
		} else if (cmd == "goToTutorial"){
			c.startTutorial();
			//TODO: get rid of below
		} else if (cmd== "fishCaught"){
			c.getModel().getFishy().setCaught(!c.getModel().getFishy().getIsCaught());
		} else if (cmd == "miniGameOver"){
			c.getMiniGame().setOver(true);
		} else if (cmd == "mainGameOver"){
			c.getModel().setOver(true);
		}
		
	}
	
	
	private static void createAndShowGUI() {
		// window
		JFrame frame = new JFrame("TitleScreen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// content
        JComponent newContentPane = new TitleScreen();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
		
		// display
        frame.pack();
        frame.setVisible(true);
	}
	
	
	public static void activateTitle() {
		createAndShowGUI();
	}

}
