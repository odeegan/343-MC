package mc;
import java.util.ArrayList;


public class FreeParkingBehavior implements SquareBehavior {

	public FreeParkingBehavior() {}

	@Override
	public void execute() {
		GamePane.getInstance().setMessagePanelText("Free Parking!");
		
		ArrayList<Player> players = GameMaster.getInstance().getPlayers();
		
		for (Player player : players) {
			if (player.hasRentDodgeCard == true) {
				if (player == GameMaster.getInstance().getCurrentPlayer()) {
					GamePane.getInstance().addMessagePanelText(
						"\nUnfortunately, you already have the Rent Dodge Card");
				} else {
					GamePane.getInstance().setMessagePanelText(
							" you get to take the RentDodge card from "
							+ player.getName());
					player.hasRentDodgeCard = false;
				}
			} else {
				GamePane.getInstance().setMessagePanelText(
						"\nYou get to take the Rent Dodge Card!");
			}
			player.hasRentDodgeCard = false;
		}
		GameMaster.getInstance().getCurrentPlayer().hasRentDodgeCard = true;
	}

}
