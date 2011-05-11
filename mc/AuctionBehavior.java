package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class AuctionBehavior implements SquareBehavior {

	GameMaster gameMaster;
	GamePane gamePane;
	ArrayList<District> districts;
	ArrayList<District> unownedDistricts;
	boolean failed;
	
	public AuctionBehavior() {
	gamePane = GamePane.getInstance();
	failed = false;
	}

	@Override
	public void execute() {
		gameMaster = GameMaster.getInstance();
		gamePane.clearMessageLayer();
//		districts = gameMaster.getBoard().getDistricts();
		if(!failed)	gamePane.setMessagePanelText("You landed on Auction");
		else gamePane.setMessagePanelText("District Already Owned.");
		gamePane.addMessagePanelText("Select an Unowned District to Auction.");
		
//		for(District district: districts){
//			if(district.isOwned() == false)
//				unownedDistricts.add(district);
//		}

		gamePane.setSelectedDistrict(0);
		JButton startAuctionButton = new JButton("Start Auction");
		startAuctionButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent evt){
						startAuctionButtonPerformed();
					}
				});
		gamePane.addMessagePanelButton(startAuctionButton);
	}

	protected void startAuctionButtonPerformed() {
		int selectedDistrictIndex = gamePane.getSelectedDistrict();
		Board board = gameMaster.getBoard();
			Square selectedSquare = board.getSquare(selectedDistrictIndex);
			District district;
			if(selectedSquare.getType() == SQUARETYPE.DISTRICT){
				district = (District)selectedSquare;
				if(!district.isOwned()){
					GameMaster.getInstance().setPerformed(true);
					gameMaster.startAuction();}
				else{
					failed = true;
					execute();
			}}
		}	
	}
