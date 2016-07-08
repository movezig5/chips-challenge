package model;

public class FireTile extends Tile{
	
	public FireTile(int xp, int yp){
		setPosition(xp, yp);
		setSolid(false);
		setPushable(true);
		setImg("resources/fire.jpg");
	}
	
	public void onWalk(Chip c) {
		if(!c.hasFireBoots()){
			c.getState().burn();
		}
		else if(c.getState() == c.getSwimState()){
			c.getState().revive();
		}
	}
	
	public void onPush(Block b){}
	
	public void reset(){}
	
}
