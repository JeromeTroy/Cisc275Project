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
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
	private JPanel infoPanel;
	
	Color customColor = new Color(98,101,176);
	
	// methods
	
	// constructor
	public TitleScreen() {
		layeredPane = new JPanel();
		layeredPane.setPreferredSize(new Dimension(300,300));
		//layeredPane.setBorder(BorderFactory.createTitledBorder("Game Over"));
		layeredPane.addMouseMotionListener(this);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(layeredPane);
		
	}
	public TitleScreen(MainController c){
		//invoke constructor
		
		game = c;
		// layout
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		// layered pane
		layeredPane = new JPanel();
		layeredPane.setPreferredSize(new Dimension(700,700));
		//layeredPane.setBorder(BorderFactory.createTitledBorder("Title Screen")); //TODO: remove
		
		layeredPane.addMouseMotionListener(this);
		BufferedImage myPicture = createBufferedImage(c.getTitleURL());
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		picLabel.setPreferredSize(new Dimension(700,700));
		layeredPane.add(picLabel, gbc);
		
		
		infoPanel = new JPanel();
		JTextArea info = new JTextArea("Play as an eel migrating through the Delaware Estuary back to the sea to spawn.  \n"
				+ "Eat food to increase your eel's chance of survival and your score.  \n"
				+ "Avoid hitting the trash polluting the estuary.  If your eel gets \n"
				+ "caught in the trash, you will need to clean the trash to free your eel friend!");
		infoPanel.setSize(new Dimension(100,100));
		info.setFont(new Font("Arial",Font.PLAIN,30));
		info.setBackground(customColor);
		info.setSize(1000, 100);
		info.setForeground(Color.white);
		infoPanel.add(info,gbc);
		
		
		add(Box.createRigidArea(new Dimension(1,10)));
		gbc.weightx=0.0;
		gbc.gridwidth=3;
		gbc.gridx=1;
		gbc.gridy=0;
		add(layeredPane,gbc);
		gbc.weightx=0.0;
		gbc.gridwidth=3;
		gbc.gridx=1;
		gbc.gridy=1;
		add(createControlPanel(),gbc);
		gbc.weightx=0.0;
		gbc.gridwidth=3;
		gbc.gridx=1;
		gbc.gridy=2;
		add(infoPanel,gbc);
		setBackground(customColor);
		layeredPane.setBackground(customColor);
		infoPanel.setBackground(customColor);
		

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
		
		JPanel controls = new JPanel();
		controls.add(gameStart);
		controls.add(tutorial);
		controls.setBackground(customColor);
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
