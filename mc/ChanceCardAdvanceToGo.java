package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ChanceCardAdvanceToGo extends ChanceCard {


	GameMaster gameMaster;
	GamePane gamePane;
	Player currentPlayer;
	JButton advanceToGoButton;
	
	public ChanceCardAdvanceToGo(){
		
	}
	
	public void performCard(){
		gameMaster = GameMaster.getInstance();
		gamePane = GamePane.getInstance();
		advanceToGoButton = new JButton("Advance To Go");
		advanceToGoButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent evt){
						advanceToGoButtonPerformed();
					}
				});
		gamePane.setMessagePanelText("You drew the Advance To Go Card!");
		gamePane.addMessagePanelText("Advance to Go. Collect 2m!");
		gamePane.addMessagePanelButton(advanceToGoButton);
		
	}

	protected void advanceToGoButtonPerformed() {
		Player currentPlayer = gameMaster.getCurrentPlayer();
		currentPlayer.setPosition(0);
		currentPlayer.collect(2);
		GameMaster.getInstance().setPerformed(true);
		gamePane.update();
		gamePane.clearMessageLayer();
		gamePane.setMessagePanelText("Welcome to Go!");
	}
}
