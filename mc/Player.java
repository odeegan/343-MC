package mc;
import java.util.*;
import java.text.DecimalFormat;


public class Player {

	String name;
	int index;
	
	Square currentSquare;
	
	String tokenFile;
	
	double cash = 37.7;
	int position = 0;
	int previousPosition;
	int numDoubles = 0;
	int turnsInJail = 0;

	boolean rolledDoubles = false;
	boolean isInJail = false;
	boolean hasGetOutOfJailCard = false;
	boolean hasRentDodgeCard = false;
	boolean hasTaxiCard = false;
	boolean hasTaxDodgeCard = false;
	
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
		DecimalFormat df = new DecimalFormat("###.##");
		return Double.valueOf(df.format(cash));
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
	
	public int testMove(int delta) {
		int newPosition = (getPosition() + delta) % 39;
		previousPosition = position;
		position = newPosition;
		return position;
	}
	
	public void doMove() {
		// use mod math to loop around the board
		// pay the player for passing Go
		if (position < previousPosition) {
			collect(2);
		}
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
		DecimalFormat df = new DecimalFormat("###.##");
		
		int i;
		Double sum = 0.0;
		for (i=0; i < districts.size(); i++) {
			sum += districts.get(i).getRent();
		}
		sum += getCash();
		return Double.valueOf(df.format(sum));
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
			turnsInJail +=1;
		} else {
			turnsInJail = 0;
		}
		
		if (turnsInJail > 1) {
			System.out.println("remaining in Jail");
		}
	}
	
	public void setPosition(int index) {
		System.out.println("Setting " + name + " position to " + index);
		position = index;		
	}
	
	public void setTokenFile(String str) {
		tokenFile = str;	
	}
	
	public String getTokenFile() {
		return tokenFile;
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
		
		if (districts.size() > 0) {
			string += "\nDistricts: ";
		}
		
		for (District district: districts) {
			string += district.getName() + ",  ";
		}
		
		
		return string;
	}
	
	public String toString() {
		return getName();
	}
	
}
