package model;

public class WallTile extends Tile {
	
	public WallTile(int xPos, int yPos){
		setPosition(xPos, yPos);
		setSolid(true); // Wall tiles are "solid" -- that is, you can't walk on them.
		setPushable(false);
		setImg("resources/wall.jpg");
	}
	
	public void onWalk(Chip c){}
	
	public void onPush(Block b){}
	
	public void reset(){}
	
}
