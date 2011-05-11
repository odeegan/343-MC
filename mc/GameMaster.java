package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameMaster {

	GamePane gamePane;
	
	GameStateMachine gameStateMachine;
	
	private static ArrayList<Player> players;
	private static Board board;
	private static Player currentPlayer;
	private static ChanceDeck chanceDeck;
	
	private boolean isPaused = false;
	private boolean isBuilding = false;
	private boolean isTrading = false;
	private boolean hasRolled = false;
	private boolean hasPerformed = false;	
	
	
	//this should be in gamepane but I had to modify it to work properly
	private JButton railButton;
		
	private static final GameMaster GAMEMASTER = new GameMaster();

	
	private GameMaster() {
		players = new ArrayList<Player>();
		board = new Board();
		gamePane = GamePane.getInstance();
		chanceDeck = new ChanceDeck();
	}
	
	public static GameMaster getInstance() {
		return GAMEMASTER;
	}
	
	public void build() {
		System.out.println("build stuff");
	}
	
	public void startAuction() {
		isPaused = true;
		GamePane.getInstance().clearMessageLayer();
		gameStateMachine.setState(gameStateMachine.getAuctionState());
	}
	
	public void startBuild() {
		isBuilding = true;
		gameStateMachine.setState(gameStateMachine.getBuildState());
	}
	
	public void startTrade() {
		isTrading = true;
		gameStateMachine.setState(gameStateMachine.getTradeState());
	}
	
	public void startTurn() {
//		if (isPaused || currentPlayer.rolledDoubles || isBuilding || isTrading) {
			resumeTurn();
//			isPaused = false;
//			isBuilding = false;
//		}
//		else{
//			gamePane.enableButton(gamePane.getRollDiceButton());
//			isPaused = false;
//			isBuilding = false;
//			gamePane.disableButton(gamePane.getEndTurnButton());
//		}
		gamePane.update();
		displayPlayerChanceCards();
		System.out.println("--------------------------------");
		System.out.println("STARTING " + currentPlayer.getName() + " turn");
		System.out.println("--------------------------------");
		System.out.println("turns in jail = " 
					+ Integer.toString(currentPlayer.getTurnsInJail()));
		testIfPlayerIsInJail();
	}
	
	public void resumeTurn() {
		//have they rolled?
		//no
		//ROLL PUNK
		if(isPaused) isPaused = false;
		if(isTrading) isTrading = false;
		
		
		if(!hasRolled){
			gamePane.enableButton(gamePane.getRollDiceButton());
			gamePane.disableButton(gamePane.getBuildButton());
			gamePane.disableButton(gamePane.getEndTurnButton());
		}
		//they have rolled
		//but have they performed?
		//no they haven't performed
		else if(!hasPerformed){
			gamePane.disableButton(gamePane.getRollDiceButton());
			gamePane.disableButton(gamePane.getBuildButton());
			gamePane.disableButton(gamePane.getEndTurnButton());
		}else{
		//if they did perform did they roll dubs?
		//dubs have been rolled!
			if(currentPlayer.rolledDoubles){
				hasPerformed = false;
				gamePane.enableButton(gamePane.getRollDiceButton());
				gamePane.enableButton(gamePane.getBuildButton());
				gamePane.disableButton(gamePane.getEndTurnButton());
			}
		//no dubs?
		//they can still build!
			else if(!isBuilding){
				gamePane.disableButton(gamePane.getRollDiceButton());
				gamePane.enableButton(gamePane.getBuildButton());
				gamePane.enableButton(gamePane.getEndTurnButton());
			}else{
				gamePane.disableButton(gamePane.getRollDiceButton());
				gamePane.disableButton(gamePane.getBuildButton());
				gamePane.enableButton(gamePane.getEndTurnButton());
			}
		}
		
//		if(!currentPlayer.rolledDoubles){
//			if(!isBuilding){
//				gamePane.disableButton(gamePane.getRollDiceButton());
//				gamePane.enableButton(gamePane.getBuildButton());
//				gamePane.enableButton(gamePane.getEndTurnButton());
//				}
//			else{
//				gamePane.disableButton(gamePane.getRollDiceButton());
//				gamePane.disableButton(gamePane.getBuildButton());
//				gamePane.enableButton(gamePane.getEndTurnButton());
//			}
//		}
//		else{
//			if(isBuilding){
//				gamePane.disableButton(gamePane.getRollDiceButton());
//				gamePane.disableButton(gamePane.getBuildButton());
//				gamePane.enableButton(gamePane.getEndTurnButton());
//				}
//			else{
//				gamePane.enableButton(gamePane.getRollDiceButton());
//				gamePane.enableButton(gamePane.getBuildButton());
//				gamePane.disableButton(gamePane.getEndTurnButton());
//			}
//		}
//		if (currentPlayer.getNumDoubles() > 2) {
//			gamePane.disableButton(gamePane.getBuildButton());
//			gamePane.disableButton(gamePane.getRollDiceButton());
//			gamePane.enableButton(gamePane.getEndTurnButton());
//		}
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
	
	public boolean getPerformed(){
		return hasPerformed;
	}
	
	public void setPerformed(boolean arg){
		this.hasPerformed = arg;
	}
	
	public int getNumPlayers() {
		// TODO Auto-generated method stub
		return players.size();
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
		currentPlayer.testMove(roll);
		
		final Square newSquare = board.getSquare(currentPlayer.getPosition());
			
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
							gamePane.disableButton(gamePane.getTaxiButton());
							newSquare.performBehavior();
							//resumeTurn();
							gamePane.update();
						}
					});
			
			gamePane.addMessagePanelButton(stayPutButton);
		} else {
			currentPlayer.doMove();
			newSquare.performBehavior();
			//resumeTurn();
			gamePane.update();	
		}
	}
	
	public void checkForRailroad() {
		District district = board.getDistrict(currentPlayer.getPosition());
		
		if (district.isRailRoaded()) {
			gamePane.setMessagePanelText(district.getName() +  " has a railroad.");
			JButton railroadButton = new JButton("Use the RailRoad");
			railroadButton.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {						
							gamePane.clearMessageLayer();
							setPerformed(true);
							useRailroad();
						}
					});	
			gamePane.addMessagePanelButton(railroadButton);
		}
		if (district.isRailRoaded()) {
			JButton railroadButton = new JButton("Stay Put");
			railroadButton.addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {						
							gamePane.clearMessageLayer();
							setPerformed(true);
							gamePane.update();
						}
					});
			gamePane.addMessagePanelButton(railroadButton);
		}
	}
	
	
	public void useRailroad() {
		gamePane.clearSelectedDistrict();		
		gamePane.setMessagePanelText("Select a District with a Railroad.");
		
		railButton = new JButton("Move");
			railButton.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						railroadButtonClicked();
						}
					});	
		gamePane.addMessagePanelButton(railButton);
	}
	
	protected void railroadButtonClicked() {
		
		if (gamePane.getSelectedDistrict() == -1) {
			gamePane.addMessagePanelText("Please selected a district");
		}
		if (board.getDistrict(gamePane.getSelectedDistrict()).isRailRoaded() == true) {
			int delta = gamePane.getSelectedDistrict() - currentPlayer.getPosition();
			System.out.println("take railroad");
			currentPlayer.testMove(delta);
			currentPlayer.doMove();
			railButton.setVisible(false);
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
		
		hasRolled = true;
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
				gamePane.setMessagePanelText(
						"You rolled doubles 3 times! You're going to JAIL!");
			}
			// rolled doubles, has not rolled doubles three times
			// and is not in jail
			if (!currentPlayer.isInJail) {					
				checkSquare(dice[0] + dice[1]);
			}
			
		} else {
			if (currentPlayer.isInJail) {
				gamePane.clearMessageLayer();
				gamePane.setMessagePanelText("Better luck next time.");
				currentPlayer.setIsInJail(true);
			} else {
			currentPlayer.setDoubles(false);
			checkSquare(dice[0] + dice[1]);
			}
		}
		resumeTurn();
		gamePane.update();

	}
		
	public int[] rollDice() {
		
		Random generator = new Random();
		int[] dice = new int[2];
		dice[0] = generator.nextInt(6) + 1;
		dice[1] = generator.nextInt(6) + 1;
		
//		dice[0] = 5;
//		dice[1] = 5;
		
		JPanel diceBox = new JPanel();
		diceBox.setOpaque(false);
		for (int i = 0; i < 2; i++) {
			JLabel die = new JLabel(new ImageIcon("images/" + dice[i] + ".png"));
			diceBox.add(die);
		}
		gamePane.setDice(diceBox);
		
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
		hasRolled = false;
		hasPerformed = false;
		currentPlayer = getNextPlayer();
		gamePane.clearMessageLayer();
		gamePane.clearDice();
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
	
	public Player getPlayerByName(String str){
		 Player player = null;
		 for(int ii = 0; ii < players.size(); ii++)
		 if(players.get(ii).getName().equals(str))
		 player = players.get(ii);

		 return player;
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

	public ChanceDeck getChanceDeck() {
		return chanceDeck;
	}
	
	
}
