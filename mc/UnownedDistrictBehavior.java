package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class UnownedDistrictBehavior implements SquareBehavior{

	public UnownedDistrictBehavior() {}
	
	public void execute() {
		final Player currentPlayer = GameMaster.getInstance().getCurrentPlayer();
		final int position = currentPlayer.getPosition();
		final Board board = GameMaster.getInstance().getBoard();
		final District district = board.getDistrict(position);
		final GamePane gamePane = GamePane.getInstance();
		gamePane.setMessagePanelText("You landed on an Unowned District");

		JButton purchaseButton = new JButton("Purchase");
		purchaseButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						currentPlayer.pay(district.getCost());
						currentPlayer.addDistrict(district);
						district.setOwner(currentPlayer);
						gamePane.clearMessageLayer();
						gamePane.update();
					}
				});
		
		
		JButton auctionButton = new JButton("Auction");
		auctionButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						//TODO: create method for seleting a district
						GameMaster.getInstance().startAuction();
						}
				});
		
		if (currentPlayer.getCash() > district.getCost()) {
			gamePane.addMessagePanelButton(purchaseButton);
		} else {
			gamePane.setMessagePanelText("You do not have"
						+ " enough cash to purchase it.");	
			gamePane.addMessagePanelButton(auctionButton);
		}
	}
}

