package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

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
		gm = GameMaster.getInstance();
		gp = GamePane.getInstance();
		gm.getNextPlayer().pay(1);
		gm.getCurrentPlayer().collect(1);
		gp.addMessagePanelText("You drew " + this.name + "!");
		gp.addMessagePanelText(gm.getNextPlayer().getName() +" has paid you 1m!");
		gp.update();
//		JButton continueButton = new JButton();
//		continueButton.addActionListener(
//				new ActionListener(){
//					public void actionPerformed(ActionEvent evt){
//						continueButtonPerformed();
//					}
//				});
//		
	}
//
//	protected void continueButtonPerformed() {
//		
//	}
}
