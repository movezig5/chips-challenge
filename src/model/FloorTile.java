package model;

//The basic floor tile.
public class FloorTile extends Tile {
	
	public FloorTile(int xPos, int yPos){
		setPosition(xPos, yPos);
		setSolid(false);
		setPushable(true);
		setImg("resources/floor.jpg");
	}
	
	public void onWalk(Chip c){
		if(c.getState() instanceof SwimState){
			c.getState().revive();
		}
	}
	
	public void onPush(Block b){}
	
	public void reset(){}
	
}
