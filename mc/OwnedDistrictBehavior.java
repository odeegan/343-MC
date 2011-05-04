package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class OwnedDistrictBehavior implements SquareBehavior {

	@Override
	public void execute() {
		final Player currentPlayer = GameMaster.getInstance().getCurrentPlayer();
		final int position = currentPlayer.getPosition();
		final Board board = GameMaster.getInstance().getBoard();
		final District district = board.getDistrict(position);
		final GamePane gamePane = GamePane.getInstance();
		
		JButton payRentButton = new JButton("Pay Rent");
		payRentButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						currentPlayer.pay(district.getRent());
						gamePane.clearMessageLayer();
						gamePane.update();
					}
				});
		
		gamePane.setMessagePanelText("You landed on an Owned District.");

		if (district.getRent() != 0) {
			gamePane.addMessagePanelText( 
						"\nThe current rent is " + district.getRent());
			if (currentPlayer.hasRentDodgeCard) {
				gamePane.addMessagePanelText(
						"\nUse your Rent Dodge card or... ");
				gamePane.enableButton(gamePane.getRentDodgeButton());
			}
			gamePane.addMessagePanelButton(payRentButton);
		} else {
			gamePane.addMessagePanelText("A hazard is preventing"
						+ " the landlord from collecting rent");
		}		
	}
}
