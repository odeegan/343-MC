package mc;

public class Auction extends Square{

	int position;
	SQUARETYPE type = SQUARETYPE.AUCTION;
	SquareBehavior squareBehavior = new AuctionBehavior();
	
				
	public Auction(){
		// empty constructor
	}
	
	public void performBehavior() {
		squareBehavior.execute();
	}
	
	public void setSquareBehavior(SquareBehavior sb) {
		squareBehavior = sb;
	}
}

