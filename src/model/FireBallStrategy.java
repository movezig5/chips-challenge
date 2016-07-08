package model;

/*
 * The movement strategy for the fireball enemy. The enemy moves until it hits
 * an obstacle, then changes direction based on whether it's set to move clockwise
 * or counterclockwise.
 */
public class FireBallStrategy implements EnemyMovementStrategy{
	
	Enemy enemy;
	boolean clockwise;
	
	public boolean clockWise(){
		return clockwise;
	}
	
	public FireBallStrategy(Enemy enemy, boolean clockwise){
		this.enemy = enemy;
		this.clockwise = clockwise;
	}
	
	public void move(){
		if(clockwise){
			switch(enemy.getDirection()){
			case(0):
				if(enemy.movableUp())
					enemy.moveUp();
				else if(enemy.movableRight())
					enemy.moveRight();
				else if(enemy.movableDown())
					enemy.moveDown();
				else if(enemy.movableLeft())
					enemy.moveLeft();
				break;
			case(1):
				if(enemy.movableDown())
					enemy.moveDown();
				else if(enemy.movableLeft())
					enemy.moveLeft();
				else if(enemy.movableUp())
					enemy.moveUp();
				else if(enemy.movableRight())
					enemy.moveRight();
				break;
			case(2):
				if(enemy.movableLeft())
					enemy.moveLeft();
				else if(enemy.movableUp())
					enemy.moveUp();
				else if(enemy.movableRight())
					enemy.moveRight();
				else if(enemy.movableDown())
					enemy.moveDown();
				break;
			case(3):
				if(enemy.movableRight())
					enemy.moveRight();
				else if(enemy.movableDown())
					enemy.moveDown();
				else if(enemy.movableLeft())
					enemy.moveLeft();
				else if(enemy.movableUp())
					enemy.moveUp();
				break;
			}
		}
		else{
			switch(enemy.getDirection()){
			case(0):
				if(enemy.movableUp())
					enemy.moveUp();
				else if(enemy.movableLeft())
					enemy.moveLeft();
				else if(enemy.movableDown())
					enemy.moveDown();
				else if(enemy.movableRight())
					enemy.moveRight();
				break;
			case(1):
				if(enemy.movableDown())
					enemy.moveDown();
				else if(enemy.movableRight())
					enemy.moveRight();
				else if(enemy.movableUp())
					enemy.moveUp();
				else if(enemy.movableLeft())
					enemy.moveLeft();
				break;
			case(2):
				if(enemy.movableLeft())
					enemy.moveLeft();
				else if(enemy.movableDown())
					enemy.moveDown();
				else if(enemy.movableRight())
					enemy.moveRight();
				else if(enemy.movableUp())
					enemy.moveUp();
				break;
			case(3):
				if(enemy.movableRight())
					enemy.moveRight();
				else if(enemy.movableUp())
					enemy.moveUp();
				else if(enemy.movableLeft())
					enemy.moveLeft();
				else if(enemy.movableDown())
					enemy.moveDown();
				break;
			}
		}
	}
	
}
