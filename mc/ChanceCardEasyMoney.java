package mc;

public class ChanceCardEasyMoney extends ChanceCard{
	/**
	 * EASY MONEY
	 * 
	 * The player to your left must give
	 * you $1M.
	 */
	
	String name = "Easy Money";
	
	boolean isPocketable = false;
	
	public ChanceCardEasyMoney() {
		// empty constructor
	}
	
	/**
	 * Subtracts 1 Million from the next Player.
	 * Gives the current Player 1 Million.
	 * 
	 * @param player - the current Player
	 */
	public void performCard(GameInstance gi) {
		// the player's pay method should handle the case
		// where the player has insufficient cash
		gm.getNextPlayer().pay(1000000);
		gm.currentPlayer().collect(1000000);
	}
}
