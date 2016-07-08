package model;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

/*
 * This class parses a text file and constructs a map based on the contents.
 */
public class MapMaker {
	
	public static Map readMapFile(String fileName, int x, int y, int chipsRequired){
		Map returnMap = new Map(x, y, chipsRequired);
		char[][] characters = new char[x][y];
		String line;
		EnemyFactory enemyFactory = new EnemyFactory("FIREBALL", returnMap);
		int count = 0;
		try(
				BufferedReader in = new BufferedReader(new FileReader(fileName));
		) {
			while((line = in.readLine()) != null){
				for(int i = 0; i < line.length(); i++){
					characters[i][count] = line.charAt(i);
				}
				count++;
			}
		} catch(IOException e){
			e.printStackTrace();
		}
		for(int i = 0; i < characters.length; i++){
			for(int j = 0; j < characters.length; j++){
				if(characters[i][j] == ' '){
					returnMap.setTile(i, j, new FloorTile(i, j));
				}
				else if(characters[i][j] == 'X'){
					returnMap.setTile(i, j, new WallTile(i, j));
				}
				else if(characters[i][j] == 'w'){
					returnMap.setTile(i, j, new WaterTile(i, j));
				}
				else if(characters[i][j] == '#'){
					returnMap.setTile(i, j, new FloorTile(i, j));
					returnMap.setObject(i, j, new Block(i, j, returnMap));
				}
				else if(characters[i][j] == '@'){
					returnMap.setTile(i, j, new FloorTile(i, j));
					returnMap.setStartingPosition(i, j);
				}
				else if(characters[i][j] == 'R'){
					returnMap.setTile(i, j, new FloorTile(i, j));
					returnMap.setObject(i, j, new RedDoor(i, j));
				}
				else if(characters[i][j] == 'r'){
					returnMap.setTile(i, j, new FloorTile(i, j));
					returnMap.setObject(i, j, new RedKey(i, j));
				}
				else if(characters[i][j] == 'U'){
					returnMap.setTile(i, j, new FloorTile(i, j));
					returnMap.setObject(i, j, new BlueDoor(i, j));
				}
				else if(characters[i][j] == 'u'){
					returnMap.setTile(i, j, new FloorTile(i, j));
					returnMap.setObject(i, j, new BlueKey(i, j));
				}
				else if(characters[i][j] == 'Y'){
					returnMap.setTile(i, j, new FloorTile(i, j));
					returnMap.setObject(i, j, new YellowDoor(i, j));
				}
				else if(characters[i][j] == 'y'){
					returnMap.setTile(i, j, new FloorTile(i, j));
					returnMap.setObject(i, j, new YellowKey(i, j));
				}
				else if(characters[i][j] == 'G'){
					returnMap.setTile(i, j, new FloorTile(i, j));
					returnMap.setObject(i, j, new GreenDoor(i, j));
				}
				else if(characters[i][j] == 'g'){
					returnMap.setTile(i, j, new FloorTile(i, j));
					returnMap.setObject(i, j, new GreenKey(i, j));
				}
				else if(characters[i][j] == 'F'){
					returnMap.setTile(i, j, new FireTile(i, j));
				}
				else if(characters[i][j] == 'O'){
					returnMap.setTile(i, j, new BombTile(i, j));
				}
				else if(characters[i][j] == 'f'){
					returnMap.setTile(i, j, new FloorTile(i, j));
					returnMap.setObject(i, j, new FireBoots(i, j));
				}
				else if(characters[i][j] == 'l'){
					returnMap.setTile(i, j, new FloorTile(i, j));
					returnMap.setObject(i, j, new Flippers(i, j));
				}
				else if(characters[i][j] == '!'){
					returnMap.setTile(i, j, new GoalTile(i, j));
				}
				else if(characters[i][j] == 'B'){
					returnMap.setTile(i, j, new FloorTile(i, j));
					returnMap.setObject(i, j, new Robber(i, j));
				}
				else if(characters[i][j] == 'x'){
					returnMap.setTile(i, j, new FloorTile(i, j));
					returnMap.setObject(i, j, new FakeWall(i, j));
				}
				else if(characters[i][j] == '*'){
					enemyFactory.setEnemyType("FIREBALL");
					enemyFactory.setDirection(2);
					returnMap.setTile(i, j, new FloorTile(i, j));
					returnMap.setObject(i, j, enemyFactory.makeEnemy(i, j));
				}
				else if(characters[i][j] == '+'){
					enemyFactory.setEnemyType("FIREBALLCCW");
					enemyFactory.setDirection(3);
					returnMap.setTile(i, j, new FloorTile(i, j));
					returnMap.setObject(i, j, enemyFactory.makeEnemy(i, j));
				}
				else if(characters[i][j] == 'c'){
					returnMap.setTile(i, j, new FloorTile(i, j));
					returnMap.setObject(i, j, new ComputerChip(i, j));
				}
				else if(characters[i][j] == 'C'){
					returnMap.setTile(i, j, new FloorTile(i, j));
					returnMap.setObject(i, j, new Gate(i, j));
				}
				else if(characters[i][j] == 'I'){
					returnMap.setTile(i,  j, new IceTile(i, j, returnMap));
				}
				else if(characters[i][j] == 'i'){
					returnMap.setTile(i, j, new FloorTile(i, j));
					returnMap.setObject(i, j, new IceSkates(i, j));
				}
				else if(characters[i][j] == '^'){
					returnMap.setTile(i, j, new MagnetTile(i, j, "UP"));
				}
				else if(characters[i][j] == 'v'){
					returnMap.setTile(i, j, new MagnetTile(i, j, "DOWN"));
				}
				else if(characters[i][j] == '<'){
					returnMap.setTile(i, j, new MagnetTile(i, j, "LEFT"));
				}
				else if(characters[i][j] == '>'){
					returnMap.setTile(i, j, new MagnetTile(i, j, "RIGHT"));
				}
				else if(characters[i][j] == 'm'){
					returnMap.setTile(i, j, new FloorTile(i, j));
					returnMap.setObject(i, j, new MagnetShoes(i, j));
				}
				else{
					returnMap.setTile(i, j, new FloorTile(i, j));
					returnMap.setObject(i, j, null);
				}
			}
		}
		return returnMap;
}
}
