package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ChanceCardToxicWaste extends ChanceCard {
	
	GameMaster gameMaster;
	GamePane gamePane;
	Player currentPlayer;
	JButton goToJailButton;
	JButton payToxicWasteButton;
	boolean hasSewage;
	int count = 0;
	
	public ChanceCardToxicWaste(){}
	
	public void performCard(){
		//Initialize Block
		gameMaster = GameMaster.getInstance();
		gamePane = GamePane.getInstance();
		currentPlayer = gameMaster.getCurrentPlayer();
		goToJailButton = new JButton("Go To Jail");
		goToJailButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent evt){
						goToJailButtonPerformed();
					}
				});
		payToxicWasteButton = new JButton("Pay");
		payToxicWasteButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent evt){
						payToxicWasteButtonPerformed();
					}
			});
		
		//Lets Check the Data!
		for(District district: currentPlayer.getDistricts()){
			if(district.hazard == STRUCTURE.SEWAGEPLANT){
				hasSewage = true;
				count++;
			}
		}
		
		//Lets Build the UI AND PUNISH!

		
		
		if(hasSewage){
			gamePane.setMessagePanelText("You drew the Toxic Waste card and " +
					"have "+ count +" sewage plant(s)!");
			gamePane.addMessagePanelText("Now you must pay 500k.");
			gamePane.addMessagePanelButton(payToxicWasteButton);
		}
		else{
			gamePane.setMessagePanelText("You drew the Toxic Waste card and " +
					"have no sewage plants!");
			gamePane.addMessagePanelText("You must go to Jail.");
			gamePane.addMessagePanelButton(goToJailButton);
		}
	}

	protected void payToxicWasteButtonPerformed() {
			currentPlayer.pay(((double)count));
	}

	protected void goToJailButtonPerformed() {
			currentPlayer.setPosition(10);
			currentPlayer.setIsInJail(true);
			gamePane.update();
			gameMaster.endTurn();
	}

}
