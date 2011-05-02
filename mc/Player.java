package mc;
import java.util.*;

public class Player {

	String name;
	int index;
	
	double cash = 37.7;
	int position = 0;
	int numDoubles = 0;
	int turnsInJail = 0;

	boolean rolledDoubles = false;
	boolean isInJail = true;
	boolean hasGetOutOfJailCard = true;
	boolean hasRentDodgeCard = false;
	boolean hasTaxiCard = false;
	
	ArrayList<District> districts;
	
	public Player(int index) {
		this.name = "Player" + Integer.toString(index);
		this.index = index;
		districts = new ArrayList<District>();
	}

	public void addDistrict(District district) {
		districts.add(district);
	}
	
	public double getCash() {
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
	
	public int getTurnsInJail() {
		return turnsInJail;
	}
	
	public int move(int delta) {
		int newPosition = position + delta;
		position = newPosition;
		return newPosition;
	}
	
	public void pay(double amount) {
		cash = cash - amount;
		// insert logic to check for bankruptcy or
		// figure out when to check if a player can't afford something
		// do we put it here, or in the logic before this method gets called?
	}
		
	public void collect(double amount) {
		cash = cash  + amount;
	}
		
	public String getName() {
		return name;
	}
	
	public Double getNetWorth() {
		// logic to calculate a players networth
		// cash + mortgage value of districts
		int i;
		Double sum = 0.0;
		for (i=0; i < districts.size(); i++) {
			sum += districts.get(i).getRent();
		}
		sum += getCash();
		return sum;
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
	
	public void setIsInJail(boolean bool) {
		isInJail = bool;
		// set player position to jail square
		if (bool == true) {
			setPosition(10);
		} else {
			stillInJail(false);
		}
	}
	
	public void stillInJail(boolean bool) {
		if (bool = true) {
			System.out.println("staying in Jail");
			turnsInJail += 1;
		} else {
			turnsInJail = 0;
		}
	}
	
	public void setPosition(int index) {
		System.out.println("Setting " + name + " position to " + index);
		position = index;
	}
	
	public ArrayList<District> getDistricts() {
		return districts;
	}
	
	public int getNumDoubles() {
		return numDoubles;
	}
	
	public String getDetails() {
		String string = new String("");
		string += "Name: " + getName(); 
		string += "\nCash: " + Double.toString(getCash());
		if (getCash() != getNetWorth()) {
			string += "\nNetworth: " + getNetWorth();
		}
		if (districts != null) {
			string += "\nDistricts: " + districts.toString();
		}
		return string;
	}
	
	public String toString() {
		return getName();
	}
	
}
