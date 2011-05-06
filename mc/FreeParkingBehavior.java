package mc;
import java.util.ArrayList;


public class FreeParkingBehavior implements SquareBehavior {

	Player previousOwner;
	
	public FreeParkingBehavior() {}

	@Override
	public void execute() {
		GamePane.getInstance().setMessagePanelText("Free Parking!");
		
		ArrayList<Player> players = GameMaster.getInstance().getPlayers();
		
		for (Player player : players) {
			if (player.hasRentDodgeCard == true) {
				previousOwner = player;
			}
		}
		
		if (previousOwner != null) {			
			if (previousOwner.getName() == GameMaster.getInstance().getCurrentPlayer().getName()) {
					GamePane.getInstance().addMessagePanelText(
					"\nUnfortunately, you already have the Rent Dodge Card");
			} else {
				GamePane.getInstance().addMessagePanelText(
					"\nYou get to take the Rent Dodge Card from " + previousOwner.getName());
			}
			previousOwner.hasRentDodgeCard = false;
		} else {
			GamePane.getInstance().addMessagePanelText(
					"\nYou get to take the Rent Dodge Card.");	
		}
		GameMaster.getInstance().getCurrentPlayer().hasRentDodgeCard = true;
	}

}
