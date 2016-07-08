package model;

import java.util.Observable;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Point;

import controller.PlayChip;

/*
 * The Chip class ended up being huge. I probably should have broken it down into
 * several smaller classes.
 */
public class Chip extends Observable{ // Many objects will need to be notified of Chip's status.
	
	final static int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
	final static int FLIPPERS = 0, ICESKATES = 1, FIREBOOTS = 2, MAGNETSHOES = 3;
	final static int RED = 0, BLUE = 1, YELLOW = 2, GREEN = 3;
	
	private int x, y, xPos, yPos; // Chip's x and y positions on screen, and x and y coordinates on the map
	private int lastXPos, lastYPos; // This is where chip was before moving.
	private Image uimg, dimg, limg, rimg,
	drownedImg, burnedImg, swimUImg, swimDImg, swimLImg, swimRImg;
	private int direction; // See the final static ints above.
	
	private boolean[] powerUp; // What powerups does Chip have?
	private int[] key; // How many of each key does Chip have?
	private int chips; // How many computer chips has Chip collected?
	
	private ChipState state; // Chip's state. Changes in response to external stimuli.
	private AliveState alive;
	private DeadState dead;
	private BurnedState burned;
	private DrownedState drowned;
	private SwimState swimming;
	private SlidingState sliding;
	private WinState victory;
	
	private PlayChip game; // Chip needs to tell the game when he's won.
	
	public Chip(int xPos, int yPos, PlayChip game){
		this.xPos = xPos;
		this.yPos = yPos;
		x = xPos * 32; //Each tile is 32 x 32 pixels.
		y = yPos * 32;
		powerUp = new boolean[4];
		key = new int[4];
		for(int i = 0; i < 4; i++){
			powerUp[i] = false;
			key[i] = 0;
		}
		direction = DOWN;
		alive = new AliveState(this);
		dead = new DeadState(this);
		burned = new BurnedState(this);
		drowned = new DrownedState(this);
		swimming = new SwimState(this);
		victory = new WinState(this);
		state = alive;
		this.game = game;
		loadImages();
	}
	
	public Chip(Point p, PlayChip game){ // An alternate way of instantiating Chip. I'm not sure if I ever use it.
		xPos = (int)p.getX();
		yPos = (int)p.getY();
		x = xPos * 32;
		y = yPos * 32;
		powerUp = new boolean[4];
		key = new int[4];
		for(int i = 0; i < 4; i++){
			powerUp[i] = false;
			key[i] = 0;
		}
		direction = DOWN;
		alive = new AliveState(this);
		dead = new DeadState(this);
		burned = new BurnedState(this);
		drowned = new DrownedState(this);
		swimming = new SwimState(this);
		sliding = new SlidingState(this);
		victory = new WinState(this);
		state = alive;
		this.game = game;
		loadImages();
	}
	
	private void loadImages(){
		try{
			uimg = ImageIO.read(new File("resources/uchip.png"));
			dimg = ImageIO.read(new File("resources/dchip.png"));
			limg = ImageIO.read(new File("resources/lchip.png"));
			rimg = ImageIO.read(new File("resources/rchip.png"));
			drownedImg = ImageIO.read(new File("resources/drownedchip.jpg"));
			burnedImg = ImageIO.read(new File("resources/burntchip.jpg"));
			swimUImg = ImageIO.read(new File("resources/uswim.jpg"));
			swimDImg = ImageIO.read(new File("resources/dswim.jpg"));
			swimLImg = ImageIO.read(new File("resources/lswim.jpg"));
			swimRImg = ImageIO.read(new File("resources/rswim.jpg"));
		} catch(IOException e){
			System.err.println("An exception occurred while attempting to read resources/dchip.jpg.");
			System.exit(1);
		}
	}
	
	public void draw(Graphics2D g2){
		if(state == alive || state == dead || state == victory){
			switch(direction){
			case(UP):
				g2.drawImage(uimg, x, y, null);
				break;
			case(DOWN):
				g2.drawImage(dimg, x, y, null);
				break;
			case(LEFT):
				g2.drawImage(limg, x, y, null);
				break;
			case(RIGHT):
				g2.drawImage(rimg, x, y, null);
				break;
			}
		}
		else if (state == swimming){
			switch(direction){
			case(UP):
				g2.drawImage(swimUImg, x, y, null);
				break;
			case(DOWN):
				g2.drawImage(swimDImg, x, y, null);
				break;
			case(LEFT):
				g2.drawImage(swimLImg, x, y, null);
				break;
			case(RIGHT):
				g2.drawImage(swimRImg, x, y, null);
				break;
			}
		}
		else if (state == burned){
			g2.drawImage(burnedImg, x, y, null);
		}
		else if (state == drowned){
			g2.drawImage(drownedImg, x, y, null);
		}
	}
	
