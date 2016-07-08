package model;

public class WaterTile extends Tile{
	
	public WaterTile(int xp, int yp){
		setPosition(xp, yp);
		setSolid(false);
		setPushable(true);
		setImg("resources/water.jpg");
	}
	
	public void onWalk(Chip c){
		if(c.hasFlippers() && !(c.getState() instanceof SwimState)){
			c.getState().swim();
		}
		else if(!c.hasFlippers()){ // Chip can only swim with flippers on.
			c.getState().drown();
		}
	}
	
	public void onPush(Block b){
		b.getState().sink();
		b.map.setTile(getXPos(), getYPos(), new FloorTile(getXPos(), getYPos()));
	}
	
	public void reset(){}
	
}
