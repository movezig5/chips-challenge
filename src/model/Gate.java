package model;

// A gate that can only be unlocked if Chip has collected enough computer chips.
public class Gate extends SolidObject{
	
	public Gate(int xp, int yp){
		setPosition(xp, yp);
		setImg1("resources/gate.jpg");
		setImg2("resources/blank.png");
		initialize();
	}
	
	public void onCollision(Chip c, Map m){
		if(getCollided()){}
		else if(c.getChips() >= m.getChipsRequired()){
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
