package model;

public class EnemyFactory {
	
	private String enemyType;
	private Map m;
	private int dir;
	
	public EnemyFactory(String enemyType, Map m){
		this.enemyType = enemyType;
		this.dir = 1;
		this.m = m;
	}
	
	public EnemyFactory(String enemyType, int dir, Map m){
		this.enemyType = enemyType.toUpperCase();
		this.dir = dir;
		this.m = m;
	}
	
	public Enemy makeEnemy(int xp, int yp){
		if(enemyType.equals("FIREBALL")
				|| enemyType.equals("FIREBALLCW")){
			return new FireBall(xp, yp, dir, m, true);
		}
		else if(enemyType.equals("FIREBALLCCW")){
			return new FireBall(xp, yp, dir, m, false);
		}
		else
			return new FireBall(xp, yp, dir, m, true);
	}
	
	public String getEnemyType(){
		return enemyType;
	}
	public Map getMap(){
		return m;
	}
	public int getDirection(){
		return dir;
	}
	
	public void setEnemyType(String enemyType){
		this.enemyType = enemyType;
	}
	public void setDirection(int dir){
		this.dir = dir;
	}
	
}
