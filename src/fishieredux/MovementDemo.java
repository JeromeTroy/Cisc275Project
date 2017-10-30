package fishieredux;

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

import acontroller.TestController;

public class MovementDemo extends JPanel implements ActionListener, MouseMotionListener {
	private String[] layerStrings = { "Yellow (0)", "Magenta (1)", "Cyan (2)", "Red (3)", "Green (4)", "Blue (5)" };
	private Color[] layerColors = { Color.yellow, Color.magenta, Color.cyan, Color.red, Color.green, Color.blue };

	//public TestController tc;
	public int fishX;
	public int fishY;
	private JLayeredPane layeredPane;
	private JLabel dukeLabel;
	private JLabel bgLabel;
	private Image bgImage;
	private JCheckBox onTop;
	private JComboBox layerList;

	// Action commands
	// private static String ON_TOP_COMMAND = "ontop";
	// private static String LAYER_COMMAND = "layer";

	public MovementDemo() {
		fishX = 0;
		fishY = 0;
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		// Create and load the duke icon.
		final ImageIcon icon = createImageIcon("images/fishie.png");

		// Create and load the background image.
		final ImageIcon bg = createImageIcon("images/bg.png");

		// Create and set up the layered pane.
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(700, 500));
		layeredPane.setBorder(BorderFactory.createTitledBorder("Move the Mouse to Move Fishie"));
		layeredPane.addMouseMotionListener(this);

		// Add several labels to the layered pane.
		layeredPane.setLayout(new GridLayout(1, 1));
		JLabel label = createColoredLabel("Seawater", Color.blue);
		// for (int i = 0; i < layerStrings.length; i++) {
		// JLabel label = createColoredLabel(layerStrings[i], layerColors[i]);
		// layeredPane.add(label, new Integer(i));
		// }

		// Create and add the background to the pane.

//		bgLabel = new JLabel(bg);
//		if (icon == null) {
//			System.err.println("Background icon not found; using black rectangle instead.");
//			bgLabel.setOpaque(true);
//			bgLabel.setBackground(Color.BLACK);
//		}
//		layeredPane.add(bgLabel, new Integer(0), 0);
		
		bgImage = bg.getImage();
		if (bg == null){
			System.err.println("NOt found, black rectangle as substitute");
			//TODO: set black rectangle
		}
		

		// Create and add the Duke label to the layered pane.
		dukeLabel = new JLabel(icon);
		if (icon == null) {
			System.err.println("Fishie icon not found; using black rectangle instead.");
			dukeLabel.setOpaque(true);
			dukeLabel.setBackground(Color.BLACK);
		}
		layeredPane.add(dukeLabel, new Integer(2), 0);

		// Add control pane and layered pane to this JPanel.
		add(Box.createRigidArea(new Dimension(0, 10)));
		// add(createControlPanel());
		add(Box.createRigidArea(new Dimension(0, 10)));
		add(layeredPane);
	}
	
	

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = MovementDemo.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	// Create and set up a colored label.
	private JLabel createColoredLabel(String text, Color color) {
		JLabel label = new JLabel(text);
		label.setVerticalAlignment(JLabel.TOP);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		label.setBackground(color);
		label.setForeground(Color.black);
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		label.setPreferredSize(new Dimension(400, 200));
		return label;
	}

	// Create the control pane for the top of the frame.
	private JPanel createControlPanel() {
		onTop = new JCheckBox("Top Position in Layer");
		onTop.setSelected(true);
		// onTop.setActionCommand(ON_TOP_COMMAND);
		onTop.addActionListener(this);

		layerList = new JComboBox(layerStrings);
		layerList.setSelectedIndex(2); // cyan layer
		// layerList.setActionCommand(LAYER_COMMAND);
		layerList.addActionListener(this);

		JPanel controls = new JPanel();
		controls.add(layerList);
		controls.add(onTop);
		// controls.setBorder(BorderFactory.createTitledBorder("Choose Duke's
		// Layer and Position"));
		return controls;
	}

	// Make Duke follow the cursor.
	public void mouseMoved(MouseEvent e) {
		dukeLabel.setLocation(e.getX() - dukeLabel.getWidth() / 2, e.getY() - dukeLabel.getHeight() / 2);
		TestController.fishXPos = e.getX();
		TestController.fishYPos = e.getY();
		System.out.println("hi");
		//TestController.updateModel();
	}

	public void mouseDragged(MouseEvent e) {
	} // do nothing

	// Handle user interaction with the check box and combo box.
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();

		// if (ON_TOP_COMMAND.equals(cmd)) {
		// if (onTop.isSelected())
		// layeredPane.moveToFront(dukeLabel);
		// else
		// layeredPane.moveToBack(dukeLabel);
		//
		// } else if (LAYER_COMMAND.equals(cmd)) {
		// int position = onTop.isSelected() ? 0 : 1;
		// layeredPane.setLayer(dukeLabel, layerList.getSelectedIndex(),
		// position);
		// }
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("LayeredPaneDemo2");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		JComponent newContentPane = new MovementDemo();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void activateView(){
		createAndShowGUI();
	}



	

	
}
