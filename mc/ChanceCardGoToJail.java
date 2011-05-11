package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ChanceCardGoToJail extends ChanceCard {
	
	GameMaster gameMaster;
	GamePane gamePane;
	Player currentPlayer;
	JButton goToJailButton;
	
	public ChanceCardGoToJail(){
		
	}
	
	public void performCard(){
		gameMaster = GameMaster.getInstance();
		gamePane = GamePane.getInstance();
		goToJailButton = new JButton("Go To Jail");
		goToJailButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent evt){
						goToJailButtonPerformed();
					}
				});
		gamePane.setMessagePanelText("You drew the Go To Jail Card!");
		gamePane.addMessagePanelText("Do Not Pass Go. Do Not Collect 2m.");
		gamePane.addMessagePanelButton(goToJailButton);
		
	}

	protected void goToJailButtonPerformed() {
		Player currentPlayer = gameMaster.getCurrentPlayer();
		currentPlayer.setPosition(10);
		currentPlayer.setIsInJail(true);
		GameMaster.getInstance().setPerformed(true);
		gamePane.update();
		gameMaster.endTurn();
	}
}
