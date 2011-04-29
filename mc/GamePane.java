package mc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import net.miginfocom.swing.MigLayout;



class GamePane extends JLayeredPane {

	
	private static JPanel baseLayer;
	private static JPanel playerTokensPanel;
	private static JPanel squaresPanel;
	private static JPanel mainHudPanel;
	
	private static JPanel messageLayer;
	
	private static JPanel hudButtonPanel;
	private static JPanel hudPanel1;
	private static JPanel hudPanel2;
	private static JPanel hudPanel3;
	
	private static JButton rollDiceButton;
	private static JButton buildButton;
	private static JButton endTurnButton;
	
	private static JButton getOutOfJailButton;
	private static JButton rentDodgeButton;
	private static JButton taxiButton;
	
	
	private static final GamePane GAMEPANE = new GamePane();
	
	public class GetOutOfJailButton extends JButton {
		
		public GetOutOfJailButton() {
		    //setIcon(new ImageIcon("images/getOutOfJailFree.png"));
		    //setRolloverIcon(new ImageIcon("images/getOutOfJailFreeRollOver.png"));
		   //setRolloverEnabled(false);
		    setText("Get out of Jail");
			setPreferredSize(new Dimension(118, 74));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User used Get Out Of Jail Card");
							//setEnabled(false);	
						}
					});
		}
	}
	
	public class RentDodgeButton extends JButton {
		
		public RentDodgeButton() {
		    setText("Rent Dodge Card");
		    //setIcon(new ImageIcon("images/rentDodge.png"));
		    //setRolloverIcon(new ImageIcon("images/rentDodgeRollOver.png"));
		    //setRolloverEnabled(true);
			setPreferredSize(new Dimension(118, 74));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User used Rent Dodge Card");
							//setEnabled(false);
						}
					});
		}
	}
	
	public class TaxiButton extends JButton {
		
		public TaxiButton() {
		    setText("Taxi Card");
			//setIcon(new ImageIcon("images/taxi.png"));
		    //setRolloverIcon(new ImageIcon("images/taxiRollOver.png"));
		    //setRolloverEnabled(true);
			setPreferredSize(new Dimension(118, 74));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User used Taxi Card");
							//setEnabled(false);
						}
					});
		}
	}
	
	public class RollDiceButton extends JButton {
		
		public RollDiceButton() {
			setText("Roll Dice");
			setPreferredSize(new Dimension(120, 35));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User clicked RollDice");
							setEnabled(false);
							GameMaster.getInstance().roll();
						}
					});
		}
	}
	
	public class BuildButton extends JButton {
		
		public BuildButton() {
			setText("Build");
			setPreferredSize(new Dimension(120,35));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User clicked Build");
						}
					});
		}
	}
	
	public class EndTurnButton extends JButton {
		
		public EndTurnButton() {
			setText("End Turn");
			setPreferredSize(new Dimension(120,35));
			addActionListener(
					new ActionListener() {
						public void actionPerformed(ActionEvent event) {
							System.out.println("User clicked EndTurn");
						}
					});
		}
	}	
	
	
	public class CurrentPlayerPanel extends JPanel {
		
		public CurrentPlayerPanel() {
			setLayout(new MigLayout());
			setPreferredSize(new Dimension(370,190));
			Player currentPlayer = GameMaster.getInstance().getCurrentPlayer();
			add(new JLabel(currentPlayer.getName()), "cell 0 0");			
			add(new JLabel("CASH"), "cell 0 1");
			add(new JLabel(Integer.toString(currentPlayer.getCash())), "cell 1 1");
			add(new JLabel("DISTRICTS"), "cell 0 2");
			add(new JLabel(currentPlayer.getDistricts().toString()), "cell 1 2");
		}
			
		public void update() {
			revalidate();
			repaint();
		}
		
	}
	
	public class CurrentSquarePanel extends JPanel {
		
		public CurrentSquarePanel() {
		setLayout(new MigLayout());
		setPreferredSize(new Dimension(370,190));
		Board board = GameMaster.getInstance().getBoard();
		Player currentPlayer = GameMaster.getInstance().getCurrentPlayer();
		Square currentSquare = board.getSquare(currentPlayer.getPosition());
		add(new JLabel(currentSquare.getName()), "cell 0 1");
		//add(new JLabel(currentPlayer.getName()), "cell 0 2");			
		//add(new JLabel("CASH"), "cell 0 3");
		//add(new JLabel(Integer.toString(currentPlayer.getCash())), "cell 0 4");
		//add(new JLabel("DISTRICTS"), "cell 0 5");
		//add(new JLabel(currentPlayer.getDistricts().toString()));
		}
	}
	
	
	private GamePane() {
		

		baseLayer = new JPanel(new BorderLayout());
		baseLayer.setBounds(0, 0, 1200, 800);
		baseLayer.setOpaque(true);

		
		ImageIcon icon = new ImageIcon("images/board-small.png");
		JLabel board = new JLabel(icon);
		board.setVerticalAlignment(SwingConstants.TOP);
		board.setPreferredSize(new Dimension(812,768));
		board.setOpaque(true);
			
		playerTokensPanel = new JPanel();
		playerTokensPanel.setOpaque(false);
		
		mainHudPanel = new JPanel();
		mainHudPanel.setLayout(null);
		mainHudPanel.setPreferredSize(new Dimension(388,768));
		mainHudPanel.setOpaque(false);

		hudPanel1 = new JPanel();
		hudPanel1.setBounds(0, 0, 382, 200);
		hudPanel1.setBorder(BorderFactory.createTitledBorder("Current Square"));		
		
		hudPanel2 = new JPanel();
		hudPanel2.setBounds(0, 201, 382, 200);
		hudPanel2.setBorder(BorderFactory.createTitledBorder("Current Player"));
		
		hudPanel3 = new JPanel();
		hudPanel3.setBounds(0, 451, 382, 118);
		hudPanel3.setBorder(BorderFactory.createTitledBorder("Chance Cards"));

		hudButtonPanel = new JPanel(new MigLayout());
		hudButtonPanel.setBounds(0, 401, 388, 50);
		
		messageLayer = new JPanel(new GridBagLayout());
		messageLayer.setBounds(0, 0, 812, 767);
		messageLayer.setOpaque(false);
		
		baseLayer.add(board, BorderLayout.WEST);	
		baseLayer.add(mainHudPanel, BorderLayout.EAST);
			
		add(baseLayer, new Integer(0));
		add(messageLayer, new Integer(1));	
	}
	
	public void build() {

		hudPanel1.add(new CurrentSquarePanel());

		hudPanel2.add(new CurrentPlayerPanel());
		
		getOutOfJailButton = new GetOutOfJailButton();
		rentDodgeButton = new RentDodgeButton();
		taxiButton = new TaxiButton();
		
		
		hudPanel3.add(getOutOfJailButton);
		hudPanel3.add(rentDodgeButton);
		hudPanel3.add(taxiButton);
		
		rollDiceButton = new RollDiceButton();
		buildButton = new BuildButton();
		endTurnButton = new EndTurnButton();
				
		hudButtonPanel.add(rollDiceButton);
		hudButtonPanel.add(buildButton);
		hudButtonPanel.add(endTurnButton);
		
		mainHudPanel.add(hudPanel1);
		mainHudPanel.add(hudButtonPanel);
		mainHudPanel.add(hudPanel2);
		mainHudPanel.add(hudPanel3);
		
		update();

		
	}
	
	public static GamePane getInstance() {
		return GAMEPANE;
	}
	
	
	public void clearMessageLayer() {
		System.out.println("clearing messageLayer");
		messageLayer.removeAll();
		//messageLayer.revalidate();
		//messageLayer.repaint();
	}
	
	public void setMessageLayer(JPanel jp) {
		clearMessageLayer();
		System.out.println("setting messageLayer");
		messageLayer.add(jp);
		messageLayer.revalidate();
		messageLayer.repaint();
	}
	
	public void enableButton(JButton btn) {
		btn.setEnabled(true);
	}
	
	public void disableButton(JButton btn) {
		btn.setEnabled(false);
	}
	
	public void hideButton(JButton btn) {
		btn.setVisible(false);
	}
	
	public void showButton(JButton btn) {
		btn.setVisible(true);
	}
	
	public JButton getRollDiceButton() {
		return rollDiceButton;
	}
	
	public JButton getBuildButton() {
		return buildButton;
	}
	
	public JButton getEndTurnButton() {
		return endTurnButton;
	}
	
	public JButton getGetOutOfJailButton() {
		return getOutOfJailButton;
	}
	
	public JButton getRentDodgeButton() {
		return rentDodgeButton;
	}
	
	public JButton getTaxiButton() {
		return taxiButton;
	}
	
	public void update() {
		revalidate();
		repaint();
	}
	
}