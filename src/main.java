import controller.MainController;
import controller.RunGame;
import view.TitleScreen;

public class main {

	//invoke the program
	public static void main(String[] args) {
		openGame();
	}
	
	/* openGame() - static method to open title screen
	 * 			
	 */
	private static void openGame() {
		
		Runnable theGame = new RunGame();
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				TitleScreen.activateTitle();
				//GamePlayScreen.activateGamePlayScreen();
			}
		});
	}
	
}
