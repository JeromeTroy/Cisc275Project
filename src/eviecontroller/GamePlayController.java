package eviecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import view.GamePlayScreen;
import model.FishCharacter;

public class GamePlayController implements ActionListener, MouseMotionListener  {
	
	static GamePlayScreen thisGameScreen;
	FishCharacter f;

	public GamePlayController(){
		f = new FishCharacter();
		thisGameScreen = new GamePlayScreen();
		thisGameScreen.getLayeredPane().addMouseMotionListener(this);
		thisGameScreen.fishMovement(10,10);
	}
	
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		thisGameScreen.getFishLabel().setLocation(10, 10);
		//thisGameScreen.getFishLabel().setLocation(f.getPosition().getX() - thisGameScreen.getFishLabel().getWidth() / 2, f.getPosition().getX() - thisGameScreen.getFishLabel().getHeight() / 2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		
	}
	
	
	
	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				thisGameScreen.activateGamePlayScreen();
			}
		});
	}

}
