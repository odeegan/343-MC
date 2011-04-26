package mc;
import java.util.*;
import javax.swing.*;
import javax.swing.JLabel;
import java.awt.BorderLayout;

import java.io.IOException;


public class Board implements Drawable {

	//ArrayList of all the board squares
	ArrayList<Square> squares;

	JPanel gameBoard;
	Icon image;
	
	public Board() {
		ArrayList<Square> squares = new ArrayList<Square>();
		int ii = 0;
		
		//initialize every square here
//			 Board Ordering:
//			 	GO
		squares.add(new Square());
		squares.get(ii++).setSquareBehavior(new GoBehavior());
//			 	Summergate - BRN
		squares.add(new District(ii, 
				"BROWN",
				"Summergate",
				500000,
				1000000,
				2000000));
		squares.get(ii++).setSquareBehavior(new UnownedDistrictBehavior());
//			 	Chance
		squares.add(new Square());
		squares.get(ii++).setSquareBehavior(new ChanceBehavior());
//			 	Stoneside - PRP
		squares.add(new District(ii, 
				"BROWN",
				"Stoneside",
				500000,
				1000000,
				2000000));
		squares.get(ii++).setSquareBehavior(new UnownedDistrictBehavior());
//			 	Industry Tax
		squares.add(new Square());
		squares.get(ii++).setSquareBehavior(new IndustryTaxBehavior());
//			 	Planning Permission - Rubbish Dump (2 Blocks) / Park
		squares.add(new Square());
		squares.get(ii++).setSquareBehavior(new PlanningPermissionBehavior());
//			 	Westlands - BBY
		squares.add(new District(ii, 
				"SKY",
				"Westlands",
				500000,
				1000000,
				2000000));
		squares.get(ii++).setSquareBehavior(new UnownedDistrictBehavior());
//			 	Chance
		squares.add(new Square());
		squares.get(ii++).setSquareBehavior(new ChanceBehavior());
//			 	Bayview Dock - BBY
		squares.add(new District(ii, 
				"SKY",
				"Bayview Dock",
				500000,
				1000000,
				2000000));
		squares.get(ii++).setSquareBehavior(new UnownedDistrictBehavior());
//			 	Brightside - BBY
		squares.add(new District(ii, 
				"SKY",
				"Stoneside",
				500000,
				1000000,
				2000000));
		squares.get(ii++).setSquareBehavior(new UnownedDistrictBehavior());
//			 	Jail
		squares.add(new Square());
		squares.get(ii++).setSquareBehavior(new JailBehavior());
//			 	Old Town - PRP
		squares.add(new District(ii, 
				"PURPLE",
				"Old Town",
				1000000,
				2000000,
				3000000));
		squares.get(ii++).setSquareBehavior(new UnownedDistrictBehavior());
//			 	Auction UnOwned
		squares.add(new Square());
		squares.get(ii++).setSquareBehavior(new AuctionBehavior());
//			 	Harbor heights - PRP
		squares.add(new District(ii, 
				"PURPLE",
				"Harbor Heights",
				1000000,
				2000000,
				3000000));
		squares.get(ii++).setSquareBehavior(new UnownedDistrictBehavior());
//			 	Central Quay - PRP
		squares.add(new District(ii, 
				"PURPLE",
				"Central Quay",
				1000000,
				2000000,
				3000000));
		squares.get(ii++).setSquareBehavior(new UnownedDistrictBehavior());
//			 	Planning Permission - Prison (4 Blocks) / School
		squares.add(new Square());
		squares.get(ii++).setSquareBehavior(new PlanningPermissionBehavior());
//			 	Shine Village - ORN
		squares.add(new District(ii, 
				"ORANGE",
				"Shine Village",
				1000000,
				2000000,
				3000000));
		squares.get(ii++).setSquareBehavior(new UnownedDistrictBehavior());
//			 	Chance
		squares.add(new Square());
		squares.get(ii++).setSquareBehavior(new ChanceBehavior());
//			 	East Bank - ORN
		squares.add(new District(ii, 
				"ORANGE",
				"East Bank",
				1000000,
				2000000,
				3000000));
		squares.get(ii++).setSquareBehavior(new UnownedDistrictBehavior());
//			 	Treetop Park - ORN
		squares.add(new District(ii, 
				"ORANGE",
				"Treetop Park",
				1000000,
				2000000,
				3000000));
		squares.get(ii++).setSquareBehavior(new UnownedDistrictBehavior());
//			 	Free Parking
		squares.add(new Square());
		squares.get(ii++).setSquareBehavior(new FreeParkingBehavior());
//			 	Seaview - RED
		squares.add(new District(ii, 
				"RED",
				"Seaview",
				1500000,
				3000000,
				4000000));
//			 	Chance
		squares.add(new Square());
		squares.get(ii++).setSquareBehavior(new ChanceBehavior());
//			 	The Wharf - RED
		squares.add(new District(ii, 
				"RED",
				"The Wharf",
				1500000,
				3000000,
				4000000));
		squares.get(ii++).setSquareBehavior(new UnownedDistrictBehavior());
//			 	Middleton - RED
		squares.add(new District(ii, 
				"RED",
				"Middleton",
				1500000,
				3000000,
				4000000));
		squares.get(ii++).setSquareBehavior(new UnownedDistrictBehavior());
//			 	Planning Permission - Sewage Plant (3 Blocks) / Water Tower
		squares.add(new Square());
		squares.get(ii++).setSquareBehavior(new PlanningPermissionBehavior());
//			 	New Town - YLW
		squares.add(new District(ii, 
				"YELLOW",
				"New Town",
				1500000,
				3000000,
				4000000));
		squares.get(ii++).setSquareBehavior(new UnownedDistrictBehavior());
//			 	Silver Harbor - YLW
		squares.add(new District(ii, 
				"YELLOW",
				"Silver Harbor",
				1500000,
				3000000,
				4000000));
		squares.get(ii++).setSquareBehavior(new UnownedDistrictBehavior());
//			 	Change
		squares.add(new Square());
		squares.get(ii++).setSquareBehavior(new ChanceBehavior());
//			 	Central City - YLW
		squares.add(new District(ii, 
				"YELLOW",
				"Central City",
				1500000,
				3000000,
				4000000));
		squares.get(ii++).setSquareBehavior(new UnownedDistrictBehavior());
//			 	GOTOJAIL
		squares.add(new Square());
		squares.get(ii++).setSquareBehavior(new GoToJailBehavior());
//			 	Royal Court - GRN
		squares.add(new District(ii, 
				"GREEN",
				"Royal Court",
				2000000,
				4000000,
				5000000));
		squares.get(ii++).setSquareBehavior(new UnownedDistrictBehavior());
//			 	The Marina - GRN
		squares.add(new District(ii, 
				"GREEN",
				"The Marina",
				2000000,
				4000000,
				5000000));
		squares.get(ii++).setSquareBehavior(new UnownedDistrictBehavior());
//			 	Auction UnOwned
		squares.add(new Square());
		squares.get(ii++).setSquareBehavior(new AuctionBehavior());
//			 	Riverside - GRN
		squares.add(new District(ii, 
				"GREEN",
				"Riverside",
				2000000,
				4000000,
				5000000));
		squares.get(ii++).setSquareBehavior(new UnownedDistrictBehavior());
//			 	Planning Permission Power Station (3 Blocks) / Wind Farm
		squares.add(new Square());
		squares.get(ii++).setSquareBehavior(new PlanningPermissionBehavior());
//			 	Chance
		squares.add(new Square());
		squares.get(ii++).setSquareBehavior(new ChanceBehavior());
//			 	Fortune Valley - BLU
		squares.add(new District(ii, 
				"BLUE",
				"Fortune Valley",
				2000000,
				4000000,
				5000000));
		squares.get(ii++).setSquareBehavior(new UnownedDistrictBehavior());
//			 	Industry Tax
		squares.add(new Square());
		squares.get(ii++).setSquareBehavior(new IndustryTaxBehavior());
//			 	Diamond Hills - BLU
		squares.add(new District(ii, 
				"BLUE",
				"Diamond Hills",
				2000000,
				4000000,
				5000000));
	}
	
	public Square getSquare(int position) {
		return squares.get(position);
	}
	
	private void addSquare(Square square) {
		squares.add(square);
	}
	
	public JPanel init() {
	
		gameBoard = new JPanel();
		image = new ImageIcon("images/mboard.png");
		JLabel bg = new JLabel(image);
		//bg.setLayout(new BorderLayout());
		gameBoard.add(bg);
		return gameBoard;
	}
	
	public void update() {
		
	}
	
}
