package mc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

import net.miginfocom.swing.MigLayout;



public class AuctionState implements GameState {
	
	GameStateMachine gameStateMachine;
	int playerCount, moneyMaximum;
	Player currentPlayer;
	
	JFrame mainFrame;
	JLayeredPane layeredPane;
	JLabel counterLabel;
	JLabel player1Label;
	JLabel player2Label;
	JLabel player3Label;
	JLabel player4Label;
	JLabel player5Label;
	JLabel player6Label;
	JLabel player1Bid;
	JLabel player2Bid;
	JLabel player3Bid;
	JLabel player4Bid;
	JLabel player5Bid;
	JLabel player6Bid;
	JSlider auctionStartSlider;
	JButton auctionStartButton;
	JPanel counterPanel;
	JPanel playersPanel;

	private JPanel player1Panel;

	private JPanel player2Panel;

	private JPanel player3Panel;

	private JPanel player4Panel;

	private JPanel player5Panel;

	private JPanel player6Panel;
	private JPanel auctionStartSliderPanel;
	
	
	public AuctionState(GameStateMachine gsm, JFrame mf) {
		gameStateMachine = gsm;
		mainFrame = mf;
		playerCount = GameMaster.getInstance().getNumPlayers();
		currentPlayer = GameMaster.getInstance().getCurrentPlayer();
		
//		layeredPane = new JLayeredPane();
//		layeredPane.setLayout(new MigLayout());
//		
		
		initComponents();
		
	}
		
		
	private void initComponents() {
		layeredPane = new JLayeredPane();
		counterPanel = new JPanel();
		counterLabel = new JLabel();
		playersPanel = new JPanel();
		player1Panel = new JPanel();
		player1Bid = new JLabel();
		player2Panel = new JPanel();
		player2Bid = new JLabel();
		player3Bid = new JLabel();
		player3Panel = new JPanel();
		player4Bid = new JLabel();
		player4Panel = new JPanel();
		player5Bid = new JLabel();
		player5Panel = new JPanel();
		player6Bid = new JLabel();
		player6Panel = new JPanel();
		auctionStartSlider = new JSlider();
		auctionStartButton = new JButton();
		auctionStartSliderPanel = new JPanel();
		
		layeredPane.setLayout(new MigLayout());
		auctionStartSliderPanel.setLayout(new MigLayout());
		counterPanel.setLayout(new MigLayout());
		playersPanel.setLayout(new MigLayout());
		player1Panel.setLayout(new MigLayout());
		player2Panel.setLayout(new MigLayout());
		player3Panel.setLayout(new MigLayout());
		player4Panel.setLayout(new MigLayout());
		player5Panel.setLayout(new MigLayout());
		player6Panel.setLayout(new MigLayout());
		
		counterPanel.setBorder(BorderFactory.createTitledBorder("Time Remaining:"));
		auctionStartSliderPanel.setBorder(BorderFactory.createTitledBorder("Select Your Auction Start Value:"));

		playersPanel.setBorder(BorderFactory.createTitledBorder("Players Bids"));
		player1Panel.setBorder(BorderFactory.createTitledBorder("Player 1:"));
		player2Panel.setBorder(BorderFactory.createTitledBorder("Player 2:"));
		player3Panel.setBorder(BorderFactory.createTitledBorder("Player 3:"));
		player4Panel.setBorder(BorderFactory.createTitledBorder("Player 4:"));
		player5Panel.setBorder(BorderFactory.createTitledBorder("Player 5:"));
		player6Panel.setBorder(BorderFactory.createTitledBorder("Player 6:"));
		
		counterLabel.setText("HURR");
		player1Bid.setText("x1x");
		player2Bid.setText("x2x");
		player3Bid.setText("x3x");
		player4Bid.setText("x4x");
		player5Bid.setText("x5x");
		player6Bid.setText("x6x");
		
		auctionStartButton.setText("Start Auction");
		auctionStartButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				auctionStartButtonActionPerformed(evt);
		}
			});
		
		auctionStartSlider.setMinimum(10000);
		auctionStartSlider.setMaximum(moneyMaximum);
		auctionStartSlider.setMinorTickSpacing(10000);
		auctionStartSlider.setSnapToTicks(true);
		
		counterPanel.add(counterLabel,"span");
		
		player1Panel.add(player1Bid);
		player2Panel.add(player2Bid);
		player3Panel.add(player3Bid);
		player4Panel.add(player4Bid);
		player5Panel.add(player5Bid);
		player6Panel.add(player6Bid);
		
		playersPanel.add(player1Panel);
		playersPanel.add(player2Panel);
		playersPanel.add(player3Panel, "wrap");
		playersPanel.add(player4Panel);
		playersPanel.add(player5Panel);
		playersPanel.add(player6Panel);
		
		layeredPane.add(counterPanel, "span");
		layeredPane.add(playersPanel,"span");
	}


	protected void auctionStartButtonActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		
	}


	public void enter() {
		System.out.println("This is the Auction.");
		mainFrame.setContentPane(layeredPane);
		layeredPane.revalidate();
		startMode();
	}


	private void startMode() {
		counterPanel.setVisible(false);
		playersPanel.setVisible(false);
		auctionStartSliderPanel.setVisible(true);
		auctionStartButton.setVisible(true);
		
	}
}