	public void draw(Graphics2D g2, int x, int y){
		if(state == alive || state == dead || state == victory){
			switch(direction){
			case(UP):
				g2.drawImage(uimg, x, y, null);
				break;
			case(DOWN):
				g2.drawImage(dimg, x, y, null);
				break;
			case(LEFT):
				g2.drawImage(limg, x, y, null);
				break;
			case(RIGHT):
				g2.drawImage(rimg, x, y, null);
				break;
			}
		}
		else if (state == swimming){
			switch(direction){
			case(UP):
				g2.drawImage(swimUImg, x, y, null);
				break;
			case(DOWN):
				g2.drawImage(swimDImg, x, y, null);
				break;
			case(LEFT):
				g2.drawImage(swimLImg, x, y, null);
				break;
			case(RIGHT):
				g2.drawImage(swimRImg, x, y, null);
				break;
			}
		}
		else if (state == burned){
			g2.drawImage(burnedImg, x, y, null);
		}
		else if (state == drowned){
			g2.drawImage(drownedImg, x, y, null);
		}
	}
	
	
	public int getXPos(){
		return xPos;
	}
	public int getYPos(){
		return yPos;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public void setPosition(int xPos, int yPos){
		this.xPos = xPos;
		this.yPos = yPos;
		x = xPos * 32;
		y = yPos * 32;
		notifyObservers();
	}
	public int getDirection(){
		return direction;
	}
	public void setDirection(int dir){
		direction = dir;
	}
	
	public int getLastXPos(){
		return lastXPos;
	}
	public int getLastYPos(){
		return lastYPos;
	}
	
	public ChipState getState(){
		return state;
	}
	public AliveState getAliveState(){
		return alive;
	}
	public DeadState getDeadState(){
		return dead;
	}
	public BurnedState getBurnedState(){
		return burned;
	}
	public DrownedState getDrownedState(){
		return drowned;
	}
	public SwimState getSwimState(){
		return swimming;
	}
	public SlidingState getSlidingState(){
		return sliding;
	}
	public WinState getWinState(){
		return victory;
	}
	public void setState(ChipState state){
		this.state = state;
	}
	
	
	public boolean hasFlippers(){
		return powerUp[FLIPPERS];
	}
	public boolean hasIceSkates(){
		return powerUp[ICESKATES];
	}
	public boolean hasFireBoots(){
		return powerUp[FIREBOOTS];
	}
	public boolean hasMagnetShoes(){
		return powerUp[MAGNETSHOES];
	}
	
	public void getFlippers(){
		powerUp[FLIPPERS] = true;
		notifyObservers();
	}
	public void getIceSkates(){
		powerUp[ICESKATES] = true;
		notifyObservers();
	}
	public void getFireBoots(){
		powerUp[FIREBOOTS] = true;
		notifyObservers();
	}
	public void getMagnetShoes(){
		powerUp[MAGNETSHOES] = true;
		notifyObservers();
	}
	public void losePowerUps(){
		for(int i = 0; i < 4; i++){
			powerUp[i] = false;
		}
		notifyObservers();
	}
	
	public boolean[] itemStatus(){
		return powerUp;
	}
	
	public int[] keyStatus(){
		return key;
	}
	
	public boolean hasRedKey(){
		return key[RED] > 0;
	}
	public boolean hasBlueKey(){
		return key[BLUE] > 0;
	}
	public boolean hasYellowKey(){
		return key[YELLOW] > 0;
	}
	public boolean hasGreenKey(){
		return key[GREEN] > 0;
	}
	
	public int getChips(){
		return chips;
	}
	
	public void obtainChip(){
		chips++;
	}
	
	public void clearChips(){
		chips = 0;
	}
	
	public void getRedKey(){
		key[RED]++;
	}
	public void getBlueKey(){
		key[BLUE]++;
	}
	public void getYellowKey(){
		key[YELLOW]++;
	}
	public void getGreenKey(){
		key[GREEN]++;
	}
	public void loseKeys(){
		for(int i = 0; i < 4; i++){
			key[i] = 0;
		}
	}
	
	public void useRedKey(){
		if(hasRedKey())
			key[RED]--;
	}
	public void useBlueKey(){
		if(hasBlueKey())
			key[BLUE]--;
	}
	public void useYellowKey(){
		if(hasYellowKey())
			key[YELLOW]--;
	}
	public void useGreenKey(){
		if(hasGreenKey())
			key[GREEN]--;
	}
	
	public void move(int dx, int dy){
		lastXPos = xPos;
		lastYPos = yPos;
		xPos += dx;
		yPos += dy;
		x += (dx * 32);
		y += (dy * 32);
		notifyObservers();
	}
	
	public void moveUp(){
		if(state == alive || state == swimming){ // You can't move when you're dead!
			move(0, -1);
			if(direction != UP)
				direction = UP;
		}
	}
	public void moveDown(){
		if(state == alive || state == swimming){
			move(0, 1);
			if(direction != DOWN)
				direction = DOWN;
		}
		
	}
	public void moveLeft(){
		if(state == alive || state == swimming){
			move(-1, 0);
			if(direction != LEFT)
				direction = LEFT;
		}
	}
	public void moveRight(){
		if(state == alive || state == swimming){
			move(1, 0);
			if(direction != RIGHT)
				direction = RIGHT;
		}
	}
	
	public PlayChip getGame(){
		return game;
	}
	
}
