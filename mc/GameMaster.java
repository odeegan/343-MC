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

	
	public class SimpleMessage extends JPanel {
		
		JPanel textPanel;
		JPanel buttonPanel;
		String formattedString;
		
		public SimpleMessage(String str) {
			setLayout(new BorderLayout());
			setPreferredSize(new Dimension(350,250));
			setBorder(BorderFactory.createLineBorder(Color.gray, 2));
			setOpaque(true);
			
			textPanel = new JPanel(new MigLayout());
			textPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
			textPanel.setPreferredSize(new Dimension(350, 75));
			JLabel textLabel = new JLabel("<html>" + str + "</html>");
			textLabel.setHorizontalAlignment(SwingConstants.CENTER);

			buttonPanel = new JPanel();
			
			textPanel.add(textLabel, "wrap");
			add(textPanel, BorderLayout.NORTH);
			add(buttonPanel, BorderLayout.SOUTH);
		}
		
		public void addText(String str) {
			JLabel newLabel = new JLabel(str);
			newLabel.setHorizontalAlignment(SwingConstants.CENTER);
			textPanel.add(newLabel, "wrap");
			redraw();
		}
		
		public void addButton(JButton btn) {
			buttonPanel.add(btn);
			redraw();
		}
		
		public void redraw() {
			buttonPanel.revalidate();
			buttonPanel.repaint();
		}
	}
	
	
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
		gamePane.build();
		displayPlayerChanceCards();
		System.out.println("starting " + currentPlayer.getName() + " turn");
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
		String message;
		System.out.println("checking square");
		System.out.println(Integer.toString(roll));
		System.out.println(Integer.toString(board.squares.size()));
		Square newSquare = board.getSquare(currentPlayer.getPosition() + roll);
		message = currentPlayer.getName() + " rolled " + Integer.toString(roll) +
			" and landed on " + newSquare.getName();
		SimpleMessage sm = new SimpleMessage(message);
		gamePane.setMessageLayer(sm);
		if (currentPlayer.hasTaxiCard) {
			sm.addText("You have a TaxiCard. Would you like to use it?");
			JButton useCardButton = new JButton("Use Card");
			useCardButton.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							gamePane.setMessageLayer(
									new SimpleMessage("You used your Taxi Card!"));
							gamePane.disableButton(gamePane.getRollDiceButton());
						}
					});
			JButton keepCardButton = new JButton("Keep Card");
			keepCardButton.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							gamePane.setMessageLayer(
									new SimpleMessage("We'll keep it for you."));
						}
					});
			sm.addButton(useCardButton);
			sm.addButton(keepCardButton);			
		}
		
	}
	
	public void displayPlayerChanceCards() {
		
	}
	
	public void roll() {
		gamePane.enableButton(gamePane.getEndTurnButton());
		String message;
		int roll = rollDice();
		if (currentPlayer.isInJail) {
			if (currentPlayer.lastRollWasDoubles) {
				message = "DOUBLES !    " + currentPlayer.getName()
						+ " is a free man.";
				gamePane.setMessageLayer(new SimpleMessage(message));

			} else {
				message = "Better luck next time.";
				gamePane.disableButton(gamePane.getRollDiceButton());
				gamePane.setMessageLayer(new SimpleMessage(message));

			}
		} else if (currentPlayer.lastRollWasDoubles) {
			message = "DOUBLES !    " + currentPlayer.getName()
						+ " rolled " + Integer.toString(roll);
			gamePane.setMessageLayer(new SimpleMessage(message));

		} else {
			checkSquare(roll);
		}
	}
	
	public int rollDice() {
		Random generator = new Random();
		int die1 = generator.nextInt(6) + 1;
		int die2 = generator.nextInt(6) + 1;
		// check for doubles and send to jail if necessary
		if (die1 == die2) {
			currentPlayer.rolledDoubles(true);
			if (currentPlayer.getNumDoubles() == 3) {
				System.out.println("Send Player" + 
						currentPlayer.getIndex() + " to Jail");
			}
		} else {
			currentPlayer.rolledDoubles(false);
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
		System.out.println("setting current player to 0");
		currentPlayer = players.get(0);
	}
	
	public void testIfPlayerIsInJail() {
		System.out.println("test if current user is in jail");
		if (currentPlayer.isInJail) {
			if (currentPlayer.hasGetOutOfJailCard) {
				JButton useCardButton = new JButton("Use Card");
				useCardButton.addActionListener(
						new ActionListener() {
							public void actionPerformed(ActionEvent event) {
								gamePane.setMessageLayer(
										new SimpleMessage("You're a free man!"));
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
								gamePane.setMessageLayer(
										new SimpleMessage("Better hope you roll doubles!"));
							}
						});
				SimpleMessage sm = new SimpleMessage("" +
							"You're in Jail. Would you like to use" + 
							" your Get out Of Jail Free Card?");
				sm.addButton(useCardButton);
				sm.addButton(keepCardButton);
				gamePane.setMessageLayer(sm);

			}
		}
	}
	
}
