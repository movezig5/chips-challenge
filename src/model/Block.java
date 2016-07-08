package model;

import java.awt.Graphics2D;

public class Block extends SolidObject{
	/*
	 * Blocks are one of the most complex objects, because they can be pushed.
	 * There are methods for checking whether they can be pushed or not, depending on
	 * what's in the way.
	 *
	 * Also, about previousObject: the block keeps track of what was in its space before
	 * it was pushed there, so that, if the map is reset, it can simply put it back.
	 * Enemies do the same thing, and it's one of the reasons I think resetting
	 * could have been handled better.
	 */
	
	private int initX, initY;
	private SolidObject nextObject;
	private BlockState state;
	private NormalBlockState normal;
	private SunkBlockState sunk;
	private SolidBlockState solid;
	private ExplodedBlockState exploded;
	private SolidObject previousObject;
	Map map;
	
	public Block(int x, int y, Map map){
		normal = new NormalBlockState(this);
		sunk = new SunkBlockState(this);
		solid = new SolidBlockState(this);
		exploded = new ExplodedBlockState(this);
		if(map.getTile(x, y) instanceof WaterTile){
			state = sunk;
		}
		else{
			state = normal;
		}
		setPosition(x, y);
		initX = x;
		initY = y;
		this.map = map;
		setCollided(false);
		previousObject = null;
	}
	
	/*
	 * The block moves in the appropriate direction if possible, based on Chip's
	 * previous position. If it can't, it just pushes Chip back. If Chip would die on
	 * the tile the block is on, it revives him and puts him in the appropriate state.
	 * 
	 * That's right: this block can bring back the dead. All hail the almighty block!
	 */
	public void onCollision(Chip c, Map m){
		if(state == sunk){
			map.setTile(getXPos(), getYPos(), new FloorTile(getXPos(), getYPos()));
			state.solidify();
		}
		else if(state == solid || state == sunk || state == exploded){}
		else if(c.getLastYPos() == getYPos() + 1 && pushableUp()){
			map.setObject(getXPos(), getYPos(), previousObject);
			previousObject = map.getObject(getXPos(), getYPos() - 1);
			map.setObject(getXPos(), getYPos() - 1, this);
			setPosition(getXPos(), getYPos() - 1);
			map.getTile(getXPos(), getYPos()).onPush(this);
		}
		else if(c.getLastYPos() == getYPos() - 1 && pushableDown()){
			map.setObject(getXPos(), getYPos(), previousObject);
			previousObject = map.getObject(getXPos(), getYPos() + 1);
			map.setObject(getXPos(), getYPos() + 1, this);
			setPosition(getXPos(), getYPos() + 1);
			map.getTile(getXPos(), getYPos()).onPush(this);
		}
		else if(c.getLastXPos() == getXPos() + 1 && pushableLeft()){
			map.setObject(getXPos(), getYPos(), previousObject);
			previousObject = map.getObject(getXPos() - 1, getYPos());
			map.setObject(getXPos() - 1, getYPos(), this);
			setPosition(getXPos() - 1, getYPos());
			map.getTile(getXPos(), getYPos()).onPush(this);
		}
		else if(c.getLastXPos() == getXPos() - 1 && pushableRight()){
			map.setObject(getXPos(), getYPos(), previousObject);
			previousObject = map.getObject(getXPos() + 1, getYPos());
			map.setObject(getXPos() + 1, getYPos(), this);
			setPosition(getXPos() + 1, getYPos());
			map.getTile(getXPos(), getYPos()).onPush(this);
		}
		else{
			if(!(c.getState() instanceof AliveState || c.getState() instanceof SwimState)){
				if(map.getTile(c.getLastXPos(), c.getLastYPos()) instanceof WaterTile){
					c.getState().swim();
				}
				else{
					c.getState().revive();
				}
			}
			c.setPosition(c.getLastXPos(), c.getLastYPos());
		}
	}
	
	public void initialize(){ // Used in resetting.
		if(state == solid || state == sunk || state == exploded){
			state.surface();
		}
		if(map.getTile(initX, initY) instanceof WaterTile){
			state.sink();
		}
		map.setObject(getXPos(), getYPos(), previousObject);
		previousObject = map.getObject(initX, initY);
		map.setObject(initX, initY, this);
		setPosition(initX, initY);
	}
	
	// Check to see if the block can move in a certain direction.
	public boolean pushableUp(){
		nextObject = map.getObject(getXPos(), getYPos() - 1);
		if((nextObject == null || nextObject.getCollided() == true)
				&& map.getTile(getXPos(), getYPos() - 1).isPushable() && getYPos() != 0){
			return true;
		}
		else
			return false;
	}
	public boolean pushableDown(){
		nextObject = map.getObject(getXPos(), getYPos() + 1);
		if((nextObject == null || nextObject.getCollided() == true)
				&& map.getTile(getXPos(), getYPos() + 1).isPushable() && getYPos() != map.getYMax()){
			return true;
		}
		else
			return false;
	}
	public boolean pushableLeft(){
		nextObject = map.getObject(getXPos() - 1, getYPos());
		if((nextObject == null || nextObject.getCollided() == true)
				&& map.getTile(getXPos() - 1, getYPos()).isPushable() && getX() != 0){
			return true;
		}
		else
			return false;
	}
	public boolean pushableRight(){
		nextObject = map.getObject(getXPos() + 1, getYPos());
		if((nextObject == null || nextObject.getCollided() == true)
				&& map.getTile(getXPos() + 1, getYPos()).isPushable() && getX() != map.getXMax()){
			return true;
		}
		else
			return false;
	}
	
	@Override
	public void draw(Graphics2D g2){
		g2.drawImage(state.getImg(), getX(), getY(), null);
	}
	
	@Override
	public void draw(Graphics2D g2, int x, int y){
		g2.drawImage(state.getImg(), x, y, null);
	}
	
	
	public BlockState getState(){
		return state;
	}
	public NormalBlockState getNormal(){
		return normal;
	}
	public SunkBlockState getSunk(){
		return sunk;
	}
	public SolidBlockState getSolid(){
		return solid;
	}
	public ExplodedBlockState getExploded(){
		return exploded;
	}
	
	public void setState(BlockState state){
		this.state = state;
	}
	
}
