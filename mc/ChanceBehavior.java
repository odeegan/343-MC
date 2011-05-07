package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ChanceBehavior implements SquareBehavior {

	private JButton drawChanceCard;
	
	public ChanceBehavior() {
	}

	@Override
	public void execute() {
		GamePane.getInstance().setMessagePanelText("You landed on Chance");
//		if(drawChanceCard == null){
			drawChanceCard = new JButton("Draw Card");
			drawChanceCard.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent evt){
					drawChanceCardButtonActionPerformed();
				}
			});
			GamePane.getInstance().addMessagePanelButton(drawChanceCard);
	//	}
	//	else
	//		drawChanceCard.setVisible(true);
	}

	protected void drawChanceCardButtonActionPerformed() {
		//GamePane.getInstance().clearMessageLayer();
		ChanceCard drawnCard = GameMaster.getInstance().getChanceDeck().drawCard();
		drawnCard.performCard();
		drawChanceCard.setVisible(false);
	}

}
