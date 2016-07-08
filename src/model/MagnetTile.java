package model;

import java.awt.Robot;
import java.awt.AWTException;

/*
 * A sort of "conveyor belt" tile that moves the player involuntarily. Uses a Robot
 * to perform the movements so that it triggers the tile and collision handlers, and
 * updates the screen. Similar to IceTile.
 */
public class MagnetTile extends Tile{
	
	Robot robot;
	String direction;
	
	public MagnetTile(int xp, int yp, String dir){
		setPosition(xp, yp);
		setSolid(false);
		setPushable(false);
		this.direction = dir.toUpperCase();
		if(direction.equals("UP")){
			setImg("resources/uarrow.jpg");
		}
		else if(direction.equals("DOWN")){
			setImg("resources/darrow.jpg");
		}
		else if(direction.equals("LEFT")){
			setImg("resources/larrow.jpg");
		}
		else if(direction.equals("RIGHT")){
			setImg("resources/rarrow.jpg");
		}
		else{
			setImg("resources/blank.jpg");
		}
		try{
			robot = new Robot();
		} catch(AWTException e){
			e.printStackTrace();
		}
	}

	public void onWalk(Chip c) {
		if(!c.hasMagnetShoes()){
			try{
				Thread.sleep(100);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
			if(direction.equals("UP")){
				robot.keyPress(38);
				robot.keyRelease(38);
			}
			else if(direction.equals("DOWN")){
				robot.keyPress(40);
				robot.keyRelease(40);
			}
			else if(direction.equals("LEFT")){
				robot.keyPress(37);
				robot.keyRelease(37);
			}
			else if(direction.equals("RIGHT")){
				robot.keyPress(39);
				robot.keyRelease(39);
			}
		}
	}

	public void onPush(Block b) {
		// Do nothing
		
	}

	public void reset() {
		// Do nothing
		
	}
	
}
