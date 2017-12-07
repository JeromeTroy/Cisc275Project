package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class GodView extends JPanel implements ActionListener{

	private int cursorx;
	private int cursory;
	
	
	public void actionPerformed(ActionEvent e) {
		
	}
	
	public void mouseMoved(MouseEvent e) {
		cursorx = e.getX();
		cursory = e.getY();
	}
	
	
	public int getCursorX() {
		return cursorx;
	}
	
	public int getCursorY() {
		return cursory;
	}
	
}
