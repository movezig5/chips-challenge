package model;

public class DeadState implements ChipState{
	
	private Chip chip;
	
	public DeadState(Chip chip){
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
