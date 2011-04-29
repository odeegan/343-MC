package mc;

import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import net.miginfocom.swing.MigLayout;



public class GameMaster {

	GamePane gamePane;

	private static ArrayList<Player> players;
	private static Board board;
	private static Player currentPlayer;
	
	
	private static final GameMaster GAMEMASTER = new GameMaster();

	
	private GameMaster() {
		players = new ArrayList<Player>();
		board = new Board();
		gamePane = GamePane.getInstance();
	}
	
	public static GameMaster getInstance() {
		return GAMEMASTER;
	}
	
	public void build() {
		System.out.println("build stuff");
	}
	
	public void startTurn() {
		//draw the screen
		gamePane.enableButton(gamePane.getRollDiceButton());
		gamePane.disableButton(gamePane.getEndTurnButton());
		gamePane.update();
		displayPlayerChanceCards();
		System.out.println("--------------------------------");
		System.out.println("STARTING " + currentPlayer.getName() + " turn");
		System.out.println("--------------------------------");
		gamePane.disableButton(gamePane.getEndTurnButton());
		testIfPlayerIsInJail();
	}
	
	public Board getBoard() {
		return board;
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
	
	
	public void checkSquare(int roll) {
		System.out.println("checking square");
		Square newSquare = board.getSquare(currentPlayer.getPosition() + roll);
		System.out.println(currentPlayer.getName() + " landed on "
					+ newSquare.getName());

		gamePane.setMessagePanelText(
				currentPlayer.getName() + " rolled "
				+ Integer.toString(roll) 
				+ " and landed on " + newSquare.getName());		
	}
	
	public void displayPlayerChanceCards() {
		gamePane.hideButton(gamePane.getGetOutOfJailButton());
		gamePane.hideButton(gamePane.getRentDodgeButton());
		gamePane.hideButton(gamePane.getTaxiButton());
		if (currentPlayer.hasGetOutOfJailCard) {
			gamePane.showButton(gamePane.getGetOutOfJailButton());
		}
		if (currentPlayer.hasRentDodgeCard) {
			gamePane.showButton(gamePane.getRentDodgeButton());
		}
		if (currentPlayer.hasTaxiCard) {
			gamePane.showButton(gamePane.getTaxiButton());
		}
	}
	
	public void roll() {
		gamePane.enableButton(gamePane.getEndTurnButton());
		int roll = rollDice();
		
		
		
		
		if (currentPlayer.isInJail) {
			if (currentPlayer.rolledDoubles) {
				gamePane.setMessagePanelText("DOUBLES !    " 
						+ currentPlayer.getName() + " is a free man.");
			} else {
				gamePane.disableButton(gamePane.getRollDiceButton());
				gamePane.setMessagePanelText("Better luck next time.");
			}
		} else if (currentPlayer.rolledDoubles) {
			gamePane.setMessagePanelText("DOUBLES !    "
						+ currentPlayer.getName()
						+ " rolled " + Integer.toString(roll));
			checkSquare(roll);

		} else {
			checkSquare(roll);
		}
	}
	
	public int rollDice() {
		Random generator = new Random();
		int[] dice = new int[2];
		dice[0] = generator.nextInt(6) + 1;
		dice[1] = generator.nextInt(6) + 1;
		System.out.println("Player rolled " 
				+ dice.toString());

		// check for doubles and send to jail if necessary
		if (die1 == die2) {
			System.out.println(currentPlayer.getName() + " rolled DOUBLES");
			currentPlayer.setDoubles(true);
			if (currentPlayer.getNumDoubles() == 3) {
				System.out.println("Send Player" + 
						currentPlayer.getIndex() + " to Jail");
			}
		} else {
			currentPlayer.setDoubles(false);
		}
		return die1 + die2;
	}
	
	
	public void endTurn() {
		currentPlayer = getNextPlayer();
		gamePane.clearMessageLayer();
		startTurn();
	}
	
	public void setNumPlayers(int numPlayers) {
		System.out.println("Creating " + numPlayers + " players");
		int ii;
		for (ii=0; ii <= numPlayers; ii++) {
			players.add(new Player(ii));
		}
		//set the current player to the first in the index
		System.out.println("setting current player to 0");
		currentPlayer = players.get(0);
	}
	
	public void testIfPlayerIsInJail() {
		if (currentPlayer.isInJail) {
			System.out.println(currentPlayer.getName() + " is in Jail");
			if (currentPlayer.hasGetOutOfJailCard) {
				JButton useCardButton = new JButton("Use Card");
				useCardButton.addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								gamePane.setMessagePanelText(
										"You're a free man!");
								currentPlayer.hasGetOutOfJailCard = false;
								gamePane.hideButton(gamePane.getGetOutOfJailButton());
								gamePane.disableButton(gamePane.getRollDiceButton());
								gamePane.enableButton(gamePane.getEndTurnButton());

							}
						});
				JButton keepCardButton = new JButton("Keep Card");
				keepCardButton.addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								gamePane.setMessagePanelText(
										"Better hope you roll doubles!");
							}
						});
				gamePane.setMessagePanelText("You're in Jail. Would you like" 
						+ " to use your Get out Of Jail Free Card?");
				gamePane.addMessagePanelButton(useCardButton);
				gamePane.addMessagePanelButton(keepCardButton);

			}
		}
	}
	
}
