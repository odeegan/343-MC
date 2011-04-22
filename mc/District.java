package mc;


public class District extends Square {

	SQUARETYPE type = SQUARETYPE.DISTRICT;
	
	SquareBehavior squareBehavior = new UnownedDistrictBehavior();
	
	String color;
	String name;
	
	// Rather than store a player object, we can store the index
	// of a player. This will make it easy to retrieve the Player
	// object when we need it by simply asking the Game class for it.
	
	Player owner;
	

	int residentialBlockCount = 0;
	int industrialBlockCount = 0;
		
	// STRUCTURES is an enum that stores type and block count
	STRUCTURE bonus;	
	STRUCTURE hazard;
		
	boolean railroad = false;
	boolean skyscraper = false;

	int residentialCost;
	int industrialCost;
	int skyscraperCost;
	
	public District (int position, String color, String name, int residentialCost, 
			int industrialCost, int skyscraperCost) {
		this.position = position;
		this.name = name;
		this.color = color;
		this.residentialCost = residentialCost;
		this.industrialCost = industrialCost;
		this.skyscraperCost = skyscraperCost;
		// set the intial square behavior
		// check out the super class 'Square' for more details
	}
	
	public String getColor() {
		return color;
	}
	
	public String getName() {
		return name;
	}
	
	
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public int getRent() {
		// calculate rent based on buildings, hazards, etc.
		return 0;
		}
	
	
	public int getMortgageValue() {
		return getRent();
	}
	
	public boolean isOwned() {
		return owner != null;
	}

	public boolean isBonused() {
		return bonus != null;
	}

	public boolean isRailRoaded() {
		return railroad;
	}

	public boolean isSkyScrapered() {
		return skyscraper;
	}
	
}
