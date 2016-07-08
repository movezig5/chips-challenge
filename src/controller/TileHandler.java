package controller;

import java.util.Observer;
import java.util.Observable;

import model.Chip;
import model.*;

/*
 * This class handles events that occur when Chip walks on a tile. KeyPressController
 * contains an instance of it.
 */
public class TileHandler implements Observer{
	
	private Chip chip;
	private Tile t;
	
	public TileHandler(){}
	
	public void update(Observable o, Object arg) {
		
		chip = (Chip)o;
		t = (Tile)arg;
		t.onWalk(chip);
		
	}
	
}
