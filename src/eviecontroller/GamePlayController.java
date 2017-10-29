package eviecontroller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import view.GamePlayScreen;
import model.FishCharacter;
import eviemodel.MovementTestModel;

public class GamePlayController {
	
	public static GamePlayScreen thisGameScreen;
	public MovementTestModel thisModel;

	public GamePlayController(){
		//f = new FishCharacter();
		thisGameScreen = new GamePlayScreen(this);
		thisModel = new MovementTestModel();
		GameMouseMotion thisMouse = new GameMouseMotion();
		//thisGameScreen.getLayeredPane().addMouseMotionListener(thisMouse);
		//thisGameScreen.layeredPane.addMouseMotionListener(this);
		thisGameScreen.fishMovement(10,10);
		thisGameScreen.setGameScore(thisModel.getFishy().getScore());
	}
	
//	@Override
//	public void mouseDragged(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseMoved(MouseEvent e) {
//		// TODO Auto-generated method stub
//		thisModel.moveFishy(e.getX(), e.getY());
//		thisModel.printFishy();
//		System.out.println("bababooey");
//		thisGameScreen.getFishLabel().setLocation(10, 10);
//		//thisGameScreen.getFishLabel().setLocation(f.getPosition().getX() - thisGameScreen.getFishLabel().getWidth() / 2, f.getPosition().getX() - thisGameScreen.getFishLabel().getHeight() / 2);
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		String cmd = e.getActionCommand();
//		
//	}
	
	public static void activateGame(){
		GamePlayController g = new GamePlayController();
		
		g.thisGameScreen.activateGamePlayScreen();
		
	}
	
	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				activateGame();
			}
		});
	}

}
