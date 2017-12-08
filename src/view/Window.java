package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * Window class creates JFrame to hold JPanel game screens
 * All view timers should be added to window for proper program termination
 * 
 * @author Team 4
 *
 */
public class Window extends JFrame {
	static HashSet<Timer> timers = new HashSet<>();

	public Window() {
		// set JFrame settings
		setTitle("Estuary Adventure - DNERR");

		// set close action
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				// System.out.println("WindowClosing");
				//close window
				dispose();
				//stop timers
				for (Timer t : timers) {
					t.stop();
					//System.out.println("Timer Stopped");
				}
				//close program
				System.exit(0);
			}
		});

	}

	/**
	 * Add timers that need to be stopped to hashSet
	 * @param timer
	 */
	public void addTimer(Timer timer) {
		System.out.println("Timer Added");
		timers.add(timer);
		System.out.println(timers);
	}

	/**
	 * Remove and stop the timer from the HashSet
	 * @param timer
	 */
	public void stopAndRemoveTimer(Timer timer) {
		timer.stop();
		timers.remove(timer);
	}
}
