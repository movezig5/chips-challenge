package model;

public class SwimState implements ChipState{
	
	private Chip chip;
	
	public SwimState(Chip chip){
		this.chip = chip;
	}
	
	public void die() {
		chip.setState(chip.getDrownedState());
	}

	public void burn() {
		chip.setState(chip.getBurnedState());
	}

	public void drown() {
		chip.setState(chip.getDrownedState());
	}

	public void explode() {
		chip.setState(chip.getDrownedState());
	}

	public void revive() {
		chip.setState(chip.getAliveState());
	}

	public void swim() {
		// Cannot swim if already swimming
	}
	
	public void slide(){
		chip.setState(chip.getSlidingState());
	}
	
	public void win(){
		chip.setState(chip.getWinState());
		chip.getGame().win();
	}

}
