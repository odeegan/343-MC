package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class ChanceCardAuctionUnowned extends ChanceCard {
	GameMaster gameMaster;
	GamePane gamePane;
	ArrayList<District> districts;
	ArrayList<District> unownedDistricts;
	boolean failed;
	
	public ChanceCardAuctionUnowned() {
	failed = false;
	}

	@Override
	public void performCard() {
		gameMaster = GameMaster.getInstance();
		gamePane = GamePane.getInstance();
		gamePane.clearMessageLayer();
		if(!failed)	gamePane.setMessagePanelText("You drew Auction Card!");
		else gamePane.setMessagePanelText("District Already Owned.");
		gamePane.addMessagePanelText("Select an Unowned District to Auction.");

		gamePane.clearSelectedDistrict();
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
		if(selectedDistrictIndex != -1){
			Square selectedSquare = board.getSquare(selectedDistrictIndex);
			District district;
			if(selectedSquare.getType() == SQUARETYPE.DISTRICT){
				district = (District)selectedSquare;
				if(!district.isOwned()){
					gameMaster.startAuction();
				}else{
					failed = true;
					performCard();
			}}
		}
		else{
			failed = true;
			performCard();
		}}
}
