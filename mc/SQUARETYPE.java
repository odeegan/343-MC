package mc;

public enum SQUARETYPE {

	AUCTION("Auction"), 
	CHANCE("Chance"), 
	DISTRICT("District"), 
	FREEPARKING("Free Parking"),
	GO("G0"), 
	GOTOJAIL("Go To Jail!"),
	INDUSTRYTAX("Industry Tax"),
	PLANNINGPERMISSION("Planning Permission"), 
	JAIL("Jail");
	
	
	// enum instance fields
	private final String name;
	
	// enum constructor
	SQUARETYPE(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
