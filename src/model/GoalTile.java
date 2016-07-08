package model;

//This is the tile the player has to reach to complete the level. Usually surrounded by gates.
public class GoalTile extends Tile{
	
	public GoalTile(int xp, int yp){
		setPosition(xp, yp);
		setImg("resources/portal.jpg");
		setSolid(false);
		setPushable(false);
	}
	
	public void onWalk(Chip c){
		c.getState().win();
	}
	
	public void onPush(Block b){}
	
	public void reset(){}
	
}
