package model;

import view.ChipPanel;

import java.util.Observer;
import java.util.Observable;
/*
 * Enemies actually extend the SolidObject class. It's easier to just have everything
 * in one array. The class uses the strategy pattern to determine how it moves.
 */
public abstract class Enemy extends SolidObject implements Observer, Runnable{
	
	public final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	
	private int initX, initY, direction, initDir, chipX, chipY;
	private long pauseLength;
	private boolean chipAlive, chipWon;
	private SolidObject nextObject;
	private SolidObject previousObject;
	private EnemyMovementStrategy strategy;
	private ChipPanel panel;
	private Chip chip;
	Map map;
	
	public int getInitX(){
		return initX;
	}
	public int getInitY(){
		return initY;
	}
	public int getInitDir(){
		return initDir;
	}
	public int getDirection(){
		return direction;
	}
	public SolidObject getNextObject(){
		return nextObject;
	}
	public SolidObject getPreviousObject(){
		return previousObject;
	}
	public Map getMap(){
		return map;
	}
	public EnemyMovementStrategy getStrategy(){
		return strategy;
	}
	public ChipPanel getPanel(){
		return panel;
	}
	public Chip getChip(){
		return chip;
	}
	public boolean chipAlive(){
		return chipAlive;
	}
	public boolean chipWon(){
		return chipWon;
	}
	public long getPauseLength(){
		return pauseLength;
	}
	
	public void setInitPosition(int initX, int initY){
		this.initX = initX;
		this.initY = initY;
	}
	public void setInitDir(int initDir){
		this.initDir = initDir;
	}
	public void setDirection(int direction){
		this.direction = direction;
	}
	public void setNextObject(SolidObject nextObject){
		this.nextObject = nextObject;
	}
	public void setPreviousObject(SolidObject previousObject){
		this.previousObject = previousObject;
	}
	public void setMap(Map map){
		this.map = map;
	}
	public void setStrategy(EnemyMovementStrategy strategy){
		this.strategy = strategy;
	}
	public void setPanel(ChipPanel panel){
		this.panel = panel;
	}
	public void setChip(Chip chip){
		this.chip = chip;
	}
	public void setChipAlive(boolean chipAlive){
		this.chipAlive = chipAlive;
	}
	public void setChipWon(boolean chipWon){
		this.chipWon = chipWon;
	}
	public void setPauseLength(long pauseLength){
		this.pauseLength = pauseLength;
	}
	
	@Override
	public void moveUp(){
		map.setObject(getXPos(), getYPos(), previousObject);
		previousObject = map.getObject(getXPos(), getYPos() - 1);
		map.setObject(getXPos(), getYPos() - 1, this);
		setPosition(getXPos(), getYPos() - 1);
		direction = UP;
	}
	@Override
	public void moveDown(){
		map.setObject(getXPos(), getYPos(), previousObject);
		previousObject = map.getObject(getXPos(), getYPos() + 1);
		map.setObject(getXPos(), getYPos() + 1, this);
		setPosition(getXPos(), getYPos() + 1);
		direction = DOWN;
		checkDeath();
	}
	@Override
	public void moveLeft(){
		map.setObject(getXPos(), getYPos(), previousObject);
		previousObject = map.getObject(getXPos() - 1, getYPos());
		map.setObject(getXPos() - 1, getYPos(), this);
		setPosition(getXPos() - 1, getYPos());
		direction = LEFT;
		checkDeath();
	}
	@Override
	public void moveRight(){
		map.setObject(getXPos(), getYPos(), previousObject);
		previousObject = map.getObject(getXPos() + 1, getYPos());
		map.setObject(getXPos() + 1, getYPos(), this);
		setPosition(getXPos() + 1, getYPos());
		direction = RIGHT;
		checkDeath();
	}
	
	public boolean movableUp(){
		nextObject = map.getObject(getXPos(), getYPos() - 1);
		if(getYPos() != 0 && !map.getTile(getXPos(), getYPos() - 1).isSolid()
				&& (nextObject == null || nextObject.getCollided())){
			return true;
		}
		else
			return false;
	}
	public boolean movableDown(){
		nextObject = map.getObject(getXPos(), getYPos() + 1);
		if(getYPos() != map.getYMax() && !map.getTile(getXPos(), getYPos() + 1).isSolid()
				&& (nextObject == null || nextObject.getCollided())){
			return true;
		}
		else
			return false;
	}
	public boolean movableLeft(){
		nextObject = map.getObject(getXPos() - 1, getYPos());
		if(getXPos() != 0 && !map.getTile(getXPos() - 1, getYPos()).isSolid()
				&& (nextObject == null || nextObject.getCollided())){
			return true;
		}
		else
			return false;
	}
	public boolean movableRight(){
		nextObject = map.getObject(getXPos() + 1, getYPos());
		if(getXPos() != map.getXMax() && !map.getTile(getXPos() + 1, getYPos()).isSolid()
				&& (nextObject == null || nextObject.getCollided())){
			return true;
		}
		else
			return false;
	}
	
	public void onCollision(Chip c, Map m){
		if(!getCollided()){
			c.getState().die();
		}
	}
	
	public void initialize(){
		map.setObject(getXPos(), getYPos(), previousObject);
		previousObject = map.getObject(initX, initY);
		map.setObject(initX, initY, this);
		setPosition(initX, initY);
		direction = initDir;
		setImg(getImg1());
		setCollided(false);
		chipAlive = true;
		chipWon = false;
	}
	
	public void run(){
		while(true){
			update(chip, null);
			if(chipAlive && !chipWon && !getCollided()){
				strategy.move();
				if(chipX == getXPos() && chipY == getYPos()){
					chip.getState().die();
					chipAlive = false;
				}
			}
			panel.repaint();
			try{
				Thread.sleep(pauseLength);
			} catch(InterruptedException e){
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	
	public void checkDeath(){
		if(diesOn(map.getTile(getXPos(), getYPos()))){
			setCollided(true);
			setImg(getImg2());
		}
	}
	
	public void update(Observable o, Object arg){
		chipX = ((Chip)o).getXPos();
		chipY = ((Chip)o).getYPos();
		chipAlive = (((Chip)o).getState() == ((Chip)o).getAliveState())
				|| (((Chip)o).getState() == ((Chip)o).getSwimState());
		chipWon = ((Chip)o).getState() == ((Chip)o).getWinState();
	}
	
	public abstract boolean diesOn(Tile t);
	
}
