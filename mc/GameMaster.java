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

		//newSquare.performBehavior();
	}
	
	public void displayPlayerChanceCards() {
		gamePane.hideButton(gamePane.getGetOutOfJailButton());
		gamePane.hideButton(gamePane.getRentDodgeButton());
		gamePane.hideButton(gamePane.getTaxiButton());
		gamePane.disableButton(gamePane.getTaxiButton());
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
		int[] dice = rollDice();
		
		System.out.println("Player rolled " 
				+ Integer.toString(dice[0]) 
				+ " " + Integer.toString(dice[1]));

		// check for doubles and send to jail if necessary
		if (dice[0] == dice[1]) {
			System.out.println(currentPlayer.getName() + " rolled DOUBLES");
			System.out.println("doubles count = " 
					+ Integer.toString(currentPlayer.getNumDoubles()));			
			currentPlayer.setDoubles(true);
			if (currentPlayer.isInJail) {
				gamePane.setMessagePanelText("DOUBLES !    " 
						+ currentPlayer.getName() + " is a free man.");	
			}
			if (currentPlayer.getNumDoubles() > 2) {
				System.out.println("Send Player" + 
						currentPlayer.getIndex() + " to Jail");
				currentPlayer.setIsInJail(true);
				gamePane.setMessagePanelText(
						"You rolled doubles 3 times! You're going to JAIL!");
				JButton button = new JButton("Cuff Me");
				button.addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								endTurn();
							}
						});
			} else {
				checkSquare(dice[0] + dice[1]);
			}
		} else {
			if (currentPlayer.isInJail) {
				gamePane.setMessagePanelText("Better luck next time.");
			} else {
			currentPlayer.setDoubles(false);
			checkSquare(dice[0] + dice[1]);
			}
		}
	}
		
	public int[] rollDice() {
		Random generator = new Random();
		int[] dice = new int[2];
		dice[0] = generator.nextInt(6) + 1;
		dice[1] = generator.nextInt(6) + 1;
		return dice;
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
			gamePane.setMessagePanelText("You're in Jail. Would you like" 
					+ " to...");
				
			JButton postBailButton = new JButton("Pay $500K");
			postBailButton.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							gamePane.setMessagePanelText(
									"You're a free man!");
							currentPlayer.hasGetOutOfJailCard = false;
							currentPlayer.pay(500000);
							gamePane.hideButton(gamePane.getGetOutOfJailButton());
							gamePane.disableButton(gamePane.getRollDiceButton());
							gamePane.enableButton(gamePane.getEndTurnButton());
							gamePane.update();
							}
					});
			
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
			JButton hopeForDoublesButton = new JButton("Neither");
			hopeForDoublesButton.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							gamePane.setMessagePanelText(
									"Better hope you roll doubles!");
						}
					});
			if (currentPlayer.hasGetOutOfJailCard) {
				gamePane.addMessagePanelButton(useCardButton);
			}
			gamePane.addMessagePanelButton(postBailButton);
			gamePane.addMessagePanelButton(hopeForDoublesButton);
		}
	}
	
}
