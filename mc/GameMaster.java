package mc;

import java.util.ArrayList;
import java.util.Random;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;;

public class GameMaster {

	GamePane gamePane;

	ArrayList<Player> players;
	Board board;
	Player currentPlayer;
	
	
	private static final GameMaster GAMEMASTER = new GameMaster();

	
	public class SimpleMessage extends JPanel {
		
		JLabel text;
		JPanel buttonPanel;
		
		public SimpleMessage(String str) {
			setLayout(new BorderLayout());
			setPreferredSize(new Dimension(350,250));
			setBorder(BorderFactory.createLineBorder(Color.gray, 2));
			setOpaque(true);
			buttonPanel = new JPanel();
			text = new JLabel(str);
			text.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
			text.setHorizontalAlignment(SwingConstants.CENTER);
			add(text, BorderLayout.NORTH);
			add(buttonPanel, BorderLayout.SOUTH);
		}
		
		public void setText(String str) {
			text.setText(str);
		}
		
		public void addButton(JButton btn) {
			buttonPanel.add(btn);
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
		System.out.println("starting " + currentPlayer.getName() + " turn");
		testIfPlayerIsInJail();
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
		gamePane.setMessageLayer(new SimpleMessage(message));
	}
	
	
	public void roll() {
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
								gamePane.disableButton(gamePane.getRollDiceButton());
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
				SimpleMessage sm = new SimpleMessage("Use your Get out Of Jail Free Card?");
				sm.addButton(useCardButton);
				sm.addButton(keepCardButton);
				gamePane.setMessageLayer(sm);

			}
		}
	}
	
}
