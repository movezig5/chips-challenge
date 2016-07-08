package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import view.ChipPanel;
import model.Chip;
import model.Map;

/*
 * This class is based on the class of the same name used in guided step 1.
 */
public class KeyPressController implements KeyListener{
	
	ChipPanel chipPanel;
	Map map;
	Chip chip;
	TileHandler tHandler;
	CollisionHandler cHandler;
	
	public KeyPressController(ChipPanel chipPanel, Map map, Chip chip){
		this.chipPanel = chipPanel;
		this.map = map;
		this.chip = chip;
		tHandler = new TileHandler();
		cHandler = new CollisionHandler(map);
	}
	
	@Override
	public void keyPressed(KeyEvent e){
		if(chip != null){
			int keyPressed = e.getKeyCode();
			if(keyPressed < 41 && keyPressed > 36 ){
				switch(keyPressed){
				case 37: //Move left
					if(chip.getXPos() > 0
							&& !(map.getTile(chip.getXPos() - 1, chip.getYPos()).isSolid())){
						chip.moveLeft();
					}
					break;
				case 38: //Move up
					if(chip.getYPos() > 0
							&& !(map.getTile(chip.getXPos(), chip.getYPos() - 1).isSolid())){
						chip.moveUp();
					}
					break;
				case 39: //Move right
					if(chip.getXPos() < map.getXMax()
							&& !(map.getTile(chip.getXPos() + 1, chip.getYPos()).isSolid())){
						chip.moveRight();
					}
					break;
				case 40: //Move down
					if(chip.getYPos() < map.getYMax()
							&& !(map.getTile(chip.getXPos(), chip.getYPos() + 1).isSolid())){
						chip.moveDown();
					}
					break;
				}
			}
			else if(keyPressed == 82){ // This is the "R" button.
				/*
				 * This whole resetting routine is a mess, and could probably be done
				 * a lot simpler and better.
				 */
				chip.setPosition(map.getStartingX(), map.getStartingY());
				chip.setDirection(1);
				chip.loseKeys();
				chip.losePowerUps();
				chip.getState().revive();
				map.reset();
			}
			/*
			 * Tile and collision handlers perform any special actions whenever Chip
			 * moves to a new tile.
			 */
			tHandler.update(chip, map.getTile(chip.getXPos(), chip.getYPos()));
			cHandler.update(chip, map.getObject(chip.getXPos(), chip.getYPos()));
			chipPanel.repaint();
		}
		chipPanel.turnOffWelcomeText(); // The welcome message turns off after you move for the first time.
	}
	
	@Override
	public void keyReleased(KeyEvent e) {  
		// No response for keyReleased
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// No response for keyTyped
	}
	
}
