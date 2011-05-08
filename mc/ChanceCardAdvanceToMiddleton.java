package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ChanceCardAdvanceToMiddleton extends ChanceCard {
	
	GameMaster gameMaster;
	GamePane gamePane;
	JButton advanceToMiddletonButton;
	
	public ChanceCardAdvanceToMiddleton(){
		
	}
	
	public void performCard(){
		gameMaster = GameMaster.getInstance();
		gamePane = GamePane.getInstance();
		advanceToMiddletonButton = new JButton("Advance To Middleton");
		advanceToMiddletonButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent evt){
						advanceToMiddletonButtonPerformed();
					}
				});
		gamePane.setMessagePanelText("You drew the Advance to Middleton Card!");
		gamePane.addMessagePanelButton(advanceToMiddletonButton);
	}

	protected void advanceToMiddletonButtonPerformed() {
		Player currentPlayer = gameMaster.getCurrentPlayer();
		int currentPosition = currentPlayer.getPosition();
		if(currentPosition > 24)
			gameMaster.checkSquare((40 - currentPosition) + 24 );
		else
			gameMaster.checkSquare(24 - currentPosition);
	}
	
}
