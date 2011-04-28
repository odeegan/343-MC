package mc;

import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

import javax.swing.*;
import java.awt.event.*;;

public class GameMaster {

	GamePane gamePane;

	ArrayList<Player> players;
	Player currentPlayer;
	
	boolean rolled;
	
	public class RollDiceMenu extends JPanel {
		
		public RollDiceMenu() {
			System.out.println("instantiating RollDiceMenu");
			setLayout(new GridBagLayout());
			setBounds(100,100,100,100);
			setPreferredSize(new Dimension(200,100));
			setBorder(BorderFactory.createLineBorder(Color.gray, 2));
			setOpaque(true);

			JButton rollDiceButton = new JButton("Roll Dice");
			rollDiceButton.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User clicked RollDice");
							JOptionPane.showMessageDialog(null, "ack");
						}
					});
			add(rollDiceButton);
		}
	}
	
	public class SimpleMessage extends JPanel {
		
		JLabel text;
		
		public SimpleMessage(String str) {
			setLayout(new GridBagLayout());
			setBounds(100,100,100,100);
			setPreferredSize(new Dimension(200,100));
			setBorder(BorderFactory.createLineBorder(Color.gray, 2));
			setOpaque(true);
			
			text = new JLabel(str);
			text.setFont(new Font("Serif", Font.BOLD, 24));
			add(text);
		}
		
		public void setText(String str) {
			text.setText(str);
		}
	}
	
	
	public GameMaster() {
		players = new ArrayList<Player>();
		gamePane = GamePane.getInstance();
	}
	
	
	public void enterGameLoop() {
		System.out.println("This is the Game Loop");
		rollAndMove();
		System.out.println("Done rolling and moving");

	}
	
	public Player getNextPlayer() {
		int index;
		// use mod math to loop back to the first player
		index = (currentPlayer.getIndex() + 1) % players.size();
		return players.get(index);
	}
	
	public Player getPreviousPlayer() {
		int index;
		// use mod math to loop back to the first player
		index = (currentPlayer.getIndex() - 1) % players.size();
		return players.get(index);
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void rollAndMove() {
		System.out.println("entering rollAndMove()");
		gamePane.setMessageLayer(new RollDiceMenu());

	}
	
	public int rollDice() {
		Random generator = new Random();
		int die1 = generator.nextInt(6) + 1;
		int die2 = generator.nextInt(6) + 1;
		// check for doubles and send to jail if necessary
		if (die1 == die2) {
			getCurrentPlayer().rolledDoubles();
			if (currentPlayer.getNumDoubles() == 3) {
				System.out.println("Send Player" + 
						currentPlayer.getIndex() + " to Jail");
			}
		}
		
		
		return die1 + die2;
	}
	
	public void setNumPlayers(int numPlayers) {
		System.out.println("Creating " + numPlayers + " players");
		int ii;
		for (ii=0; ii <= numPlayers; ii++) {
			players.add(new Player(ii));
		}
		//set the current player to the first in the index
		currentPlayer = players.get(0);
	}
	
}
