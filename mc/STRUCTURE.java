package mc;

public enum STRUCTURE {

	PARK(3),
	POWERSTATION(3),
	PRISON(4),
	RUBBISHDUMP(2),
	SCHOOL(2), 
	SEWAGEPLANT(3),
	WATERTOWER(1),
	WINDFARM(1),
	MONOPOLYTOWER(0),
	SKYSCRAPER(0),
	RAILROAD(0),
	RESIDENTIAL(1),
	INDUSTRIAL(1),
	STADIUM(0);
	
	
	// enum instance fields
	private final int blockCount;
	
	// enum constructor
	STRUCTURE(int blockCount) {
		this.blockCount = blockCount;
	}
	
	public int getBlockCount() {
		return blockCount;
	}
}
