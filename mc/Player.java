package mc;
import java.util.*;

public class Player {

	String name;
	int index;
	
	int cash = 37700000;
	int position = 0;
	int numDoubles = 0;
	int turnsInJail = 0;

	boolean rolledDoubles = false;
	boolean isInJail = false;
	boolean hasGetOutOfJailCard = false;
	boolean hasRentDodgeCard = true;
	boolean hasTaxiCard = true;
	
	ArrayList<District> districts;
	
	public Player(int index) {
		this.name = "Player" + Integer.toString(index + 1);
		this.index = index;
		districts = new ArrayList<District>();
	}

	public void addDistrict(District district) {
		districts.add(district);
	}
	
	public int getCash() {
		return cash;
	}
	
	
	/*
	 * returns the index of the player in the 
	 * GameMaster's players ArrayList
	 */
	public int getIndex() {
		return index;
	}
	
	public int getPosition() {
		return position;
	}
	
	public int move(int delta) {
		int newPosition = position + delta;
		
		// insert logic to check if player has passed Go
		// deal with Chance Cards and squares that prevent
		// the player from collecting $200
		
		position = newPosition;
		return newPosition;
	}
	
	public void pay(int amount) {
		cash = cash - amount;
		// insert logic to check for bankruptcy or
		// figure out when to check if a player can't afford something
		// do we put it here, or in the logic before this method gets called?
	}
		
	public void collect(int amount) {
		cash = cash  + amount;
	}
		
	public String getName() {
		return name;
	}
	
	public int getNetWorth() {
		// logic to calculate a players networth
		// cash + mortgage value of districts
		return 0;
	}
	
	public void setDoubles(boolean bool) {
		if (bool == false) {
			numDoubles = 0;
			rolledDoubles = false;
		} else {
			numDoubles += 1;
			rolledDoubles = true;
		}	
	}
	
	public ArrayList<District> getDistricts() {
		return districts;
	}
	
	public int getNumDoubles() {
		return numDoubles;
	}
	
}
