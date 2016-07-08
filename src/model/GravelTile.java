package model;

// Unused tile. Meant to be a place where Chip could walk, but enemies couldn't.
public class GravelTile extends Tile{
	
	public GravelTile(int xp, int yp){
		setPosition(xp, yp);
		setSolid(false);
		setPushable(true);
		setImg("resources/gravel.jpg");
	}
	
	public void onWalk(Chip c){}
	
	public void onPush(Block b){}
	
	public void reset(){}
	
}
