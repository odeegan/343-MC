package mc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;

public class AuctionState implements GameState {

	GameStateMachine gameStateMachine;
	int playerCount, moneyMaximum, timerValue, currentBid;
	int bidArray[];
	Player currentPlayer;
	boolean isAuctioning;

	JFrame mainFrame;
	JLayeredPane layeredPane;
	JLabel counterLabel;
	JLabel player1Label;
	JLabel player2Label;
	JLabel player3Label;
	JLabel player4Label;
	JLabel player5Label;
	JLabel player6Label;
//	JLabel player1Bid;
//	JLabel player2Bid;
//	JLabel player3Bid;
//	JLabel player4Bid;
//	JLabel player5Bid;
//	JLabel player6Bid;
	JSlider auctionStartSlider;
	JButton auctionStartButton;
	JPanel counterPanel;
	JPanel playersPanel;
	Timer auctionTimer;
	JLabel playerBids[];
	JPanel playerPanels[];

	private JPanel auctionStartSliderPanel;
	private JLabel sliderLabel;

	// test variables
	JPanel winnerPanel;
	JLabel winnerLabel;
	private static final Font counterFont = new Font("Impact", Font.BOLD, 50);

	public AuctionState(GameStateMachine gsm, JFrame mf) {
		gameStateMachine = gsm;
		mainFrame = mf;
		isAuctioning = false;

		bidArray = new int[6];
		for (int ii = 0; ii < 6; ii++)
			bidArray[ii] = 0;

		// timerValue = (int)((Math.random()*100)%40 + 10);
		timerValue = 15;

		initComponents();

	}

