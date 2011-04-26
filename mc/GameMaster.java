package mc;

import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import javax.swing.*;


public class GameMaster {

	JFrame mainFrame;
	JComponent layeredPane;

	
	ArrayList<Player> players;
	Player currentPlayer;
	

	
	public class RollDiceMenu extends JPanel {
		
		
		
	}
	
	
	
	public GameMaster(JFrame mainFrame) {
		players = new ArrayList<Player>();
	}
	
	
	public void enterGameLoop() {
		
		
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
