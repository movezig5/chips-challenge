package controller;

import java.util.Observer;
import java.util.Observable;

import model.Chip;
import model.SolidObject;
import model.Map;

/*
 * This class handles collisions with Chip and objects on screen. KeyPressController
 * contains an instance of it.
 */
public class CollisionHandler implements Observer{
	
	private Chip chip;
	private SolidObject s;
	private Map m;
	
	public CollisionHandler(Map m){
		this.m = m;
	}
	
	public void update(Observable o, Object arg){
		
		chip = (Chip)o;
		
		if(arg != null){
			s = (SolidObject)arg;
			s.onCollision(chip, m);
		}
		
	}
	
}