	private void initComponents() {
		layeredPane = new JLayeredPane();
		counterPanel = new JPanel();
		auctionStartSliderPanel = new JPanel();
		counterLabel = new JLabel();
		sliderLabel = new JLabel();
		playersPanel = new JPanel();
		auctionStartSlider = new JSlider();
		auctionStartButton = new JButton();
		winnerPanel = new JPanel();
		winnerLabel = new JLabel();
		playerBids = new JLabel[6];
		playerPanels = new JPanel[6];
		playersPanel.setLayout(new MigLayout("align center"));
		for(int jj = 0; jj < 6; jj++){
			playerBids[jj] = new JLabel();
			playerPanels[jj] = new JPanel();
			playerPanels[jj].setLayout(new MigLayout("align center"));
			playerPanels[jj].add(playerBids[jj]);
		}
		
		//Setting up Keybinding...
		@SuppressWarnings("serial")
		Action incBid1 = new AbstractAction(){
			public void actionPerformed(ActionEvent evt){
				bidArray[0] = currentBid + 10000;
				currentBid = bidArray[0];
				playerBids[0].setText(Integer.toString(bidArray[0]));
			}
		};
		layeredPane.getInputMap().put(KeyStroke.getKeyStroke("Q"),"incrementBid1");
		layeredPane.getActionMap().put("incrementBid1",incBid1);
		 
		@SuppressWarnings("serial")
		Action incBid2 = new AbstractAction(){
			public void actionPerformed(ActionEvent evt){
				bidArray[1] = currentBid + 10000;
				currentBid = bidArray[1];
				playerBids[1].setText(Integer.toString(bidArray[1]));
			}
		};
		layeredPane.getInputMap().put(KeyStroke.getKeyStroke("CLOSE_BRACKET"),"incrementBid2");
		layeredPane.getActionMap().put("incrementBid2",incBid2);
		
		@SuppressWarnings("serial")
		Action incBid3 = new AbstractAction(){
			public void actionPerformed(ActionEvent evt){
				bidArray[2] = currentBid + 10000;
				currentBid = bidArray[2];
				playerBids[2].setText(Integer.toString(bidArray[2]));
			}
		};
		layeredPane.getInputMap().put(KeyStroke.getKeyStroke("Z"),"incrementBid3");
		layeredPane.getActionMap().put("incrementBid3",incBid3);
		
		@SuppressWarnings("serial")
		Action incBid4 = new AbstractAction(){
			public void actionPerformed(ActionEvent evt){
				bidArray[3] = currentBid + 10000;
				currentBid = bidArray[3];
				playerBids[3].setText(Integer.toString(bidArray[3]));
			}
		};
		layeredPane.getInputMap().put(KeyStroke.getKeyStroke("SLASH"),"incrementBid4");
		layeredPane.getActionMap().put("incrementBid4",incBid4);
		
		@SuppressWarnings("serial")
		Action incBid5 = new AbstractAction(){
			public void actionPerformed(ActionEvent evt){
				bidArray[4] = currentBid + 10000;
				currentBid = bidArray[4];
				playerBids[4].setText(Integer.toString(bidArray[4]));
			}
		};
		layeredPane.getInputMap().put(KeyStroke.getKeyStroke("V"),"incrementBid5");
		layeredPane.getActionMap().put("incrementBid5",incBid5);
		
		@SuppressWarnings("serial")
		Action incBid6 = new AbstractAction(){
			public void actionPerformed(ActionEvent evt){
				bidArray[5] = currentBid + 10000;
				currentBid = bidArray[5];
				playerBids[5].setText(Integer.toString(bidArray[5]));
			}
		};
		layeredPane.getInputMap().put(KeyStroke.getKeyStroke("M"),"incrementBid6");
		layeredPane.getActionMap().put("incrementBid6",incBid6);
		
		 
		//

		layeredPane.setLayout(new MigLayout("align center"));
		auctionStartSliderPanel.setLayout(new MigLayout("align center"));
		counterPanel.setLayout(new MigLayout("align center"));
		winnerPanel.setLayout(new MigLayout("align center"));

		counterPanel.setBorder(BorderFactory
				.createTitledBorder("Time Remaining:"));
		counterPanel.setPreferredSize(new Dimension(370, 170));
		auctionStartSliderPanel.setBorder(BorderFactory
				.createTitledBorder("Select Your Auction Start Value:"));

		playersPanel
				.setBorder(BorderFactory.createTitledBorder("Players Bids"));
		playersPanel.setPreferredSize(new Dimension(370, 200));
		playerPanels[0].setBorder(BorderFactory.createTitledBorder("Player 1: (Q)"));
		playerPanels[1].setBorder(BorderFactory.createTitledBorder("Player 2: (])"));
		playerPanels[2].setBorder(BorderFactory.createTitledBorder("Player 3: (Z)"));
		playerPanels[3].setBorder(BorderFactory.createTitledBorder("Player 4: (/)"));
		playerPanels[4].setBorder(BorderFactory.createTitledBorder("Player 5: (V)"));
		playerPanels[5].setBorder(BorderFactory.createTitledBorder("Player 6: (M)"));
		winnerPanel.setBorder(BorderFactory.createTitledBorder("Congratulations!"));

		counterLabel.setText("");
		counterLabel.setFont(counterFont);
		sliderLabel.setText("10000");
		sliderLabel.setPreferredSize(new Dimension(130, 30));

		for(int jj = 0; jj < 6; jj++){
			playerBids[jj].setText("0");
			playerBids[jj].setPreferredSize(new Dimension(130,30));
		}

		auctionTimer = new Timer(1000, new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				auctionTimerActionPerformed();
			}
		});
		
		auctionTimer.setInitialDelay(3000);
		auctionStartButton.setText("Start Auction");
		auctionStartButton.setVisible(true);
		auctionStartButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						auctionStartButtonActionPerformed(evt);
					}
				});
		auctionStartSlider.setPreferredSize(new Dimension(600,40));
		auctionStartSlider.setMinimum(0);

		auctionStartSlider.setValue(100000);
		auctionStartSlider.setMinorTickSpacing(100000);
		auctionStartSlider.setMajorTickSpacing(1000000);
		auctionStartSlider.setSnapToTicks(true);
		auctionStartSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent changeEvent) {
				auctionStartSliderChanged();
			}
		});

		counterPanel.add(counterLabel);

		auctionStartSliderPanel.add(sliderLabel);
		auctionStartSliderPanel.add(auctionStartSlider);
		auctionStartSliderPanel.add(auctionStartButton);
		
		winnerPanel.add(winnerLabel);
		
	}

	protected void auctionTimerActionPerformed() {
		isAuctioning = true;
		if (timerValue == 5) {
			counterLabel.setForeground(Color.RED);
			counterLabel.updateUI();
		}
		if (timerValue == -1) {
			auctionTimer.stop();
			isAuctioning = false;
			finishAuction();
		}
		counterLabel.setText(Integer.toString(timerValue--));

	}

	private void finishAuction() {

		counterPanel.setVisible(false);
		playersPanel.setVisible(false);
		layeredPane.removeAll();
		layeredPane.add(winnerPanel);
		
		layeredPane.revalidate();
		//layeredPane.updateUI();
		
		int winningBid = -1;
		int winningPlayer = -1;
		for (int ii = 0; ii < bidArray.length; ii++)
			if (bidArray[ii] > winningBid) {
				winningBid = bidArray[ii];
				winningPlayer = ii;
			}
		winnerLabel.setText("Player " + (winningPlayer + 1) + " has won with a bid of: " + winningBid);
		
	}

	protected void auctionStartSliderChanged() {
		sliderLabel.setText(Integer.toString((auctionStartSlider.getValue())));
		System.out.println("I CHANGED!");

	}

	protected void auctionStartButtonActionPerformed(ActionEvent evt) {
		System.out.println("Auction Started");
		currentBid = auctionStartSlider.getValue();
		bidArray[currentPlayer.index] = currentBid;
		auctionMode();
	}

	private void auctionMode() {
		for(int ii = 0; ii <GameMaster.getInstance().getNumPlayers();ii++)
			if(ii != 2)
				playersPanel.add(playerPanels[ii],"span 1");
			else playersPanel.add(playerPanels[ii],"wrap");
		
		auctionStartSliderPanel.setVisible(false);
		layeredPane.removeAll();
		layeredPane.add(counterPanel,"wrap");
		layeredPane.add(playersPanel);
		counterPanel.setVisible(true);
		playersPanel.setVisible(true);
		JOptionPane.showMessageDialog (layeredPane, "Welcome to the Auction Players. These are the Rules:\n" +
				"Each Player will be assigned a key that is used to bid. Each bid will increment\n" +
				"10000 dollars higher than the previous highest bid. You may only bid the amount\n" +
				"of money you currently have in your inventory. No last minute Mortgages punks!\n" +
				"Bid Keys:\n" +
				"	Player 1: Q	        Player 2: ]\n" +
				"	Player 3: Z	        Player 4: /\n" +
				"	Player 5: V	        Player 6: M\n" +
				"Once you're ready. Hit the OK Button!", "Welcome to The Auction!", JOptionPane.INFORMATION_MESSAGE);
		auctionTimer.start();
		counterLabel.setText("Get Ready!");
		layeredPane.revalidate();
		layeredPane.updateUI();
	}

	public void enter() {
		System.out.println("This is the Auction.");
		playerCount = GameMaster.getInstance().getNumPlayers();
		currentPlayer = GameMaster.getInstance().getCurrentPlayer();
		auctionStartSlider.setMaximum((int)(currentPlayer.getCash()*1000000));

		mainFrame.setContentPane(layeredPane);
		layeredPane.revalidate();
		startMode();
	}

	private void startMode() {
		layeredPane.add(auctionStartSliderPanel);
		auctionStartSliderPanel.setVisible(true);
	}
}
