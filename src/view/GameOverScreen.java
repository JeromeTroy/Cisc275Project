package view;


// imports
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.MainController;


public class GameOverScreen extends GodView  {
	
	
	// attributes
	private JPanel layeredPane;
	private JButton tryAgain;
	private JButton returnToTitle;
	
	private static String RETURN_TO_TITLE = "goToTitle";
	private static String TRY_AGAIN = "goToGame";
	
	private MainController game;
	
	Color customColor = new Color(98,101,176);
	// methods
	
	// constructor
	public GameOverScreen() {
		layeredPane = new JPanel();
		layeredPane.setPreferredSize(new Dimension(300,300));
		//layeredPane.addMouseMotionListener(this);
		add(Box.createRigidArea(new Dimension(0,10)));
		add(layeredPane);
		
	}
	public GameOverScreen(MainController c){
		//invoke constructor
		
		game = c;
		// layout
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		// layered pane
		layeredPane = new JPanel();
		layeredPane.setPreferredSize(new Dimension(700,700));
		//layeredPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//layeredPane.addMouseMotionListener(this);
		BufferedImage myPicture = createBufferedImage(c.getTitleURL());
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		picLabel.setPreferredSize(new Dimension(700,700));
		layeredPane.add(picLabel);
		
		add(Box.createRigidArea(new Dimension(0,10)));
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
		setBackground(customColor);
		layeredPane.setBackground(customColor);

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
		tryAgain = createButton("Try Again", TRY_AGAIN);
		tryAgain.addActionListener(this);
		returnToTitle = createButton("Return To Title",RETURN_TO_TITLE);
		returnToTitle.addActionListener(this);

		JPanel controls = new JPanel();
		controls.add(tryAgain);
		controls.add(returnToTitle);
		controls.setBackground(customColor);
		return controls;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd == "goToGame"){
			System.out.println("game");
			game.startGame();
		} else if (cmd == "goToTitle"){
			System.out.println("Return Pressed");
			game.newGame();
		} 
		
	}
	
	
	private static GameOverScreen createAndShowGUI(Window frame, MainController c) {
		
		
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
	
	
	public static GameOverScreen activateGameOver(Window w, MainController c) {
		return createAndShowGUI(w, c);
	}
	
	

}
