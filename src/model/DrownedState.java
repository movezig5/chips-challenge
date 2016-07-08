package model;

public class DrownedState implements ChipState{
	
	private Chip chip;
	
	public DrownedState(Chip chip){
		this.chip = chip;
	}
	
	public void die() {
		//Cannot die if already dead
	}
	public void burn() {}
	public void drown() {}
	public void explode() {}

	public void revive() {
		chip.setState(chip.getAliveState());
	}
	
	public void swim() {}
	public void slide() {}
	public void win() {}
	
}
