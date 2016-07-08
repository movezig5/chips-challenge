package model;

// This state is used when Chip completes the level.
public class WinState implements ChipState{
	
	private Chip chip;
	
	public WinState(Chip chip){
		this.chip = chip;
	}
	
	public void die() {
		//You can't die if you've won!
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
