package model;

import java.util.Iterator;
import java.awt.Point;

/*
 * The Map class is iterable--see it for more details.
 */
public class MapIterator implements Iterator<Point>{
	
	private int x, y;
	private Map m;
	
	public MapIterator(Map m){
		this.m = m;
		x = 0;
		y = 0;
	}
	
	public boolean hasNext(){
		return(x < m.getXMax() || y < m.getYMax());
	}
	
	public Point next(){
		if(x < m.getXMax()){
			x++;
		}
		else if(y < m.getYMax()){
			x = 0;
			y++;
		}
		return new Point(x, y);
	}
	
	public Point currentItem(){
		return new Point(x, y);
	}
	
}
