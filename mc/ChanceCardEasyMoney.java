package mc;

public class ChanceCardEasyMoney extends ChanceCard{
	/**
	 * EASY MONEY
	 * 
	 * The player to your left must give
	 * you $1M.
	 */
	
	String name = "Easy Money";
	GameMaster gm;
	GamePane gp;
	
	boolean isPocketable = false;
	
	public ChanceCardEasyMoney() {
		gm = GameMaster.getInstance();
		gp = GamePane.getInstance();
	}
	
	/**
	 * Subtracts 1 Million from the next Player.
	 * Gives the current Player 1 Million.
	 * 
	 * @param player - the current Player
	 */
	public void performCard() {
		// the player's pay method should handle the case
		// where the player has insufficient cash
		gm.getNextPlayer().pay(1);
		gm.getCurrentPlayer().collect(1);
		
	}
}
