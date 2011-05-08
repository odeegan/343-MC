package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

public class ChanceCardAdvanceToDiamondHills extends ChanceCard {
	GameMaster gameMaster;
	GamePane gamePane;
	Player currePlayer;
	JButton advanceToDiamondHillsButton;
	
	public ChanceCardAdvanceToDiamondHills(){
		
	}
	
	public void performCard(){
		gameMaster = GameMaster.getInstance();
		gamePane = GamePane.getInstance();
		advanceToDiamondHillsButton = new JButton("Advance To Diamond Hills");
		advanceToDiamondHillsButton.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent evt){
						advanceToDiamondHillsButtonPerformed();
					}
				});
		gamePane.setMessagePanelText("You drew the Advance to Diamond Hills Card!");
		gamePane.addMessagePanelButton(advanceToDiamondHillsButton);
	}

	protected void advanceToDiamondHillsButtonPerformed() {
		Player currentPlayer = gameMaster.getCurrentPlayer();
		int currentPosition = currentPlayer.getPosition();
		//currentPlayer.setPosition(gameMaster.getBoard().getSquares().size());
		//ArrayList<Square> squares = gameMaster.getBoard().getSquares();
		gameMaster.checkSquare(39 - currentPosition);
	}
	
}
