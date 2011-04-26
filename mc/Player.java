package mc;
import java.util.*;

public class Player {

	String name;
	int index;
	
	int cash = 37700000;
	int currentPosition = 0;
	int numDoubles = 0;

	
	boolean isInJail = false;
	boolean hasGetOutOfJailCard = false;
	boolean hasRentDodgeCard = false;
	
	ArrayList<District> districts;
	
	//TODO: require an Image for the constructor
	public Player(int index) {
		this.name = "Player" + Integer.toString(index);
		this.index = index;
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
	
	public int getCurrentPosition() {
		return currentPosition;
	}
	
	public int move(int delta) {
		int newPosition = currentPosition + delta;
		
		// insert logic to check if player has passed Go
		// deal with Chance Cards and squares that prevent
		// the player from collecting $200
		
		currentPosition = newPosition;
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
		
	
	public int getNetWorth() {
		// logic to calculate a players networth
		// cash + mortgage value of districts
		return 0;
	}
	
	public void rolledDoubles() {
		numDoubles += 1;
	}
	
	public int getNumDoubles() {
		return numDoubles;
	}
	
}
