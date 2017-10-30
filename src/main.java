import controller.MainController;
import controller.RunGame;

public class main {

	public static void main(String[] args) {
		Runnable theGame = new RunGame();
		javax.swing.SwingUtilities.invokeLater(theGame);
	}
	
	private static void openGame() {
		Runnable theGame = new RunGame();
		javax.swing.SwingUtilities.invokeLater(theGame);
	}
	
}
