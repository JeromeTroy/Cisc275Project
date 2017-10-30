package acontroller;

import amodel.TestModel;
import fishieredux.MovementDemo;
import model.MainGameModel;

public class TestController {
	static MovementDemo view;
	//static TestModel model;
	static MainGameModel m;
	public static int fishXPos;
	public static int fishYPos;
	
	public TestController(){
		view = new MovementDemo();
		//view.setController(this);
		//model = new TestModel();
		//model.setController(this);
		m = new MainGameModel();
		fishXPos = 0;
		fishYPos = 0;
	}
	
	public static void updateModel(){
		System.out.println(fishXPos);
		System.out.println(m.getTrashAmount());
		//model.f.getClass();
		//model.f.moveToPoint(10, 10);
		//System.out.println(model.f.getName());
	}
	
	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		//System.out.println("hi there");
		//System.out.println(m.getTrashAmount());
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				view.activateView();
				updateModel();
				//System.out.println("hi there");
			}
		});
	}

}
