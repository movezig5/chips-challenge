package view;

//import java.awt.BasicStroke;
//import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.JPanel;

import controller.KeyPressController;

import model.*;

public class ChipPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	KeyPressController kpc;
	Chip chip;
	Map map;
	int xMin, yMin, chipX, chipY;
	private int gameXOffset = 8; //These determine how far away the "screen" is from the window border.
	private int gameYOffset = 8;
	private Image border;
	private Image flippers, iceSkates, fireBoots, magnetShoes; // Used in the inventory
	private Image redKey, blueKey, yellowKey, greenKey;
	private boolean welcome; // Should the welcome message be displayed?
	private Font largeFont, smallFont; // Two fonts that get used repeatedly
	
	public ChipPanel(Chip chip, Map map){
		this.chip = chip;
		this.map = map;
		kpc = new KeyPressController(this, map, chip);
		welcome = true;
		largeFont = new Font("Courier", Font.BOLD, 28);
		smallFont = new Font("Courier", Font.BOLD, 20);
		this.addKeyListener(kpc);
		this.setFocusable(true);
		try{
			border = ImageIO.read(new File("resources/border.jpg"));
			flippers = ImageIO.read(new File("resources/flippers.jpg"));
			iceSkates = ImageIO.read(new File("resources/iceskates.jpg"));
			fireBoots = ImageIO.read(new File("resources/fireboots.jpg"));
			magnetShoes = ImageIO.read(new File("resources/magnetshoes.jpg"));
			
			redKey = ImageIO.read(new File("resources/redkey.jpg"));
			blueKey = ImageIO.read(new File("resources/bluekey.jpg"));
			yellowKey = ImageIO.read(new File("resources/yellowkey.jpg"));
			greenKey = ImageIO.read(new File("resources/greenkey.jpg"));
		} catch(IOException e){
			System.err.println("An exception occurred while attempting to read image files for the main panel.");
			System.exit(1);
		}
		
	}
	
	public void paintComponent(Graphics g){
		/*
		 * The new version of this method gets a "range" of tiles around Chip,
		 * then draws only those to the screen. Chip's position is determined by how
		 * close he is to the edge of the level.
		 */
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(border, 0, 0, null); // Drawing that snazzy blue border!
		calculateViewRange();
		getChipPosition();
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				map.getTile(xMin + i, yMin + j).draw(g2, (i * 32) + gameXOffset, (j * 32) + gameYOffset);
				if(map.getObject(xMin + i, yMin + j) != null){
					map.getObject(xMin + i, yMin + j).draw(g2, (i * 32) + gameXOffset, (j * 32) + gameYOffset);
				}
			}
		}
		drawInventory(g2);
		chip.draw(g2, (chipX * 32) + gameXOffset, (chipY * 32) + gameYOffset);
		//Display any appropriate messages:
		if(chip.getState() == chip.getWinState()){
			victory(g2);
		}
		else if(chip.getState() == chip.getDrownedState()){
			drown(g2);
		}
		else if(chip.getState() == chip.getBurnedState()){
			burn(g2);
		}
		else if((chip.getState() == chip.getDeadState())
				&& (map.getTile(chip.getXPos(), chip.getYPos()) instanceof BombTile)){
			bombDeath(g2);
		}
		else if((chip.getState() == chip.getDeadState())
				&& map.getObject(chip.getXPos(), chip.getYPos()) instanceof Enemy){
			enemyDeath(g2);
		}
		else if(chip.getState() != chip.getAliveState() && chip.getState() != chip.getSwimState()){
			gameOver(g2);
		}
		g2.setFont(smallFont);
		g2.drawString("R: Restart", 490, 430); // Additional small instruction
		if(welcome){ // The welcome message
			g2.setFont(smallFont);
			g2.drawString("Welcome to Chip's Challenge!", 4, 324);
		}
		//Old tile drawing code:
		/*
		for(int i = 0; i <= map.getXMax(); i++){
			for(int j = 0; j <= map.getYMax(); j++){
				map.getTile(i, j).draw(g2);
				map.getObject(i, j).draw(g2);
			}
		}
		chip.draw(g2);
		*/
	}
	
	public void turnOffWelcomeText(){
		welcome = false;
	}
	
	public void setOffsets(int x, int y){
		gameXOffset = x;
		gameYOffset = y;
	}
	
	public Dimension getPreferredSize(){
        return new Dimension(288,288);
    }

    public Dimension getMinimumSize(){
        return getPreferredSize();
    }
    //These four methods determine whether Chip is too close to the edge of the map to be centered.
    private boolean chipNearNorth(){
    	return chip.getYPos() < 4;
    }
    private boolean chipNearSouth(){
    	return chip.getYPos() > (map.getYMax() - 4);
    }
    private boolean chipNearEast(){
    	return chip.getXPos() > (map.getXMax() - 4);
    }
    private boolean chipNearWest(){
    	return chip.getXPos() < 4;
    }
    // This method determines what area should be visible on screen at any given time.
    private void calculateViewRange(){
    	if(chipNearNorth()){
    		yMin = 0;
    	}
    	else if(chipNearSouth()){
    		yMin = map.getYMax() - 8;
    	}
    	else{
    		yMin = chip.getYPos() - 4;
    	}
    	
    	if(chipNearWest()){
    		xMin = 0;
    	}
    	else if(chipNearEast()){
    		xMin = map.getXMax() - 8;
    	}
    	else{
    		xMin = chip.getXPos() - 4;
    	}
    }
    private void getChipPosition(){
    	if(chipNearNorth()){
    		chipY = chip.getYPos();
    	}
    	else if(chipNearSouth()){
    		chipY = chip.getYPos() - yMin;
    	}
    	else{
    		chipY = 4;
    	}
    	
    	if(chipNearWest()){
    		chipX = chip.getXPos();
    	}
    	else if(chipNearEast()){
    		chipX = chip.getXPos() - xMin;
    	}
    	else{
    		chipX = 4;
    	}
    }
    
    public void drawInventory(Graphics2D g2){ // Displays the appropriate inventory images
		g2.setFont(largeFont);
		g2.drawString("Inventory", 320, 28);
		if(map.getChipsRequired() >= chip.getChips()){
			g2.drawString("Chips: " + (map.getChipsRequired() - chip.getChips()), 320, 120);
		}
		else{
			g2.drawString("Chips: 0", 320, 120);
		}
		
		if(chip.hasFlippers()){
			g2.drawImage(flippers, 320, 34, null);
		}
		if(chip.hasIceSkates()){
			g2.drawImage(iceSkates, 352, 34, null);
		}
		if(chip.hasFireBoots()){
			g2.drawImage(fireBoots, 384, 34, null);
		}
		if(chip.hasMagnetShoes()){
			g2.drawImage(magnetShoes, 416, 34, null);
		}
		
		if(chip.hasRedKey()){
			g2.drawImage(redKey, 320, 66, null);
		}
		if(chip.hasBlueKey()){
			g2.drawImage(blueKey, 352, 66, null);
		}
		if(chip.hasYellowKey()){
			g2.drawImage(yellowKey, 384, 66, null);
		}
		if(chip.hasGreenKey()){
			g2.drawImage(greenKey, 416, 66, null);
		}
	}
    
    //Various status messages, depending on Chip's state.
    public void gameOver(Graphics2D g2){
    	g2.setFont(smallFont);
		g2.drawString("Game over!", 4, 324);
    }
    
    public void enemyDeath(Graphics2D g2){
    	g2.setFont(smallFont);
		g2.drawString("Watch out for enemies!", 4, 324);
    }
    
    public void bombDeath(Graphics2D g2){
    	g2.setFont(smallFont);
		g2.drawString("Don't step on bombs!", 4, 324);
    }
    
    public void burn(Graphics2D g2){
    	g2.setFont(smallFont);
		g2.drawString("Don't step on fire without fire boots!", 4, 324);
    }
    
    public void drown(Graphics2D g2){
    	g2.setFont(smallFont);
		g2.drawString("Chip can't swim without flippers!", 4, 324);
    }
    
    public void victory(Graphics2D g2){
    	g2.setFont(smallFont);
		g2.drawString("You win!", 4, 324);
    }
    
    // A lot of classes have a method like this one. I probably should have defined it elsewhere.
    public void readImg(Image img, String imgFile){
		try{
			img = ImageIO.read(new File(imgFile));
		} catch(IOException e){
			System.err.println("An exception occurred while attempting to read " + imgFile + ".");
			System.exit(1);
		}
	}
    
}
