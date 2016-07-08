package model;

public class AliveState implements ChipState{
	
	private Chip chip;
	
	public AliveState(Chip chip){
		this.chip = chip;
	}
	
	public void die(){
		chip.setState(chip.getDeadState());
	}
	public void burn(){
		chip.setState(chip.getBurnedState());
	}
	public void drown() {
		chip.setState(chip.getDrownedState());
		
	}
	public void explode() {
		chip.setState(chip.getDeadState());
		
	}
	public void revive() {
		//Cannot revive if already alive	
	}
	public void swim(){
		chip.setState(chip.getSwimState());
	}
	public void slide(){
		chip.setState(chip.getSlidingState());
	}
	public void win(){
		chip.setState(chip.getWinState());
		chip.getGame().win();
	}
	
}
