package model;

public class MainMap {

	private int length; 			// total length of the map
	private int height; 			// total height of the map
	private int uniqueLength; 		// length of the map that is unique, beyond this will be periodic
	
	public int getLength(){
		return length;
	}
	
	public int getHeight(){
		return height;
	}
	
	public MainMap(int l, int h, int ul){
		length = l;
		height = h;
		uniqueLength = ul;
	}
	
	public MainMap(int l, int h){
		length = l;
		height = h;
	}
	
	public void setLength(int l){
		length = l;
	}
	
	public void setHeight(int h){
		height = h;
	}
	
	public void setUniqueLength(int u){
		uniqueLength = u;
	}
}
