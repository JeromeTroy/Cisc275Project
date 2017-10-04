package model;

public class Stuff {
	// TODO RENAME ME

	private boolean isTrash; 			// true = trash, else food
	private int xloc;					// x location
	private int yloc;					// y location
	private int xlen;					// x length
	private int ylen;					// y length
	
	public Stuff(boolean istrash){
		isTrash = istrash;
	}
	
	public Stuff(int x, int y){
		xloc = x;
		yloc = y;
	}
	
	public void setxlen(int l){
		xlen = l;
	}
	
	public void setylen(int l){
		ylen = l;
	}
	
	public void setx(int x){
		xloc = x;
	}
	
	public void sety(int y){
		yloc = y;
	}
	
	public int getLowerX(){
		return xloc;
	}
	
	public int getUpperX(){
		return xloc + xlen;
	}
	
	public int getLowerY(){
		return yloc;
	}
	
	public int getUpperY(){
		return yloc + ylen;
	}
	
	public boolean isTrash(){
		return isTrash;
	}
	
}
