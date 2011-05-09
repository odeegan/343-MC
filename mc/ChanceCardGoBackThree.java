package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ChanceCardGoBackThree extends ChanceCard {

	String name = "Easy Money";
	GameMaster gm;
	GamePane gp;
	
	boolean isPocketable = false;
	
	public ChanceCardGoBackThree() {
	}
	
	public void performCard() {
		// the player's pay method should handle the case
		// where the player has insufficient cash
		gm = GameMaster.getInstance();
		gp = GamePane.getInstance();

		int position = gm.getCurrentPlayer().getPosition();
		if(position - 3 < 0) position = 40 + position - 3;
		gm.getCurrentPlayer().setPosition(position);
		gp.setMessagePanelText("You must go back three squares!");
		JButton continueButton = new JButton("Go");
		continueButton.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent evt){
						continueButtonPerformed();
					}
				});
		gp.addMessagePanelButton(continueButton);
		
}

	protected void continueButtonPerformed() {		
		gp.update();
		gm.checkSquare(0);
	}
}