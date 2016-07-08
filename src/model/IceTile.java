package model;

import java.awt.Robot;
import java.awt.AWTException;

/*
 * In order to make Chip slide across the ice, I make a robot send simulated
 * keyboard inputs. This way, the KeyPressController handles the onWalk commands
 * and repaints the panel.
 */
public class IceTile extends Tile{
	
	Map map;
	Robot robot;
	
	public IceTile(int xp, int yp, Map map){
		setPosition(xp, yp);
		this.map = map;
		setSolid(false);
		setPushable(false);
		setImg("resources/ice.jpg");
		try{
			robot = new Robot();
		} catch(AWTException e){
			e.printStackTrace();
		}
	}
	
	public boolean movableUp(){
		if(getYPos() > 0 && !(map.getTile(getXPos(), getYPos() - 1).isSolid())){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean movableDown(){
		if(getYPos() < map.getYMax() && !(map.getTile(getXPos(), getYPos() + 1).isSolid())){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean movableLeft(){
		if(getXPos() > 0 && !(map.getTile(getXPos() - 1, getYPos()).isSolid())){
			return true;
		}
		else{
			return false;
		}
	}
	public boolean movableRight(){
		if(getXPos() < map.getXMax() && !(map.getTile(getXPos() + 1, getYPos()).isSolid())){
			return true;
		}
		else{
			return false;
		}
	}
	
	public void onWalk(Chip c) {
		if(!c.hasIceSkates()){
			try{
				Thread.sleep(100);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
			if(c.getLastYPos() == getYPos() + 1){
				if(movableUp()){
					robot.keyPress(38);
					robot.keyRelease(38);
				}
				else{
					robot.keyPress(40);
					robot.keyRelease(40);
				}
			}
			else if(c.getLastYPos() == getYPos() - 1){
				if(movableDown()){
					robot.keyPress(40);
					robot.keyRelease(40);
				}
				else{
					robot.keyPress(38);
					robot.keyRelease(38);
				}
			}
			else if(c.getLastXPos() == getXPos() + 1){
				if(movableLeft()){
					robot.keyPress(37);
					robot.keyRelease(37);
				}
				else{
					robot.keyPress(39);
					robot.keyRelease(39);
				}
			}
			else if(c.getLastXPos() == getXPos() - 1){
				if(movableRight()){
					robot.keyPress(39);
					robot.keyRelease(39);
				}
				else{
					robot.keyPress(37);
					robot.keyRelease(37);
				}
			}
		}
	}

	public void onPush(Block b) {
		//Do nothing
	}

	public void reset() {
		//Do nothing
	}
	
}
