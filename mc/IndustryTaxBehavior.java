package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class IndustryTaxBehavior implements SquareBehavior {

	
	Player currentPlayer;
	
	public IndustryTaxBehavior() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		currentPlayer = GameMaster.getInstance().getCurrentPlayer();
		GamePane gamePane = GamePane.getInstance();
		gamePane.setMessagePanelText("You landed on Industry Tax");
		Boolean hasBlocks = false;
		for (District district: currentPlayer.getDistricts()) {
			if (district.getIndustrialBlockCount() > 0) {
				hasBlocks = true;
			}
		}
		
		if (hasBlocks) {
			gamePane.addMessagePanelText("Pay $2M for your industrial buildings");
			JButton payFineButton = new JButton("Pay $2M");
			payFineButton.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							currentPlayer.pay(2);
							GamePane.getInstance().update();
							}
					});
			gamePane.addMessagePanelButton(payFineButton);
			if(currentPlayer.hasTaxDodgeCard) {
				gamePane.addMessagePanelText("\n"
							+ "Use your Tax Dodge Card or...");
				gamePane.enableButton(gamePane.getTaxDodgeButton());
			}
		} else {
			gamePane.addMessagePanelText("You do not have industrial buildings."
						+ " Pay nothing.");
		}
		
		

	}

}

