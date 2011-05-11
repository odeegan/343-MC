package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ChanceCardSteal extends ChanceCard {

	GameMaster gameMaster;
	GamePane gamePane;
	JButton stealButton;
	boolean failed;
	
	public ChanceCardSteal() {
	failed = false;
	}

	@Override
	public void performCard() {
		gameMaster = GameMaster.getInstance();
		gamePane = GamePane.getInstance();
		gamePane.clearMessageLayer();
		
		
		boolean noneOwned = true;
		
		for(Player player: gameMaster.getPlayers())
			if(player.getDistricts().size() > 0)
				noneOwned = false;
		
		if(noneOwned){
			gamePane.setMessagePanelText("You drew the Steal card but no districts " +
					"are currently owned! Better Luck next time.");
			gameMaster.setPerformed(true);
			gamePane.update();
			return;
			
		}
		
		if(!failed)	gamePane.setMessagePanelText("You drew the Steal Card!");
		else gamePane.setMessagePanelText("Invalid District.");
		gamePane.addMessagePanelText("Select an Owned District to Steal.");

		
		
		gamePane.clearSelectedDistrict();
		stealButton = new JButton("Steal District");
		stealButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent evt){
						stealButtonPerformed();
					}
				});
		gamePane.addMessagePanelButton(stealButton);
	}

	protected void stealButtonPerformed() {
		int selectedDistrictIndex = gamePane.getSelectedDistrict();
		Board board = gameMaster.getBoard();
		if(selectedDistrictIndex != -1){
			Square selectedSquare = board.getSquare(selectedDistrictIndex);
			District district;
			if(selectedSquare.getType() == SQUARETYPE.DISTRICT){
				district = (District)selectedSquare;
				if(qualifiesForSteal(district)){
					Player owner = district.getOwner();
					Player currentPlayer = gameMaster.getCurrentPlayer();
					owner.districts.remove(district);
					currentPlayer.addDistrict(district);
					gamePane.clearMessageLayer();
					gamePane.setMessagePanelText("You have stolen "+district.getName());
					stealButton.setVisible(false);
					gameMaster.setPerformed(true);
					gamePane.update();
				}else{
					failed = true;
					performCard();
			}}
		}
		else{
			failed = true;
			performCard();
		}}

	private boolean qualifiesForSteal(District district) {
		String districtColor = district.color;
		int counter = 0;
		
		if(district.getOwner() == gameMaster.getCurrentPlayer()) return false;
		
		if(district.isOwned() == true)
			for(District district2 : district.getOwner().getDistricts()){
				if(district2.color == districtColor)
					counter++;
			}
		else return false;
		
		if(counter == 3) return false;
		
		if(counter == 2 && (districtColor == "BLUE" || districtColor == "BROWN")) return false;
		
		return true;
	}
}
