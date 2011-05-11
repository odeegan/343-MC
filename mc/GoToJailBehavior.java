package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class GoToJailBehavior implements SquareBehavior {

	Player currentPlayer;
	
	public GoToJailBehavior() {
	}

	@Override
	public void execute() {
		currentPlayer = GameMaster.getInstance().getCurrentPlayer();
		GamePane gamePane = GamePane.getInstance();
		gamePane.setMessagePanelText("" +
					"You landed on Go to Jail." 
					+ " If you have a Get Out of Jail Card"
					+ " use it. Otherwise.... "
				);
		
		JButton goToJailButton = new JButton("Go To Jail");
		goToJailButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						GameMaster.getInstance().getCurrentPlayer().setPosition(10);
						currentPlayer.setIsInJail(true);
						GameMaster.getInstance().setPerformed(true);
						GamePane.getInstance().update();
						GameMaster.getInstance().endTurn();
						}
				});
		gamePane.addMessagePanelButton(goToJailButton);
		if (currentPlayer.hasGetOutOfJailCard) {
			gamePane.enableButton(gamePane.getGetOutOfJailButton());
		}
		if (currentPlayer.hasTaxiCard) {
			gamePane.enableButton(gamePane.getTaxiButton());
		}
	}

}
