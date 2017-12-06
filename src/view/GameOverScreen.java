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


public class GameOverScreen extends GodView implements MouseMotionListener {
	
	
	// attributes
	private JLayeredPane layeredPane;
	private JButton tryAgain;
	private JButton returnToTitle;
	
	private static String RETURN_TO_TITLE = "goToTitle";
	private static String TRY_AGAIN = "goToGame";
	
	private MainController game;
	
	// methods
	
	// constructor
	public GameOverScreen() {
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(300,300));
		layeredPane.setBorder(BorderFactory.createTitledBorder("Game Over"));
		layeredPane.addMouseMotionListener(this);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(layeredPane);
		
	}
	public GameOverScreen(MainController c){
		//invoke constructor
		
		game = c;
		// layout
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		// layered pane
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(300,300));
		layeredPane.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //TODO: remove
		
		layeredPane.addMouseMotionListener(this);
		//BufferedImage myPicture = createBufferedImage(c.getTitleURL());
		//JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		//picLabel.setPreferredSize(new Dimension(700,700));
		//layeredPane.add(picLabel);
		
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
		tryAgain = createButton("Try Again?", TRY_AGAIN);
		tryAgain.addActionListener(this);
		returnToTitle = createButton("Return To Title?",RETURN_TO_TITLE);
		returnToTitle.addActionListener(this);

		JPanel controls = new JPanel();
		controls.add(tryAgain);
		controls.add(returnToTitle);
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
		if (cmd == "tryAgain"){
			System.out.println("game");
			game.startGame();
			//c.showTitleScreen(true);
			//TODO: need action to open game
		} else if (cmd == "returnToTitle"){
			System.out.println("Return Pressed");
			game.showTitleScreen();
			//TODO: get rid of below
		} 
		
	}
	
	
	private static GameOverScreen createAndShowGUI(Window frame, MainController c) {
		// window
		//JFrame frame = new JFrame("TitleScreen");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// content
        JComponent newContentPane = new GameOverScreen(c);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		// display
        frame.pack();
        frame.setVisible(true);
        
        return (GameOverScreen) newContentPane;
	}
	
	
	public static GameOverScreen activateTitle(Window w, MainController c) {
		return createAndShowGUI(w, c);
	}
	
	

}
