package mc;

public class StructureFactory {
	/**
	 * Singleton class used to keep track of the structures
	 * that have been built
	 */
	

	int parkCount = 3;
	int powerStationCount = 3;
	int prisonCount = 3;
	int rubbishDumpCount = 2;
	int schoolCount = 3;
	int sewagePlantCount = 3;
	int waterTowerCount = 3;
	int windFarmCount = 3;
	int railroadCount = 8;
	int monopolyTowerCount = 1;
	int skyscraperCount = 5;
	int industrialCount = 20;
	int residentialCount = 20;
	int stadiumCount = 2;
	
	
	private static StructureFactory uniqueInstance;
	
	private StructureFactory() {}
	
	public static StructureFactory getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new StructureFactory();
		}
		return uniqueInstance;
	}
	
	public STRUCTURE getStructureByName(String stName){
		if(stName.compareToIgnoreCase("residential") == 0)
			return STRUCTURE.RESIDENTIAL;
		else if (stName.compareToIgnoreCase("industrial") == 0)
			return STRUCTURE.INDUSTRIAL;
		else if (stName.compareToIgnoreCase("railroad") == 0)
			return STRUCTURE.RAILROAD;
		else if (stName.compareToIgnoreCase("stadium") == 0)
			return STRUCTURE.STADIUM;
		else if (stName.compareToIgnoreCase("monopolyTower") == 0)
			return STRUCTURE.MONOPOLYTOWER;
		else if (stName.compareToIgnoreCase("POWERSTATION") == 0)
			return STRUCTURE.POWERSTATION;
		else if (stName.compareToIgnoreCase("PRISON") == 0)
			return STRUCTURE.PRISON;
		else if (stName.compareToIgnoreCase("RUBBISHDUMP") == 0)
			return STRUCTURE.RUBBISHDUMP;
		else if (stName.compareToIgnoreCase("SCHOOL") == 0)
			return STRUCTURE.SCHOOL;
		else if (stName.compareToIgnoreCase("SEWAGEPLANT") == 0)
			return STRUCTURE.SEWAGEPLANT;
		else if (stName.compareToIgnoreCase("WATERTOWER") == 0)
			return STRUCTURE.WATERTOWER;
		else if (stName.compareToIgnoreCase("WINDFARM") == 0)
			return STRUCTURE.WINDFARM;
		else if (stName.compareToIgnoreCase("PARK") == 0)
			return STRUCTURE.PARK;
		else
			return null;
	}
	
	public STRUCTURE get(STRUCTURE st) {
		if (st == STRUCTURE.PARK && parkCount != 0) {
			parkCount--;
			return STRUCTURE.PARK;
		} else if (st == STRUCTURE.POWERSTATION &&
					powerStationCount != 0) {
			powerStationCount--;
			return STRUCTURE.POWERSTATION;
		} else if (st == STRUCTURE.PRISON &&
					prisonCount != 0) {
			prisonCount--;
			return STRUCTURE.PRISON;
		} else if (st == STRUCTURE.RUBBISHDUMP &&
					rubbishDumpCount != 0) {
			rubbishDumpCount--;
			return STRUCTURE.RUBBISHDUMP;			
		} else if (st == STRUCTURE.SCHOOL &&
				schoolCount != 0) {
			schoolCount--;
			return STRUCTURE.SCHOOL;
		} else if (st == STRUCTURE.SEWAGEPLANT &&
				sewagePlantCount != 0) {
			sewagePlantCount--;
			return STRUCTURE.SEWAGEPLANT;
		} else if (st == STRUCTURE.WATERTOWER &&
				waterTowerCount != 0) {
			waterTowerCount--;
			return STRUCTURE.WATERTOWER;
		} else if (st == STRUCTURE.WINDFARM &&
				windFarmCount != 0) {
			windFarmCount--;
			return STRUCTURE.WINDFARM;
		} else if ( st == STRUCTURE.MONOPOLYTOWER &&
				monopolyTowerCount != 0) {
			monopolyTowerCount--;
			return STRUCTURE.MONOPOLYTOWER;
		} else if ( st == STRUCTURE.RAILROAD &&
				railroadCount != 0) {
			railroadCount--;
			return STRUCTURE.RAILROAD;
		} else if ( st == STRUCTURE.SKYSCRAPER &&
				skyscraperCount != 0) {
			skyscraperCount--;
			return STRUCTURE.SKYSCRAPER;
		} else if ( st == STRUCTURE.RESIDENTIAL &&
				residentialCount != 0) {
			residentialCount--;
			return STRUCTURE.RESIDENTIAL;
		} else if ( st == STRUCTURE.INDUSTRIAL && 
				industrialCount != 0) {
			industrialCount--;
			return STRUCTURE.INDUSTRIAL;
		} else if ( st == STRUCTURE.STADIUM && 
				stadiumCount != 0) {
			stadiumCount--;
			return STRUCTURE.STADIUM;
		} else {
			return null;
		}
	}
	
	public void scrap(STRUCTURE st) {
		if (st == STRUCTURE.PARK) {
			parkCount++;
		} else if (st == STRUCTURE.POWERSTATION) {
			powerStationCount++;
		} else if (st == STRUCTURE.PRISON) {
			prisonCount++;
		} else if (st == STRUCTURE.RUBBISHDUMP) {
			rubbishDumpCount++;
		} else if (st == STRUCTURE.SCHOOL) {
			schoolCount++;
		} else if (st == STRUCTURE.SEWAGEPLANT) {
			sewagePlantCount++;
		} else if (st == STRUCTURE.WATERTOWER) {
			waterTowerCount++;
		} else if (st == STRUCTURE.WINDFARM) {
			windFarmCount++;
		} else if (st == STRUCTURE.MONOPOLYTOWER){
			monopolyTowerCount++;
		} else if (st == STRUCTURE.RAILROAD) {
			railroadCount++;
		} else if (st == STRUCTURE.SKYSCRAPER) {
			skyscraperCount++;
		} else if (st == STRUCTURE.STADIUM){
			stadiumCount++;
		} else if (st == STRUCTURE.INDUSTRIAL){
			industrialCount++;
		} else if (st == STRUCTURE.RESIDENTIAL){
			residentialCount++;
		}
	}
}

	
	
	
	
	
	