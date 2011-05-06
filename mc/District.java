package mc;


public class District extends Square {

	SQUARETYPE type;
	
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
	boolean stadium = false;
	boolean monopolyTower = false;

	double cost;
	double residentialCost;
	double industrialCost;
	double skyscraperCost;
	
	boolean isMortgaged = false;
	
	double[] rents;
	
	public District (SQUARETYPE type, String color, String name,  double cost) {
		this.name = name;
		this.color = color;
		this.cost = cost;
	}
	
	public String getColor() {
		return color;
	}
		
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
	public Player getOwner() {
		return owner;
	}
	
	public void setRents(double zero, double one, double two, double three,
						double four, double five, double six, double seven,
						double eight) {

		rents = new double[9];
		rents[0] = zero;
		rents[1] = one;
		rents[2] = two;
		rents[3] = three;
		rents[4] = four;
		rents[5] = five;
		rents[6] = six;
		rents[7] = seven;
		rents[8] = eight;
	}
	
	public void setResidentialCost(double rc) {
		this.residentialCost = rc;
	}
	
	public void setIndustrialCost(double ic) {
		this.industrialCost = ic;
	}
	
	public void setSkyScraperCost(double sc) {
		this.skyscraperCost = sc;
	}
	
	public double getCost() {
		return cost;
	}
	
	public int getIndustrialBlockCount() {
		return industrialBlockCount;
	}
	
	public int getResidentialBlockCount() {
		return residentialBlockCount;
	}
	
	public int getTotalBlockCount() {
		return residentialBlockCount + industrialBlockCount;
	}
	
	public void addIndustrialBlock(int amount) {
		industrialBlockCount = industrialBlockCount + amount;
	}
	
	public void addResidentialBlock(int amount) {
		residentialBlockCount = residentialBlockCount + amount;
	}
	
	public void addStadium() {
		stadium = true;
	}
	
	public void addSkyscraper() {
		skyscraper = true;
	}
	
	public void addMonopolyTower() {
		monopolyTower = true;
	}
	
	public void addHazard(STRUCTURE str) {
		hazard = str;
	}
	
	public void addBonus(STRUCTURE str) {
		bonus = str;
	}
	
	public String getName() {
		return name;
	}
	
	public double getRent() {
		// calculate rent based on buildings, hazards, etc.
		if (isMortgaged) {
			return getCost();
		}
		
		if (hazard != null) {
			return new Double(rents[industrialBlockCount]);
		} else {
			return new Double(rents[residentialBlockCount + industrialBlockCount]);
		}
	}	
	
	public double getMortgageValue() {
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
	
	public boolean isStadiumed() {
		return stadium;
	}
	
	public boolean isMonopolyTowered() {
		return monopolyTower;
	}
	
	public String toString() {
		String string = new String("");
		string += "Name: " + getName(); 
		if (owner != null) {
			string += "\nOwner: " + owner;
			string += "\nCurrent Rent: " + getRent();
		} else {
			string += "\nCost: " + Double.toString(getCost());
		}
		
		return string;
	}

	
}
