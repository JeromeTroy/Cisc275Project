package model;

import java.util.Vector;

public class Map {

	private int length; 			// total length of the map
	private int height; 			// total height of the map
	private int uniqueLength; 		// length of the map that is unique, beyond this will be periodic
	private Vector position;
	
	
	
	public int getLength(){
		return length;
	}
	
	public int getHeight(){
		return height;
	}
	
	public Map(int l, int h, int ul){
		length = l;
		height = h;
		uniqueLength = ul;
	}
	
	public Map(int l, int h){
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
	
	public void getCenter(){
		//TODO: implement
	}
	
	public Vector getPosition(){
		return position;
	}
}
