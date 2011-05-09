package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ChanceCardRepossessed extends ChanceCard {

	GameMaster gameMaster;
	GamePane gamePane;
	Player currentPlayer;
	JButton repossessedButton;
	private boolean failed;
	
	public ChanceCardRepossessed(){
		failed = false;
	}
	
	public void performCard(){
		gameMaster = GameMaster.getInstance();
		gamePane = GamePane.getInstance();
		currentPlayer = gameMaster.getCurrentPlayer();
		
		repossessedButton = new JButton("Discard");
		repossessedButton.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					repossessedButtonPerformed();
				}
		});
		gamePane.clearSelectedDistrict();

		//Check if they own Districts.
		if(currentPlayer.getDistricts().size() == 0){
			gamePane.setMessagePanelText("You drew the Repossessed card but own no Districts!");
			gamePane.addMessagePanelText("You pay M1m.");
			currentPlayer.pay(1);
			gamePane.update();
		}
		else{
			//gamePane.clearMessageLayer();
			gamePane.setMessagePanelText("You drew the Repossessed! Select one of your" +
					" Districts to sell back to the bank for M1m.");
			if(failed)gamePane.setMessagePanelText("Select Another. You must OWN the district!");
			gamePane.addMessagePanelButton(repossessedButton);
			gamePane.update();
		}
	}

protected void repossessedButtonPerformed() {
	int selectedDistrictIndex = gamePane.getSelectedDistrict();
	Board board = gameMaster.getBoard();
	if(selectedDistrictIndex != -1){
		Square selectedSquare = board.getSquare(selectedDistrictIndex);
		District district;
		if(selectedSquare.getType() == SQUARETYPE.DISTRICT){
			district = (District)selectedSquare;
			
			for(District district2 : currentPlayer.getDistricts())
				if(district2.getName() == district.getName()){
					boolean success = currentPlayer.districts.remove(district);
					System.out.println("success is "+ success);
					currentPlayer.collect(1);
					district.reset();
					failed = false;
					repossessedButton.setVisible(false);
					gamePane.clearMessageLayer();
					gamePane.clearSelectedDistrict();
					gamePane.setMessagePanelText(district.getName() +" has been repossessed.");
					gamePane.update();
					return;
				}else failed = true;
		}else failed = true;		
	}else failed = true;
	performCard();
}
}
