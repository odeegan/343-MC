package mc;
import java.util.ArrayList;


public class FreeParkingBehavior implements SquareBehavior {

	public FreeParkingBehavior() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		GamePane.getInstance().setMessagePanelText("Free Parking! You are now"
					+ " the proud owner of the Rent Dodge Card.");
		
		ArrayList<Player> players = GameMaster.getInstance().getPlayers();
		
		for (Player player : players) {
			if (player.hasRentDodgeCard) {
				if (GameMaster.getInstance().getCurrentPlayer() != player) {
					GamePane.getInstance().setMessagePanelText("Free Parking!"
							+ " you get to take the RentDodge card from "
							+ player.getName());
				} else {
					GamePane.getInstance().setMessagePanelText("Free Parking!"
							+ " Unforunatley, you already have the Rent Dodge Card");
				}
			} else {
				GamePane.getInstance().setMessagePanelText("Free Parking!"
						+ " you get to take the RentDodge card from "
						+ player.getName());
			}
			player.hasRentDodgeCard = false;
		}
		GameMaster.getInstance().getCurrentPlayer().hasRentDodgeCard = true;
	}

}
