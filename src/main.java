import controller.MainController;
import controller.RunGame;

public class main {

	public static void main(String[] args) {
		openGame();
	}
	
	private static void openGame() {
		Runnable theGame = new RunGame();
		javax.swing.SwingUtilities.invokeLater(theGame);
	}
	
}
