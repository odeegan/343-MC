package mc;

import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import net.miginfocom.swing.MigLayout;


public class GameMaster {

	GamePane gamePane;
	
	GameStateMachine gameStateMachine;
	
	private static ArrayList<Player> players;
	private static Board board;
	private static Player currentPlayer;
	
	private static int selectedDistrict;
	
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
	
	public void startAuction() {
		System.out.println(currentPlayer.getName() + " is in Jail");
		gamePane.setMessagePanelText("Start the Auction !");
			

		JButton startAuctionButton = new JButton("START");
		startAuctionButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						gameStateMachine.setState(gameStateMachine.getAuctionState());

					}
				});

		gamePane.addMessagePanelButton(startAuctionButton);
	}
	
	public void startTurn() {
		//draw the screen
		//gamePane.addSelectionLayer();
		gamePane.enableButton(gamePane.getRollDiceButton());
		gamePane.disableButton(gamePane.getEndTurnButton());
		gamePane.update();
		displayPlayerChanceCards();
		System.out.println("--------------------------------");
		System.out.println("STARTING " + currentPlayer.getName() + " turn");
		System.out.println("--------------------------------");
		System.out.println("turns in jail = " 
					+ Integer.toString(currentPlayer.getTurnsInJail()));
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
	
	public int getSelectedDistrict() {
		return selectedDistrict;
	}
	
	public Player getCurrentPlayer() {
		return currentPlayer;
	}
	
	public void checkSquare(int roll) {
		System.out.println("checking square");
		currentPlayer.testMove(roll);
		final Square newSquare = board.getSquare(currentPlayer.position);
		
		if (currentPlayer.hasTaxiCard) {
			gamePane.enableButton(gamePane.getTaxiButton());

			gamePane.setMessagePanelText("You landed on "
						+ newSquare.getName()
						+ " You may use your Taxi Card or ....");

			JButton stayPutButton = new JButton("Stay Put");
			stayPutButton.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {						
							System.out.println(currentPlayer.getName() + " is staying put on "
									+ newSquare.getName());
							currentPlayer.doMove();
							newSquare.performBehavior();
							gamePane.update();
						}
					});
			
			gamePane.addMessagePanelButton(stayPutButton);
		} else {
			currentPlayer.doMove();
			newSquare.performBehavior();
			gamePane.update();	
		}
	}
	
	public void displayPlayerChanceCards() {
		gamePane.hideButton(gamePane.getGetOutOfJailButton());
		gamePane.hideButton(gamePane.getRentDodgeButton());
		gamePane.hideButton(gamePane.getTaxiButton());
		gamePane.hideButton(gamePane.getTaxDodgeButton());
		// these buttons will be enabled when appropriate
		gamePane.disableButton(gamePane.getTaxiButton());
		gamePane.disableButton(gamePane.getTaxDodgeButton());
		gamePane.disableButton(gamePane.getRentDodgeButton());
		gamePane.disableButton(gamePane.getGetOutOfJailButton());
		if (currentPlayer.hasGetOutOfJailCard) {
			gamePane.showButton(gamePane.getGetOutOfJailButton());
			gamePane.disableButton(gamePane.getGetOutOfJailButton());
		}
		if (currentPlayer.hasRentDodgeCard) {
			gamePane.showButton(gamePane.getRentDodgeButton());
		}
		if (currentPlayer.hasTaxiCard) {
			gamePane.showButton(gamePane.getTaxiButton());
		}
		if (currentPlayer.hasTaxDodgeCard) {
			gamePane.showButton(gamePane.getTaxDodgeButton());
		}}
	
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
				// free the player
				currentPlayer.setIsInJail(false);
				// clear the doubles counter
				currentPlayer.setDoubles(false);
			}
			if (currentPlayer.getNumDoubles() > 2) {
				System.out.println("Send Player" + 
						currentPlayer.getIndex() + " to Jail");
				// send player to jail
				currentPlayer.setIsInJail(true);
				// disable build button when player is sent directly to jail
				gamePane.disableButton(gamePane.getBuildButton());
				gamePane.setMessagePanelText(
						"You rolled doubles 3 times! You're going to JAIL!");
				JButton button = new JButton("Cuff Me");
				button.addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								endTurn();
							}
				});
			}
			// rolled doubles, has not rolled doubles three times
			// and is not in jail
			if (!currentPlayer.isInJail) {					
				checkSquare(dice[0] + dice[1]);
			}
			
		} else {
			if (currentPlayer.isInJail) {
				gamePane.setMessagePanelText("Better luck next time.");
				currentPlayer.setIsInJail(true);
			} else {
			currentPlayer.setDoubles(false);
			checkSquare(dice[0] + dice[1]);
			}
		}
		gamePane.update();
	}
		
	public int[] rollDice() {
		Random generator = new Random();
		int[] dice = new int[2];
		dice[0] = generator.nextInt(6) + 1;
		dice[1] = generator.nextInt(6) + 1;
		return dice;
	}
	
	public void useRentDodgeCard() {
		currentPlayer.hasRentDodgeCard = false;
		gamePane.hideButton(gamePane.getRentDodgeButton());
		gamePane.clearMessageLayer();
		gamePane.update();
	}
	
	public void useTaxiCard() {
		currentPlayer.hasTaxiCard = false;
		gamePane.hideButton(gamePane.getTaxiButton());
		
		gamePane.setMessagePanelText("Take a Taxi to...");		
		int[] positions = {-2, -1, 1, 2};
		int i = 0;
		for (i=0; i < positions.length; i++) {
			final int delta = positions[i];
			final Square square = board.getSquare(currentPlayer.getPosition() + delta);
			JButton button = new JButton(square.getName());
			button.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("taking a taxi");
							currentPlayer.testMove(delta);
							currentPlayer.doMove();
							square.performBehavior();
							gamePane.update();
						}
					});
			gamePane.addMessagePanelButton(button);	
		}
	}
	
	
	public void endTurn() {
		currentPlayer = getNextPlayer();
		gamePane.clearMessageLayer();
		gamePane.update();
		startTurn();
	}
	
	public void setNumPlayers(int numPlayers) {
		ArrayList<String> tokens = new ArrayList<String>();
		tokens.add("images/lightblueToken.png");
		tokens.add("images/greenToken.png");
		tokens.add("images/yellowToken.png");
		tokens.add("images/orangeToken.png");
		tokens.add("images/redToken.png");
		tokens.add("images/blueToken.png");
		System.out.println("Creating " + numPlayers + " players");
		int ii;
		for (ii=0; ii <= numPlayers; ii++) {
			players.add(new Player(ii));
			players.get(ii).setTokenFile(tokens.get(ii));
		}
		//set the current player to the first in the index
		System.out.println("setting current player to 0");
		currentPlayer = players.get(0);
	}
	
	public void setSelectedDistrict(int index) {
		selectedDistrict = index;
	}
	
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	public void testIfPlayerIsInJail() {
		if (currentPlayer.turnsInJail > 2) {
			currentPlayer.pay(.5);
			gamePane.setMessagePanelText("You've sat in Jail long enough. "
					+ "We're taking the bail money and kicking you out!" );
			currentPlayer.setIsInJail(false);
		}
				
		if (currentPlayer.isInJail) {
			System.out.println(currentPlayer.getName() + " is in Jail");
			
			// if this is players 3rd turn in jail
			// subtract 500K and set them free

			
			gamePane.setMessagePanelText("You're in Jail.");
			gamePane.addMessagePanelText("You can try your luck with the dice or...");
				
			JButton postBailButton = new JButton("Pay $500K");
			postBailButton.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							gamePane.setMessagePanelText(
									"You're a free man!");
							currentPlayer.hasGetOutOfJailCard = false;
							currentPlayer.pay(.5);
							currentPlayer.setIsInJail(false);
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
							currentPlayer.setIsInJail(false);
							currentPlayer.hasGetOutOfJailCard = false;
							gamePane.hideButton(gamePane.getGetOutOfJailButton());
							gamePane.disableButton(gamePane.getRollDiceButton());
							gamePane.enableButton(gamePane.getEndTurnButton());
							gamePane.update();
						}
					});

			if (currentPlayer.hasGetOutOfJailCard) {
				gamePane.addMessagePanelButton(useCardButton);
			}
			gamePane.addMessagePanelButton(postBailButton);
		}
	}
	

	public void setGameStateMachine(GameStateMachine gsm) {
		gameStateMachine = gsm;
	}
	
	
	
}
