package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ChanceCardFreeBuild extends ChanceCard {
	
	GameMaster gameMaster;
	GamePane gamePane;
	Player currentPlayer;
	JButton freeBuildButton;
	private boolean failed;
	
	public ChanceCardFreeBuild(){
		failed = false;
	}
	
	public void performCard(){
		gameMaster = GameMaster.getInstance();
		gamePane = GamePane.getInstance();
		currentPlayer = gameMaster.getCurrentPlayer();
		
		freeBuildButton = new JButton("Build");
		freeBuildButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					freeBuildButtonPerformed();
				}
		});
		gamePane.clearSelectedDistrict();
		
		//Check if they own Districts.
		if(currentPlayer.getDistricts().size() == 0){
			gamePane.setMessagePanelText("You drew the Free Build card but own no Districts!");
			gamePane.addMessagePanelText("You gained M500k");
			currentPlayer.collect(0.5);
			gamePane.update();
		}
		else{
			gamePane.setMessagePanelText("You drew the Free Build Card! Select one of your" +
					" Districts to build a free Residential on.");
			gamePane.addMessagePanelButton(freeBuildButton);
			if(failed)gamePane.addMessagePanelText("You must OWN the district!");
		}
	}

	protected void freeBuildButtonPerformed() {
		int selectedDistrictIndex = gamePane.getSelectedDistrict();
		Board board = gameMaster.getBoard();
		if(selectedDistrictIndex != -1){
			Square selectedSquare = board.getSquare(selectedDistrictIndex);
			District district;
			if(selectedSquare.getType() == SQUARETYPE.DISTRICT){
				district = (District)selectedSquare;
				
				for(District district2 : currentPlayer.getDistricts())
					if(district2.getName() == district.getName()){
						district.addResidentialBlock(1);
						failed = false;
						freeBuildButton.setVisible(false);
						gamePane.clearMessageLayer();
						gamePane.clearSelectedDistrict();
						gamePane.setMessagePanelText(district.getName() +" has gained a residential");
						gamePane.update();
						return;
					}else failed = true;
			}		
		}
	}
}