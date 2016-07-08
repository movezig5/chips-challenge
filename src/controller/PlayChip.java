package controller;

import view.*;

import javax.swing.JFrame;
import java.util.LinkedList;
import java.awt.Point;

import model.*;

public class PlayChip {
	
	ChipPanel chipPanel;
	Chip chip;
	Map map;
	private LinkedList<Thread> threads;
	private boolean title;
	private boolean won;
	private boolean lost;
	
	public PlayChip(){
		map = MapMaker.readMapFile("resources/demoLevel.txt", 25, 25, 10); // MapMaker parses a txt file
		chip = new Chip(map.getStartingPosition(), this);
		threads = new LinkedList<Thread>();
		title = false;
		won = false;
		lost = false;
	}
	
	public void startGUI(){ //Setting up the window
		int windowWidth = 640;
		int windowHeight = 480;
		chipPanel = new ChipPanel(chip, map);
		chipPanel.setSize(windowWidth, windowHeight);
		ChipWindowMaker w = new ChipWindowMaker(chipPanel);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setSize(windowWidth, windowHeight);
		w.setLocationRelativeTo(null);
		w.setVisible(true);
	}
	
	public boolean title(){
		return title;
	}
	public void setTitle(boolean title){
		this.title = title;
	}
	
	public boolean isWon(){
		return won;
	}
	public boolean isLost(){
		return lost;
	}
	public boolean isOver(){
		return (won || lost);
	}
	public void win(){
		won = true;
	}
	public void lose(){
		lost = true;
	}
	public void reset(){
		won = false;
		lost = false;
	}
	
	public void setUpThreads(){ // Enemies need a reference to a ChipPanel to function properly.
		int i, j;
		for(Point p : map){
			i = (int)p.getX();
			j = (int)p.getY();
			if(map.getObject(i, j) instanceof Enemy){
				((Enemy)map.getObject(i, j)).setPanel(chipPanel);
				((Enemy)map.getObject(i, j)).setChip(chip);
				chip.addObserver((Enemy)map.getObject(i, j));
				threads.add(new Thread((Enemy)map.getObject(i, j)));
			}
		}
		/*
		for(int i = 0; i <= map.getXMax(); i++){
			for(int j = 0; j <= map.getYMax(); j++){
				if(map.getObject(i, j) instanceof Enemy){
					((Enemy)map.getObject(i, j)).setPanel(chipPanel);
					((Enemy)map.getObject(i, j)).setChip(chip);
					chip.addObserver((Enemy)map.getObject(i, j));
					threads.add(new Thread((Enemy)map.getObject(i, j)));
				}
			}
		}
		*/
	}
	
	public void startThreads(){
		for(Thread t : threads){
			t.start();
		}
	}
	
	public static void main(String[] args){
		
		PlayChip chipsChallenge = new PlayChip();
		
		chipsChallenge.startGUI();
		
		chipsChallenge.setUpThreads();
		
		chipsChallenge.startThreads();
		
	}
	
	//The following getters and setters are in here for testing purposes!
	public ChipPanel getChipPanel() {
		return chipPanel;
	}

	public void setChipPanel(ChipPanel chipPanel) {
		this.chipPanel = chipPanel;
	}

	public Chip getChip() {
		return chip;
	}

	public void setChip(Chip chip) {
		this.chip = chip;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public LinkedList<Thread> getThreads() {
		return threads;
	}

	public void setThreads(LinkedList<Thread> threads) {
		this.threads = threads;
	}

	public boolean isTitle() {
		return title;
	}

	public void setWon(boolean won) {
		this.won = won;
	}

	public void setLost(boolean lost) {
		this.lost = lost;
	}
	
	public PlayChip(boolean test){
		map = MapMaker.readMapFile("resources/testmap.txt", 25, 25, 10);
		chip = new Chip(map.getStartingPosition(), this);
		threads = new LinkedList<Thread>();
		title = false;
		won = false;
		lost = false;
	}
	
}
