package mc;
import java.util.*;
import javax.swing.*;
import javax.swing.JLabel;



public class Board {

	//ArrayList of all the board squares
	ArrayList<Square> squares;

	JPanel gameBoard;
	Icon image;
	
	public Board() {
		squares = new ArrayList<Square>();
		
//GO
		Square go = new Square(SQUARETYPE.GO);
		go.setSquareBehavior(new GoBehavior());
		go.setX(664);		
		go.setY(700);
		squares.add(go);

//Summergate - BRN
			
		District summergate = new District(
				SQUARETYPE.DISTRICT,
				"BROWN",
				"Summergate",
				.6);
		summergate.setRents(.02, .1, .2, .4, .8, 1.6, 2.2, 3.0, 5.5);
		summergate.setSquareBehavior(new UnownedDistrictBehavior());
		summergate.setX(602);		
		summergate.setY(708);
		go.setXX(664);
		go.setYY(768);
		squares.add(summergate);

//Chance
		Square chance0 = new Square(SQUARETYPE.CHANCE);
		chance0.setSquareBehavior(new ChanceBehavior());
		chance0.setX(540);		
		chance0.setY(708);
		squares.add(chance0);

//Stoneside - PRP
		District stoneside = new District( 
				SQUARETYPE.DISTRICT,
				"BROWN",
				"Stoneside",
				.6);

		stoneside.setRents(.04, .2, .4, .8, 1.6, 2.2, 3, 4, 7);
		stoneside.setSquareBehavior(new UnownedDistrictBehavior());
		stoneside.setX(478);		
		stoneside.setY(708);
		go.setXX(540);
		go.setYY(768);
		squares.add(stoneside);

//Industry Tax
		Square industryTax0 = new Square(SQUARETYPE.INDUSTRYTAX);
		industryTax0.setSquareBehavior(new IndustryTaxBehavior());
		industryTax0.setX(416);		
		industryTax0.setY(708);
		squares.add(industryTax0);

//Planning Permission - Rubbish Dump (2 Blocks) / Park
		Square planningPermission0 = new Square(SQUARETYPE.PLANNINGPERMISSION);
		planningPermission0.setSquareBehavior(new PlanningPermissionBehavior());
		planningPermission0.setX(354);		
		planningPermission0.setY(708);
		squares.add(planningPermission0);

//Westlands - BBY
		District westlands = new District( 
				SQUARETYPE.DISTRICT,
				"SKY",
				"Westlands",
				1.0);

		westlands.setRents(.06, .3, .6, 1.2, 1.8, 2.5, 3.5, 5, 7.5);
		westlands.setSquareBehavior(new UnownedDistrictBehavior());
		westlands.setX(292);		
		westlands.setY(708);
		go.setXX(354);
		go.setYY(768);
		squares.add(westlands);


//Chance
		Square chance1 = new Square(SQUARETYPE.CHANCE);
		chance1.setSquareBehavior(new ChanceBehavior());
		chance1.setX(230);		
		chance1.setY(708);
		squares.add(chance1);

//Bayview Dock - BBY
		District bayviewDock = new District( 
				SQUARETYPE.DISTRICT,
				"SKY",
				"Bayview Dock",
				1);

		bayviewDock.setRents(.06, .3, .6, 1.2, 1.8, 2.5, 3.5, 5, 7.5);
		bayviewDock.setSquareBehavior(new UnownedDistrictBehavior());
		bayviewDock.setX(168);		
		bayviewDock.setY(708);
		go.setXX(230);
		go.setYY(768);
		squares.add(bayviewDock);

//Brightside - BBY
		District brightside = new District( 
				SQUARETYPE.DISTRICT,
				"SKY",
				"Brightside",
				1.2);

		brightside.setRents(.08, .4, .8, 1.5, 2.5, 3.5, 4.5, 6, 8.5);
		brightside.setSquareBehavior(new UnownedDistrictBehavior());
		brightside.setX(106);		
		brightside.setY(708);
		go.setXX(168);
		go.setYY(768);
		squares.add(brightside);

//Jail
		Square jail = new Square(SQUARETYPE.JAIL);
		jail.setSquareBehavior(new JailBehavior());
		jail.setX(4);		
		jail.setY(708);
		squares.add(jail);

//Old Town - PRP
		District oldTown = new District( 
				SQUARETYPE.DISTRICT,
				"PURPLE",
				"Old Town",
				1.4);
				
		oldTown.setRents(.1, .5, 1, 2, 3, 4, 5, 6.5, 9);
		oldTown.setSquareBehavior(new UnownedDistrictBehavior());
		oldTown.setX(4);		
		oldTown.setY(602);
		go.setXX(106);
		go.setYY(664);
		squares.add(oldTown);

//Auction UnOwned
		Square auction0 = new Square(SQUARETYPE.AUCTION);
		auction0.setSquareBehavior(new AuctionBehavior());
		auction0.setX(4);		
		auction0.setY(540);
		squares.add(auction0);

//Harbor heights - PRP
		District harborHeights = new District( 
				SQUARETYPE.DISTRICT,
				"PURPLE",
				"Harbor Heights",
				1.4);

		harborHeights.setRents(.1, .5, 1, 2, 3, 4, 5, 6.5, 9);
		harborHeights.setSquareBehavior(new UnownedDistrictBehavior());
		harborHeights.setX(4);		
		harborHeights.setY(478);
		go.setXX(106);
		go.setYY(540);
		squares.add(harborHeights);


//Central Quay - PRP
		District centralQuay = new District( 
				SQUARETYPE.DISTRICT,
				"PURPLE",
				"Central Quay",
				1.6);

		centralQuay.setRents(.12, .6, 1.2, 2.4, 3.5, 4.5, 5.5, 7, 9.5);
		centralQuay.setSquareBehavior(new UnownedDistrictBehavior());
		centralQuay.setX(4);		
		centralQuay.setY(416);
		go.setXX(106);
		go.setYY(478);
		squares.add(centralQuay);

//Planning Permission - Prison (4 Blocks) / School
		Square planningPermission1 = new Square(SQUARETYPE.PLANNINGPERMISSION);
		planningPermission1.setSquareBehavior(new PlanningPermissionBehavior());
		planningPermission1.setX(4);		
		planningPermission1.setY(354);
		squares.add(planningPermission1);

		
//Shine Village - ORN
		District shineVillage = new District( 
				SQUARETYPE.DISTRICT,
				"ORANGE",
				"Shine Village",
				1.8);

		shineVillage.setRents(.14, .7, 1.4, 2.8, 3.8, 4.8, 6, 7.5, 10);
		shineVillage.setSquareBehavior(new UnownedDistrictBehavior());
		shineVillage.setX(4);		
		shineVillage.setY(292);
		go.setXX(106);
		go.setYY(354);
		squares.add(shineVillage);

//Chance
		Square chance2 = new Square(SQUARETYPE.CHANCE);
		chance2.setSquareBehavior(new ChanceBehavior());
		chance2.setX(4);		
		chance2.setY(230);
		squares.add(chance2);
		
//East Bank - ORN
		District eastBank = new District( 
				SQUARETYPE.DISTRICT,
				"ORANGE",
				"East Bank",
				1.8);

		eastBank.setRents(.14, .7, 1.4, 2.8, 3.8, 4.8, 6, 7.5, 10);
		eastBank.setSquareBehavior(new UnownedDistrictBehavior());
		eastBank.setX(4);		
		eastBank.setY(168);
		go.setXX(106);
		go.setYY(230);
		squares.add(eastBank);

//Treetop Park - ORN
		District treetopPark = new District( 
				SQUARETYPE.DISTRICT,
				"ORANGE",
				"Treetop Park",
				2);

		treetopPark.setRents(.16, .8, 1.6, 3.0, 4.0, 5.0, 6.0, 8.0, 11.0);
		treetopPark.setSquareBehavior(new UnownedDistrictBehavior());
		treetopPark.setX(4);		
		treetopPark.setY(106);
		go.setXX(106);
		go.setYY(168);
		squares.add(treetopPark);

//Free Parking
		Square freeParking = new Square(SQUARETYPE.FREEPARKING);
		freeParking.setSquareBehavior(new FreeParkingBehavior());
		freeParking.setX(4);		
		freeParking.setY(4);
		squares.add(freeParking);

//Seaview - RED
		District seaview = new District( 
				SQUARETYPE.DISTRICT,
				"RED",
				"Seaview",
				2.2);

		seaview.setRents(.18, .9, 1.8, 3.5, 4.5, 5.5, 6.5, 8.5, 12);
		seaview.setSquareBehavior(new UnownedDistrictBehavior());
		seaview.setX(106);		
		seaview.setY(4);
		go.setXX(168);
		go.setYY(104);
		squares.add(seaview);

//Chance
		Square chance3 = new Square(SQUARETYPE.CHANCE);
		chance3.setSquareBehavior(new ChanceBehavior());
		chance3.setX(168);		
		chance3.setY(4);
		squares.add(chance3);

//The Wharf - RED
		District theWarf = new District( 
				SQUARETYPE.DISTRICT,
				"RED",
				"The Wharf",
				2.2);

		theWarf.setRents(.18, .9, 1.8, 3.5, 4.5, 5.5, 6.5, 8.5, 12);
		theWarf.setSquareBehavior(new UnownedDistrictBehavior());
		theWarf.setX(230);		
		theWarf.setY(4);
		go.setXX(292);
		go.setYY(104);
		squares.add(theWarf);

		
//Middleton - RED
		District middleton = new District( 
				SQUARETYPE.DISTRICT,
				"RED",
				"Middleton",
				2.4);

		middleton.setRents(.2, 1, 2, 3.8, 4.8, 5.8, 6.8, 8.8, 13.5);
		middleton.setSquareBehavior(new UnownedDistrictBehavior());
		middleton.setX(292);		
		middleton.setY(4);
		go.setXX(416);
		go.setYY(104);
		squares.add(middleton);

//Planning Permission - Sewage Plant (3 Blocks) / Water Tower
		Square planningPermission2 = new Square(SQUARETYPE.PLANNINGPERMISSION);
		planningPermission2.setSquareBehavior(new PlanningPermissionBehavior());
		planningPermission2.setX(354);		
		planningPermission2.setY(4);
		squares.add(planningPermission2);

//New Town - YLW
		District newTown = new District( 
				SQUARETYPE.DISTRICT,
				"YELLOW",
				"New Town",
				2.6);

		newTown.setRents(.22, 1.1, 2.2, 4, 5, 6, 7, 8, 14);
		newTown.setSquareBehavior(new UnownedDistrictBehavior());
		newTown.setX(416);		
		newTown.setY(4);
		go.setXX(478);
		go.setYY(104);
		squares.add(newTown);

//Silver Harbor - YLW
		District silverHarbor = new District( 
				SQUARETYPE.DISTRICT,
				"YELLOW",
				"Silver Harbor",
				2.6);

		silverHarbor.setRents(.22, 1.1, 2.2, 4, 5, 6, 7, 10, 14);
		silverHarbor.setSquareBehavior(new UnownedDistrictBehavior());
		silverHarbor.setX(478);		
		silverHarbor.setY(4);
		go.setXX(540);
		go.setYY(104);
		squares.add(silverHarbor);

//Chance
		Square chance4 = new Square(SQUARETYPE.CHANCE);
		chance4.setSquareBehavior(new ChanceBehavior());
		chance4.setX(540);		
		chance4.setY(4);
		squares.add(chance4);

//Central City - YLW
		District centralCity = new District( 
				SQUARETYPE.DISTRICT,
				"YELLOW",
				"Central City",
				2.8);

		centralCity.setRents(.24, 1.2, 2.4, 4.2, 5.2, 6.2, 8.2, 10.5, 15);
		centralCity.setSquareBehavior(new UnownedDistrictBehavior());
		centralCity.setX(602);		
		centralCity.setY(4);
		go.setXX(664);
		go.setYY(104);
		squares.add(centralCity);

//GOTOJAIL
		Square goToJail = new Square(SQUARETYPE.GOTOJAIL);
		goToJail.setSquareBehavior(new GoToJailBehavior());
		goToJail.setX(664);		
		goToJail.setY(4);
		squares.add(goToJail);

//Royal Court - GRN
		District royalCourt = new District( 
				SQUARETYPE.DISTRICT,
				"GREEN",
				"Royal Court",
				3);

		royalCourt.setRents(.26, 1.3, 2.6, 4.4, 5.3, 6.5, 8.5, 11, 15.5);
		royalCourt.setSquareBehavior(new UnownedDistrictBehavior());
		royalCourt.setX(708);		
		royalCourt.setY(104);
		go.setXX(768);
		go.setYY(104);
		squares.add(royalCourt);

//The Marina - GRN
		District theMarina = new District( 
				SQUARETYPE.DISTRICT,
				"GREEN",
				"The Marina",
				3);

		theMarina.setRents(.26, 1.3, 2.6, 4.4, 5.3, 6.5, 8.5, 11, 15.5);
		theMarina.setSquareBehavior(new UnownedDistrictBehavior());
		theMarina.setX(708);		
		theMarina.setY(166);
		go.setXX(768);
		go.setYY(104);
		squares.add(theMarina);

//Auction UnOwned
		Square auction1 = new Square(SQUARETYPE.AUCTION);
		auction1.setSquareBehavior(new AuctionBehavior());
		auction1.setX(708);		
		auction1.setY(228);
		squares.add(auction1);

//Riverside - GRN
		District riverside = new District( 
				SQUARETYPE.DISTRICT,
				"GREEN",
				"Riverside",
				3.2);

		riverside.setRents(.28, 1.5, 3, 4.5, 5.5, 7, 9, 12, 16);
		riverside.setSquareBehavior(new UnownedDistrictBehavior());
		riverside.setX(708);		
		riverside.setY(290);
		go.setXX(768);
		go.setYY(292);
		squares.add(riverside);

//Planning Permission Power Station (3 Blocks) / Wind Farm
		Square planningPermission3 = new Square(SQUARETYPE.PLANNINGPERMISSION);
		planningPermission3.setSquareBehavior(new PlanningPermissionBehavior());
		planningPermission3.setX(708);		
		planningPermission3.setY(352);
		squares.add(planningPermission3);

//Chance
		Square chance5 = new Square(SQUARETYPE.CHANCE);
		chance5.setSquareBehavior(new ChanceBehavior());
		chance5.setX(708);		
		chance5.setY(414);
		squares.add(chance5);

//Fortune Valley - BLU
		District fortuneValley = new District( 
				SQUARETYPE.DISTRICT,
				"BLUE",
				"Fortune Valley",
				3.5);

		fortuneValley.setRents(.35, 1.75, 4.5, 5.5, 7, 9, 11, 16, 18);
		fortuneValley.setSquareBehavior(new UnownedDistrictBehavior());
		fortuneValley.setX(708);		
		fortuneValley.setY(476);
		go.setXX(768);
		go.setYY(478);
		squares.add(fortuneValley);

//Industry Tax
		Square industryTax1 = new Square(SQUARETYPE.INDUSTRYTAX);
		industryTax1.setSquareBehavior(new IndustryTaxBehavior());
		industryTax1.setX(708);		
		industryTax1.setY(538);
		squares.add(industryTax1);

//Diamond Hills - BLU
		District diamondHills = new District( 
				SQUARETYPE.DISTRICT,
				"BLUE",
				"Diamond Hills",
				4);

		diamondHills.setRents(.5, 3, 5, 8, 10, 12, 15, 18, 20);
		diamondHills.setSquareBehavior(new UnownedDistrictBehavior());
		diamondHills.setX(708);		
		diamondHills.setY(602);
		go.setXX(768);
		go.setYY(664);
		squares.add(diamondHills);


		
	}
	
	public Square getSquare(int position) {
		return squares.get(position);
	}
	
	public District getDistrict(int position) {
		return (District)squares.get(position);
	}
	
	
	public District getDistrictByName(String name) {
		for(int jj = 0; jj < squares.size(); jj++){
			if(name.equals((squares.get(jj).getName()))){
					return (District)squares.get(jj);
			}
		}
		return null;
	}
	
	public ArrayList<Square> getSquares() {
		return squares;
	}
	
	public ArrayList<District> getDistricts() {
		ArrayList<District> districtList = new ArrayList<District>();
		for (int i=0; i < squares.size(); i++) {
			if (getSquare(i).getType() == null) {
				districtList.add(getDistrict(i));
			}
		}
		return districtList;
	}
	
	public JPanel init() {
	
		gameBoard = new JPanel();
		image = new ImageIcon("images/mboard.png");
		JLabel bg = new JLabel(image);
		//bg.setLayout(new BorderLayout());
		gameBoard.add(bg);
		return gameBoard;
	}
}
