package mc;

public class AuctionBehavior implements SquareBehavior {

	public AuctionBehavior() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() {
		GamePane.getInstance().setMessagePanelText("You landed on Auction");

	}

}
