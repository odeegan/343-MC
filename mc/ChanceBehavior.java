package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import	mc.ChanceDeck;
import javax.swing.JButton;

public class ChanceBehavior implements SquareBehavior {
	
	static ChanceDeck chanceDeck;

	public ChanceBehavior() {
		chanceDeck = new ChanceDeck();
	}

	@Override
	public void execute() {
		GamePane.getInstance().setMessagePanelText("You landed on Chance");
		JButton drawChanceCard = new JButton("Draw Card");
		drawChanceCard.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				drawChanceCardButtonActionPerformed();
			}
		});
		GamePane.getInstance().addMessagePanelButton(drawChanceCard);
	}

	protected void drawChanceCardButtonActionPerformed() {
		GamePane.getInstance().clearMessageLayer();
		chanceDeck.drawCard();
	}

}
