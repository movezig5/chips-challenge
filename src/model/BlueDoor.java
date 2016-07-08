package model;

public class BlueDoor extends SolidObject{
	
	public BlueDoor(int xp, int yp){
		setPosition(xp, yp);
		setImg1("resources/bluedoor.jpg");
		setImg2("resources/blank.png");
		initialize();
	}
	
	public void onCollision(Chip c, Map m){
		if(getCollided()){}
		else if(c.hasBlueKey()){
			c.useBlueKey();
			setImg(getImg2());
			setCollided(true);
		}
		else{
			if(!(c.getState() instanceof AliveState || c.getState() instanceof SwimState)){
				if(m.getTile(c.getLastXPos(), c.getLastYPos()) instanceof WaterTile){
					c.getState().swim();
				}
				else{
					c.getState().revive();
				}
			}
			c.setPosition(c.getLastXPos(), c.getLastYPos());
		}
	}
	
	public void initialize(){
		setImg(getImg1());
		setCollided(false);
	}
	
}
